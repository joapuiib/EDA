package tema3.ejercicios;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema3.implementacion.TablaHash;
import tema3.modelos.Map;

public class Rec2022DiferenciaSalario {

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

    public static ListaConPI<Empleado> diferenciaSalario(ListaConPI<Empleado> l1, ListaConPI<Empleado> l2){
        ListaConPI<Empleado> l3 = new LEGListaConPI<>();
        Map<String, Double> map = new TablaHash<>(l1.talla());

        for(l1.inicio(); !l1.esFin(); l1.siguiente()){
            Empleado e = l1.recuperar();
            String nombre = e.getNombre();
            double salario = e.getSalario();
            map.insertar(nombre, salario);
        }

        for(l2.inicio(); !l2.esFin(); l2.siguiente()){
            Empleado e = l2.recuperar();
            String nombre = e.getNombre();
            double salario = e.getSalario();
            Double salarioAnterior = map.recuperar(nombre); // O(1)

            // Si salarioAnterior == null significa que no hay un salario para ese nombre
            if(salarioAnterior != null){
                double diferencia = salario - salarioAnterior;
                if(diferencia != 0)
                    l3.insertar(new Empleado(nombre, diferencia));
            }
        }
        return l3;
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
