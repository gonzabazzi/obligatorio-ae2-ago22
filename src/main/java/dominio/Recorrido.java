package dominio;

import lista.ListaCamino;

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

    public void agregarCamioALista(Camino camino){
        this.listaCaminos.insertar(camino);
    }
}
