package abb;

import dominio.Jugador;

public class AbbJugador {
    private NodoAbbJugador raiz;

    public AbbJugador(){
        this.raiz = null;
    }

    public NodoAbbJugador getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoAbbJugador raiz) {
        this.raiz = raiz;
    }

    public String insertarJugador(Jugador jugador){
        return "";
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
        } else if(nodo.getJugador().getCedula().equals(ci)) {
            return 1;
        }else{
            if(formatearCi(nodo.getJugador().getCedula()) > formatearCi(ci)){
                int cantIzq = iteracionesAlBuscarJugador(nodo.getIzq(), ci);
                int cantDer = iteracionesAlBuscarJugador(nodo.getDer(), ci);
                return cantDer + cantIzq;

            } else{
                int cantDer = iteracionesAlBuscarJugador(nodo.getDer(), ci);
                return cantDer;
            }
        }
    }

    public NodoAbbJugador buscarJugador(String ci){
        return this.buscarJugador(this.raiz, ci);
    }
    private NodoAbbJugador buscarJugador(NodoAbbJugador nodo, String ci){

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


}
