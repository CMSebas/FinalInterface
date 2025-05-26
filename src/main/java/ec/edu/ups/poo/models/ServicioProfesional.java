package ec.edu.ups.poo.models;

public class ServicioProfesional extends Producto {


    private int tarifaFija;
    private  int horasNecesarias;
    private  String tipoServicioProfesional;
    public ServicioProfesional() {}

    public ServicioProfesional(int id, int cantidad, String nombre, int tarifaFija,int horasNecesarias,String tipoServicioProfesional) {
        super(id, cantidad, nombre);
        this.tarifaFija = tarifaFija;
        this.horasNecesarias = horasNecesarias;
        this.tipoServicioProfesional = tipoServicioProfesional;
    }

    public String getTipoServicioProfesional() {
        return tipoServicioProfesional;
    }

    public void setTipoServicioProfesional(String tipoServicioProfesional) {
        this.tipoServicioProfesional = tipoServicioProfesional;
    }

    public int getHorasNecesarias() {
        return horasNecesarias;
    }

    public void setHorasNecesarias(int horasNecesarias) {
        this.horasNecesarias = horasNecesarias;
    }

    public ServicioProfesional(int tarifaFija) {
        this.tarifaFija = tarifaFija;
    }

    public int getTarifaFija() {
        return tarifaFija;
    }

    public void setTarifaFija(int tarifaFija) {
        this.tarifaFija = tarifaFija;
    }

    @Override
    public double calcularSubtotal() {
        return (tarifaFija*horasNecesarias)*getCantidad();
    }




    @Override
    public String toString() {
        return super.toString()+
                " |  Tarifa fija: " + tarifaFija +
                " | Horas necesarias:" + horasNecesarias +
                " | Tipo de Servicio Profesional: " + tipoServicioProfesional ;
    }
}
