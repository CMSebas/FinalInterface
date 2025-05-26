package ec.edu.ups.poo.view;
import ec.edu.ups.poo.controllers.Controller;
import ec.edu.ups.poo.enums.Cargo;
import ec.edu.ups.poo.models.Producto;
import java.util.Scanner;
public class View {
    private Scanner leer = new Scanner(System.in);
    public int showMenu() {
        Scanner leer = new Scanner(System.in);
        int option;
        do {
            System.out.println("\n--------------MENÚ PRINCIPAL -------");
            System.out.println("1. Ingresar provedores");
            System.out.println("2. Ingresar productos");
            System.out.println("3. Registrar solitud de compras");
            System.out.println("4. Listar proveedores");
            System.out.println("5. Listar productos");
            System.out.println("6. Listar solitud de compras");
            System.out.println("7. Buscar provedor por Id");
            System.out.println("8. Buscar producto por Id");
            System.out.println("9. Buscar solitud por numero");
            System.out.println("10. Aprobar/Rechazar Solicitud");
            System.out.println("11. Calcular total de una solicitud");
            System.out.println("12. Salir");
            System.out.print("Seleccione una opción (1-12): ");
            option = leer.nextInt();
        } while (option < 1 || option > 12);
        return option;
    }
    public int ingresarId() {
        System.out.println("Ingrese el id del producto: ");
        int id = leer.nextInt();
        return id;
    }
    public String ingresarNombre() {
        System.out.println("Ingrese el nombre: ");
        String nombre = leer.nextLine();
        return nombre;
    }
    public int ingresarCantidad(int opcion) {
        if(opcion==1){
            System.out.println("Ingrese la cantidad de servicios necesarios: ");
        }else if(opcion==2){
            System.out.println("Ingrese la cantidad de licencias: ");
        }else{
            System.out.println("Ingrese la cantidad del producto: ");
        }
        int cantidad = leer.nextInt();
        return cantidad;
    }
    public int ingresarTarifa() {
        System.out.println("Ingrese el tarifa: ");
        int tarifa = leer.nextInt();
        leer.nextLine();
        return tarifa;
    }
    public int ingresarHoras() {
        System.out.println("Ingrese las horas del servicio: ");
        int horas = leer.nextInt();
        leer.nextLine();
        return horas;
    }
    public String ingresarTipo() {
        System.out.println("Ingrese el tipo del servicio: ");
        String tipo = leer.next();
        leer.nextLine();
        return tipo;
    }
    public String ingresarDescripcion() {
        System.out.println("Ingrese la descripcion: ");
        String descripcion = leer.next();
        leer.nextLine();
        return descripcion;
    }
    public int ingresarSuscipcion() {
        System.out.println("Ingrese la suscipcion mensual: ");
        int suscripcion = leer.nextInt();
        leer.nextLine();
        return suscripcion;
    }
    public int ingresarNumMeses() {
        System.out.println("Ingrese los meses necesarios de la Licencia: ");
        int numMeses = leer.nextInt();
        leer.nextLine();
        return numMeses;
    }
    public int ingresarPrecioUnitario() {
        System.out.println("Ingrese el precio unitario: ");
        int precioUnitario = leer.nextInt();
        leer.nextLine();
        return precioUnitario;
    }
    public String ingresarNombreProveedor() {
        System.out.println("Ingrese el nombre del proveedor: ");
        String nombreProveedor = leer.nextLine();
        return nombreProveedor;
    }
    public int ingresarIdProveedor() {
        System.out.println("Ingrese el id del proveedor: ");
        int idProveedor = leer.nextInt();
        leer.nextLine();
        return idProveedor;
    }
    public String ingresarRuc() {
        System.out.println("Ingrese ruc del proveedor: ");
        String ruc = leer.next();
        leer.nextLine();
        return ruc;
    }
    public String ingresarEmpresa() {
        System.out.println("Ingrese la empresa del proveedor: ");
        String empresa = leer.next();
        leer.nextLine();
        return empresa;
    }
    public String ingresaNombreEmpleado() {
        System.out.println("Ingrese su nombre como Empleado : ");
        String nombreEmpleado = leer.next();
        leer.nextLine();
        return nombreEmpleado;
    }
    public int ingresaIdEmpleado() {
        System.out.println("Ingrese su id: ");
        int idEmpleado = leer.nextInt();
        leer.nextLine();
        return idEmpleado;
    }
    public Cargo ingresaCargoEmpleado() {
        System.out.println("Cual es su cargo: ");
        System.out.println("1.Jefe");
        System.out.println("2.Secretario");
        System.out.println("3.Asistente");
        int opcion = leer.nextInt();
        if(opcion==1){
            return Cargo.JEFE;
        }else if (opcion==2){
            return Cargo.SECRETARIO;
        }else if (opcion==3){
            return Cargo.ASISTENTE;
        }else {
            return null;
        }
    }
    public String ingresarDepartamento() {
        leer.nextLine();
        System.out.println("Ingrese el nombre del departamento:");
        String nombreDepto = leer.nextLine();
        return nombreDepto;
    }
    public int numSolitud(){
        System.out.println("Ingrese el número de la solicitud que desea buscar (empezando desde 1): ");
        int numeroSolicitud = leer.nextInt();
        leer.nextLine();
        return numeroSolicitud;
    }
}