package ec.edu.ups.poo.models;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private String nombre;
    private List<Empleado> empleados;

    public Departamento(String nombre ) {
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "nombre del producto='" + nombre + '\'' +
                ", empleados=" + empleados +
                '}';
    }
}
