package ec.edu.ups.poo.models;

import ec.edu.ups.poo.models.Producto;

import java.util.ArrayList;
import java.util.List;

public class DetalleCompra {
    private List<Producto> productos;

    private String observaciones;

    public DetalleCompra(String observaciones) {
        this.productos = new ArrayList<>();
        this.observaciones = observaciones;

    }

    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (Producto producto : productos) {
            subtotal += producto.calcularSubTotalConIva();
        }
        return subtotal;
    }

    @Override
    public String toString() {
        return "DetalleCompra{" +
                "producto=" + productos +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}