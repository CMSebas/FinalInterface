package ec.edu.ups.poo.models;

public class Licencia extends Producto {

    private String descripcion;
    private int valSuscripcion;
    private int numMeses;
    public Licencia() {}


    public Licencia(int id, int cantidad, String nombre, String descripcion, int valSuscripcion,int numMeses) {
        super(id, cantidad, nombre);
        this.descripcion = descripcion;
        this.valSuscripcion = valSuscripcion;
        this.numMeses = numMeses;
    }

    @Override
    public double calcularSubtotal() {
        return (valSuscripcion*numMeses)*getCantidad();
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Descripcion=" + descripcion +
                " | Valor de la suscripcion mensual: " + valSuscripcion ;
    }


}
