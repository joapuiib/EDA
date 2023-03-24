package tema1.ejercicios;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema3.implementacion.TablaHash;
import tema3.modelos.Map;

public class Rec2021DiferenciaSalarioOrdenado {

    public static class Empleado {
        private String nombre;
        private double salario;

        public Empleado(String nombre, double salario) {
            this.nombre = nombre;
            this.salario = salario;
        }

        public String getNombre() {
            return nombre;
        }

        public double getSalario() {
            return salario;
        }

        @Override
        public String toString() {
            return String.format("(%s, %.2f)", nombre, salario);
        }
    }

    /**
     * l1 y l2 estan ordenador por nombre
     * @param l1
     * @param l2
     * @return
     */
    public static ListaConPI<Empleado> diferenciaSalario(ListaConPI<Empleado> l1, ListaConPI<Empleado> l2){
        ListaConPI<Empleado> l3 = new LEGListaConPI<>();
        return null;
    }

    public static void main(String[] args) {
        ListaConPI<Empleado> l1 = new LEGListaConPI<>();
        l1.insertar(new Empleado("Pepe", 24000));
        l1.insertar(new Empleado("Ana", 32000));
        l1.insertar(new Empleado("Juan", 23000));
        l1.insertar(new Empleado("Paco", 50000));
        l1.insertar(new Empleado("Jorge", 36000));
        l1.insertar(new Empleado("Lola", 42000));

        ListaConPI<Empleado> l2 = new LEGListaConPI<>();
        l2.insertar(new Empleado("Ana", 31500));
        l2.insertar(new Empleado("Lola", 43800));
        l2.insertar(new Empleado("Pepe", 25000));
        l2.insertar(new Empleado("Jorge", 36300));
        l2.insertar(new Empleado("Luis", 19000));
        l2.insertar(new Empleado("Paco", 50000));


        ListaConPI<Empleado> l3 = diferenciaSalario(l1, l2);
        System.out.println(l3);
    }
}
