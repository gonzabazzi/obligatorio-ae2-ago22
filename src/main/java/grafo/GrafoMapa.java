package grafo;

import dominio.CentroUrbano;
import dominio.Camino;
import dominio.Recorrido;
import interfaz.EstadoCamino;
import lista.ListaCentro;

import java.util.Objects;

public class GrafoMapa {
    private final int tope;
    private int cantidad;
    private Recorrido[][] matAdy; //ToDo ES NECESARIO TENER RECORRIDO??
    private CentroUrbano [] centros;
    private ListaCentro listaCentro;

    public GrafoMapa(int unTope, boolean esDirigido) {
        this.tope = unTope;
        this.cantidad = 0;
        this.matAdy = new Recorrido[unTope][unTope];
        this.centros = new CentroUrbano[unTope];

        if (esDirigido) { // Inicializamos toda la matriz
            for (int i = 0; i < this.tope; i++) {
                for (int j = 0; j < this.tope; j++) {
                    this.matAdy[i][j] = new Recorrido();
                }
            }
        } else { // Dada la diagonal recorro triangulo superior
            for (int i = 0; i < this.tope; i++) {
                for (int j = i; j < this.tope; j++) {
                    this.matAdy[i][j] = new Recorrido(); // Pongo un objeto en esa posicion
                    this.matAdy[j][i] = this.matAdy[i][j]; // Y en su espejo del otro triángulo, para que quede no dirigido
                }
            }
        }
    }

    public ListaCentro getListaCentro() {
        return listaCentro;
    }

    public boolean esLleno () {
        return cantidad == tope;
    }

    public boolean esVacio () {
        return cantidad == 0;
    }

