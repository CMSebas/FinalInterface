package ec.edu.ups.poo.view;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ec.edu.ups.poo.controllers.Controller;
import ec.edu.ups.poo.models.Producto;
import ec.edu.ups.poo.models.Proveedor;

import javax.swing.*;

public class VentRegPro extends Frame implements ActionListener {
    private Ventana1 ventanaPrincipal;
    private Controller controller;
    private Panel panelGeneral;
    private Panel panelTitulo;
    private Panel panelLabels;
    private Panel panelConPadding;
    private Panel panelConPadding2;
    private Panel panelBoton;

    private Label labelNumero1;

    private Button boton1;

    private Label labelNumero2;
    private Label labelNumero3;
    private Label labelNumero4;
    private Label labelNumero5;
    private TextField textField1;
    private TextField textField2;
    private TextField textField3;
    private TextField textField4;
    private Producto productoAsociado;


    public void setProductoAsociado(Producto productoAsociado) {
        this.productoAsociado = productoAsociado;
    }


    public VentRegPro(Ventana1 ventanaPrincipal,Controller controller) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.controller = controller;
        this.productoAsociado = productoAsociado;
        setTitle("Calculadora del Sabado");
        setSize(600, 250);
        setLocationRelativeTo(null);

        panelGeneral = new Panel(new BorderLayout());
        panelTitulo = new Panel();
        panelLabels = new Panel(new GridLayout(2, 4, 10, 0));
        panelBoton = new Panel(new FlowLayout());

        panelConPadding = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelConPadding.add(panelLabels);

        panelConPadding2 = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelConPadding2.add(panelBoton);


        labelNumero1 = new Label("Registro de provedor ");

        labelNumero1.setFont(new Font("Arial", Font.BOLD, 28));

        panelTitulo.add(labelNumero1);


        labelNumero2 = new Label("Nombre ");
        labelNumero3 = new Label("Cedula ");
        labelNumero4 = new Label("Nombre de empresa ");
        labelNumero5 = new Label("Ruc ");
        textField1 = new TextField("", 10);
        textField2 = new TextField("", 10);
        textField3 = new TextField("", 10);
        textField4 = new TextField("", 10);
        panelLabels.add(labelNumero2);
        panelLabels.add(labelNumero3);
        panelLabels.add(labelNumero4);
        panelLabels.add(labelNumero5);
        panelLabels.add(textField1);
        panelLabels.add(textField2);
        panelLabels.add(textField3);
        panelLabels.add(textField4);





        panelGeneral.add(panelConPadding, BorderLayout.CENTER);
        panelGeneral.add(panelTitulo, BorderLayout.NORTH);
        panelGeneral.add(panelConPadding2, BorderLayout.SOUTH);
        boton1 = new Button("GUARDAR");
        boton1.addActionListener(this);

        panelBoton.add(boton1);

        add(panelGeneral);



    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == boton1) {
            String nombre = textField1.getText();
            int id = Integer.parseInt(textField2.getText());
            String empresa = textField3.getText();
            String ruc = textField4.getText();

            Proveedor proveedor = new Proveedor(id, nombre, ruc, empresa);
            if (productoAsociado != null) {
                productoAsociado.agregarProveedor(proveedor);
                controller.agregarProductoDesdeGUI(productoAsociado);
            }
            controller.agregarProveedorDesdeGUI(proveedor);

            JOptionPane.showMessageDialog(null, "Datos guardados exitosamente.");

            // Limpiar campos y volver a la ventana principal
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
            this.setVisible(false);
            ventanaPrincipal.setVisible(true);
        }
    }

    private void showDialog(String mensaje) {
        Dialog dialog = new Dialog(this, "Mensaje", true);
        dialog.setLayout(new FlowLayout());
        dialog.add(new Label(mensaje));
        Button ok = new Button("OK");
        ok.addActionListener(ev -> dialog.setVisible(false));
        dialog.add(ok);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}





