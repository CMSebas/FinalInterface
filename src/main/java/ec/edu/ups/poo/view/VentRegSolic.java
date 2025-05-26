package ec.edu.ups.poo.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentRegSolic extends Frame implements ActionListener {

    private Ventana1 ventanaPrincipal;
    private Panel panelGeneral;
    private Panel panelTitulo;
    private Panel panelEstado;
    private Panel panelFecha;
    private Panel panelDetalles;
    private Panel panelID;
    private Panel panelBoton;
    private Panel panelNorte;
    private Panel panelSoli;

    private Label labelTitulo;
    private Label labelFecha;
    private Label labelDetalles;
    private Label labelID;
    private Label labelMensajeGuardado;

    private TextField textFieldFecha;
    private TextArea textAreaDetalles;
    private TextField textFieldID;

    private CheckboxGroup estadoGroup;
    private Checkbox checkboxAprobado;
    private Checkbox checkboxDesaprobado;

    private Button botonGuardar;

    public VentRegSolic(Ventana1 ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setTitle("Sistema de Gestión de Inventario");
        setSize(625, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel principal
        panelGeneral = new Panel(new BorderLayout());

        // Panel título
        panelTitulo = new Panel();
        labelTitulo = new Label("Registro de Solicitud");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        panelTitulo.add(labelTitulo);

        // Panel fecha
        panelSoli = new Panel();
        panelSoli.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelNorte= new Panel(new FlowLayout());
        labelID = new Label("ID: ");
        textFieldID = new TextField(10); // ¡IMPORTANTE!
        labelFecha = new Label("Fecha (dd/mm/aaaa): ");
        textFieldFecha = new TextField(15);
        panelNorte.add(labelID);
        panelNorte.add(textFieldID);
        panelNorte.add(labelFecha);
        panelNorte.add(textFieldFecha);

        //Panel id

        // Panel estado (radio buttons)
        panelEstado = new Panel(new FlowLayout());
        estadoGroup = new CheckboxGroup();
        checkboxAprobado = new Checkbox("Aprobado", estadoGroup, false);
        checkboxDesaprobado = new Checkbox("Desaprobado", estadoGroup, false);
        panelEstado.add(new Label("Estado: "));
        panelEstado.add(checkboxAprobado);
        panelEstado.add(checkboxDesaprobado);



        // Panel detalles con estilo
        panelDetalles = new Panel(new BorderLayout());
        panelDetalles.setBackground(new Color(245, 245, 245)); // Fondo claro
        panelDetalles.setPreferredSize(new Dimension(400, 150));
        panelDetalles.setLayout(new BorderLayout());

        labelDetalles = new Label("Detalles:");
        labelDetalles.setFont(new Font("Arial", Font.BOLD, 16));
        labelDetalles.setForeground(Color.DARK_GRAY);

        textAreaDetalles = new TextArea(5, 40);
        textAreaDetalles.setFont(new Font("Arial", Font.PLAIN, 14));
        textAreaDetalles.setBackground(Color.WHITE);
        textAreaDetalles.setForeground(Color.BLACK);


        panelDetalles.add(labelDetalles, BorderLayout.NORTH);
        panelDetalles.add(textAreaDetalles, BorderLayout.CENTER);

        // Panel botón
        panelBoton = new Panel(new FlowLayout());
        botonGuardar = new Button("GUARDAR");
        botonGuardar.addActionListener(this);
        panelBoton.add(botonGuardar);

        //Panel mensaje final
        labelMensajeGuardado = new Label("");
        labelMensajeGuardado.setForeground(Color.GREEN);
        panelBoton.add(labelMensajeGuardado);
        // Armar centro
        panelNorte.setPreferredSize(new Dimension(550, 40));     // Panel ID y Fecha
        panelEstado.setPreferredSize(new Dimension(550, 30));    // Estado con menos altura
        panelDetalles.setPreferredSize(new Dimension(550, 120)); // Detalles más grande

        panelSoli.add(panelNorte);
        panelSoli.add(panelEstado);
        panelSoli.add(panelDetalles);

        panelGeneral.add(panelTitulo, BorderLayout.NORTH);
        panelGeneral.add(panelSoli, BorderLayout.CENTER);
        panelGeneral.add(panelBoton, BorderLayout.SOUTH);



        add(panelGeneral);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonGuardar) {
            String estadoSeleccionado = estadoGroup.getSelectedCheckbox().getLabel();
            String fecha = textFieldFecha.getText();
            String detalles = textAreaDetalles.getText();
            String id = textFieldID.getText();
            System.out.println("ID: "+ id);
            System.out.println("Estado: " + estadoSeleccionado);
            System.out.println("Fecha: " + fecha);
            System.out.println("Detalles: " + detalles);
            labelMensajeGuardado.setText("Solicitud Guardada Con Exito");
            labelMensajeGuardado.setFont(new Font("Arial", Font.BOLD, 16));
            botonGuardar.setVisible(false);
        }
    }
}
