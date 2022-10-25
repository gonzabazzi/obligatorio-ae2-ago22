package lista;

import dominio.CentroUrbano;

public class NodoCentro {
    private CentroUrbano centro;
    private NodoCentro ant;
    private NodoCentro sig;

    public NodoCentro getAnt() {
        return ant;
    }

    public void setAnt(NodoCentro ant) {
        this.ant = ant;
    }

    public NodoCentro(CentroUrbano centro) {
        this.centro = centro;
    }
    public CentroUrbano getCentro() {
        return centro;
    }
    public NodoCentro getSig() {
        return sig;
    }
    public void setSig(NodoCentro sig) {
        this.sig = sig;
    }
}
