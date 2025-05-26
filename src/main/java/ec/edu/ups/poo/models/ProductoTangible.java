package ec.edu.ups.poo.models;

public class ProductoTangible extends Producto {
    private int precioUnitario;
    public ProductoTangible() {}


    public ProductoTangible(int id, int cantidad, String nombre, int precioUnitario) {
        super(id, cantidad, nombre);
        this.precioUnitario = precioUnitario;
    }



    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(int precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public double calcularSubtotal() {
        return precioUnitario*getCantidad();
    }

    @Override
    public String toString() {
        return super.toString() +
                "| Precio por unidad: " + precioUnitario
                ;
    }
}
