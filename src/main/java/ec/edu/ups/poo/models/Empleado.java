package ec.edu.ups.poo.models;

import ec.edu.ups.poo.enums.Cargo;

import java.util.ArrayList;
import java.util.List;

public class Empleado extends Persona {
    private Cargo cargo;
    private Departamento departamento;
    private List<SolicitudCompra> solicitudes;

    public Empleado() {
        this.solicitudes = new ArrayList<>();
    }

    public Empleado(int id, String nombre, Cargo cargo, Departamento departamento) {
        super(id, nombre);
        this.cargo = cargo;
        this.departamento = departamento;
        this.solicitudes = new ArrayList<>(); // Muy importante inicializar aquí también
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<SolicitudCompra> getSolicitudes() {
        return solicitudes;
    }

    public void agregarSolicitud(SolicitudCompra solicitud) {
        this.solicitudes.add(solicitud);
    }

    @Override
    public String toString() {
        return
                "Cargo del empleado:" + cargo +
                ", nombre del departamento:" + (departamento != null ? departamento.getNombre() : "Sin departamento") +
                ", solicitudes:" + solicitudes.size() ;
    }
}