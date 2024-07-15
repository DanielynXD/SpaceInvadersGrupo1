package Logica;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class NaveEnemigoUno extends Logica.Nave {
    private double x;
    private int y;
    private int dx;
    private int dy;
    private double velocidadDelEnemigo;
    private Image image;

    public NaveEnemigoUno(int x, int y) {
        this.x = x;
        this.y = y;
        iniciarEnemigoUno();
    }

    private void iniciarEnemigoUno() {
        image = new ImageIcon(Objects.requireNonNull(NaveEnemigoUno.class.getResource("/ImagenesJuego/Enemigos/ImagenEnemigoUno.png"))).getImage();
        velocidadDelEnemigo = 0.5; //velocidad de los enemigos, puse un cast en el metodo obtenerHitbox()
    }

    public void mover(int direccion) {
        x += velocidadDelEnemigo * direccion;
        //todo darle responsabilidad unica a la velocidad y a la direccion
    }

    public void descender() {
        y += image.getHeight(null) / 2; // Bajar la mitad de la altura de la imagen del enemigo
    }


    public Rectangle obtenerHitbox() {
        return new Rectangle((int)x, y, image.getWidth(null), image.getHeight(null)); //hice un cast de int para la velocidad de la nave
    }




    @Override
    public Image obtenerImagen() {
        return image;
    }

    @Override
    public int obtenerX() {
        return (int)x;
    }

    @Override
    public int obtenerY() {
        return y;
    }


}
