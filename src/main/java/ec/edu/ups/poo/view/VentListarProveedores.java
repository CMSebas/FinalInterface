package ec.edu.ups.poo.view;

import ec.edu.ups.poo.controllers.Controller;
import ec.edu.ups.poo.models.Proveedor;

import java.awt.*;
import java.awt.event.*;

public class VentListarProveedores extends Frame {
    private Ventana1 ventanaPrincipal;
    private Controller controller;
    private Panel panelConPadding;
    private Label titulo;

    public VentListarProveedores(Controller controller, Ventana1 ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.controller = controller;

        setTitle("Listado de Proveedores");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        // Título
        titulo = new Label("LISTADO DE PROVEEDORES", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        // Área de texto para mostrar los proveedores
        TextArea textArea = new TextArea();

        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.ITALIC, 13));

        for (Proveedor p : controller.getProveedores()) {
            textArea.append(p.toString() + "\n\n");
        }


        panelConPadding = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelConPadding.add(textArea);
        add(panelConPadding, BorderLayout.CENTER);

        // Botón de volver
        Button botonVolver = new Button("Volver");
        botonVolver.addActionListener(e -> {
            this.setVisible(false);
            ventanaPrincipal.setVisible(true);
        });

        Panel panelBoton = new Panel();
        panelBoton.add(botonVolver);
        add(panelBoton, BorderLayout.SOUTH);

        // Cierra solo esta ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ventanaPrincipal.setVisible(true);
                dispose();
            }
        });
    }
}