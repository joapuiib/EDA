package tema6.implementacion;

import tema1.implementacion.array.ArrayCola;
import tema1.implementacion.puntointeres.LEGCola;
import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.Cola;
import tema1.modelos.ListaConPI;
import tema4.implementacion.MonticuloBinario;
import tema4.modelos.ColaPrioridad;

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
            res.append("Vertice ").append(i);
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

    public String[] spanningTreeDFS(){
        visitados = new int[numVertices()];
        ordenVisita = 0;
        String[] stree = new String[numVertices() - 1];
        spanningTreeDFS(0, stree);
        if (ordenVisita != numVertices() - 1)
            return null;
        return stree;
    }

    protected void spanningTreeDFS(int v, String[] stree){
        visitados[v] = 1;
        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin(); l.siguiente()){
            Adyacente a = l.recuperar();
            int w = a.getDestino();
            if (visitados[w] == 0){
                stree[ordenVisita++] = String.format("(%d, %d)", v, w);
                spanningTreeDFS(w, stree);
            }
        }
    }

    public String[] spanningTreeBFS(){
        visitados = new int[numVertices()];
        ordenVisita = 0;
        String[] stree = new String[numVertices() - 1];
        q = new ArrayCola<>();
        spanningTreeBFS(0, stree);
        if (ordenVisita != numVertices() - 1)
            return null;
        return stree;
    }

    protected void spanningTreeBFS(int v, String[] stree){
        visitados[v] = 1;
        q.encolar(v);
        while (!q.esVacia()){
            v = q.desencolar();
            ListaConPI<Adyacente> l = adyacentesDe(v);
            for (l.inicio(); !l.esFin(); l.siguiente()){
                Adyacente a = l.recuperar();
                int w = a.getDestino();
                if (visitados[w] == 0){
                    stree[ordenVisita++] = String.format("(%d, %d)", v, w);
                    q.encolar(w);
                    visitados[w] = 1;
                }
            }
        }
    }

    public ListaConPI<Integer> verticesCercanos(int v, int n){
        visitados = new int[numVertices()];
        distanciaMin = new double[numVertices()];
        for (int i = 0; i < numVertices(); i++) {
            distanciaMin[i] = INFINITO;
        }

        ColaPrioridad<VerticeCoste> qP = new MonticuloBinario<>(numVertices());
        qP.insertar(new VerticeCoste(v, 0));
        distanciaMin[v] = 0;

        while (!qP.esVacia()){
            VerticeCoste vc = qP.eliminarMin();
            int u = vc.codigo;
            if (visitados[u] == 0){
                visitados[u] = 1;

                ListaConPI<Adyacente> l = adyacentesDe(u);
                for (l.inicio(); !l.esFin(); l.siguiente()){
                    Adyacente a = l.recuperar();
                    int w = a.getDestino();
                    double costeW = distanciaMin[u] + a.getPeso();
                    if (distanciaMin[w] > costeW && costeW <= n){
                        distanciaMin[w] = costeW;
                        qP.insertar(new VerticeCoste(w, distanciaMin[w]));
                    }
                }
            }
        }

        ListaConPI<Integer> res = new LEGListaConPI<>();
        for (int i = 0; i < numVertices(); i++) {
            if (visitados[i] == 1)
                res.insertar(i);
        }
        return res;
    }

    /**
     * 2n parcial 2021/2022
     */
    public int contarRamificaciones() {
        int cont = 0;
        for (int i = 0; i < numVertices(); i++)
            if (adyacentesDe(i).talla() > 1) cont++;
        return cont;
    }

    /**
     * Recuperació 2n parcial 2021/2022
     */
    public int[] dobolonesMinimos(int v){
        distanciaMin = new double[numVertices()];
        for (int i = 0; i < numVertices(); i++) {
            distanciaMin[i] = INFINITO;
        }

        q = new ArrayCola<>();
        q.encolar(v);
        distanciaMin[v] = 0;

        while (!q.esVacia()){
            int u = q.desencolar();

            ListaConPI<Adyacente> l = adyacentesDe(u);
            for (l.inicio(); !l.esFin(); l.siguiente()){
                Adyacente a = l.recuperar();
                int w = a.getDestino();
                if (distanciaMin[w] == INFINITO){
                    distanciaMin[w] = distanciaMin[u] + 1;
                    q.encolar(w);
                }
            }
        }

        int[] doblonesMin = new int[numVertices()];
        for (int i = 0; i < numVertices(); i++) {
            doblonesMin[i] = (int) distanciaMin[i];
        }
        return doblonesMin;
    }

    /**
     * Segon parcial 2018/2019
     */
    public boolean sePuedeColorear(){
        visitados = new int[numVertices()];
        boolean[] colores = new boolean[numVertices()];
        ordenVisita = 0;
        for (int i = 0; i < numVertices(); i++) {
            if (visitados[i] == 0){
                if (!sePuedeColorear(i, colores, true))
                    return false;
            }
        }
        return true;
    }

    protected boolean sePuedeColorear(int v, boolean[] colores, boolean color){
        visitados[v] = 1;
        colores[v] = color;

        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            Adyacente a = l.recuperar();
            int w = a.getDestino();
            if (visitados[w] == 0){
                if(!sePuedeColorear(w, colores, !color))
                    return false;
            } else {
                if(colores[w] == colores[v])
                    return false;
            }
        }
        return true;
    }

    /**
     * 2n parcial de 2016/2017
     */
    public int aristasHA(){
        visitados = new int[numVertices()];
        int res = 0;
        for (int i = 0; i < numVertices(); i++) {
            if (visitados[i] == 0){
                res += aristasHA(i);
            }
        }
        return res;
    }

    protected int aristasHA(int v){
        visitados[v] = 1;
        ListaConPI<Adyacente> l = adyacentesDe(v);
        int res = 0;
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            Adyacente a = l.recuperar();
            int w = a.getDestino();
            if (visitados[w] == 0){
                res += aristasHA(w);
            } else if (visitados[w] == 1) {
                res++;
            }
        }
        visitados[v] = 2;
        return res;
    }

    public int numeroVerticesGradoSeparacions(int v, int g){
        int[] distanciaMinima = new int[numVertices()];
        for (int i = 0; i < numVertices(); i++) {
            distanciaMinima[i] = Integer.MAX_VALUE;
        }
        q = new ArrayCola<>();

        q.encolar(v);
        distanciaMinima[v] = 0;

        int res = 0;

        while (!q.esVacia()){
            int u = q.desencolar();

            ListaConPI<Adyacente> l = adyacentesDe(u);
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                Adyacente a = l.recuperar();
                int w = a.getDestino();
                if (distanciaMinima[w] == Integer.MAX_VALUE){
                    distanciaMinima[w] = distanciaMinima[u] + 1;

                    if (distanciaMinima[w] == g)
                        res++;
                    else
                        q.encolar(w);
                }
            }
        }
        return res;
    }

    /**
     * 3r 2014/2015
     */
    public ListaConPI<Integer> tallaCC(){
        visitados = new int[numVertices()];
        ListaConPI<Integer> res = new LEGListaConPI<>();
        for (int i = 0; i < numVertices(); i++) {
            if (visitados[i] == 0)
                res.insertar(tallaCC(i));
        }
        return res;
    }

    protected int tallaCC(int v){
        visitados[v] = 1;
        int res = 1;
        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            Adyacente a = l.recuperar();
            int w = a.getDestino();
            if (visitados[w] == 0){
                res += tallaCC(w);
            }
        }
        return res;
    }

    public int primeraRaiz(){
        visitados = new int[numVertices()];
        ordenVisita = 0;

        for (int i = 0; i < numVertices(); i++) {
            if (visitados[i] == 0){
                visitados = new int[numVertices()];
                ordenVisita = 0;

                primeraRaiz(i);
                if (ordenVisita == numVertices())
                    return i;
            }
        }
        return -1;
    }

    protected void primeraRaiz(int v){
        visitados[v] = 1;
        ordenVisita++;

        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin(); l.siguiente()){
            Adyacente a = l.recuperar();
            int w = a.getDestino();
            if (visitados[w] == 0){
                primeraRaiz(w);
            }
        }
    }

    public int[] doblones(int v){
        int[] doblones = new int[numVertices()];
        final int INF = Integer.MAX_VALUE;
        for (int i = 0; i < doblones.length; i++) {
            doblones[i] = INF;
        }

        q = new ArrayCola<>();
        doblones[v] = 0;
        q.encolar(v);

        while (!q.esVacia()){
            int u = q.desencolar();

            ListaConPI<Adyacente> l = adyacentesDe(u);
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                Adyacente a = l.recuperar();
                int w = a.getDestino();
                if (doblones[w] == INF){
                    doblones[w] = doblones[u] + 1;
                    q.encolar(w);
                }
            }
        }

        return doblones;
    }
}