package tema3.implementacion;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema3.modelos.Map;

public class TablaHash<C, V> implements Map<C, V> {
    // Array de listas (con PI) de entradas
    private ListaConPI<EntradaHash<C,V>> elArray[];
    // Número de datos almacenados en la tabla
    private int talla;

    @SuppressWarnings("unchecked")
    public TablaHash(int tallaMaximaEstimada) {
        int capacidad = siguientePrimo((int) (tallaMaximaEstimada/0.75));
        elArray = new LEGListaConPI[capacidad];

        for (int i = 0; i < elArray.length; i++)
            elArray[i] = new LEGListaConPI<EntradaHash<C,V>>();

        talla = 0;
    }

    // Devuelve un numero primo MAYOR o IGUAL a n, i.e. el primo que sigue a n
    public static final int siguientePrimo(int n) {
        int nn = n;
        if (nn % 2 == 0) { nn++; }
        for ( ; !esPrimo(nn); nn += 2) { ; }
        return nn;
    }

    // Comprueba si n es un numero primo
    protected static final boolean esPrimo(int n) {
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) { return false; } // n NO es primo
        }
        return true; // n SI es primo
    }


    /** Calcula la cubeta en la que debe estar un elemento
     * con clave c. Para ello primero obtiene el valor de
     * hash (hashCode) y a continuación su índice hash
     * @param c Clave del dato a localizar
     * @return Cubeta en la que se encuentra el dato
     */
    protected int indiceHash(C c) {
        // c.hashCode() es la función de dispersion
        // % this.elarray.length es la función de compresion
        int indiceHash = c.hashCode() % this.elArray.length;
        if (indiceHash < 0)
            indiceHash += this.elArray.length;
        return indiceHash;
    }

    @Override
    public V insertar(C c, V v) {
        V antiguoValor = null;
        int pos = indiceHash(c);
        ListaConPI<EntradaHash<C, V>> cubeta = this.elArray[pos];

        cubeta.inicio();
        while (!cubeta.esFin() && !cubeta.recuperar().clave.equals(c))
            cubeta.siguiente();

        if(cubeta.esFin()){
            EntradaHash<C, V> entrada = new EntradaHash<C, V>(c, v);
            cubeta.insertar(entrada);
            talla++;
        } else {
            EntradaHash<C, V> antiguaEntrada = cubeta.recuperar();
            antiguoValor = antiguaEntrada.valor;
            antiguaEntrada.valor = v;
        }
        return antiguoValor;
    }

    @Override
    public V eliminar(C c) {
        int pos = indiceHash(c);
        ListaConPI<EntradaHash<C, V>> cubeta = this.elArray[pos];
        V valor = null;

        cubeta.inicio();
        while (!cubeta.esFin() && !cubeta.recuperar().clave.equals(c))
            cubeta.siguiente();

        if(!cubeta.esFin()){
            valor = cubeta.recuperar().valor;
            cubeta.eliminar();
            talla--;
        }

        return valor;
    }

    @Override
    public V recuperar(C c) {
        int pos = indiceHash(c);
        ListaConPI<EntradaHash<C, V>> cubeta = this.elArray[pos];

        cubeta.inicio();
        while (!cubeta.esFin() && !cubeta.recuperar().clave.equals(c))
            cubeta.siguiente();

        if(cubeta.esFin())
            return null;
        else
            return cubeta.recuperar().valor;

        // return cubeta.esFin() ? null : cubeta.recuperar().valor;
    }

    @Override
    public boolean esVacio() {
        return talla() == 0;
    }

    @Override
    public int talla() {
        return talla;
    }

    @Override
    public ListaConPI<C> claves() {
        ListaConPI<C> claves = new LEGListaConPI<>();
        for (int i = 0; i < elArray.length; i++) {
            ListaConPI<EntradaHash<C, V>> cubeta = this.elArray[i];
            cubeta.inicio();
            while (!cubeta.esFin()){
                C clave = cubeta.recuperar().clave;
                claves.insertar(clave);
                cubeta.siguiente();
            }
        }
        return claves;
    }

    @Override
    public String toString(){
        ListaConPI<C> claves = this.claves();
        StringBuilder str = new StringBuilder();
        str.append("{");

        claves.inicio();
        while(!claves.esFin()){
            C clave = claves.recuperar();
            V valor = this.recuperar(clave);
            str.append(String.format("<%s => %s>", clave.toString(), valor.toString()));
            claves.siguiente();

            if(!claves.esFin())
                str.append(", ");
        }
        str.append("}");
        return str.toString();
    }

    /**
     * Ejercicio del parcial 2015
     */
    public ListaConPI<C> colisionanCon(C c){
        int pos = indiceHash(c);
        ListaConPI<EntradaHash<C, V>> cubeta = elArray[pos];

        ListaConPI<C> colisiones = new LEGListaConPI<>();
        for(cubeta.inicio(); !cubeta.esFin(); cubeta.siguiente()){
            C clave = cubeta.recuperar().clave;
            if(!clave.equals(c))
                colisiones.insertar(clave);
        }
        return colisiones;
    }
}
