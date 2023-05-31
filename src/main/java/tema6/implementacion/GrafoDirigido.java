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

    /**
     * Exercici 1.3
     */
    public int grado() {
        int[] grados = new int[numVertices()];

        // Grado Salida
        for (int i = 0; i < numVertices(); i++) {
            grados[i] = gradoSalida(i);
        }

        // Grado entrada eficient, nomes recorrec 1 volta
        for (int i = 0; i < numVertices(); i++) {
            ListaConPI<Adyacente> adyacentes = adyacentesDe(i);
            for (adyacentes.inicio(); !adyacentes.esFin(); adyacentes.siguiente()){
                Adyacente a = adyacentes.recuperar();
                grados[a.destino]++;
            }
        }

        int gradoMax = grados[0];
        for (int i = 1; i < numVertices(); i++) {
            if (gradoMax < grados[i])
                gradoMax = grados[i];
        }

        return gradoMax;
    }

    /**
     * Exercici 1.3
     */
    public double aristaMayorPeso() {
        double pesoMax = 0;
        for (int i = 0; i < numVertices(); i++) {
            ListaConPI<Adyacente> adyacentes = adyacentesDe(i);
            for (adyacentes.inicio(); !adyacentes.esFin(); adyacentes.siguiente()){
                Adyacente a = adyacentes.recuperar();
                if (pesoMax < a.getPeso())
                    pesoMax = a.getPeso();
            }
        }

        return pesoMax;
    }

    /**
     * Exercici 1.3
     */
    public boolean esRegular() {
        int[] grados = new int[numVertices()];

        // Grado Salida
        for (int i = 0; i < numVertices(); i++) {
            grados[i] = gradoSalida(i);
        }

        // Grado entrada eficient, nomes recorrec 1 volta
        for (int i = 0; i < numVertices(); i++) {
            ListaConPI<Adyacente> adyacentes = adyacentesDe(i);
            for (adyacentes.inicio(); !adyacentes.esFin(); adyacentes.siguiente()){
                Adyacente a = adyacentes.recuperar();
                grados[a.destino]++;
            }
        }

        int grado = grados[0];
        for (int i = 1; i < numVertices(); i++) {
            if (grado != grados[i])
                return false;
        }

        return true;
    }

    /**
     * Exercici 1.4
     */
    public int getVerticeReceptivo(){
        int[] grados = new int[numVertices()];

        // Grados d'entrada
        for (int i = 0; i < numVertices(); i++) {
            ListaConPI<Adyacente> l = adyacentesDe(i);
            for (l.inicio(); !l.esFin(); l.siguiente()){
                Adyacente a = l.recuperar();
                grados[a.getDestino()]++;
            }
        }

        for (int i = 0; i < numVertices(); i++) {
            if (grados[i] == numVertices() - 1)
                return i;
        }
        return -1;
    }

    /**
     * Exercici 1.4
     */
    public boolean esSumidero(int v){
        return gradoEntrada(v) > 0 && gradoSalida(v) == 0;
    }

    /**
     * Exercici 1.4
     */
    public int getSumideroUniversal(){
        int[] gradosEntrada = new int[numVertices()];

        // Grados d'entrada
        for (int i = 0; i < numVertices(); i++) {
            ListaConPI<Adyacente> l = adyacentesDe(i);
            for (l.inicio(); !l.esFin(); l.siguiente()){
                Adyacente a = l.recuperar();
                gradosEntrada[a.getDestino()]++;
            }
        }

        for (int i = 0; i < numVertices(); i++) {
            if (gradosEntrada[i] == numVertices() - 1 && gradoSalida(i) == 0)
                return i;
        }
        return -1;
    }

    /**
     * Exercici 1.4
     */
    public int getFuenteUniversal(){
        int[] gradosEntrada = new int[numVertices()];

        // Grados d'entrada
        for (int i = 0; i < numVertices(); i++) {
            ListaConPI<Adyacente> l = adyacentesDe(i);
            for (l.inicio(); !l.esFin(); l.siguiente()){
                Adyacente a = l.recuperar();
                gradosEntrada[a.getDestino()]++;
            }
        }

        for (int i = 0; i < numVertices(); i++) {
            if (gradosEntrada[i] == 0 && gradoSalida(i) == numVertices() - 1)
                return i;
        }
        return -1;
    }

    /**
     * Exercici 1.4
     */
    public boolean esCompleto(){
        for (int i = 0; i < numVertices(); i++) {
            if (gradoSalida(i) != numVertices() - 1)
                return false;
        }
        return true;
    }

    public boolean esDebilmenteConexo() {
        visitados = new int[numVertices()];
        int componente = 1;
        for (int v = 0; v < numVertices(); v++) {
            if (visitados[v] == 0) {
                esDebilmenteConexo(v, componente);
                componente++;
            }
        }

        for (int v = 0; v < numVertices(); v++) {
            if (visitados[v] != 1) {
                return false;
            }
        }
        return true;
    }

    protected void esDebilmenteConexo(int v, int componente) {
        visitados[v] = componente;
        ListaConPI<Adyacente> adyacentes = adyacentesDe(v);
        for (adyacentes.inicio(); !adyacentes.esFin(); adyacentes.siguiente()){
            Adyacente a = adyacentes.recuperar();
            int destino = a.getDestino();
            if(visitados[destino] == 0)
                esDebilmenteConexo(destino, componente);
            else if (visitados[destino] < componente) {
                for (int w = 0; w < numVertices(); w++) {
                    if (visitados[w] == componente) {
                        visitados[w] = visitados[destino];
                    }
                }
                componente = visitados[destino];
            }
            else if (visitados[destino] > componente) {
                for (int w = 0; w < numVertices(); w++) {
                    if (visitados[w] == visitados[destino]) {
                        visitados[w] = componente;
                    }
                }
            }
        }
    }
}
