package grafo;

//import cola.Cola;
//import cola.ColaImp;
import dominio.CentroUrbano;
import lista.Lista;
import lista.ListaImp;

public class GrafoCentroUrbano {
    private final int tope;
    private int cantidad;
    private String[] vertices;
    private Camino[][] matAdy;
    private CentroUrbano [] centros;

    public GrafoCentroUrbano(int unTope, boolean esDirigido) {
        this.tope = unTope;
        this.cantidad = 0;
        this.vertices = new String[unTope];
        this.matAdy = new Camino[unTope][unTope];
        //this.centros = new CentroUrbano[tope];

        if (esDirigido) { // Inicializamos toda la matriz
            for (int i = 0; i < this.tope; i++) {
                for (int j = 0; j < this.tope; j++) {
                    this.matAdy[i][j] = new Camino();
                }
            }
        } else { // Dada la diagonal recorro tiangulo superior
            for (int i = 0; i < this.tope; i++) {
                for (int j = i; j < this.tope; j++) {
                    this.matAdy[i][j] = new Camino(); // Pongo un objeto en esa posicion
                    this.matAdy[j][i] = this.matAdy[i][j]; // Y en su espejo del otro triángulo, para que quede no dirigido
                }
            }
        }
    }

    public boolean esLleno () {
        return cantidad == tope;
    }

    public boolean esVacio () {
        return cantidad == 0;
    }

    private int obtenerPosLibre () {
        for (int i = 0; i < this.tope; i++) {
            if (this.vertices[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int obtenerPos (String vert) {
        for (int i = 0; i < this.tope; i++) {
            if (this.vertices[i] != null && this.vertices[i].equals(vert)) {
                return i;
            }
        }
        return -1;
    }

    private void agregarVertice (String vert) {
        int pos = obtenerPos(vert);
        this.vertices[pos] = vert;
        cantidad++;
    }

    private void borrarVertice (String vert) {
        int posABorrar = obtenerPos(vert);
        vertices[posABorrar] = null;
        for (int k = 0; k < tope; k++) {
            this.matAdy[posABorrar][k].setExiste(false);
            this.matAdy[k][posABorrar].setExiste(false);
        }
        cantidad--;
    }

    private boolean existeVertice (String vert) {
        return obtenerPos(vert) != -1;
    }

    private void agregarArista (String origen, String destino, int peso) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        matAdy[posOrigen][posDestino].setExiste(true);
        matAdy[posOrigen][posDestino].setPeso(peso);
    }

    private boolean existeArista (String origen, String destino) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        return matAdy[posOrigen][posDestino].isExiste();
    }

    private void borrarArista (String origen, String destino) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        matAdy[posOrigen][posDestino].setExiste(false);
    }

    public Lista<String> verticesAdyacentes(String vert) { // Filas
        Lista<String> retorno = new ListaImp<>();
        int pos = obtenerPos(vert);
        for (int i = 0; i < tope; i++) {
            if (this.matAdy[pos][i].isExiste()) {
                retorno.insertar(vertices[i]);
            }
        }
        return retorno;
    }

    public Lista<String> verticesIncidentes(String vert) { // Columnas
        Lista<String> retorno = new ListaImp<>();
        int pos = obtenerPos(vert);
        for (int i = 0; i < tope; i++) {
            if (this.matAdy[i][pos].isExiste()) {
                retorno.insertar(vertices[i]);
            }
        }
        return retorno;
    }

    public String insertarCentroUrbano(CentroUrbano aAgregar) {
        if (this.esLleno()) {
            return "1";
        } else if (aAgregar.getCodigo() == null || aAgregar.getNombre() == null || aAgregar.getCodigo() == "" || aAgregar.getNombre() == "") {
            return "2";
        } else if (existeCentro(aAgregar)) {
            return "3";
        } else {
            int posicionLibre = obtenerPosLibre();
            centros[posicionLibre] = aAgregar;
            cantidad++;
            return "Ok";
        }
    }

    private boolean existeCentro(CentroUrbano centro) {
        return obtenerPos(centro.getCodigo()) > - 1;
    }

//    public void dfs(String vert) {
//        boolean[] visitados = new boolean[tope];
//        int pos = obtenerPos(vert);
//        dfsRec(pos, visitados);
//    }
//
//    private void dfsRec(int pos, boolean[] visitados) {
//        System.out.println(vertices[pos]);
//        visitados[pos] = true;
//        for (int i = 0; i < tope; i++) {
//            if (matAdy[pos][i].isExiste() && !visitados[i]) { // Si existe la arista y no fue visitado
//                dfsRec(i, visitados);
//            }
//        }
//    }

//    public void bfs(String vert) {
//        int inicio = obtenerPos(vert);
//        boolean[] visitados = new boolean[tope];
//        Cola<Integer> cola = new ColaImp<>();
//        cola.encolar(inicio);
//        while (!cola.esVacia()) {
//            int pos = cola.desencolar();
//            if (!visitados[pos]) {
//                visitados[pos] = true;
//                System.out.println(vertices[pos]);
//                for (int i = 0; i < tope; i++) {
//                    if (this.matAdy[pos][i].isExiste() && !visitados[i]) {
//                        cola.encolar(i);
//                    }
//                }
//            }
//        }
//    }
//
//    public void bfs2(String vert) {
//        int inicio = obtenerPos(vert);
//        boolean[] visitados = new boolean[tope];
//        Cola<Integer> cola = new ColaImp<>();
//        cola.encolar(inicio);
//        visitados[inicio] = true;
//        while (!cola.esVacia()) {
//            int pos = cola.desencolar();
//            System.out.println(vertices[pos]);
//            for (int i = 0; i < tope; i++) {
//                if (this.matAdy[pos][i].isExiste() && !visitados[i]) {
//                    cola.encolar(i);
//                    visitados[i] = true;
//                }
//            }
//        }
//    }
//
//    public int dijkstra(String vOrigen, String vDestino) {
//        int posOrigen = obtenerPos(vOrigen);
//        int posDestino = obtenerPos(vDestino);
//        boolean[] visitados = new boolean[this.tope];
//        int[] costos = new int[this.tope];
//        String[] anterior = new String[this.tope];
//
//        for (int i = 0; i < tope; i++) {
//            costos[i] = Integer.MAX_VALUE;
//            anterior[i] = "-";
//        }
//
//        costos[posOrigen] = 0;
//        for (int i = 0; i < tope; i++) {
//            int pos = obtenerSiguienteVerticeNoVisitadoDeMenorDistancia(costos, visitados);
//            visitados[pos] = true;
//            for (int j = 0; j < tope; j++) {
//                int distanciaNueva = costos[pos] + matAdy[pos][j].getPeso();
//                if (matAdy[pos][j].isExiste() && !visitados[j]) {
//                    costos[j] = distanciaNueva;
//                    anterior[i] = vertices[pos];
//                }
//            }
//        }
//
//        return 0;
//    }
//
//    private int obtenerSiguienteVerticeNoVisitadoDeMenorDistancia(int[] costos, boolean[] visitados) {
//        int posMin = -1;
//        int min = Integer.MAX_VALUE;
//
//        for (int i = 0; i < tope; i++) {
//            if (!visitados[i] && costos[i] < min) {
//                min = costos[i];
//                posMin = i;
//            }
//        }
//
//        return posMin;
//    }
}