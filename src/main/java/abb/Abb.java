package abb;

import dominio.Jugador;
import interfaz.Retorno;

public class Abb<T extends Comparable<T>> {
    private NodoAbb<T> raiz;

    public Abb(){
        this.raiz = null;
    }

    public boolean existe(){
        return false;
    }

    public boolean insertar(){
        return false;
    }

    public NodoAbb<T> buscar(T dato){
        return null;
    }

    public String listarAsc(){
        return "";
    }
    public String listarDesc(){
        return "";
    }
    public String listarPorDato(T dato){
        return "";
    }

}
