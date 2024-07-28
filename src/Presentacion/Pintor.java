package Presentacion;

import Logica.EnjambreUno;
import Logica.NaveEnemigo;
import Logica.NaveJugador;
import Logica.Proyectil;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Pintor extends JPanel {
    PanelDeJuego panel;
    private Image fondo;
    private Image imagenNaveJugador;
    private Image imagenNaveEnemigoUno;
    private Image imagenNaveEnemigoDos;
    private Image imagenNaveEnemigoTres;
    private Image imagenProyectil;

    public Pintor(PanelDeJuego panel) {
        this.panel = panel;
        imagenNaveJugador = new ImageIcon(Objects.requireNonNull(NaveJugador.class.getResource("/ImagenesJuego/Jugador/ModeloNaveJugador.png"))).getImage();
        imagenProyectil = new ImageIcon(Objects.requireNonNull(Proyectil.class.getResource("/ImagenesJuego/Proyectiles/ProyectilJugador.png"))).getImage();
        imagenNaveEnemigoUno = new ImageIcon(Objects.requireNonNull(NaveEnemigo.class.getResource("/ImagenesJuego/Enemigos/GifEnemigoUno.gif"))).getImage();
        imagenNaveEnemigoDos = new ImageIcon(Objects.requireNonNull(NaveEnemigo.class.getResource("/ImagenesJuego/Enemigos/GifEnemigoDos.gif"))).getImage();
        imagenNaveEnemigoTres = new ImageIcon(Objects.requireNonNull(NaveEnemigo.class.getResource("/ImagenesJuego/Enemigos/GifEnemigoTres.gif"))).getImage();
        fondo = new ImageIcon(Objects.requireNonNull(PanelDeJuego.class.getResource("/ImagenesJuego/Fondos/FondoEscena.png"))).getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarFondo(g);
        dibujarNave(g);
        dibujarEnemigos(g);//dibuja a los enemigos
        dibujarProyectiles(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void dibujarEnemigos(Graphics g) {

        dibujarEnjambreUno(g);
        dibujarEnjambreDos(g);
        dibujarEnjambreTres(g);

    }

    private void dibujarEnjambreUno(Graphics g) {
        for (int[] arregloPosiciones : panel.obtenerPosicionesEnjambreUno()) {
            g.drawImage(imagenNaveEnemigoUno, arregloPosiciones[0], arregloPosiciones[1], this);
        }
    }

    private void dibujarEnjambreDos(Graphics g) {
        for (int[] arregloPosiciones : panel.obtenerPosicionesEnjambreDos()) {
            g.drawImage(imagenNaveEnemigoDos, arregloPosiciones[0], arregloPosiciones[1], this);
        }
    }

    private void dibujarEnjambreTres(Graphics g) {
        for (int[] arregloPosiciones : panel.obtenerPosicionesEnjambreTres()) {
            g.drawImage(imagenNaveEnemigoTres, arregloPosiciones[0], arregloPosiciones[1], this);
        }
    }

    private void dibujarProyectiles(Graphics g) {
        for (int[] arregloPosiciones : panel.obtenerPosicionesProyectiles()) {
            g.drawImage(imagenProyectil, arregloPosiciones[0], arregloPosiciones[1], this);
        }
    }

    private void dibujarNave(Graphics g) {
        g.drawImage(imagenNaveJugador, panel.obtenerPosicionEnXNave(), panel.obtenerPosicionEnYNave(), null);
    }

    private void dibujarFondo(Graphics g) {
        g.drawImage(fondo, 0, 0, 800, 600, null);
    }

    public void actualizar() {
        repaint();
    }
}
