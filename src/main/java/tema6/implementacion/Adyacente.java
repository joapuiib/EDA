package tema6.implementacion;

public class Adyacente {
    protected int destino;
    protected double peso;

    public Adyacente(int v, double p) {
        destino = v;
        peso = p;
    }

    public int getDestino() {
        return destino;
    }

    public double getPeso() {
        return peso;
    }

    public String toString() {
        return destino + "(" + peso + ")";
    }
}
