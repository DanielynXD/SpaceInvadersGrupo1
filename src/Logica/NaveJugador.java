package Logica;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Objects;
import javax.swing.ImageIcon;

public class NaveJugador extends Logica.Nave {
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int velocidad;
    private Image image;

    public NaveJugador() {
        iniciarNave();
    }

    private void iniciarNave() {
        Image imagenNave = new ImageIcon(Objects.requireNonNull(NaveJugador.class.getResource("/ImagenesJuego/Jugador/ModeloNaveJugador.png"))).getImage();
        image = imagenNave;
        x = 350;
        y = 500;
        velocidad = 5; // Velocidad de movimiento
    }

    public void mover() {
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (x > 785 - image.getWidth(null)) {
            x = 785 - image.getWidth(null);
        }
//        if (y > 600 - image.getHeight(null)) {
//            y = 600 - image.getHeight(null);
//        }
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

    public void teclaPresionada(KeyEvent e) {
        int tecla = e.getKeyCode();

        if (tecla == KeyEvent.VK_LEFT || tecla == KeyEvent.VK_A) {
            dx = -velocidad;
        }

        if (tecla == KeyEvent.VK_RIGHT || tecla == KeyEvent.VK_D) {
            dx = velocidad;
        }

//        if (tecla == KeyEvent.VK_UP || tecla == KeyEvent.VK_W) {
//            dy = -velocidad;
//        }
//
//        if (tecla == KeyEvent.VK_DOWN || tecla == KeyEvent.VK_S) {
//            dy = velocidad;
//        }
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
}
