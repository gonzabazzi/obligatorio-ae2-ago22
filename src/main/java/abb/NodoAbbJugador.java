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
}
