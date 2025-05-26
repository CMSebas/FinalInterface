package ec.edu.ups.poo;

import ec.edu.ups.poo.controllers.Controller;
import ec.edu.ups.poo.controllers.Ventana1Controller;
import ec.edu.ups.poo.models.*;
import ec.edu.ups.poo.enums.Cargo;
import ec.edu.ups.poo.controllers.Controller;
import ec.edu.ups.poo.view.VentAsignarProveedor;
import ec.edu.ups.poo.view.Ventana1;
import ec.edu.ups.poo.view.View;

import java.io.Console;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new View()); // Puedes pasar null si no usas consola
        Ventana1 ventana1 = new Ventana1(controller);

        ventana1.addWindowListener(new Ventana1Controller());
        ventana1.setVisible(true);



    }
}