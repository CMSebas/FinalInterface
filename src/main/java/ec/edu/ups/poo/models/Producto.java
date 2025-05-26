package ec.edu.ups.poo.models;
import java.util.ArrayList;
import java.util.List;
import  ec.edu.ups.poo.models.Proveedor;

public abstract class Producto implements Calculable {
    private int id;
    private String nombre;
    private int cantidad;
    private List<Proveedor> provedores;


    public Producto() {
    }

    public Producto(int id, int cantidad, String nombre) {
        this.id = id;

        this.cantidad = cantidad;
        this.nombre = nombre;
        this.provedores = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarProveedor(Proveedor proveedor) {
        provedores.add(proveedor);
    }

    public abstract double calcularSubtotal();

    @Override
    public double calcularSubTotalConIva() {
        return calcularSubtotal()*1.12;
    }

    @Override
    public String toString() {
        return
                "Id del producto :" + id +
                " | Nombre del producto :" + nombre +
                " | Cantidad solicitada: " + cantidad +
                " | Provedores del producto: " + provedores;
    }


}
