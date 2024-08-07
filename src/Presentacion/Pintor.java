package Presentacion;

import Logica.Entidades.Modificadores.Modificador;
import Logica.Entidades.Enemigos.NaveEnemigo;
import Logica.Entidades.Jugador.NaveJugador;
import Logica.Proyectiles.Proyectil;
import Logica.Puntaje.Puntaje;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.List;

public class Pintor extends JPanel {
    PanelDeJuego panel;
    private VentanaPuntuaciones ventanaPuntuaciones;
    private VentanaFinDeJuego ventanaFinDeJuego;
    private Menú  ventanaMenu;

    private Image fondo;
    private Image imagenNaveJugador;
    private Image imagenNaveEnemigoUno;
    private Image imagenNaveEnemigoDos;
    private Image imagenNaveEnemigoTres;
    private Image imagenProyectil;
    private Image imagenProyectilEnemigo;
    private Image imagenPuntuaciones;
    private Image imagenModificadorVidaExtra;
    private Image imagenFinDelJuego;
    private Image imagenMenu;

    public Pintor(PanelDeJuego panel) {
        this.panel = panel;
        imagenNaveJugador = new ImageIcon(Objects.requireNonNull(NaveJugador.class.getResource("/ImagenesJuego/Jugador/ModeloNaveJugador.gif"))).getImage();
        imagenProyectil = new ImageIcon(Objects.requireNonNull(Proyectil.class.getResource("/ImagenesJuego/Proyectiles/ProyectilJugador.png"))).getImage();
        imagenProyectilEnemigo = new ImageIcon(Objects.requireNonNull(Proyectil.class.getResource("/ImagenesJuego/Proyectiles/ProyectilEnemigo.png"))).getImage();
        imagenNaveEnemigoUno = new ImageIcon(Objects.requireNonNull(NaveEnemigo.class.getResource("/ImagenesJuego/Enemigos/GifEnemigoUno.gif"))).getImage();
        imagenNaveEnemigoDos = new ImageIcon(Objects.requireNonNull(NaveEnemigo.class.getResource("/ImagenesJuego/Enemigos/GifEnemigoDos.gif"))).getImage();
        imagenNaveEnemigoTres = new ImageIcon(Objects.requireNonNull(NaveEnemigo.class.getResource("/ImagenesJuego/Enemigos/GifEnemigoTres.gif"))).getImage();
        imagenModificadorVidaExtra = new ImageIcon(Objects.requireNonNull(Modificador.class.getResource("/ImagenesJuego/Modificadores/VidaExtra.gif"))).getImage();
        fondo = new ImageIcon(Objects.requireNonNull(PanelDeJuego.class.getResource("/ImagenesJuego/Fondos/FondoEscena.gif"))).getImage();

    }

    public Pintor(VentanaPuntuaciones panelPuntuaciones) {
        imagenPuntuaciones = new ImageIcon(Objects.requireNonNull(VentanaPuntuaciones.class.getResource("/ImagenesJuego/Fondos/FondoPuntuaciones.gif"))).getImage();
        this.ventanaPuntuaciones = panelPuntuaciones;
    }

    public Pintor(VentanaFinDeJuego finDeJuego) {
        //imagenFinDelJuego = new ImageIcon(Objects.requireNonNull(VentanaFinDeJuego.class.getResource("/ImagenesJuego/Fondos/FondoGameOver.png"))).getImage();
        imagenFinDelJuego = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ImagenesJuego/Fondos/FondoGameOver.png"))).getImage();
        this.ventanaFinDeJuego = finDeJuego;
    }

    public Pintor(Menú menu) {
        imagenMenu = new ImageIcon(Objects.requireNonNull(Menú.class.getResource("/ImagenesJuego/Fondos/FondoMenu.gif"))).getImage();
        this.ventanaMenu = menu;
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);


        if (ventanaMenu != null ){
            mensajeInicial(g);
        } else if (panel != null) {
            dibujarFondo(g);
            dibujarPuntajesEnPantalla(g);
            dibujarNave(g);
            dibujarEnemigos(g);
            dibujarModificadores(g);
            dibujarProyectiles(g);
            dibujarProyectilesEnemigos(g);
            dibujarVidasEnPantalla(g);
        } else if (ventanaPuntuaciones != null) {
            dibujarContenidoDePuntajes(g);
        } else if (ventanaFinDeJuego != null) {
            mensajeFinal(g);
        }
        Toolkit.getDefaultToolkit().sync();

    }

    private void dibujarPuntajesEnPantalla(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString("Puntuacion: ", 25, 25);
        g.drawString(String.valueOf(panel.getPuntajeTotal()), 130, 25);
    }

    private void dibujarVidasEnPantalla(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString("Vidas: ", 25, 50);
        g.drawString(String.valueOf(panel.obtenerVidasJugador()), 130, 50 );
    }

    private void dibujarContenidoDePuntajes(Graphics g) {
        if (ventanaPuntuaciones == null) {
            return;
        }

        g.drawImage(imagenPuntuaciones, 0, 0, 520, 800, this);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString("PUNTUACIONES", 180, 50);
        int inicioDeLosPuntajes = 100;

        List<Puntaje> puntuaciones = ventanaPuntuaciones.getPuntuaciones();
        int diezMejoresJugadores= Math.min(puntuaciones.size(), 10);


        for (int i = 0; i < diezMejoresJugadores; i++) {
            Puntaje puntuacion = puntuaciones.get(i);
            String nombre = puntuacion.getNombre();
            String puntos = String.valueOf(puntuacion.getPuntaje());
            g.drawString(nombre, 80, inicioDeLosPuntajes);
            g.drawString(puntos, getWidth() - 125, inicioDeLosPuntajes);
            inicioDeLosPuntajes += 30;
        }
    }

    private void dibujarModificadores(Graphics g) {
        for(int [] arregloPosiciones : panel.obtenerPosicionesModificadores()){
            g.drawImage(imagenModificadorVidaExtra, arregloPosiciones[0], arregloPosiciones[1], this);
        }
    }

    private void dibujarProyectilesEnemigos(Graphics g) {
        for (int[] arregloPosiciones : panel.obtenerPosicionesProyectilesEnemigos()) {
            g.drawImage(imagenProyectilEnemigo, arregloPosiciones[0], arregloPosiciones[1], this);
        }

    }

    private void dibujarEnemigos(Graphics g) {
        dibujarEnjambreUno(g);
        dibujarEnjambreDos(g);
        dibujarEnjambreTres(g);
    }

    private void dibujarEnjambreUno(Graphics g) {
        for (int [] arregloPosiciones : panel.obtenerPosicionesEnjambreUno()){
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

    public void mensajeInicial(Graphics g) {
        g.drawImage(imagenMenu, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 50));
        g.drawString("SPACE INVADERS", 150, 100);

    }

    private void mensajeFinal(Graphics g) {
        g.drawImage(imagenFinDelJuego, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 50));
        g.drawString("GAME OVER", 25, 180);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Britannic Bold", Font.BOLD, 20));
        g.drawString("INGRESA", 360, 280);
        g.setColor(Color.WHITE);
        g.drawString("TU NOMBRE", 445, 280);
    }
}
