package ec.edu.ups.poo.view;

import ec.edu.ups.poo.controllers.Controller;
import ec.edu.ups.poo.models.Producto;
import ec.edu.ups.poo.models.Proveedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentAsignarProveedor extends Frame {

    private Ventana1 ventanaPrincipal;
    private Controller controller;
    private Producto productoAsociado;

    private TextField campoIDProveedor;
    private Label mensajeResultado;
    private Panel panelConPadding;
    private Label titulo;
    private Label labelID;
    private Panel panelCentral;
    private Panel panelBotones;
    private Panel panelTexto;
    private Panel panelIngreso;
    private Button botonAsignar;
    private Button botonVolver;
    private TextArea textArea;

    public VentAsignarProveedor(Ventana1 ventanaPrincipal, Controller controller, Producto productoAsociado) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.controller = controller;
        this.productoAsociado = productoAsociado;

        setTitle("Asignar Proveedor");
        setSize(650, 320);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        titulo = new Label("ASIGNAR PROVEEDOR EXISTENTE", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(titulo, BorderLayout.NORTH);

        

        panelConPadding = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelCentral = new Panel(new BorderLayout(10,10));

        textArea = new TextArea(5, 5);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 13));

        for (Proveedor p : controller.getProveedores()) {
            textArea.append(p.toString() + "\n");
        }

        panelTexto = new Panel(new BorderLayout());
        panelTexto.add(new Label("Proveedores registrados:", Label.LEFT), BorderLayout.NORTH);
        panelTexto.add(textArea, BorderLayout.CENTER);


        
        panelIngreso = new Panel(new GridLayout(3, 1, 5, 5));
        panelIngreso.setPreferredSize(new Dimension(580, 100));
        Label labelID = new Label("Ingrese ID del proveedor:", Label.CENTER);
        campoIDProveedor = new TextField(20);
        mensajeResultado = new Label("", Label.CENTER);

        panelIngreso.add(labelID);
        panelIngreso.add(campoIDProveedor);
        panelIngreso.add(mensajeResultado);

        panelCentral.add(panelTexto, BorderLayout.NORTH);
        panelCentral.add(panelIngreso, BorderLayout.CENTER);
        panelConPadding.add(panelCentral);

        add(panelConPadding, BorderLayout.CENTER);

        
        botonAsignar = new Button("Asignar");
        botonAsignar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = campoIDProveedor.getText().trim();

                if (!texto.matches("\\d+")) {
                    JOptionPane.showMessageDialog(VentAsignarProveedor.this, "Ingresa un número válido.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int id = Integer.parseInt(texto);
                Proveedor proveedorSeleccionado = controller.buscarProveedorPorID(id);

                if (proveedorSeleccionado != null && productoAsociado != null) {
                    productoAsociado.agregarProveedor(proveedorSeleccionado);
                    controller.agregarProductoDesdeGUI(productoAsociado);

                    JOptionPane.showMessageDialog(VentAsignarProveedor.this, "✅ Proveedor asignado correctamente.",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    setVisible(false);
                    ventanaPrincipal.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(VentAsignarProveedor.this, "❌ No se encontró proveedor con ese ID.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        
        botonVolver = new Button("Volver");
        botonVolver.addActionListener(e -> {
            this.setVisible(false);
            ventanaPrincipal.setVisible(true);
        });

        panelBotones = new Panel(new FlowLayout());
        panelBotones.add(botonAsignar);
        panelBotones.add(botonVolver);
        add(panelBotones, BorderLayout.SOUTH);

        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ventanaPrincipal.setVisible(true);
                dispose();
            }
        });
    }


}
