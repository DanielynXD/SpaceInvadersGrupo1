package Logica;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class NaveEnemigoUno extends Logica.Nave {
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int velocidad;
    private Image image;

    public NaveEnemigoUno(int x, int y) {
        this.x = x;
        this.y = y;
        iniciarEnemigoUno();
    }

    private void iniciarEnemigoUno() {
        image = new ImageIcon(Objects.requireNonNull(NaveEnemigoUno.class.getResource("/ImagenesJuego/Enemigos/ImagenEnemigoUno.png"))).getImage();
        velocidad = 1; //velocidad de los enemigos, puse un cast en el metodo obtenerHitbox()
    }

    public void mover(int direccion) {
        x += velocidad * direccion;

        //todo darle responsabilidad unica a la velocidad y a la direccion
    }

    public void descender() {
        y += 1;
    }

    private boolean llegoAlLimiteInferior() {
        return y > 600 - image.getHeight(null) * 2;
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

    @Override
    public Rectangle obtenerHitBox() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null)); //hice un cast de int para la velocidad de la nave;
    }


}
