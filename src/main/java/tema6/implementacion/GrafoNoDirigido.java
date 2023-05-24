package tema6.implementacion;

public class GrafoNoDirigido extends GrafoDirigido {
    public GrafoNoDirigido(int nV){
        super(nV);
    }

    @Override
    public int numAristas() {
        return super.numAristas() / 2;
    }

    @Override
    public void insertarArista(int i, int j) {
        super.insertarArista(i, j);
        super.insertarArista(j, i);
    }

    @Override
    public void insertarArista(int i, int j, double p) {
        super.insertarArista(i, j, p);
        super.insertarArista(j, i, p);
    }

    @Override
    public int gradoEntrada(int i){
        return gradoSalida(i);
    }
}
