package Logica;
import javax.swing.*;
import java.awt.*;
<<<<<<< HEAD:src/Logica/NaveEnemigoUno.java
import java.util.Objects;
=======
import java.util.ArrayList;
import java.util.Collection;
>>>>>>> e5bb5cbc9456351017d014e2d207ff920540527c:src/Logica/NaveEnemigo.java

public abstract class NaveEnemigo extends Logica.Nave{
    private static final int ANCHO_NAVE_ENEMIGO = 64;
    public static final int VELOCIDAD_DEL_ENEMIGO = 1;
    protected static final int NUMERO_DE_ENEMIGOS_DEL_ENJAMBRE = 7;
    private int PosicionEnX;
    private int PosicionEnY;
    private int velocidad;
    private double velocidadDelEnemigo;

    protected NaveEnemigo[] filaDeEnemigos = new NaveEnemigo[NUMERO_DE_ENEMIGOS_DEL_ENJAMBRE];

    public NaveEnemigo(int x, int y) {
        this.PosicionEnX = x;
        this.PosicionEnY = y;
        iniciarEnemigo();
    }

<<<<<<< HEAD:src/Logica/NaveEnemigoUno.java
    private void iniciarEnemigoUno() {

        Image image = new ImageIcon(Objects.requireNonNull(NaveEnemigoUno.class.getResource("/ImagenesJuego/Enemigos/ImagenEnemigoUno.png"))).getImage();
        velocidadDelEnemigo = 0.25; //velocidad de los enemigos, puse un cast en el metodo obtenerHitbox()

        velocidad = 1; //velocidad de los enemigos, puse un cast en el metodo obtenerHitbox()

=======
    protected void iniciarEnemigo() {
        velocidad = VELOCIDAD_DEL_ENEMIGO; //velocidad de los enemigos, puse un cast en el metodo obtenerHitbox()
>>>>>>> e5bb5cbc9456351017d014e2d207ff920540527c:src/Logica/NaveEnemigo.java
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
        return PosicionEnX;
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
