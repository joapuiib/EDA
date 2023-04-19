package tema4.ejercicios.colaprioridad;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema4.modelos.ColaPrioridad;

public class Problema3 {
    public static boolean cPLineal(ColaPrioridad<Double> cp, double epsilon){

        Double ant = cp.eliminarMin();
        while (!cp.esVacia()){
            Double e = cp.eliminarMin();

            double diff = e - ant;
            if(diff > epsilon){
                return false;
            }

            ant = e;
        }
        return true;
    }
}
