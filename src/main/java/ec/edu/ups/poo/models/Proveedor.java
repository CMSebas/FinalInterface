package ec.edu.ups.poo.models;

public class Proveedor extends Persona {
    private String ruc;
    private String empresa;

    public Proveedor() {}

    public Proveedor(int id, String nombre, String ruc, String empresa) {
        super(id, nombre);
        this.ruc = ruc;
        this.empresa = empresa;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }


    @Override
    public String toString() {
        return super.toString() +
                "Ruc: " + ruc +
                "|     Empresa=  " + empresa ;
    }
}
