package lista;

import java.util.Iterator;

//ToDo BORRAR SI NO SE UTILIZA
public class ListaImp <T> implements Lista<T>{
    protected NodoLista<T> inicio;
    //protected int largo;
    private NodoLista<T> ultimo;
    private int cantidad;

    public ListaImp(){
        this.inicio = null;
        this.ultimo = null;
        this.cantidad = 0;
    }

    @Override
    public void insertar(T valorNuevo) {
        if (esVacia()) {
            inicio = new NodoLista(valorNuevo, null);
            ultimo = inicio;
        } else {
            inicio = new NodoLista(valorNuevo, inicio);
        }

        cantidad++;
    }

    @Override
    public int largo() {
        return cantidad;
    }

    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoLista<T> aux = inicio;

            @Override
            public boolean hasNext() {
                return aux != null;
            }

            @Override
            public T next() {
                T dato = aux.getDato();
                aux = (NodoLista<T>) aux.getSig(); // Sin (NodoLista<T>) me da error
                return dato;
            }

            @Override
            public void remove() {
            }
        };
    }

    public class NodoLista<T> {
        private T dato;
        private T sig;

        public NodoLista() {
            this.dato = dato;
            this.sig = null;
        }

        public NodoLista(T dato, T siguiente) {
            this.dato = dato;
            this.sig = siguiente;
        }

        public T getDato() {
            return dato;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }

        public T getSig() {
            return sig;
        }

        public void setSiguiente(T sig) {
            this.sig = sig;
        }
    }
}