    private int obtenerPosLibre () {
        for (int i = 0; i < this.tope; i++) {
            if (this.centros[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public int obtenerPos (String codigo) {
        for (int i = 0; i < this.tope; i++) {
            if (this.centros[i] != null && this.centros[i].getCodigo().equals(codigo)) {
                return i;
            }
        }
        return -1;
    }

    private void borrarVertice (String vert) {
        int posABorrar = obtenerPos(vert);
        centros[posABorrar] = null;
        for (int k = 0; k < tope; k++) {
            this.matAdy[posABorrar][k].setExiste(false);
            this.matAdy[k][posABorrar].setExiste(false);
        }
        cantidad--;
    }

    private boolean existeVertice (String vert) {
        return obtenerPos(vert) != -1;
    }


    private boolean existeCamino (String origen, String destino) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        return matAdy[posOrigen][posDestino].isExiste();
    }

    private void borrarArista (String origen, String destino) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        matAdy[posOrigen][posDestino].setExiste(false);
    }

    public String insertarCentroUrbano(CentroUrbano aAgregar) {
        if (this.esLleno()) {
            return "1";
        } else if (aAgregar.getCodigo() == null || aAgregar.getNombre() == null || Objects.equals(aAgregar.getCodigo(), "") || Objects.equals(aAgregar.getNombre(), "")) {
            return "2";
        } else if (existeCentro(aAgregar.getCodigo())) {
            return "3";
        } else {
            int posicionLibre = obtenerPosLibre();
            centros[posicionLibre] = aAgregar;
            cantidad++;
            return "Ok";
        }
    }

    private boolean existeCentro(String codigoCentro) {
        return obtenerPos(codigoCentro) > - 1;
    }

    public String insertarCamino(Camino aAgregar) {
        if (aAgregar.getCosto() <= 0 || aAgregar.getTiempo() <= 0 || aAgregar.getKilometros() <= 0) {
            return "1";
        } else if (aAgregar.getCodigoCentroOrigen() == null || aAgregar.getCodigoCentroDestino() == null || aAgregar.getEstadoCamino() == null || aAgregar.getCodigoCentroOrigen().equals("") || aAgregar.getCodigoCentroDestino().equals("")) {
            return "2";
        } else if (!existeCentro(aAgregar.getCodigoCentroOrigen())) {
            return "3";
        } else if (!existeCentro(aAgregar.getCodigoCentroDestino())) {
            return "4";
        } else if (existeCamino(aAgregar.getCodigoCentroOrigen(), aAgregar.getCodigoCentroDestino()) || existeCamino(aAgregar.getCodigoCentroDestino(), aAgregar.getCodigoCentroOrigen())  ) {
            return "5";
        } else {
            int posOrigen = obtenerPos(aAgregar.getCodigoCentroOrigen());
            int posDestino =obtenerPos(aAgregar.getCodigoCentroDestino());
            matAdy[posOrigen][posDestino].setExiste(true);
            matAdy[posOrigen][posDestino].setKilometros(aAgregar.getKilometros());
            matAdy[posOrigen][posDestino].setEstadoCamino(aAgregar.getEstadoCamino());
            matAdy[posOrigen][posDestino].agregarCaminoALista(aAgregar);
            return "Ok";
        }
    }

    public String actualizarCamino (String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        if (costo <= 0 || tiempo <= 0 || kilometros <= 0) {
            return "1";
        } else if (codigoCentroOrigen == null || codigoCentroDestino == null || estadoDelCamino == null || codigoCentroOrigen.equals("") || codigoCentroDestino.equals("")) {
            return "2";
        } else if (!existeCentro(codigoCentroOrigen)) {
            return "3";
        } else if (!existeCentro(codigoCentroDestino)) {
            return "4";
        } else if (!existeCamino(codigoCentroOrigen, codigoCentroDestino)) {
            return "5";
        } else {
            boolean modifcado = matAdy[obtenerPos(codigoCentroOrigen)][obtenerPos(codigoCentroDestino)].modificarCamino(codigoCentroOrigen, codigoCentroDestino, costo, tiempo, kilometros, estadoDelCamino);
            if (modifcado)  {
                return "Ok";
            } else {
                return "5";
            }
        }
    }

    public String listadoCentrosPorCantSaltos(String codigoCentroOrigen, int cantidad) {
        ListaCentro listaCentro = new ListaCentro();
        int contador = 0;
        boolean[] visitados = new boolean[tope];
        int pos = obtenerPos(codigoCentroOrigen);
        if (pos != -1) {
            if (cantidad == 0) {
                CentroUrbano aux = centros[pos];
                return aux.toString();
            }
            return listadoCentrosPorCantSaltosRec(pos, visitados, cantidad, contador, listaCentro);
        } else {
            return "-1";
        }
    }

    private String listadoCentrosPorCantSaltosRec(int posicionCentro, boolean[] visitados, int cantidad, int contador, ListaCentro listaCentro) {
        visitados[posicionCentro] = true;
        listaCentro.agregarAlista(centros[posicionCentro]);
        for (int j = 0; j < tope; j++) {
            if (this.matAdy[posicionCentro][j].isExiste() && !visitados[j]) {
                if (contador <= cantidad) {
                    contador++;
                    listadoCentrosPorCantSaltosRec(j, visitados, cantidad, contador, listaCentro);
                }
            }
        }
        return listaCentro.toString();
    }

    public  int viajeConCostoMinimo(String codigoCentroOrigen, String codigoCentroDestino) {
        int posOrigen = obtenerPos(codigoCentroOrigen);
        int posDestino = obtenerPos(codigoCentroDestino);

        if (codigoCentroOrigen== null || codigoCentroOrigen.isEmpty() || codigoCentroDestino == null || codigoCentroDestino.isEmpty()) {
            return -4;
        }

        if (posOrigen == -1) {
            return -1;
        } else if (posDestino == -1) {
            return -2;
        }

        boolean[] visitados = new boolean[this.tope];
        int[] kilometros = new int[this.tope];
        CentroUrbano[] anterior = new CentroUrbano[tope];

        for (int i = 0; i < this.tope; i++) {
            kilometros[i] = Integer.MAX_VALUE;
            anterior[i] = null;
        }

        kilometros[posOrigen] = 0;
        for (int v = 0; v < this.cantidad; v++) {
            int pos = obtenerSiguienteVerticeNoVisitadoDeMenorKilometros(kilometros, visitados);
            if (pos != -1) {
                visitados[pos] = true;
                for (int j = 0; j < tope; j++) {
                    if (matAdy[pos][j].isExiste() && !matAdy[pos][j].getEstadoCamino().equals(EstadoCamino.MALO) && !visitados[j]) {
                        int costoNuevo = kilometros[pos] + (int) matAdy[pos][j].getKilometros();
                        if (costoNuevo < kilometros[j]) {
                            kilometros[j] = costoNuevo;
                            anterior[j] = centros[pos];
                        }
                    }
                }
            }
        }

        if (kilometros[posDestino] == Integer.MAX_VALUE) {
            return -3;
        }

        this.listaCentro = new ListaCentro();
        obtenerRecorrido(listaCentro, anterior, posDestino);
        return kilometros[posDestino];
    }

    private int obtenerSiguienteVerticeNoVisitadoDeMenorKilometros(int[] kilometros, boolean[] visitados) {
        int posMin = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < tope; i++) {
            if (!visitados[i] && kilometros[i] < min) {
                min = kilometros[i];
                posMin = i;
            }
        }
        return posMin;
    }

    private ListaCentro obtenerRecorrido (ListaCentro listaCentro, CentroUrbano[] anterior, int centroDestino) {
        CentroUrbano centro = centros[centroDestino];
        return obtenerRecorridoRec(listaCentro, anterior, centro);
    }

    private ListaCentro obtenerRecorridoRec (ListaCentro listaCentro, CentroUrbano[] anterior, CentroUrbano actual) {
        if (actual != null) {
            int posCentro = obtenerPos(actual.getCodigo());
            CentroUrbano centroAnterior = anterior[posCentro];
            obtenerRecorridoRec(listaCentro, anterior, centroAnterior);
            this.listaCentro.agregarAlista(actual);
        }
        return listaCentro;
    }

    public int viajeConCostoMinimoMonedas(String codigoCentroOrigen, String codigoCentroDestino) {
        int posOrigen = obtenerPos(codigoCentroOrigen);
        int posDestino = obtenerPos(codigoCentroDestino);

        if (codigoCentroOrigen== null || codigoCentroOrigen.isEmpty() || codigoCentroDestino == null || codigoCentroDestino.isEmpty()) {
            return -4;
        }

        if (posOrigen == -1) {
            return -1;
        } else if (posDestino == -1) {
            return -2;
        }

        boolean[] visitados = new boolean[this.tope];
        int[] costo = new int[this.tope];
        CentroUrbano[] anterior = new CentroUrbano[tope];

        for (int i = 0; i < this.tope; i++) {
            costo[i] = Integer.MAX_VALUE;
            anterior[i] = null;
        }

        costo[posOrigen] = 0;
        for (int v = 0; v < this.cantidad; v++) {
            int pos = obtenerSiguienteVerticeNoVisitadoDeMenorKilometros(costo, visitados);
            if (pos != -1) {
                visitados[pos] = true;
                for (int j = 0; j < tope; j++) {
                    if (matAdy[pos][j].isExiste() && !matAdy[pos][j].getEstadoCamino().equals(EstadoCamino.MALO) && !visitados[j]) {
                        int costoNuevo = costo[pos] + (int) matAdy[pos][j].getListaCaminos().buscarCaminoMenorCosto();
                        if (costoNuevo < costo[j]) {
                            costo[j] = costoNuevo;
                            anterior[j] = centros[pos];
                        }
                    }
                }
            }
        }

        if (costo[posDestino] == Integer.MAX_VALUE) {
            return -3;
        }

        this.listaCentro = new ListaCentro();
        obtenerRecorrido(listaCentro, anterior, posDestino);
        return costo[posDestino];
    }
}
