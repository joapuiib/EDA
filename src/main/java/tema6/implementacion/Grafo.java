package tema6.implementacion;

import tema1.implementacion.puntointeres.LEGCola;
import tema1.modelos.Cola;
import tema1.modelos.ListaConPI;

public abstract class Grafo {

    /**
     * Variable auxiliar per a DFS
     */
    protected int[] visitados;
    /**
     * Variable auxiliar per a DFS
     */
    protected int ordenVisita;


    /**
     * Cola auxiliar per al BFS
     */
    protected Cola<Integer> q;

    public abstract int numVertices();
    public abstract int numAristas();
    public abstract boolean existeArista(int i, int j);
    public abstract double pesoArista(int i, int j);
    public abstract void insertarArista(int i, int j);
    public abstract void insertarArista(int i, int j, double p);
    public abstract ListaConPI<Adyacente> adyacentesDe(int i);

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
}
