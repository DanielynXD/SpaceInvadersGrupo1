package Presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menú extends JFrame implements ActionListener{
    private JButton botonIniciarJuego, botonSalir, botonPuntuaciones;
    public static final int ANCHO = 768, ALTO = 432;

    public Menú() {
        JPanel panel = new Pintor(this); // Usar Pintor para el dibujo

        setTitle("SpaceInvaders");
        setSize(ANCHO, ALTO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        botonIniciarJuego = new JButton("Iniciar Juego");
        botonIniciarJuego.setBounds(300, 200, 150, 40);
        botonIniciarJuego.addActionListener(this);
        panel.add(botonIniciarJuego);


        botonPuntuaciones = new JButton("Puntuaciones");
        botonPuntuaciones.setBounds(300, 250, 150, 40);
        botonPuntuaciones.addActionListener(this);
        panel.add(botonPuntuaciones);

        botonSalir = new JButton("Salir");
        botonSalir.setBounds(300, 300, 150, 40);
        botonSalir.addActionListener(this);
        panel.add(botonSalir);

        add(panel);
        panel.setLayout(null);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == botonIniciarJuego) {
            abrirVentanaJuego();
        }
        if(e.getSource() == botonPuntuaciones) {
            new VentanaPuntuaciones();
        }
        if(e.getSource() == botonSalir) {
            System.exit(0);
        }

    }

    private void abrirVentanaJuego() {
        new Escenario();
        this.dispose();
    }
}


