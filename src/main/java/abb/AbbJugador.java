package abb;

import dominio.Jugador;
import interfaz.Consulta;
import interfaz.Retorno;
import interfaz.TipoJugador;

public class AbbJugador {
    private NodoAbbJugador raiz;
    private Jugador jugador;

    private Consulta consulta;
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

    public String insertarJugador(Jugador aAgregar){
        String ci = aAgregar.getCedula();
        if(!validarCampos(ci, aAgregar.getNombre(), aAgregar.getEdad(), aAgregar.getEscuela(), aAgregar.getTipoJugador())){
            return "1";
        }
        if(!Jugador.validarCi(ci)) {
            return "2";
        }
        if(this.buscarJugador(ci) != null){
            return "3";
        }
        NodoAbbJugador nodoAbbJugador = new NodoAbbJugador(aAgregar);
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

    public Jugador buscarJugador(String ci){
        cantIteraciones = -1; //ToDo ¿ESTO NO DEBERÍA ARRANCAR POR LO MENOS CON UNA ITERACIÓN SIEMPRE????
        return buscarJugador(raiz, ci);
    }

    private Jugador buscarJugador(NodoAbbJugador nodo, String ci){
        cantIteraciones ++;
        if(nodo == null){
            return null;
        } else if(nodo.getJugador().getCedula().equals(ci)) {
            return nodo.getJugador();
        }else{
            if(formatearCi(nodo.getJugador().getCedula()) > formatearCi(ci)){
                return buscarJugador(nodo.getIzq(), ci);
            } else{
                return buscarJugador(nodo.getDer(), ci);
            }
        }
    }

    public String listarJugadoresPorCedulaAscendenteAbb(){
        return listarJugadoresPorCedulaAscendenteAbb(raiz);
    }

    public String listarJugadoresPorCedulaAscendenteAbb(NodoAbbJugador nodo){
        if(nodo == null){
            return "";
        } else {
            String izq = listarJugadoresPorCedulaAscendenteAbb(nodo.getIzq());
            String stringNodo = nodo.getJugador().getCedula() + ";" +
                    nodo.getJugador().getNombre() + ";" +
                    nodo.getJugador().getEdad() + ";" +
                    nodo.getJugador().getEscuela() + ";" +
                    nodo.getJugador().getTipoJugador().getValor() + "|";
            String der = listarJugadoresPorCedulaAscendenteAbb(nodo.getDer());
            return izq + stringNodo + der;
        }
    }

    public String listarJugadoresPorCedulaDescendenteAbb(){
        return listarJugadoresPorCedulaDescendenteAbb(raiz);
    }

    public String listarJugadoresPorCedulaDescendenteAbb(NodoAbbJugador nodo){
        if(nodo == null){
            return "";
        } else {
            String izq = listarJugadoresPorCedulaDescendenteAbb(nodo.getIzq());
            String stringNodo = nodo.getJugador().getCedula() + ";" +
                    nodo.getJugador().getNombre() + ";" +
                    nodo.getJugador().getEdad() + ";" +
                    nodo.getJugador().getEscuela() + ";" +
                    nodo.getJugador().getTipoJugador().getValor() + "|";
            String der = listarJugadoresPorCedulaDescendenteAbb(nodo.getDer());
            return der + stringNodo + izq;
        }
    }

    public int formatearCi (String ci){
        return Integer.parseInt(ci.replace(".","").replace("-",""));
    }

    public boolean validarCampos (String ci, String nombre,int edad, String escuela, TipoJugador tipo){
        return !(ci == null || ci == "" || nombre == "" || nombre == null || edad <= 0 || escuela == "" || escuela == null || tipo == null);
    }

    public String filtrarJugadores(Consulta consulta) {
        //Aux Jugadores recorre la  consulta y verifica si comple no cumple
        //En el ABB recorremos cada jugador llamando a la Aux

       String jugadoresFiltrados = filtrarJugadoresRec(raiz, consulta);

        return jugadoresFiltrados;
    }

    public String filtrarJugadoresRec(NodoAbbJugador nodo, Consulta consulta){
        if(nodo == null){
            return "";
        } else {
            String stringNodo = "";
            String izq = filtrarJugadoresRec(nodo.getIzq(), consulta);
            if (consulta.cumpleConsulta(nodo.getJugador())){
                stringNodo += nodo.getJugador().getCedula() + "|";
            }
            String der = filtrarJugadoresRec(nodo.getDer(), consulta);
            return izq + stringNodo + der;
        }
    }




}