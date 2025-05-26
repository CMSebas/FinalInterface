package ec.edu.ups.poo.view;
import ec.edu.ups.poo.controllers.Controller;
import ec.edu.ups.poo.enums.EstadoSolicitud;
import ec.edu.ups.poo.models.Departamento;
import ec.edu.ups.poo.models.Empleado;
import ec.edu.ups.poo.models.Producto;
import ec.edu.ups.poo.enums.Cargo;
import ec.edu.ups.poo.models.SolicitudCompra;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class VentRegSolic extends Frame implements ActionListener {
    private Ventana1 ventanaPrincipal;
    private Controller controller;
    private Label labelCargo;
    private Choice choiceCargo;
    private Panel panelGeneral;
    private Panel panelTitulo;
    private Panel panelEstado;
    private Panel panelObservaciones;
    private Panel panelSuperior;
    private Panel panelBoton;
    private Label labelTitulo;
    private Label labelDepartamento;
    private Label labelObservaciones;
    private Label labelIDEmpleado;
    private Label labelIDProducto;
    private TextField textFieldDepartamento;
    private TextArea textAreaObservaciones;
    private TextField textFieldIDEmpleado;
    private TextField textFieldIDProducto;
    private CheckboxGroup estadoGroup;
    private Checkbox checkboxAprobado;
    private Checkbox checkboxDesaprobado;
    private Checkbox checkboxEnEspera;
    private Panel centro;
    private Button botonGuardar;
    public VentRegSolic(Ventana1 ventanaPrincipal, Controller controller) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.controller = controller;
        setTitle("Registro de Solicitud");
        setSize(600, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        panelTitulo = new Panel();
        labelTitulo = new Label("Registro de Solicitud");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        panelTitulo.add(labelTitulo);
        panelSuperior = new Panel(new FlowLayout(FlowLayout.LEFT));
        labelIDEmpleado = new Label("ID Empleado:");
        textFieldIDEmpleado = new TextField(6);
        labelDepartamento = new Label("Departamento:");
        textFieldDepartamento = new TextField(12);
        labelIDProducto = new Label("ID Producto:");
        textFieldIDProducto = new TextField(6);
        labelCargo = new Label("Cargo:");
        choiceCargo = new Choice();
        choiceCargo.add("SECRETARIO");
        choiceCargo.add("ASISTENTE");
        choiceCargo.add("JEFE");
        panelSuperior.add(labelIDEmpleado);
        panelSuperior.add(textFieldIDEmpleado);
        panelSuperior.add(labelDepartamento);
        panelSuperior.add(textFieldDepartamento);
        panelSuperior.add(labelCargo);
        panelSuperior.add(choiceCargo);
        panelSuperior.add(labelIDProducto);
        panelSuperior.add(textFieldIDProducto);
        panelEstado = new Panel(new FlowLayout(FlowLayout.LEFT));
        estadoGroup = new CheckboxGroup();
        checkboxAprobado = new Checkbox("Aprobado", estadoGroup, false);
        checkboxDesaprobado = new Checkbox("Desaprobado", estadoGroup, false);
        checkboxEnEspera = new Checkbox("En espera", estadoGroup, false);
        panelEstado.add(new Label("Estado:"));
        panelEstado.add(checkboxAprobado);
        panelEstado.add(checkboxDesaprobado);
        panelEstado.add(checkboxEnEspera);
        panelObservaciones = new Panel(new BorderLayout());
        labelObservaciones = new Label("Observaciones:");
        textAreaObservaciones = new TextArea(4, 40);
        panelObservaciones.add(labelObservaciones, BorderLayout.NORTH);
        panelObservaciones.add(textAreaObservaciones, BorderLayout.CENTER);
        panelBoton = new Panel(new FlowLayout());
        botonGuardar = new Button("GUARDAR");
        botonGuardar.addActionListener(this);
        panelBoton.add(botonGuardar);
        centro = new Panel(new GridLayout(3, 1, 5, 5));
        centro.add(panelSuperior);
        centro.add(panelEstado);
        centro.add(panelObservaciones);
        panelGeneral = new Panel(new BorderLayout());
        panelGeneral.add(panelTitulo, BorderLayout.NORTH);
        panelGeneral.add(centro, BorderLayout.CENTER);
        panelGeneral.add(panelBoton, BorderLayout.SOUTH);
        add(panelGeneral);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonGuardar) {
            int idEmpleado = Integer.parseInt(textFieldIDEmpleado.getText().trim());
            int idProducto = Integer.parseInt(textFieldIDProducto.getText().trim());
            String departamento = textFieldDepartamento.getText().trim();
            String observaciones = textAreaObservaciones.getText().trim();
            String estado = estadoGroup.getSelectedCheckbox().getLabel();
            Cargo cargo = Cargo.valueOf(choiceCargo.getSelectedItem());
            Departamento dpto = new Departamento(departamento);
            Empleado empleado = new Empleado(idEmpleado, "Empleado demo", cargo, dpto);
            SolicitudCompra solicitud = new SolicitudCompra(empleado);
            Producto producto = null;
            for (Producto p : controller.getProductos()) {
                if (p.getId() == idProducto) {
                    producto = p;
                    break;
                }
            }
            if (producto == null) {
                JOptionPane.showMessageDialog(this, "❌ Producto no encontrado");
                return;
            }
            solicitud.agregarDetalle(producto, observaciones);
            switch (estado) {
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
            controller.getSolicitudes().add(solicitud);
            System.out.println("----- SOLICITUD GUARDADA -----");
            System.out.println(solicitud);
            System.out.println("------------------------------");
            JOptionPane.showMessageDialog(this, "✅ Agregado");
            this.setVisible(false);
            ventanaPrincipal.setVisible(true);
        }
    }
}