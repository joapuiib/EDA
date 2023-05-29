package tema6.implementacion;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;

public class GrafoDirigido extends Grafo{
    private final ListaConPI<Adyacente>[] elArray;
    /**
     * Número de vertices
     */
    protected int numV;
    /**
     * Número de aristas
     */
    protected int numA;

    @SuppressWarnings("unchecked")
    public GrafoDirigido(int nV){
        super();
        numV = nV; numA = 0;
        elArray = new ListaConPI[numV];
        for (int i = 0; i < numV; i++) {
            elArray[i] = new LEGListaConPI<Adyacente>();
        }
    }

    @Override
    public int numVertices() {
        return numV;
    }

    @Override
    public int numAristas() {
        return numA;
    }

    @Override
    public boolean existeArista(int i, int j) {
        ListaConPI<Adyacente> adyacentes = adyacentesDe(i);
        for (adyacentes.inicio(); !adyacentes.esFin(); adyacentes.siguiente()){
            Adyacente a = adyacentes.recuperar();
            if(a.getDestino() == j)
                return true;
        }
        return false;
    }

    @Override
    public double pesoArista(int i, int j) {
        ListaConPI<Adyacente> adyacentes = adyacentesDe(i);
        for (adyacentes.inicio(); !adyacentes.esFin(); adyacentes.siguiente()){
            Adyacente a = adyacentes.recuperar();
            if(a.destino == j)
                return a.getPeso();
        }
        return 0.0;
    }

    @Override
    public void insertarArista(int i, int j) {
        insertarArista(i, j, 1);
    }

    @Override
    public void insertarArista(int i, int j, double p) {
        if (!existeArista(i, j)){
            adyacentesDe(i).insertar(new Adyacente(j, p));
            numA++;
        }
    }

    public void insertarActualizarArista(int i, int j, double p) {
        ListaConPI<Adyacente> adyacentes = adyacentesDe(i);
        for (adyacentes.inicio(); !adyacentes.esFin(); adyacentes.siguiente()){
            Adyacente a = adyacentes.recuperar();
            // Si el trobem, actualitzem
            if(a.destino == j) {
                a.peso = p;
                return;
            }
        }

        // Si no l'hem trobat abans
        adyacentes.insertar(new Adyacente(j, p));
        numA++;
    }

    @Override
    public ListaConPI<Adyacente> adyacentesDe(int i) {
        return elArray[i];
    }

    public int gradoSalida(int i) {
        return adyacentesDe(i).talla();
    }

    public int gradoSalida() {
        int gradoMax = gradoSalida(0);
        for (int i = 1; i < numV; i++) {
            int grado = gradoSalida(i);
            if (grado > gradoMax) gradoMax = grado;
        }
        return gradoMax;
    }

    public int gradoEntrada(int i) {
        int grado = 0;
        for (int j = 0; j < numVertices(); j++) {
            if (existeArista(j, i))
                grado++;
        }
        return grado;
    }


    public int gradoEntrada() {
        int[] grado = new int[numVertices()];

        int gradoMax = 0;
        for (int i = 0; i < numVertices(); i++) {
            ListaConPI<Adyacente> adyacentes = adyacentesDe(i);
            for (adyacentes.inicio(); !adyacentes.esFin(); adyacentes.siguiente()){
                Adyacente a = adyacentes.recuperar();
                grado[a.destino]++;

                if(grado[a.destino] > gradoMax)
                    gradoMax = grado[a.destino];
            }
        }

        return gradoMax;
    }
}
