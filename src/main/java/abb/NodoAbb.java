package abb;

public class NodoAbb<T> {
    private T dato;
    private NodoAbb<T> izq;
    private NodoAbb<T> der;

    public NodoAbb(T dato){
        this.dato = dato;
        this.izq = null;
        this.der = null;
    }
    public NodoAbb(T dato, NodoAbb<T> izq, NodoAbb<T> der){
        this.dato = dato;
        this.izq = izq;
        this.der = der;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoAbb<T> getIzq() {
        return izq;
    }

    public void setIzq(NodoAbb<T> izq) {
        this.izq = izq;
    }

    public NodoAbb<T> getDer() {
        return der;
    }

    public void setDer(NodoAbb<T> der) {
        this.der = der;
    }
}
