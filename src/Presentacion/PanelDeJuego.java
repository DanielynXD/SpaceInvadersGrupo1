package Presentacion;

import Logica.NaveEnemigoUno;
import Logica.NaveJugador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;
import javax.swing.*;

public class PanelDeJuego extends JPanel implements ActionListener {

    public static final int ANCHO = 800, ALTO = 600;
    private Timer temporizador;
    private NaveJugador nave;
    private NaveEnemigoUno naveEnemigoUno;
    private Image fondo;

    public PanelDeJuego() {
        iniciarPanel();
    }

    private void iniciarPanel() {
        setFocusable(true);
        setSize(ANCHO, ALTO);
        nave = new NaveJugador();
        naveEnemigoUno = new NaveEnemigoUno();
        fondo = new ImageIcon(Objects.requireNonNull(PanelDeJuego.class.getResource("/ImagenesJuego/Fondos/FondoEscena.png"))).getImage();
        addKeyListener(new TAdapter());

        temporizador = new Timer(10, this);
        temporizador.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarFondo(g);
        dibujarNave(g);
        dibujarEnemigoUno(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void dibujarFondo(Graphics g) {
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }

    private void dibujarNave(Graphics g) {
        g.drawImage(nave.obtenerImagen(), nave.obtenerX(), nave.obtenerY(), this);
    }

    private void dibujarEnemigoUno(Graphics g) {
        g.drawImage(naveEnemigoUno.obtenerImagen(), naveEnemigoUno.obtenerX(), naveEnemigoUno.obtenerY(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actualizarNave();
        repaint();
    }

    private void actualizarNave() {
        nave.mover();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            nave.teclaLiberada(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            nave.teclaPresionada(e);
        }
    }
}
