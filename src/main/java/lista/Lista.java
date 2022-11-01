package lista;

public interface Lista<T> extends Iterable<T> {
    void insertar(T dato);
    boolean esVacia();
}
