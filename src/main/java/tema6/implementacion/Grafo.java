package tema6.implementacion;

import tema1.implementacion.puntointeres.LEGCola;
import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.Cola;
import tema1.modelos.ListaConPI;

import java.util.Arrays;

public abstract class Grafo {

    /** Variable auxiliar per a DFS */
    protected int[] visitados;
    /** Variable auxiliar per a DFS */
    protected int ordenVisita;


    /** Cola auxiliar per al BFS */
    protected Cola<Integer> q;

    private final int INFINITO = Integer.MAX_VALUE;

    public abstract int numVertices();
    public abstract int numAristas();
    public abstract boolean existeArista(int i, int j);
    public abstract double pesoArista(int i, int j);
    public abstract void insertarArista(int i, int j);
    public abstract void insertarArista(int i, int j, double p);
    public abstract ListaConPI<Adyacente> adyacentesDe(int i);

    public abstract int getVerticeReceptivo();
    public abstract boolean esSumidero(int v);
    public abstract int getSumideroUniversal();
    public abstract int getFuenteUniversal();
    public abstract boolean esCompleto();

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < numVertices(); i++) {
            res.append("Vertice: ").append(i);
            ListaConPI<Adyacente> l = adyacentesDe(i);
            if (l.esVacia())
                res.append(" sin Adyacentes ");
            else
                res.append(" con Adyacentes: ");
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                res.append(l.recuperar()).append(" ");
            }
            res.append("\n");
        }
        return res.toString();
    }

    public int[] toArrayDFS() {
        int[] res = new int[numVertices()];
        ordenVisita = 0;
        visitados = new int[numVertices()];
        for (int v = 0; v < numVertices(); v++) {
            if (visitados[v] == 0) { toArrayDFS(v, res); }
        }
        return res;
    }

    public void toArrayDFS(int v, int[] res) {
        visitados[v] = 1;
        res[ordenVisita++] = v;
        ListaConPI<Adyacente> adyacentes = adyacentesDe(v);
        for (adyacentes.inicio(); !adyacentes.esFin(); adyacentes.siguiente()){
            Adyacente a = adyacentes.recuperar();
            int destino = a.getDestino();
            if(visitados[destino] == 0)
                toArrayDFS(destino, res);
        }
    }

    public int[] toArrayBFS() {
        int[] res = new int[numVertices()];
        ordenVisita = 0;
        visitados = new int[numVertices()];
        q = new LEGCola<>();
        for (int v = 0; v < numVertices(); v++) {
            if (visitados[v] == 0) { toArrayBFS(v, res); }
        }
        return res;
    }

    public void toArrayBFS(int origen, int[] res) {
        visitados[origen] = 1;
        q.encolar(origen);

        while(!q.esVacia()) {
            int v = q.desencolar();
            res[ordenVisita++] = v;

            ListaConPI<Adyacente> adyacentes = adyacentesDe(v);
            for (adyacentes.inicio(); !adyacentes.esFin(); adyacentes.siguiente()) {
                Adyacente a = adyacentes.recuperar();
                int destino = a.getDestino();
                if (visitados[destino] == 0) {
                    visitados[destino] = 1;
                    q.encolar(destino);
                }
            }
        }
    }

    public int[] ordenTopologicoDFS() {
        int[] res = new int[numVertices()];
        ordenVisita = numVertices() - 1;
        visitados = new int[numVertices()];
        for (int v = 0; v < numVertices(); v++) {
            if (visitados[v] == 0) { ordenTopologicoDFS(v, res); }
        }
        return res;
    }

    public void ordenTopologicoDFS(int origen, int[] res) {
        visitados[origen] = 1;

        ListaConPI<Adyacente> adyacentes = adyacentesDe(origen);
        for (adyacentes.inicio(); !adyacentes.esFin(); adyacentes.siguiente()) {
            Adyacente a = adyacentes.recuperar();
            int destino = a.getDestino();
            if (visitados[destino] == 0) {
                ordenTopologicoDFS(destino, res);
            }
        }

        res[ordenVisita--] = origen;
    }

    public int distanciaMin(int v, int w){
        int[] distancias = new int[numVertices()];
        Arrays.fill(distancias, INFINITO);

        q = new LEGCola<>();
        distancias[v] = 0;
        q.encolar(v);

        while (!q.esVacia()){
            int actual = q.desencolar();
            int distanciaActual = distancias[actual];
            ListaConPI<Adyacente> adyacentes = adyacentesDe(actual);
            for (adyacentes.inicio(); !adyacentes.esFin(); adyacentes.siguiente()){
                Adyacente a = adyacentes.recuperar();
                if (distancias[a.destino] == INFINITO){
                    distancias[a.destino] = distanciaActual + 1;
                    q.encolar(a.destino);
                    if (a.destino == w)
                        return distancias[w];
                }
            }
        }
        return distancias[w];
    }

    public ListaConPI<Integer> caminoMin(int v, int w){
        int[] camino = new int[numVertices()];
        Arrays.fill(camino, -1);

        q = new LEGCola<>();
        q.encolar(v);

        boolean encontrado = false;
        while (!q.esVacia() && !encontrado){
            int actual = q.desencolar();
            ListaConPI<Adyacente> adyacentes = adyacentesDe(actual);
            for (adyacentes.inicio(); !adyacentes.esFin() && !encontrado; adyacentes.siguiente()){
                Adyacente a = adyacentes.recuperar();
                if (camino[a.destino] == -1){
                    camino[a.destino] = actual;
                    q.encolar(a.destino);
                    if (a.destino == w)
                        encontrado = true;
                }
            }
        }

        ListaConPI<Integer> res = new LEGListaConPI<>();
        int actual = w;
        while (actual != -1){
            res.inicio();
            res.insertar(actual);
            actual = camino[actual];
        }
        return res;
    }

}
