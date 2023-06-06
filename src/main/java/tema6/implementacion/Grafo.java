package tema6.implementacion;

import tema1.implementacion.array.ArrayCola;
import tema1.implementacion.puntointeres.LEGCola;
import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.Cola;
import tema1.modelos.ListaConPI;
import tema4.implementacion.MonticuloBinario;
import tema4.modelos.ColaPrioridad;

import java.util.Arrays;

public abstract class Grafo {

    /** Variable auxiliar per a DFS */
    protected int[] visitados;
    /** Variable auxiliar per a DFS */
    protected int ordenVisita;


    /** Cola auxiliar per al BFS */
    protected Cola<Integer> q;
    protected double[] distanciaMin;
    protected int[] caminoMin;
    private final double INFINITO = Double.POSITIVE_INFINITY;

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
            if (visitados[v] == 0) {
                toArrayDFS(v, res);
            }
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

    // SII el Grafo es un Digrafo ...
    public boolean hayCicloDFS() {
        boolean ciclo = false;
        visitados = new int[numVertices()];
        for (int v = 0; v < numVertices() && !ciclo; v++)
            if (visitados[v] == 0) {
                ciclo = hayAristaHADFS(v);
            }
        return ciclo;
    }

    protected boolean hayAristaHADFS(int v) {
        boolean aristaHA = false;
        visitados[v] = 1;

        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin() && !aristaHA; l.siguiente()) {
            int w = l.recuperar().getDestino();
            if (visitados[w] == 0) {
                aristaHA = hayAristaHADFS(w);
            }
            else if (visitados[w] == 1) {
                aristaHA = true;
            }
        }
        visitados[v] = 2;
        return aristaHA;
    }

    protected void caminosMinimosSinPesos(int v) {
        caminoMin = new int[numVertices()];
        distanciaMin = new double[numVertices()];
        for (int i = 0; i < numVertices(); i++) {
            caminoMin[i] = -1;
            distanciaMin[i] = INFINITO;
        }
        distanciaMin[v] = 0;
        q = new ArrayCola<>();

        q.encolar(v);
        while (!q.esVacia()) {
            int u = q.desencolar();
            ListaConPI<Adyacente> l = adyacentesDe(u);
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                int w = l.recuperar().getDestino();
                if (distanciaMin[w] == INFINITO) {
                    distanciaMin[w] = distanciaMin[u] + 1;
                    caminoMin[w] = u;
                    q.encolar(w);
                }
            }
        }
    }

    protected ListaConPI<Integer> decodificarCaminoHasta(int w){
        ListaConPI<Integer> l = new LEGListaConPI<>();
        if (distanciaMin[w] != INFINITO) {
            l.insertar(w);
            while (caminoMin[w] != -1) {
                l.inicio();
                l.insertar(caminoMin[w]);
                w = caminoMin[w];
            }
        }
        return l;
    }

    public ListaConPI<Integer> caminoMinimoSinPesos(int v, int w) {
        caminosMinimosSinPesos(v);
        return decodificarCaminoHasta(w);
    }

    protected void dijkstra(int v){
        visitados = new int[numVertices()];
        caminoMin = new int[numVertices()];
        distanciaMin = new double[numVertices()];
        for (int i = 0; i < numVertices(); i++) {
            caminoMin[i] = -1;
            distanciaMin[i] = INFINITO;
        }
        ColaPrioridad<VerticeCoste> qP = new MonticuloBinario<>(numVertices());

        qP.insertar(new VerticeCoste(v, 0));
        distanciaMin[v] = 0;

        while (!qP.esVacia()) {
            VerticeCoste vC = qP.eliminarMin();
            int u = vC.codigo;
            if (visitados[u] == 0 ){
                visitados[u] = 1;

                ListaConPI<Adyacente> l = adyacentesDe(u);
                for (l.inicio(); !l.esFin(); l.siguiente()) {
                    Adyacente a = l.recuperar();
                    int w = a.getDestino();
                    double p = a.getPeso();

                    if (distanciaMin[w] > vC.coste + p) {
                        distanciaMin[w] = vC.coste + p;
                        caminoMin[w] = u;
                        qP.insertar(new VerticeCoste(w, distanciaMin[w]));
                    }
                }
            }
        }
    }

    public ListaConPI<Integer> caminoMinimo(int v, int w) {
        dijkstra(v);
        return decodificarCaminoHasta(w);
    }
}
