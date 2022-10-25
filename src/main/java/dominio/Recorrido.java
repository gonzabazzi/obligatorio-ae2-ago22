package dominio;

import interfaz.EstadoCamino;
import lista.ListaCamino;
import lista.NodoCamino;

public class Recorrido {

    private boolean existe;

    private String codigoCentroOrigen;

    private String codigoCentroDestino;

    private ListaCamino listaCaminos = new ListaCamino();

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    //ToDo VER DE MOVER ESTAS FUNCIONES A CAMINO
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
