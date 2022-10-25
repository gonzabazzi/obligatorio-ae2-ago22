package lista;

import dominio.CentroUrbano;

import java.util.Iterator;

public class ListaCentro implements Lista<CentroUrbano> {

    private NodoCentro inicio;
    private NodoCentro ultimo;
    protected int largo;

    public ListaCentro() {
        this.inicio = null;
        this.ultimo = null;
        this.largo = 0;
    }

    public int getLargo() {return largo;}
    public void setLargo(int largo) {this.largo = largo;}

    @Override
    public void insertar(CentroUrbano centroUrbano){
        if(inicio == null){
            inicio = new NodoCentro(centroUrbano);
        } else {
            NodoCentro aux = inicio;
            while (aux.getSig() != null){
                aux = aux.getSig();
            }
            aux.setSig(new NodoCentro(centroUrbano));
        }
        largo++;
    }

    @Override
    public int largo() {
        return this.largo;
    }

    @Override
    public boolean esVacia() {
        return this.largo > 0;
    }

    @Override
    public String toString() {
        NodoCentro aux = inicio;
        String string = "";
        while (aux != null) {
            string += aux.getCentro().getCodigo() + ";" +
                    aux.getCentro().getNombre() + "|";
            aux = aux.getSig();
        }
        return string;
    }

    @Override
    public Iterator<CentroUrbano> iterator() {
        return null;
    }
}
