package ec.edu.ups.poo.models;

import ec.edu.ups.poo.enums.EstadoSolicitud;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class SolicitudCompra {
    private EstadoSolicitud estadoSolicitud;
    private GregorianCalendar fechaSolicitud;
    private List<DetalleCompra> detalles;
    private Empleado solicitante;

    public EstadoSolicitud getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public List<DetalleCompra> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCompra> detalles) {
        this.detalles = detalles;
    }

    public Empleado getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Empleado solicitante) {
        this.solicitante = solicitante;
    }

    public SolicitudCompra(Empleado solicitante) {
        this.estadoSolicitud = EstadoSolicitud.SOLICITADA; // Estado inicial
        this.fechaSolicitud = new GregorianCalendar(); // Instancia de la fecha actual
        this.detalles = new ArrayList<>();
        this.solicitante = solicitante;
    }

    public void agregarDetalle(Producto producto, String observaciones) {
        DetalleCompra detalle = new DetalleCompra(observaciones);
        detalle.agregarProducto(producto);  // Solo agregamos el producto específico al detalle
        detalles.add(detalle);
    }

    public double calcularTotal() {
        double total = 0;
        for (DetalleCompra detalle : detalles) {
            total += detalle.calcularSubtotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Solicitud"  +
                "estado de la solicitud:" + estadoSolicitud +
                ", fecha de la solitud emitida=" + fechaSolicitud.getTime() +
                ", detalles:" + detalles +
                ", solicitante:" + solicitante ;
    }
}
