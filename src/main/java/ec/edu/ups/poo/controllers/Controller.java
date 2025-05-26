package ec.edu.ups.poo.controllers;

import ec.edu.ups.poo.enums.Cargo;
import ec.edu.ups.poo.enums.EstadoSolicitud;
import ec.edu.ups.poo.models.*;
import ec.edu.ups.poo.view.View;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private View view;
    List<Producto> productos = new ArrayList<>();
    List<Proveedor> proveedores = new ArrayList<>();
    List<SolicitudCompra> solicitudes = new ArrayList<>();
    List<Departamento> departamentos = new ArrayList<>();
    Proveedor pro=null;
    public Controller(View view) {
        this.view = view;
    }



    public void start() {//INICIA Y CONTROLA EL FLUJO DEL PROGRAMA
        boolean begin=true;
        while (begin) {
            int option=view.showMenu();
            switch(option) {
                case 1:
                    addProveedor();

                    break;
                case 2:
                    addProducto(null);

                    break;
                case 3:
                    registrarSolicitudCompra(ingresarEmpleado());
                    break;
                case 4:
                    mostrarProveedores();

                    break;
                case 5:
                    mostrarProductos();
                    break;
                case 6:
                    listarSolicitudes();
                    break;
                case 7:
                    buscarProvedor();
                    break;
                case 8:
                    buscarProducto();
                    break;
                case 9:
                    buscarSolicitud();
                    break;
                case 10:
                    estadoSolicitud();
                    break;
                case 11:
                    calcularTotalSolicitud();
                    break;
                    case 12:
                        begin=false;
                        break;
                        default:

            }

        }

    }

    public void  addProducto(Proveedor pro) {
        Scanner leer = new Scanner(System.in);
        System.out.println("1.Servicio Profesional ");
        System.out.println("2.Licencia ");
        System.out.println("3.Producto Tangible ");
        System.out.println("Elige");
        int opcion = leer.nextInt();

        Producto nuevoProducto = null;
        if (opcion == 1) {
            String nombre=view.ingresarNombre();
            int id=view.ingresarId();
            int cantidad=view.ingresarCantidad(opcion);
            int tarifa=view.ingresarTarifa();
            int horas=view.ingresarHoras();
            String tipo=view.ingresarTipo();
            nuevoProducto=new ServicioProfesional(id,cantidad,nombre,tarifa,horas,tipo);




        } else if (opcion == 2) {
            String nombre=view.ingresarNombre();
            int id=view.ingresarId();
            int cantidad=view.ingresarCantidad(opcion);
            String descrpcion=view.ingresarDescripcion();
            int suscipcion=view.ingresarSuscipcion();
            int numMeses=view.ingresarNumMeses();
            nuevoProducto=new Licencia(id,cantidad,nombre,descrpcion,suscipcion,numMeses);



        } else if (opcion == 3) {
            String nombre=view.ingresarNombre();
            int id=view.ingresarId();
            int cantidad=view.ingresarCantidad(opcion);
            int precioUnitario=view.ingresarPrecioUnitario();
            nuevoProducto=new ProductoTangible(id,cantidad,nombre,precioUnitario);



        }


        if (nuevoProducto!=null) {
            if(pro!= null){
                nuevoProducto.agregarProveedor(pro);
                System.out.println("Proveedor asociado automáticamente.");
            }else{
                System.out.println("¿Deseas?");
                System.out.println("1. Crear nuevo proveedor");
                System.out.println("2. Asignar proveedor existente");
                System.out.println("3. No asociar");
                int opcion3 = leer.nextInt();
                leer.nextLine();
                if (opcion3 == 1) {
                    if (nuevoProducto != null) {
                        nuevoProducto.agregarProveedor(addProvedorProducto());

                    }
                }else if (opcion3 == 2) {
                    if (nuevoProducto == null) {
                        System.out.println("No se puede agregar el proveedor");
                    }else {
                        System.out.println("Proveedores disponibles:");
                        for (Proveedor proveedor : proveedores) {
                            System.out.println(proveedor.getId() + " - " + proveedor.getNombre());
                        }
                        System.out.println("Ingrese el ID del proveedor que desea asociar:");
                        int idProveedor = leer.nextInt();
                        leer.nextLine();
                        Proveedor proveedorSeleccionado = null;
                        for (Proveedor p : proveedores) {
                            if (p.getId() == idProveedor) {
                                proveedorSeleccionado = p;
                                break;
                            }
                        }
                        nuevoProducto.agregarProveedor(proveedorSeleccionado);

                    }
                }else if (opcion3 == 3) {
                    System.out.println("Producto creado sin proveedor asociado.");

                }

            }
            productos.add(nuevoProducto);
        }








    }

    public void addProveedor() {
        Scanner leer = new Scanner(System.in);
        String nomProvee=view.ingresarNombreProveedor();
        int idProvee=view.ingresarIdProveedor();
        String ruc=view.ingresarRuc();
        String empresa=view.ingresarEmpresa();
        Proveedor nuevoProveedor=new Proveedor(idProvee,nomProvee,ruc,empresa);
        proveedores.add(nuevoProveedor);
        System.out.println("Deseas crear un prodcuto para este proveedor?");
        System.out.println("1.Si");
        System.out.println("2.No");
        int opcion=leer.nextInt();
        if (opcion == 1) {
            addProducto(nuevoProveedor);
        }




    }

    public void agregarProveedorDesdeGUI(Proveedor proveedor) {
        proveedores.add(proveedor);
        System.out.println("Proveedor agregado desde GUI: " + proveedor);
    }

    public Proveedor addProvedorProducto() {
        Scanner leer = new Scanner(System.in);
        String nomProvee=view.ingresarNombreProveedor();
        int idProvee=view.ingresarIdProveedor();
        String ruc=view.ingresarRuc();
        String empresa=view.ingresarEmpresa();
        Proveedor nuevoProveedor=new Proveedor(idProvee,nomProvee,ruc,empresa);
        proveedores.add(nuevoProveedor);
        return pro=nuevoProveedor;
    }

    public Proveedor buscarProveedorPorID(int id) {
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getId() == id) {
                return proveedor;
            }
        }
        return null; // No encontrado
    }

    public boolean asignarProveedorAProducto(int idProveedor) {
        Proveedor proveedorSeleccionado = null;

        for (Proveedor p : proveedores) {
            if (p.getId() == idProveedor) {
                proveedorSeleccionado = p;
                break;
            }
        }

        if (proveedorSeleccionado != null) {
            if (!productos.isEmpty()) {
                Producto producto = null;
                for (Producto p : productos) {
                    producto = p; // al final quedará el último
                }

                if (producto != null) {
                    producto.agregarProveedor(proveedorSeleccionado);
                    return true;
                }
            }
        }

        return false;
    }
    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void agregarProductoDesdeGUI(Producto producto) {
        productos.add(producto);
        System.out.println("Producto agregado desde GUI: " + producto);
    }
    public List<Producto> getProductos() {
        return productos;
    }



    public void mostrarProductos() {
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    public void mostrarProveedores() {
        for (Proveedor proveedor : proveedores) {
            System.out.println(proveedor);
        }
    }

    public void buscarProducto() {
        Scanner leer = new Scanner(System.in);
        System.out.println("Productos disponibles:");
        for (Producto producto : productos) {
            System.out.println(producto.getId() + " - " + producto.getNombre());
        }
        System.out.println("Ingrese el ID del producto que desea buscar:");
        int idProdcuto = leer.nextInt();
        leer.nextLine();
        Producto productoSeleccionado = null;
        for (Producto produc : productos) {
            if (produc.getId() == idProdcuto) {
                productoSeleccionado = produc;
                System.out.println(productoSeleccionado);
                break;
            }
        }
    }

    public void buscarProvedor() {
        Scanner leer = new Scanner(System.in);
        System.out.println("Provedores disponibles:");
        for (Proveedor proveedor : proveedores) {
            System.out.println(proveedor.getId() + " - " + proveedor.getNombre());
        }
        System.out.println("Ingrese el ID del proveedor que desea buscar:");
        int idProvedor = leer.nextInt();
        leer.nextLine();
        Proveedor provedorSeleccionado = null;
        for (Proveedor provee : proveedores) {
            if (provee.getId() == idProvedor) {
                provedorSeleccionado = provee;
                System.out.println(provedorSeleccionado);
                break;
            }else {
                System.out.println("No se encontro el proveedor");
            }
        }
    }

    public void registrarSolicitudCompra(Empleado empleado) {
        Scanner leer = new Scanner(System.in);

        Empleado solicitante = empleado;


        SolicitudCompra nuevaSolicitud = new SolicitudCompra(solicitante);
        empleado.agregarSolicitud(nuevaSolicitud);

        boolean agregarMasDetalles = true;
        while (agregarMasDetalles) {

            System.out.println("Productos disponibles:");
            for (Producto producto : productos) {
                System.out.println(producto.getId() + " - " + producto.getNombre());
            }


            System.out.println("Ingrese el ID del producto que desea agregar:");
            int idProducto = leer.nextInt();
            leer.nextLine();

            Producto productoSeleccionado = null;
            for (Producto p : productos) {
                if (p.getId() == idProducto) {
                    productoSeleccionado = p;
                    break;
                }
            }

            if (productoSeleccionado != null) {

                System.out.println("Ingrese observaciones:");
                String observaciones = leer.nextLine();

                nuevaSolicitud.agregarDetalle(productoSeleccionado, observaciones);
            } else {
                System.out.println("Producto no encontrado.");
            }

            System.out.println("¿Desea agregar otro producto?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            int opcion = leer.nextInt();
            agregarMasDetalles = (opcion == 1);
        }


        solicitudes.add(nuevaSolicitud);

        System.out.println("Solicitud creada exitosamente:");
        System.out.println(nuevaSolicitud);
    }


    public Empleado ingresarEmpleado() {
        Scanner leer = new Scanner(System.in);
        String nombreEmpleado =view.ingresaNombreEmpleado();
        int idEmpleado = view.ingresaIdEmpleado();
        Cargo cargo=view.ingresaCargoEmpleado();

        String nombreDepto = view.ingresarDepartamento();
        Departamento departamento = new Departamento(nombreDepto);

        System.out.println("Nuevo departamento creado: " + nombreDepto);


        Empleado empleado = new Empleado(idEmpleado, nombreEmpleado, cargo, departamento);
        departamento.agregarEmpleado(empleado);

        return empleado;


    }

    public void listarSolicitudes() {
        System.out.println("\nSolicitudes registradas:");
        for (SolicitudCompra solicitud : solicitudes) {
            System.out.println("-----------------------------------");
            System.out.println(solicitud);
        }
    }

    public void buscarSolicitud() {
        int num=view.numSolitud();
        int indice = num - 1;

        if (indice >= 0 && indice < solicitudes.size()) {
            SolicitudCompra solicitudEncontrada = solicitudes.get(indice);
            System.out.println("Solicitud encontrada:");
            System.out.println("-----------------------------------");
            System.out.println(solicitudEncontrada);
            System.out.println("-----------------------------------");
        } else {
            System.out.println("Número de solicitud no válido.");
        }


    }

    public void estadoSolicitud() {
        Scanner leer = new Scanner(System.in);
        System.out.println("\nSolicitudes registradas:");
        for (SolicitudCompra solicitud : solicitudes) {
            System.out.println("-----------------------------------");
            System.out.println("Solicitante: " + solicitud.getSolicitante().getNombre()+"Estado: " + solicitud.getEstadoSolicitud());
            System.out.println("-----------------------------------");

        }
        int num=view.numSolitud();
        int indice = num - 1;
        if (indice >= 0 && indice < solicitudes.size()) {
            SolicitudCompra solicitudEncontrada = solicitudes.get(indice);
            System.out.println("Deseas aprobar o desaprobar");
            System.out.println("1.Aprobar");
            System.out.println("2.Desaprobar");
            int opcion = leer.nextInt();
            if (opcion == 1) {
                solicitudEncontrada.setEstadoSolicitud(EstadoSolicitud.APROBADA);
            }else if (opcion == 2) {
                solicitudEncontrada.setEstadoSolicitud(EstadoSolicitud.RECHAZADA);
            }else {
                solicitudEncontrada.setEstadoSolicitud(EstadoSolicitud.EN_REVISION);
            }


        }

    }

    public void calcularTotalSolicitud() {
        Scanner leer = new Scanner(System.in);

        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes registradas.");
            return;
        }

        System.out.println("\nSolicitudes registradas:");
        for (SolicitudCompra solicitud : solicitudes) {
            System.out.println("-----------------------------------");
            System.out.println(solicitud);
        }

        int num = view.numSolitud();
        int indice = num - 1;

        if (indice >= 0 && indice < solicitudes.size()) {
            SolicitudCompra solicitudSeleccionada = solicitudes.get(indice);
            double total = solicitudSeleccionada.calcularTotal();
            System.out.println("-----------------------------------");
            System.out.println("Total de la solicitud de " + solicitudSeleccionada.getSolicitante().getNombre() + ": $" + total);
            System.out.println("-----------------------------------");
        } else {
            System.out.println("Número de solicitud inválido.");
        }
    }



}
