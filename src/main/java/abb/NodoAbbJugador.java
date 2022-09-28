package abb;

import dominio.Jugador;

public class NodoAbbJugador {
    private Jugador jugador;
    private NodoAbbJugador izq;
    private NodoAbbJugador der;

    public NodoAbbJugador(Jugador jugador){
        this.jugador = jugador;
        this.izq = null;
        this.der = null;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public NodoAbbJugador getIzq() {
        return izq;
    }

    public void setIzq(NodoAbbJugador izq) {
        this.izq = izq;
    }

    public NodoAbbJugador getDer() {
        return der;
    }

    public void setDer(NodoAbbJugador der) {
        this.der = der;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
