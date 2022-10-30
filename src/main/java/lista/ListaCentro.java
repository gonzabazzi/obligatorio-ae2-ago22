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
            ultimo = inicio;
        } else {
            NodoCentro aux = inicio;
            while (aux.getSig() != null){
                aux = aux.getSig();
            }
            aux.setSig(new NodoCentro(centroUrbano));
            ultimo = aux.getSig();
        }
        largo++;
    }

    public void agregarAlista(CentroUrbano centroUrbano) {
        if (inicio == null) {
            inicio = new NodoCentro(centroUrbano);
            ultimo = inicio;
            largo++;
        } else {
            inicio = agregarAlistaRec(inicio, centroUrbano);
        }
    }
    private NodoCentro agregarAlistaRec(NodoCentro nodo, CentroUrbano dato) {
        if (nodo == null || (nodo.getCentro().getCodigo().compareTo(dato.getCodigo()) > 0)) {
            NodoCentro nuevo = new NodoCentro(dato);
            if (nodo != null) {
                nodo.setAnt(nuevo);
            }
            nuevo.setSig(nodo);
            this.agregarFinal(nuevo.getCentro());
            return nuevo;
        }
        NodoCentro llamada = agregarAlistaRec(nodo.getSig(), dato);
        llamada.setAnt(nodo);
        nodo.setSig(llamada);

        return nodo;
    }
    public void agregarFinal(CentroUrbano objeto) {
        NodoCentro nuevo = new NodoCentro(objeto);
        if (this.esVacia()) {
            inicio = nuevo;
            ultimo = inicio;
        } else {
            ultimo.setSig(nuevo);
            nuevo.setAnt(ultimo);
            ultimo = nuevo;
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
