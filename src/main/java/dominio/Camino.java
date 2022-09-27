package dominio;

import interfaz.EstadoCamino;

public class Camino {
    private String codigoCentroOrigen;
    private String codigoCentroDestino;
    private double costo;
    private double tiempo;
    private double kilometros;
    private EstadoCamino estadoCamino;

    public Camino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estado) {
        this.codigoCentroOrigen = codigoCentroOrigen;
        this.codigoCentroDestino = codigoCentroDestino;
        this.costo = costo;
        this.tiempo = tiempo;
        this.kilometros = kilometros;
        this.estadoCamino = estadoCamino;
    }

    public String getCodigoCentroOrigen() {
        return codigoCentroOrigen;
    }

    public void setCodigoCentroOrigen(String codigoCentroOrigen) {
        this.codigoCentroOrigen = codigoCentroOrigen;
    }

    public String getCodigoCentroDestino() {
        return codigoCentroDestino;
    }

    public void setCodigoCentroDestino(String codigoCentroDestino) {
        this.codigoCentroDestino = codigoCentroDestino;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public double getKilometros() {
        return kilometros;
    }

    public void setKilometros(double kilometros) {
        this.kilometros = kilometros;
    }

    public EstadoCamino getEstadoCamino() {
        return estadoCamino;
    }

    public void setEstado(EstadoCamino estadoCamino) {
        this.estadoCamino = estadoCamino;
    }
}
