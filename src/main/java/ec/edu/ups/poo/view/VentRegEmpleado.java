package ec.edu.ups.poo.view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class VentRegEmpleado extends Frame implements ActionListener {
        private Ventana1 ventanaPrincipal;
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
        private Label labelNumero6;
        private TextField textField1;
        private TextField textField2;
        private TextField textField3;
        private TextField textField4;
        private TextField textField5;

        public VentRegEmpleado(Ventana1 ventanaPrincipal) {
            this.ventanaPrincipal = ventanaPrincipal;
            setTitle("Registro De Inventario");
            setSize(1000, 1000);
            setLocationRelativeTo(null);

            panelGeneral = new Panel(new BorderLayout());
            panelTitulo = new Panel();
            panelLabels = new Panel(new GridLayout(2, 4, 10, 10));
            panelBoton = new Panel(new FlowLayout());

            panelConPadding = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 20));
            panelConPadding.add(panelLabels);

            panelConPadding2 = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 20));
            panelConPadding2.add(panelBoton);


            labelNumero1 = new Label("Registro de Empleados ");

            labelNumero1.setFont(new Font("Arial", Font.BOLD, 28));

            panelTitulo.add(labelNumero1);


            labelNumero2 = new Label("Nombre y Apellido ");
            labelNumero3 = new Label("Cedula ");
            labelNumero4 = new Label("Direccion ");
            labelNumero5 = new Label("Telefono ");
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
                this.setVisible(false);
                ventanaPrincipal.setVisible(true);
            }
        }
    }



