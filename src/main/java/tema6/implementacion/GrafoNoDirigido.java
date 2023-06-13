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

    /**
     * Recuperació 2n parcial 2021/2022
     */
    @Override
    public void eliminarArista(int i, int j){
        super.eliminarArista(i, j);
        super.eliminarArista(j, i);
    }

    /**
     * Recuperació 2n parcial 2018/2019
     */
    public int maxAristasCC(){
        visitados = new int[numVertices()];
        int maxAristasCC = 0;
        for (int i = 0; i < numVertices(); i++) {
            if (visitados[i] == 0){
                int aristasCC = maxAristasCC(i) / 2;
                maxAristasCC = Math.max(aristasCC, maxAristasCC);
            }
        }
        return maxAristasCC;
    }

    protected int maxAristasCC(int v) {
        visitados[v] = 1;
        ListaConPI<Adyacente> l = adyacentesDe(v);
        int res = l.talla();
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            Adyacente a = l.recuperar();
            int w = a.getDestino();
            if (visitados[w] == 0){
                res += maxAristasCC(w);
            }
        }
        return res;
    }


















}
