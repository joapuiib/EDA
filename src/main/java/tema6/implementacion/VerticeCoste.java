package tema6.implementacion;

class VerticeCoste implements Comparable<VerticeCoste> {
    protected int codigo; // vértice al que se llega
    protected double coste; // coste de llegar a ese vértice
    public VerticeCoste(int codigo, double coste) {
        this.codigo = codigo; this.coste = coste;
    }
    public int compareTo(VerticeCoste v) {
        return Double.compare(coste, v.coste);
    }

    @Override
    public String toString() {
        return String.format("VC{%d, %.1f}", codigo, coste);
    }
}