package abb;

import dominio.Jugador;
import interfaz.Retorno;
import interfaz.TipoJugador;

public class AbbJugador {
    private NodoAbbJugador raiz;
    private Jugador jugador;
    public int cantIteraciones;

    public AbbJugador(){
        this.raiz = null;
        cantIteraciones = 0;
    }

    public NodoAbbJugador getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoAbbJugador raiz) {
        this.raiz = raiz;
    }

    public String insertarJugador(String ci, String nombre,int edad, String escuela, TipoJugador tipo){

        if(!validarCampos(ci, nombre, edad, escuela, tipo)){
            return "1";
        }
        if(!Jugador.validarCi(ci)) {
            return "2";
        }
        if(this.buscarJugador(ci) != null){
            return "3";
        }
        Jugador nuevoJugador = new Jugador(ci, nombre, edad, escuela, tipo);
        NodoAbbJugador nodoAbbJugador = new NodoAbbJugador(nuevoJugador);
        if(raiz == null){
            raiz = nodoAbbJugador;
        } else {
            insertarJugadorRec(raiz, nodoAbbJugador);
        }
        return "Ok";
    }

    public NodoAbbJugador insertarJugadorRec(NodoAbbJugador raiz, NodoAbbJugador nodoNuevo){
        if (formatearCi(nodoNuevo.getJugador().getCedula()) > formatearCi(raiz.getJugador().getCedula())) {
            if (raiz.getDer() == null) {
                raiz.setDer(nodoNuevo);
            } else {
                return this.insertarJugadorRec(raiz.getDer(), nodoNuevo);
            }
        } else if(formatearCi(nodoNuevo.getJugador().getCedula()) < formatearCi(raiz.getJugador().getCedula())) {
            if (raiz.getIzq() == null) {
                raiz.setIzq(nodoNuevo);
            } else {
                return this.insertarJugadorRec(raiz.getIzq(), nodoNuevo);
            }
        }
        return raiz;
    }

    //para contar la cantidad e iteraciones hasta encontrar y para mostrar los datos del
    //jugador hay una forma mas eficiente de hacerlo que no sea hacer 2 funciones que
    //recorran el arbol para devovler dos resultados distintos

    public int iteracionesAlBuscarJugador(String ci){
        return this.iteracionesAlBuscarJugador(this.raiz, ci);
    }
    private int iteracionesAlBuscarJugador(NodoAbbJugador nodo, String ci){

        if(nodo == null){
            return 0;
        }else{
            int cant = 0;
            if(nodo.getJugador().getCedula().equals(ci)){
                cant++;
            }
            if(formatearCi(nodo.getJugador().getCedula()) > formatearCi(ci)){
                int cantIzq = iteracionesAlBuscarJugador(nodo.getIzq(), ci);
                int cantDer = iteracionesAlBuscarJugador(nodo.getDer(), ci);
                return cantDer + cantIzq + cant;

            } else{
                int cantDer = iteracionesAlBuscarJugador(nodo.getDer(), ci);
                return cantDer + cant;
            }
        }
    }

    public NodoAbbJugador buscarJugador(String ci){
        return this.buscarJugador(this.raiz, ci);
    }
    private NodoAbbJugador buscarJugador(NodoAbbJugador nodo, String ci){
        this.cantIteraciones ++;
        if(nodo == null){
            return null;
        } else if(nodo.getJugador().getCedula().equals(ci)) {
            return nodo;
        }else{
            if(formatearCi(nodo.getJugador().getCedula()) > formatearCi(ci)){
                return buscarJugador(nodo.getIzq(), ci);
            } else{
                return buscarJugador(nodo.getDer(), ci);
            }
        }
    }

    public int formatearCi (String ci){
        return Integer.parseInt(ci.replace(".","").replace("-",""));
    }
    public boolean validarCampos (String ci, String nombre,int edad, String escuela, TipoJugador tipo){
        return (ci != "" && nombre != "" && edad > 0 && escuela != "" && tipo != null);
    }


}
