package Presentacion;

import Logica.NaveEnemigoUno;
import Logica.NaveJugador;
import Logica.Proyectil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;
import java.util.List;

public class PanelDeJuego extends JPanel implements ActionListener {

    public static final int ANCHO = 800, ALTO = 600;
    private Timer temporizador;
    private NaveJugador nave;
   // private NaveEnemigoUno naveEnemigoUno;
    private Image fondo;
    private List<NaveEnemigoUno> enemigos;
    private int posicioInicialDelEnemigoEnX = 50;
    private int posicioInicialDelEnemigoEnY = 50;
    private int contador = 0;
    private int direccionMovimiento;

    public PanelDeJuego() {
        iniciarPanel();
    }

    private void iniciarPanel() {
        setFocusable(true);
        setSize(ANCHO, ALTO);
        nave = new NaveJugador(ANCHO, ALTO);
        //naveEnemigoUno = new NaveEnemigoUno();
        fondo = new ImageIcon(Objects.requireNonNull(PanelDeJuego.class.getResource("/ImagenesJuego/Fondos/FondoEscena.png"))).getImage();
        addKeyListener(new TAdapter());

        enemigos = new ArrayList<>();//inicializa el array de enemigos
        agregarEnemigos();//agrega enemigos

        temporizador = new Timer(10, this);
        temporizador.start();

        direccionMovimiento = 1; //se inicia con derecha
    }

    //----------------
    private void agregarEnemigos() {
        for (int i = 0; i < 7; i++) { // se cambia el "i <" para disminuir o aumentar las columnas de los enemigos
            enemigos.add(new NaveEnemigoUno(posicioInicialDelEnemigoEnX + i * 100, posicioInicialDelEnemigoEnY));
            if(i == 6){
                if (contador < 4) {//controla las filas de enemigos que existe y evita un bucle en la recursividad
                    contador++;
                    posicioInicialDelEnemigoEnY += 50;
                    agregarEnemigos();
                }
            }
        }
    }
    //---------

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarFondo(g);
        dibujarNave(g);
        dibujarEnemigos(g);//dibuja a los enemigos
        dibujarProyectiles(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void dibujarProyectiles(Graphics g) {
        List<Proyectil> proyectiles = nave.obtenerProyectiles();
        for (Proyectil p : proyectiles) {
            if(p.esVisible()){
                g.drawImage(p.obtenerImagen(), p.obtenerX(), p.obtenerY(), this);
            }
        }
    }

    private void dibujarFondo(Graphics g) {
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }

    private void dibujarNave(Graphics g) {
        g.drawImage(nave.obtenerImagen(), nave.obtenerX(), nave.obtenerY(), this);
    }

    private void dibujarEnemigos(Graphics g) {
        for (NaveEnemigoUno enemigo : enemigos) {
            g.drawImage(enemigo.obtenerImagen(), enemigo.obtenerX(), enemigo.obtenerY(), this);
            //array para dibujar enemigos todo cambiar dependiendo de la columna de enemigos el diseño
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actualizarNave();
        actualizarProyectiles();
        repaint();
        verificarColisiones();
        actualizarEnemigos();//actualiza la posicion de los enemigos
    }

    private void actualizarProyectiles() {
        List<Proyectil> proyectiles = nave.obtenerProyectiles();
        for (int i = 0; i < proyectiles.size(); i++) {
            Proyectil proyectil = proyectiles.get(i);
            if (proyectil.esVisible()) {
                proyectil.mover();
            } else {
                proyectiles.remove(i);
            }
        }
    }

    private void actualizarNave() {
        nave.mover();
    }

    private void actualizarEnemigos() {
        boolean cambiarDireccion = false;

        for (NaveEnemigoUno enemigo : enemigos) {
            enemigo.mover(direccionMovimiento);
            if (enemigo.obtenerX() <= 0 || enemigo.obtenerX() >= ANCHO - enemigo.obtenerImagen().getWidth(null)) {
                cambiarDireccion = true;
            }
        }

        if (cambiarDireccion) {
            direccionMovimiento = -direccionMovimiento;
            for (NaveEnemigoUno enemigo : enemigos) {
                enemigo.descender();
            }
        }
    }

    private void verificarColisiones() {
        Rectangle hitboxNave = nave.obtenerHitbox();

        List<Proyectil> proyectiles = nave.obtenerProyectiles();

        List<NaveEnemigoUno> enemigosAEliminar = new ArrayList<>();//almacena a los enemigos a eliminar

        for (Proyectil proyectil : proyectiles) {
            Rectangle hitboxProyectil = proyectil.obtenerHitbox();

            for (int i = 0; i < enemigos.size(); i++) {
                NaveEnemigoUno enemigo = enemigos.get(i);
                Rectangle hitboxEnemigo = enemigo.obtenerHitbox();

                if (hitboxProyectil.intersects(hitboxEnemigo)) {
                    proyectil.setVisible(false);
                    enemigosAEliminar.add(enemigo);//añade al enemigo a la lista
                }
            }
        }

        enemigos.removeAll(enemigosAEliminar);//elimina a los enemigos en la lista

        for (NaveEnemigoUno enemigo : enemigos) {
            Rectangle hitboxEnemigo = enemigo.obtenerHitbox();
            if (hitboxNave.intersects(hitboxEnemigo)) {
                System.exit(0); // TERMINA EL JUEGO PORQUE SOLO TIENE 1 VIDA, ese sistem termina el programa
                // TODO: añadir explosión, mas vidas y reducción de vida
            }
        }
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