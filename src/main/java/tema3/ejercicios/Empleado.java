package tema3.ejercicios;

public class Empleado {
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
