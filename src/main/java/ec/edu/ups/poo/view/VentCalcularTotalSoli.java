package ec.edu.ups.poo.view;

import ec.edu.ups.poo.controllers.Controller;
import ec.edu.ups.poo.models.SolicitudCompra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentCalcularTotalSoli extends Frame {

    private Controller controller;
    private Ventana1 ventanaPrincipal;

    private TextArea textArea;
    private TextField textFieldNumero;
    private TextField textFieldTotal;

    private Panel panelInferior;
    private Panel panelNumero;
    private Panel panelBoton;
    private Panel panelResultado;
    private Button botonCalcular;

    private Label titulo;

    public VentCalcularTotalSoli(Controller controller, Ventana1 ventanaPrincipal) {
        this.controller = controller;
        this.ventanaPrincipal = ventanaPrincipal;

        setTitle("Calcular Total de Solicitud");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        titulo = new Label("CALCULAR TOTAL DE SOLICITUD", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        
        textArea = new TextArea(8, 60);
        textArea.setEditable(false);
        for (int i = 0; i < controller.getSolicitudes().size(); i++) {
            SolicitudCompra s = controller.getSolicitudes().get(i);
            String departamento = s.getSolicitante().getDepartamento().getNombre();
            textArea.append("[" + (i + 1) + "] Estado: " + s.getEstadoSolicitud() + " | Departamento: " + departamento + "\n");
        }
        add(textArea, BorderLayout.CENTER);

        
        panelInferior = new Panel(new GridLayout(3, 1, 5, 5));

        panelNumero = new Panel();
        panelNumero.add(new Label("Número de Solicitud:"));
        textFieldNumero = new TextField(5);
        panelNumero.add(textFieldNumero);

        panelBoton = new Panel();
        botonCalcular = new Button("CALCULAR TOTAL");
        botonCalcular.addActionListener(e -> {
            String texto = textFieldNumero.getText().trim();
            int num = Integer.parseInt(texto); 

            if (num < 1 || num > controller.getSolicitudes().size()) {
                JOptionPane.showMessageDialog(this, "❌ Solicitud no encontrada");
                return;
            }

            SolicitudCompra solicitud = controller.getSolicitudes().get(num - 1);
            double total = solicitud.calcularTotal();
            textFieldTotal.setText("$" + total);
        });
        panelBoton.add(botonCalcular);

        panelResultado = new Panel();
        panelResultado.add(new Label("Total:"));
        textFieldTotal = new TextField(10);
        textFieldTotal.setEditable(false);
        panelResultado.add(textFieldTotal);

        
        Button botonVolver = new Button("Volver");
        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ventanaPrincipal.setVisible(true);
            }
        });
        panelResultado.add(botonVolver);

        panelInferior.add(panelNumero);
        panelInferior.add(panelBoton);
        panelInferior.add(panelResultado);

        add(panelInferior, BorderLayout.SOUTH);

        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ventanaPrincipal.setVisible(true);
                dispose();
            }
        });
    }
}