package tema5.implementacion;

import tema1.modelos.ListaConPI;
import tema5.modelos.MapOrdenado;

public class ABBMapOrdenado<C extends Comparable<C>, V> implements MapOrdenado<C, V> {
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
        EntradaMap<C, V> old = abb.recuperar(new EntradaMap<>(c, null));
        abb.insertar(new EntradaMap<>(c, v));
        return old == null ? null : old.getValor();
    }

    @Override
    public V eliminar(C c) {
        EntradaMap<C, V> old = abb.recuperar(new EntradaMap<>(c, null));
        abb.eliminar(new EntradaMap<>(c, null));
        return old == null ? null : old.getValor();
    }

    @Override
    public ListaConPI<C> claves() {
        return null;
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
        return null;
    }

    @Override
    public C recuperarMax() {
        return null;
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
        return null;
    }

    @Override
    public C predecesor(C c) {
        return null;
    }
}
