package tema3.implementacion;

public class EntradaHash<C, V> {
    C clave;
    V valor;

    public EntradaHash(C clave, V valor) {
        this.clave = clave;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "<" + clave + ", " + valor + '>';
    }
}
