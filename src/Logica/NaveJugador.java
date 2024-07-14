package Logica;

import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.*;

public class NaveJugador extends Nave {
    private int x;
    private int y;
    private int dx;
    //    private int dy;
    private int velocidad;
    private Image image;
    private int anchoPanel;
    private int alturaPanel;
    private final List<Proyectil> proyectiles;
    private boolean puedeDisparar;
    private Timer temporizadorDisparo;

    public NaveJugador(int anchoPanel, int alturaPanel) {
        this.anchoPanel = anchoPanel;
        this.alturaPanel = alturaPanel;
        proyectiles = new ArrayList<>();
        iniciarNave();
    }

    private void iniciarNave() {
        image = new ImageIcon(Objects.requireNonNull(NaveJugador.class.getResource("/ImagenesJuego/Jugador/ModeloNaveJugador.png"))).getImage();
        x = 350;
        y = 500;
        velocidad = 4; // Velocidad de movimiento del jugador
        puedeDisparar = true;
        temporizadorDisparo = new Timer(650, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puedeDisparar = true;
                temporizadorDisparo.stop();
            }
        });
    }

    public void mover() {
        x += dx;
//        y += dy;
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (x > 785 - image.getWidth(null)) {
            x = 785 - image.getWidth(null);
        }
        if (y > 600 - image.getHeight(null)) {
            y = 600 - image.getHeight(null);
        }
    }

    public int obtenerX() {
        return x;
    }

    public int obtenerY() {
        return y;
    }

    public Image obtenerImagen() {
        return image;
    }

    public List<Proyectil> obtenerProyectiles(){
        return proyectiles;
    }

    public void disparar(){
        if (puedeDisparar) {
            proyectiles.add(new Proyectil (x + (image.getWidth(null) / 2) - 8, y));
            puedeDisparar = false;
            temporizadorDisparo.start();
        }
    }

    public void teclaPresionada(KeyEvent e) {
        int tecla = e.getKeyCode();

        if (tecla == KeyEvent.VK_LEFT || tecla == KeyEvent.VK_A) {
            dx = -velocidad;
        }

        if (tecla == KeyEvent.VK_RIGHT || tecla == KeyEvent.VK_D) {
            dx = velocidad;
        }
//
//        if (tecla == KeyEvent.VK_UP || tecla == KeyEvent.VK_W) {
//            dy = -velocidad;
//        }
//
//        if (tecla == KeyEvent.VK_DOWN || tecla == KeyEvent.VK_S) {
//            dy = velocidad;
//        }

        if (tecla == KeyEvent.VK_SPACE){
            disparar();
        }
    }

    public void teclaLiberada(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_A || key == KeyEvent.VK_D) {
            dx = 0;
        }

//        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_W || key == KeyEvent.VK_S) {
//            dy = 0;
//        }
    }

    public Rectangle obtenerHitbox() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }
}