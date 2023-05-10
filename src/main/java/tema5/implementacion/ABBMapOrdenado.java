package tema5.implementacion;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema3.modelos.Map;
import tema5.modelos.MapOrdenado;

public class ABBMapOrdenado<C extends Comparable<C>, V> implements MapOrdenado<C, V>, Map<C, V> {
    protected ABB<EntradaMap<C, V>> abb;

    public ABBMapOrdenado(){
        this.abb = new ABB<>();
    }

    public boolean esVacio() {
        return abb.esVacio();
    }
    public int talla() {
        return abb.talla();
    }

    public V recuperar(C c) {
        EntradaMap<C, V> e = abb.recuperar(new EntradaMap<C, V>(c, null));
        return e == null ? null : e.getValor();
    }

    @Override
    public V insertar(C c, V v) {
        EntradaMap<C, V> old = abb.insertar(new EntradaMap<>(c, v));
        return old == null ? null : old.getValor();
    }

    @Override
    public V eliminar(C c) {
        EntradaMap<C, V> old = abb.eliminar(new EntradaMap<>(c, null));
        return old == null ? null : old.getValor();
    }

    @Override
    public ListaConPI<C> claves() {
        ListaConPI<C> claves = new LEGListaConPI<>();
        EntradaMap<C, V> actual = abb.recuperarMin();
        while(actual != null){
            claves.insertar(actual.getClave());
            actual = abb.sucesor(actual);
        }
        return claves;
    }

    @Override
    public EntradaMap<C, V> recuperarEntradaMin() {
        return abb.recuperarMin();
    }

    @Override
    public C recuperarMin() {
        EntradaMap<C, V> min = recuperarEntradaMin();
        return min == null ? null : min.getClave();
    }

    @Override
    public EntradaMap<C, V> recuperarEntradaMax() {
        return abb.recuperarMax();
    }

    @Override
    public C recuperarMax() {
        EntradaMap<C, V> max = recuperarEntradaMax();
        return max == null ? null : max.getClave();
    }

    @Override
    public EntradaMap<C, V> sucesorEntrada(C c) {
        return abb.sucesor(new EntradaMap<>(c, null));
    }

    @Override
    public C sucesor(C c) {
        EntradaMap<C, V> sucesor = sucesorEntrada(c);
        return sucesor == null ? null : sucesor.getClave();
    }

    @Override
    public EntradaMap<C, V> predecesorEntrada(C c) {
        return abb.predecesor(new EntradaMap<>(c, null));
    }

    @Override
    public C predecesor(C c) {
        EntradaMap<C, V> predecesor = predecesorEntrada(c);
        return predecesor == null ? null : predecesor.getClave();
    }
}