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
        } else { // Dada la diagonal recorro tiangulo superior
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
            matAdy[obtenerPos(aAgregar.getCodigoCentroOrigen())][obtenerPos(aAgregar.getCodigoCentroDestino())].setExiste(true);
            matAdy[obtenerPos(aAgregar.getCodigoCentroOrigen())][obtenerPos(aAgregar.getCodigoCentroDestino())].agregarCaminoALista(aAgregar);
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
        if (cantidad == 0) {
            CentroUrbano aux = centros[pos];
            return aux.toString();
        }
        return listadoCentrosPorCantSaltosRec(pos, visitados, cantidad, contador, listaCentro);
    }
    private String listadoCentrosPorCantSaltosRec(int posicionCentro, boolean[] visitados, int cantidad, int contador, ListaCentro listaCentro) {
        visitados[posicionCentro] = true;
        listaCentro.insertar(centros[posicionCentro]);
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

}
