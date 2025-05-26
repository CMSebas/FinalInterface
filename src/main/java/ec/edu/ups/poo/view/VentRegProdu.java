package ec.edu.ups.poo.view;

import ec.edu.ups.poo.controllers.Controller;
import ec.edu.ups.poo.models.Licencia;
import ec.edu.ups.poo.models.Producto;
import ec.edu.ups.poo.models.ProductoTangible;
import ec.edu.ups.poo.models.ServicioProfesional;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class VentRegProdu extends Frame implements ActionListener, ItemListener {

    private Ventana1 ventanaPrincipal;
    private Panel panelGeneral;
    private Panel panelTitulo;
    private Panel panelLabels;
    private Panel panelConPadding;
    private Panel panelConPadding2;
    private Panel panelConPadding3;
    private Panel panelBoton;

    private Label labelNumero1;
    private Label labelSeleccion;
    private Choice comboOpciones;

    private Button boton1;

    private Label labelNumero2;
    private Label labelNumero3;
    private Label labelNumero4;
    private Label labelNumero5;
    private Label labelNumero6;
    private Label labelNumero7;

    private TextField textField1;
    private TextField textField2;
    private TextField textField3;
    private TextField textField4;
    private TextField textField5;
    private TextField textField6;
    private TextField textField7;
    private TextField textField8;
    private Controller controller;



    public VentRegProdu(Ventana1 ventanaPrincipal,Controller controller) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.controller = controller;
        setTitle("Sistema de Gestion de Inventario");
        setSize(600, 420);
        setLocationRelativeTo(null);

        panelGeneral = new Panel(new BorderLayout());
        panelTitulo = new Panel(new GridLayout(2, 1));
        panelLabels = new Panel(new GridLayout(6, 2, 10, 10));

        panelConPadding = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelConPadding.add(panelLabels);


        panelConPadding2 = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelConPadding3 = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        boton1 = new Button("GUARDAR");
        boton1.addActionListener(this);
        panelConPadding2.add(boton1);

        labelNumero1 = new Label("Registro de Productos ");
        labelNumero1.setFont(new Font("Arial", Font.BOLD, 28));
        panelTitulo.add(labelNumero1);

        comboOpciones = new Choice();
        comboOpciones.add("Seleccione...");
        comboOpciones.add("SERVICIO PROFESIONAL");
        comboOpciones.add("LICENCIA");
        comboOpciones.add("PRODUCTO TANGIBLE");
        comboOpciones.addItemListener(this);
        panelTitulo.add(comboOpciones);
        panelConPadding3.add(panelTitulo);

        labelNumero2 = new Label("Nombre ");
        labelNumero3 = new Label("ID del Producto ");
        labelNumero4 = new Label("Cantidad de Servicios ");
        labelNumero5 = new Label("Tarifa ");
        labelNumero6 = new Label("Horas del Servicio ");
        labelNumero7 = new Label("Tipo del Producto ");

        textField1 = new TextField("", 10);
        textField2 = new TextField("", 10);
        textField3 = new TextField("", 10);
        textField4 = new TextField("", 10);
        textField5 = new TextField("", 10);
        textField6 = new TextField("", 10);
        textField7 = new TextField("", 10);

        panelConPadding.setVisible(false);

        panelGeneral.add(panelConPadding, BorderLayout.CENTER);
        panelGeneral.add(panelConPadding3, BorderLayout.NORTH);
        panelGeneral.add(panelConPadding2, BorderLayout.SOUTH);

        add(panelGeneral);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boton1) {
            String opcion = comboOpciones.getSelectedItem();
            String nombre = textField1.getText();
            int id = Integer.parseInt(textField2.getText());

            Producto producto = null;

            if (opcion.equals("SERVICIO PROFESIONAL")) {
                int cantidadServicios = Integer.parseInt(textField3.getText());
                int tarifa = Integer.parseInt(textField4.getText());
                int horas = Integer.parseInt(textField5.getText());
                String tipo = textField6.getText();

                producto = new ServicioProfesional(id, cantidadServicios, nombre, tarifa, horas, tipo);

            } else if (opcion.equals("LICENCIA")) {
                int cantidad = Integer.parseInt(textField3.getText());
                String descripcion = textField4.getText();
                int suscripcion = Integer.parseInt(textField5.getText());
                int meses = Integer.parseInt(textField6.getText());

                producto = new Licencia(id, cantidad, nombre, descripcion, suscripcion, meses);

            } else if (opcion.equals("PRODUCTO TANGIBLE")) {
                int cantidad = Integer.parseInt(textField3.getText());
                int precio = Integer.parseInt(textField4.getText());

                producto = new ProductoTangible(id, cantidad, nombre, precio);
            }

            if (producto != null) {
                Object[] opciones = {
                        "Crear nuevo proveedor",
                        "Asignar proveedor existente",
                        "No asociar"
                };

                int seleccion = JOptionPane.showOptionDialog(this,
                        "¿Deseas asociar un proveedor?",
                        "Asociar proveedor",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]);

                switch (seleccion) {
                    case 0: // Crear nuevo
                        VentRegPro ventRegPro = new VentRegPro(ventanaPrincipal, controller);
                        ventRegPro.setProductoAsociado(producto); // 💥 Aquí se pasa el producto
                        ventRegPro.setVisible(true);
                        this.setVisible(false);
                        break;

                    case 1: // Asignar proveedor existente
                        VentAsignarProveedor ventanaAsignar = new VentAsignarProveedor(ventanaPrincipal, controller, producto);
                        this.setVisible(false);
                        ventanaAsignar.setVisible(true);
                        break;

                    case 2: // No asociar
                    default:
                        controller.agregarProductoDesdeGUI(producto);
                        JOptionPane.showMessageDialog(this, "Producto guardado sin proveedor.");
                        this.setVisible(false);
                        ventanaPrincipal.setVisible(true);
                        break;
                }
            }

        }
    }



    @Override
    public void itemStateChanged(ItemEvent e) {
        String opcion = comboOpciones.getSelectedItem();
        panelLabels.removeAll();

        if (opcion.equals("SERVICIO PROFESIONAL")) {
            panelLabels.add(labelNumero2);
            panelLabels.add(textField1);
            panelLabels.add(labelNumero3);
            panelLabels.add(textField2);
            panelLabels.add(labelNumero4);
            panelLabels.add(textField3);
            panelLabels.add(labelNumero5);
            panelLabels.add(textField4);
            panelLabels.add(labelNumero6);
            panelLabels.add(textField5);
            panelLabels.add(labelNumero7);
            panelLabels.add(textField6);

        } else if (opcion.equals("LICENCIA")) {
            panelLabels.add(new Label("Nombre"));
            panelLabels.add(textField1);
            panelLabels.add(new Label("ID de Producto"));
            panelLabels.add(textField2);
            panelLabels.add(new Label("Cantidad de Licencias"));
            panelLabels.add(textField3);
            panelLabels.add(new Label("Descripción"));
            panelLabels.add(textField4);
            panelLabels.add(new Label("Suscripción"));
            panelLabels.add(textField5);
            panelLabels.add(new Label("Meses de Suscripción"));
            panelLabels.add(textField6);

        } else if (opcion.equals("PRODUCTO TANGIBLE")) {
            panelLabels.add(new Label("Nombre"));
            panelLabels.add(textField1);
            panelLabels.add(new Label("ID de Producto"));
            panelLabels.add(textField2);
            panelLabels.add(new Label("Cantidad"));
            panelLabels.add(textField3);
            panelLabels.add(new Label("Precio Unitario"));
            panelLabels.add(textField4);
            panelLabels.add(new Label("Proveedor"));
            panelLabels.add(textField5);
            panelLabels.add(new Label("Fecha de Ingreso"));
            panelLabels.add(textField6);
        }

        panelConPadding.setVisible(true);
        panelLabels.revalidate();
        panelLabels.repaint();
    }
}