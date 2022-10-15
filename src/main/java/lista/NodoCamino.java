package lista;

import dominio.Camino;

public class NodoCamino {
    private Camino camino;
    private NodoCamino sig;

    public NodoCamino() {}

    public NodoCamino(Camino camino) {
        this.camino = camino;
    }
    public Camino getCamino() {
        return camino;
    }
    public NodoCamino getSig() {
        return sig;
    }
    public void setSig(NodoCamino sig) {
        this.sig = sig;
    }
}
