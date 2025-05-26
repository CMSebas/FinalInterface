package ec.edu.ups.poo.view;

import ec.edu.ups.poo.controllers.Controller;
import ec.edu.ups.poo.enums.EstadoSolicitud;
import ec.edu.ups.poo.models.SolicitudCompra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentCambiarEstadoSoli extends Frame {

    private Controller controller;
    private Ventana1 ventanaPrincipal;

    private TextArea textArea;
    private TextField textFieldNumero;
    private Label labelEstado;
    private CheckboxGroup estadoGroup;
    private Checkbox checkboxAprobado;
    private Checkbox checkboxDesaprobado;
    private Checkbox checkboxEspera;
    private Label mensaje;
    private Panel panelCentro;
    private Panel panelNumero;
    private Panel panelEstado;

    private Label titulo;
    private Button botonCambiar;
    private Button botonVolver;

    public VentCambiarEstadoSoli(Controller controller, Ventana1 ventanaPrincipal) {
        this.controller = controller;
        this.ventanaPrincipal = ventanaPrincipal;

        setTitle("Cambiar Estado de Solicitud");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        titulo = new Label("CAMBIAR ESTADO DE SOLICITUD", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        
        textArea = new TextArea(8, 60);
        textArea.setEditable(false);
        for (int i = 0; i < controller.getSolicitudes().size(); i++) {
            SolicitudCompra s = controller.getSolicitudes().get(i);
            String estado = s.getEstadoSolicitud().toString();
            String departamento = s.getSolicitante().getDepartamento().getNombre();
            textArea.append("[" + (i + 1) + "] Estado: " + estado + " | Departamento: " + departamento + "\n");
        }
        add(textArea, BorderLayout.CENTER);

        
        panelCentro = new Panel(new GridLayout(3, 1, 5, 5));

        panelNumero = new Panel();
        panelNumero.add(new Label("Número de Solicitud:"));
        textFieldNumero = new TextField(5);
        panelNumero.add(textFieldNumero);

        panelEstado = new Panel();
        estadoGroup = new CheckboxGroup();
        checkboxAprobado = new Checkbox("Aprobado", estadoGroup, false);
        checkboxDesaprobado = new Checkbox("Desaprobado", estadoGroup, false);
        checkboxEspera = new Checkbox("En espera", estadoGroup, false);

        panelEstado.add(checkboxAprobado);
        panelEstado.add(checkboxDesaprobado);
        panelEstado.add(checkboxEspera);

        mensaje = new Label("");

        panelCentro.add(panelNumero);
        panelCentro.add(panelEstado);
        panelCentro.add(mensaje);

        add(panelCentro, BorderLayout.SOUTH);

        botonCambiar = new Button("CAMBIAR ESTADO");
        botonCambiar.addActionListener(e -> {
            String texto = textFieldNumero.getText().trim();
            int num = Integer.parseInt(texto); 

            if (num < 1 || num > controller.getSolicitudes().size()) {
                JOptionPane.showMessageDialog(this, "❌ Solicitud no encontrada");
                return;
            }

            Checkbox seleccion = estadoGroup.getSelectedCheckbox();
            if (seleccion == null) return;

            SolicitudCompra solicitud = controller.getSolicitudes().get(num - 1);

            switch (seleccion.getLabel()) {
                case "Aprobado":
                    solicitud.setEstadoSolicitud(EstadoSolicitud.APROBADA);
                    break;
                case "Desaprobado":
                    solicitud.setEstadoSolicitud(EstadoSolicitud.RECHAZADA);
                    break;
                case "En espera":
                    solicitud.setEstadoSolicitud(EstadoSolicitud.EN_REVISION);
                    break;
            }


            JOptionPane.showMessageDialog(this, "✅ Estado actualizado con éxito.");

            
        });

        Panel panelBoton = new Panel();
        panelBoton.add(botonCambiar);

        
        botonVolver = new Button("Volver");
        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ventanaPrincipal.setVisible(true);
            }
        });
        panelBoton.add(botonVolver);

        panelCentro.add(panelBoton);

        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ventanaPrincipal.setVisible(true);
                dispose();
            }
        });
    }
}