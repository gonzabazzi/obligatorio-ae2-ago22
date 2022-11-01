package dominio;

import interfaz.EstadoCamino;
import lista.ListaCamino;
import lista.NodoCamino;

public class Recorrido {
    private boolean existe;
    private double kilometros;
    private EstadoCamino estadoCamino;
    private final ListaCamino listaCaminos = new ListaCamino();

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public ListaCamino getListaCaminos() {
        return listaCaminos;
    }

    public double getKilometros() {
        return kilometros;
    }

    public void setKilometros (double kilometros) {
        this.kilometros = kilometros;
    }

    public EstadoCamino getEstadoCamino() {
        return estadoCamino;
    }

    public void setEstadoCamino(EstadoCamino estadoCamino) {
        this.estadoCamino = estadoCamino;
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
