package Logica.Naves.Enemigos;
import Logica.Naves.Nave;
import Logica.Proyectiles.ProyectilDelEnemigo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class NaveEnemigo extends Nave {
    private static final int ANCHO_NAVE_ENEMIGO = 64;
    public static final double VELOCIDAD_DEL_ENEMIGO = 1;//0.30 velocidad ideal
    protected static final int NUMERO_DE_ENEMIGOS_DEL_ENJAMBRE = 10;
    private double PosicionEnX;
    private int PosicionEnY;
    private double velocidad;
    private final List<ProyectilDelEnemigo> proyectilEnemigo;
    protected int puntajeDelEnemigo;
    protected boolean puedeDisparar;
    private Random random = new Random();

    public NaveEnemigo(int x, int y) {
        this.PosicionEnX = x;
        this.PosicionEnY = y;
        proyectilEnemigo = new ArrayList<>();
        this.puedeDisparar = true;
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
        return (int)PosicionEnX;//cast para evitar que las naves se queden estaticas
    }

    @Override
    public int obtenerPosicionEnY() {
        return PosicionEnY;
    }

    @Override
    public Rectangle obtenerHitBox() {
        return new Rectangle((int)PosicionEnX, PosicionEnY, ANCHO_NAVE_ENEMIGO, ANCHO_NAVE_ENEMIGO); //hice un cast de int para la velocidad de la nave;
    }

    public int obtenerAncho() {
        return ANCHO_NAVE_ENEMIGO;
    }

    public void disparar() {
        proyectilEnemigo.add(new ProyectilDelEnemigo(obtenerPosicionEnX() + ANCHO_NAVE_ENEMIGO / 2, obtenerPosicionEnY(),5));
    }

    public boolean debeDisparar() {
        int probabilidadDisparo = random.nextInt(10000);//17180

        return probabilidadDisparo < 3 ; // 5% de disparo aleatorio
    }

    public List<ProyectilDelEnemigo> obtenerProyectiles() {
        return proyectilEnemigo;
    }

    public Rectangle obtenerHitBoxProyectilEnemigo() {
        return new Rectangle((int)PosicionEnX, PosicionEnY, ANCHO_NAVE_ENEMIGO, ANCHO_NAVE_ENEMIGO); //hice un cast de int para la velocidad de la nave;
    }
}