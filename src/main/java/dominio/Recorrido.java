package dominio;

import interfaz.EstadoCamino;
import lista.ListaCamino;
import lista.NodoCamino;

public class Recorrido {
    private boolean existe;
    private String codigoAeropuertoOrigen;
    private String codigoAeropuertoDestino;
    private double kilometros;
    private ListaCamino listaCaminos = new ListaCamino();

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public String getCodigoAeropuertoOrigen() {
        return codigoAeropuertoOrigen;
    }

    public void setCodigoAeropuertoOrigen(String codigoAeropuertoOrigen) {
        this.codigoAeropuertoOrigen = codigoAeropuertoOrigen;
    }

    public String getCodigoAeropuertoDestino() {
        return codigoAeropuertoDestino;
    }

    public void setCodigoAeropuertoDestino(String codigoAeropuertoDestino) {
        this.codigoAeropuertoDestino = codigoAeropuertoDestino;
    }

    public ListaCamino getListaCaminos() {
        return listaCaminos;
    }

    public void setListaCaminos(ListaCamino listaCaminos) {
        this.listaCaminos = listaCaminos;
    }

    public double getKilometros() {
        return kilometros;
    }

    public void setKilometros (double kilometros) {
        this.kilometros = kilometros;
    }

    public void agregarCaminoALista(Camino camino){
        this.listaCaminos.insertar(camino);
    }

    public boolean modificarCamino(String origen, String destino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        NodoCamino nodoCamino = listaCaminos.buscarCamino(origen, destino);
        if (nodoCamino != null) {
            Camino camino = nodoCamino.getCamino();
            camino.setCosto(costo);
            camino.setTiempo(tiempo);
            camino.setKilometros(kilometros);
            camino.setEstado(estadoDelCamino);
            return true;
        }
        return false;
    }
}
