package tema6.implementacion;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;

public class GrafoNoDirigido extends GrafoDirigido {
    public GrafoNoDirigido(int nV){
        super(nV);
    }

    @Override
    public void insertarArista(int i, int j, double p){
        if (!existeArista(i, j)){
            adyacentesDe(i).insertar(new Adyacente(j, p));
            adyacentesDe(j).insertar(new Adyacente(i, p));
            numA++;
        }
    }

    @Override
    public int gradoEntrada(int i){
        return gradoSalida(i);
    }

    /**
     * Exercici 1.4
     */
    public int getVerticeReceptivo() {
        for (int i = 0; i < numVertices(); i++) {
            if (adyacentesDe(i).talla() == numVertices() - 1)
                return i;
        }
        return -1;
    }

    /**
     * Exercici 1.4
     */
    public boolean esSumidero(int v){
        return false;
    }

    /**
     * Exercici 1.4
     */
    public int getSumideroUniversal() {
        return -1;
    }

    /**
     * Exercici 1.4
     */
    public int getFuenteUniversal() {
        return -1;
    }

    public ListaConPI<ListaConPI<Integer>> componentesConexas(){
        visitados = new int[numVertices()];

        ListaConPI<ListaConPI<Integer>> res = new LEGListaConPI<>();

        for (int i = 0; i < numVertices(); i++) {
            if (visitados[i] == 0) {
                ListaConPI<Integer> componentes = new LEGListaConPI<>();
                componentesConexas(i, componentes);
                res.insertar(componentes);
            }
        }

        return res;
    }

    protected void componentesConexas(int v, ListaConPI<Integer> componentes){
        visitados[v] = 1;
        componentes.insertar(v);

        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin(); l.siguiente()){
            Adyacente a = l.recuperar();
            int w = a.getDestino();
            if (visitados[w] == 0){
                componentesConexas(w, componentes);
            }
        }
    }

    /**
     * Recuperació 2n parcial 2021/2022
     */
    @Override
    public void eliminarArista(int i, int j){
        super.eliminarArista(i, j);
        super.eliminarArista(j, i);
    }
}
