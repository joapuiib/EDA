package tema5.implementacion;

import java.util.Objects;

public class EntradaMap<C extends Comparable<C>, V> implements Comparable<EntradaMap<C, V>>{
    public C clave;
    public V valor;

    public EntradaMap(C clave, V valor) {
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

    @Override
    public int compareTo(EntradaMap<C, V> o) {
        return clave.compareTo(o.getClave());
    }

    public boolean equals(EntradaMap<C, V> o) {
        return this.compareTo(o) == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntradaMap<?, ?> that = (EntradaMap<?, ?>) o;
        return Objects.equals(clave, that.clave);
    }
}