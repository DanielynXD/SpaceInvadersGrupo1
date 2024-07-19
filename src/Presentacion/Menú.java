package Presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menú extends JFrame implements ActionListener {

    private JButton botonIniciarJuego, botonSalir, botonPuntuaciones;
    public static final int ANCHO = 800, ALTO = 600;

    public Menú() {
        setTitle("SpaceInvaders");
        setSize(ANCHO, ALTO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        botonIniciarJuego = new JButton("Iniciar");
        botonIniciarJuego.setBounds(300, 250, 200, 50);
        botonIniciarJuego.addActionListener(this);
        add(botonIniciarJuego);

        botonSalir = new JButton("Salir");
        botonSalir.setBounds(300, 400, 200, 50);
        botonSalir.addActionListener(this);
        add(botonSalir);

        botonPuntuaciones = new JButton("Puntuaciones");
        botonPuntuaciones.setBounds(300, 325, 200, 50);
        botonPuntuaciones.addActionListener(this);
        add(botonPuntuaciones);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == botonIniciarJuego) {
            abrirVentanaJuego();
        }
        if(e.getSource() == botonSalir) {
            System.exit(0);
        }
        if(e.getSource() == botonPuntuaciones) {
            new VentanaPuntuaciones();
        }



    }

    private void abrirVentanaJuego() {
        new Escenario();
        this.dispose();
    }
}


