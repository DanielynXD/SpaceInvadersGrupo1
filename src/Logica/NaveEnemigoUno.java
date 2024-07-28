package Logica;
import java.awt.*;

public class NaveEnemigoUno extends Logica.Nave{
    private static final int ANCHO_NAVE_ENEMIGO = 64;
    private int PosicionEnX;
    private int PosicionEnY;
    private int velocidad;


    public NaveEnemigoUno(int x, int y) {
        this.PosicionEnX = x;
        this.PosicionEnY = y;
        iniciarEnemigoUno();

    }

    private void iniciarEnemigoUno() {
<<<<<<< HEAD
        image = new ImageIcon(Objects.requireNonNull(NaveEnemigoUno.class.getResource("/ImagenesJuego/Enemigos/ImagenEnemigoUno.png"))).getImage();
        velocidadDelEnemigo = 0.25; //velocidad de los enemigos, puse un cast en el metodo obtenerHitbox()
=======
        velocidad = 1; //velocidad de los enemigos, puse un cast en el metodo obtenerHitbox()
>>>>>>> 5d3ab3073c891968e36d903e7181ad45c3f01703
    }

    public void mover(int direccion) {
        PosicionEnX += velocidad * direccion;

        //todo darle responsabilidad unica a la velocidad y a la direccion
    }

    public void descender() {
        PosicionEnY += 1;
    }

    private boolean llegoAlLimiteInferior() {
        return PosicionEnY > 600 - ANCHO_NAVE_ENEMIGO * 2;
    }

    @Override
    public int obtenerPosicionEnX() {
        return (int) PosicionEnX;
    }

    @Override
    public int obtenerPosicionEnY() {
        return PosicionEnY;
    }

    @Override
    public Rectangle obtenerHitBox() {
        return new Rectangle(PosicionEnX, PosicionEnY, ANCHO_NAVE_ENEMIGO, ANCHO_NAVE_ENEMIGO); //hice un cast de int para la velocidad de la nave;
    }

    public int obtenerAncho() {
        return ANCHO_NAVE_ENEMIGO;
    }
}
