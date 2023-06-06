package tema6.implementacion;

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
}
