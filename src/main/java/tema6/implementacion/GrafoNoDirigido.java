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
}
