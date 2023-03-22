package tema3.ejercicios;

import tema1.modelos.ListaConPI;
import tema3.implementacion.TablaHash;
import tema3.modelos.Map;

import java.util.Objects;

public class Parcial2016MapaPosicionPuntos {
    public class Posicion {
        private int x;
        private int y;

        public Posicion(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        // Completar
        public boolean equals(Object other) {
            if (!(other instanceof Posicion))
                return false;
            else {
                Posicion that = (Posicion) other;
                return that.x == this.x && that.y == this.y;
            }
        }

        @Override
        public int hashCode() {
            // return (x + "," + y).hashCode();
            return Objects.hash(x, y);
        }
    }

    public int ganador(Map<Posicion, Integer> mJ1, Map<Posicion, Integer> mJ2, int penalty, ListaConPI<Posicion> optima) {
        int puntsJ1 = 0;
        int puntsJ2 = 0;

        for (optima.inicio(); !optima.esFin(); optima.siguiente()){
            Posicion p = optima.recuperar();

            Integer aux = mJ1.recuperar(p);
            if(aux == null)
                puntsJ1 -= penalty;
            else
                puntsJ1 += aux;

            aux = mJ2.recuperar(p);
            puntsJ2 = puntsJ2 + (aux == null ? -penalty : aux);
        }

        if(puntsJ1 == puntsJ2)
            return 0;
        else if(puntsJ1 > puntsJ2)
            return 1;
        else
            return 2;
    }
}