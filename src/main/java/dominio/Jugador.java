package dominio;

import interfaz.TipoJugador;

public class Jugador {
    private String cedula;
    private String nombre;
    private int edad;
    private String escuela;
    private TipoJugador tipoJugador;

    public Jugador(String cedula, String nombre, int edad, String escuela, TipoJugador tipoJugador) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.escuela = escuela;
        this.tipoJugador = tipoJugador;
    }

    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getEscuela() {
        return escuela;
    }
    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }
    public TipoJugador getTipoJugador() {
        return tipoJugador;
    }
    public void setTipoJugador(TipoJugador tipoJugador) {
        this.tipoJugador = tipoJugador;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", escuela='" + escuela + '\'' +
                ", tipoJugador=" + tipoJugador +
                '}';
    }
}
