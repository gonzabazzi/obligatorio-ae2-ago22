package lista;

import dominio.Camino;

import java.util.Iterator;

public class ListaCamino implements Lista<Camino> {

    private NodoCamino inicio;
    protected int largo;

    public ListaCamino() {
        this.inicio = null;
        this.largo = 0;
    }

    @Override
    public boolean esVacia(){
        return this.largo > 0;
    }

    @Override
    public void insertar(Camino camino){
        if(inicio == null){
            inicio = new NodoCamino(camino);
        } else {
            NodoCamino aux = inicio;
            while (aux.getSig() != null){
                aux = aux.getSig();
            }
            aux.setSig(new NodoCamino(camino));
        }
        largo++;
    }

    @Override
    public int largo() {
        return this.largo;
    }

    public NodoCamino buscarCamino (String origen, String destino){
        NodoCamino camino = inicio;
        while (camino != null){
            if(camino.getCamino().getCodigoCentroOrigen().equals(origen) && camino.getCamino().getCodigoCentroDestino().equals(destino)){
                return camino;
            }
            camino = camino.getSig();
        }
        return null;
    }

    @Override
    public Iterator<Camino> iterator() {
        return null;
    }

    public int buscarCaminoMasBarato() {
        NodoCamino camino = inicio;
        int costoMasBajo = Integer.MAX_VALUE;
        while (camino != null){
            if(camino.getCamino().getCosto() < costoMasBajo){
                costoMasBajo = (int)camino.getCamino().getCosto();
            }
            camino = camino.getSig();
        }
        return costoMasBajo;
    }
}