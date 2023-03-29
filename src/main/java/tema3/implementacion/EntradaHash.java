package tema3.implementacion;

public class EntradaHash<C, V> {
    C clave;
    V valor;

    public EntradaHash(C clave, V valor) {
        this.clave = clave;
        this.valor = valor;
    }

    public C getClave() {
        return clave;
    }

    public V getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "<" + clave + ", " + valor + '>';
    }
}
