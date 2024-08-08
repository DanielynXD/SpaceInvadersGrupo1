package Presentacion;

import Logica.Puntaje.Puntaje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import Logica.Puntaje.Puntaje;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import Logica.Puntaje.ComparadorDePuntajes;


public class VentanaFinDeJuego extends JFrame implements ActionListener {
    private final int puntajeDelPartida;
    private JButton reintentarBoton, salirBoton;
    private JTextField nombreDelJugador;
    private ReproductorMúsica reproductorMusica;
    private VentanaPuntuaciones ventanaPuntuaciones = new VentanaPuntuaciones();

    public VentanaFinDeJuego(int puntajeDelJugador) {
        this.puntajeDelPartida = puntajeDelJugador;
        setTitle("Fin de Juego");
        setSize(600, 475);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panelFinDeJuego = new Pintor(this);
        panelFinDeJuego.setBounds(0, 0, getWidth(), getHeight());

        reintentarBoton = new JButton("Reintentar");
        reintentarBoton.setBounds(50, 350, 200, 50);
        reintentarBoton.addActionListener(this);
        add(reintentarBoton);


        nombreDelJugador = new JTextField();
        nombreDelJugador.setBounds(325, 300, 200, 30);
        add(nombreDelJugador);


        salirBoton = new JButton("Guardar Puntaje");
        salirBoton.setBounds(325, 350, 200, 50);
        salirBoton.addActionListener(this);
        add(salirBoton);

        add(panelFinDeJuego);
        setVisible(true);

        reproductorMusica = new ReproductorMúsica("src/Presentacion/MúsicaYSonido/SonidoFinDeJuego.wav");
        reproductorMusica.reproducir();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == salirBoton) {
            mostrarVentanaFinal();
        } else if (e.getSource() == reintentarBoton) {
            new Escenario();
            this.dispose();
        }
    }

    private void mostrarVentanaFinal() {

        String nombre = nombreDelJugador.getText().replaceAll("\\s+", "");

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre válido.");
            return;
        }

        //-------
        List<Puntaje> puntuaciones = ventanaPuntuaciones.leerPuntuaciones("/Puntuaciones/PuntuacionesMejoresJugadores");
        puntuaciones.add(new Puntaje(nombre, puntajeDelPartida));

        puntuaciones.sort(new ComparadorDePuntajes());

        if (puntuaciones.size() > 10) {
            puntuaciones = puntuaciones.subList(0, 10);
        }

        File archivo = new File("src/Puntuaciones/PuntuacionesMejoresJugadores");
        if (archivo.exists()) {
            archivo.delete();
        }




        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Puntuaciones/PuntuacionesMejoresJugadores", true))) {

            for ( Puntaje puntaje : puntuaciones) {
                writer.write(puntaje.getNombre() + " " + puntaje.getPuntaje() + "\n");
            }
            JOptionPane.showMessageDialog(this, "Nombre y puntaje guardados exitosamente");
            new Menú();
            this.dispose();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el nombre y puntaje");
        }
    }
}

