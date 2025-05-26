package ec.edu.ups.poo.view;

import ec.edu.ups.poo.controllers.Ventana1Controller;
import ec.edu.ups.poo.controllers.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana1 extends Frame implements ActionListener {
    private Panel panelGeneral;
    private Controller controller;
    private Panel panelTitulo;
    private Panel panelBotones;
    private Panel panelConPadding;

    private Label labelNumero1;

    private Button boton1;
    private Button boton2;
    private Button boton3;
    private Button boton4;
    private Button boton5;
    private Button boton6;
    private Button boton7;
    private Button boton8;

    public Ventana1(Controller controller) {
        this.controller = controller;
        setTitle("Sistema de Gestion de Inventario");
        setSize(500, 300);
        setLocationRelativeTo(null);




        panelGeneral = new Panel(new BorderLayout());
        panelTitulo = new Panel();

        panelBotones = new Panel(new GridLayout(6, 1,10,10));
        labelNumero1 = new Label("Menu Principal ");
        panelConPadding = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelConPadding.add(panelBotones);

        labelNumero1.setFont(new Font("Arial", Font.BOLD, 28));

        panelTitulo.add(labelNumero1);
        panelGeneral.add(panelConPadding, BorderLayout.CENTER);
        panelGeneral.add(panelTitulo, BorderLayout.NORTH);

        boton1 = new Button("VER LISTA DE PROVEDORES");
        boton2 = new Button("REGISTRAR PROVEDORES");
        boton3 = new Button("VER LISTA DE PRODUCTOS");
        boton4 = new Button("REGISTRAR PRODUCTOS");
        boton5 = new Button("REGISTRAR SOLICITUD");
        boton6 = new Button("VER SOLICITUD");
        boton7 = new Button("REGISTRAR EMPLEADO");
        boton8 = new Button("SALIR");



        panelBotones.add(boton1);
        panelBotones.add(boton2);
        panelBotones.add(boton3);
        panelBotones.add(boton4);
        panelBotones.add(boton5);
        panelBotones.add(boton6);
        panelBotones.add(boton7);
        panelBotones.add(boton8);

        boton1.addActionListener(this);
        boton3.addActionListener(this);

        boton2.addActionListener(this);
        boton8.addActionListener(this);
        boton4.addActionListener(this);
        boton5.addActionListener(this);
        boton7.addActionListener(this);








        add(panelGeneral);





    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == boton1) {
            if (controller != null) {
                VentListarProveedores ventanaLista = new VentListarProveedores(controller, this);
                ventanaLista.setVisible(true);
                this.setVisible(false);
            }

        } else if (e.getSource() == boton2) {
            VentRegPro ventanaPro = new VentRegPro(this, controller);
// NO se llama al setter, porque no hay producto
            ventanaPro.setVisible(true);
            this.setVisible(false);
        }else if (e.getSource() == boton3) {
            if (controller != null) {
                VentListarProductos ventanaListaProduc = new VentListarProductos(controller, this);
                ventanaListaProduc.setVisible(true);
                this.setVisible(false);
            }
        }
        else if(e.getSource() == boton8) {
            System.exit(0);
        }
        else if(e.getSource() == boton4) {
            VentRegProdu ventanaProdu = new VentRegProdu(this,controller);
            ventanaProdu.addWindowListener(new Ventana1Controller());
            ventanaProdu.setVisible(true);
            this.setVisible(false);

        }
        else if(e.getSource() == boton5) {
           VentRegSolic ventanaSolic = new VentRegSolic(this);
           ventanaSolic.addWindowListener(new Ventana1Controller());
           ventanaSolic.setVisible(true);
            this.setVisible(false);
        }
        else if(e.getSource() == boton7){
            VentRegEmpleado ventRegEmp = new VentRegEmpleado(this);
            ventRegEmp.addWindowListener(new Ventana1Controller());
            ventRegEmp.setVisible(true);
            this.setVisible(false);

        }

        }



    }


