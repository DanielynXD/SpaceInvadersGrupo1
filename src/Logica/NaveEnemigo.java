package Logica;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public abstract class NaveEnemigo extends Logica.Nave{
    private static final int ANCHO_NAVE_ENEMIGO = 64;
    public static final int VELOCIDAD_DEL_ENEMIGO = 1;
    protected static final int NUMERO_DE_ENEMIGOS_DEL_ENJAMBRE = 7;
    private int PosicionEnX;
    private int PosicionEnY;
    private int velocidad;

    protected NaveEnemigo[] filaDeEnemigos = new NaveEnemigo[NUMERO_DE_ENEMIGOS_DEL_ENJAMBRE];

    public NaveEnemigo(int x, int y) {
        this.PosicionEnX = x;
        this.PosicionEnY = y;
        iniciarEnemigo();
    }

    protected void iniciarEnemigo() {
        velocidad = VELOCIDAD_DEL_ENEMIGO; //velocidad de los enemigos, puse un cast en el metodo obtenerHitbox()
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
