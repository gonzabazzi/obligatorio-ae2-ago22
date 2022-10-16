package lista;

import lista.Lista;
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
        return true;
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

    @Override
    public Iterator<Camino> iterator() {
        return null;
    }

 /*   public boolean existeVuelo(String codigoVuelo){
        NodoCamino vuelo = inicio;
        while (vuelo != null){
            if(vuelo.getCamino().getc().equals(codigoVuelo)){
                return true;
            }
            vuelo = camino.getSig();
        }
        return false;
    }*/

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

/*    public int buscarVueloMasBarato(){
*//*        NodoListaVuelo vuelo = inicio;
        int costoMasBajo = Integer.MAX_VALUE;
        while (vuelo != null){
            if(vuelo.getVuelo().getCostoEnDolares() < costoMasBajo){
                costoMasBajo = (int)vuelo.getVuelo().getCostoEnDolares();
            }
            vuelo = vuelo.getSig();
        }
        return costoMasBajo;
    }*/




}