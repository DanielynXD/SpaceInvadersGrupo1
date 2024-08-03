package Logica.Naves.Enemigos;
import Logica.Movimiento.Movimiento;
import Logica.Movimiento.MovimientoEnemigo;
import Logica.Naves.Nave;
import Logica.Proyectiles.ProyectilDelEnemigo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class NaveEnemigo extends Nave {
    private static final int ANCHO_NAVE = 48;
    private static final int ALTO_NAVE = 48;
    public static final double VELOCIDAD_DEL_ENEMIGO = 1;//0.30 velocidad ideal
    private double posicionEnX;
    private int posicionEnY;
    private double velocidad;
    private final List<ProyectilDelEnemigo> proyectilEnemigo;
    protected boolean puedeDisparar;
    private Random random = new Random();
    private MovimientoEnemigo movimiento;

    public int getPosicionEnY() {
        return posicionEnY;
    }

    public int getPosicionEnX() {
        return (int)posicionEnX;
    }

    public NaveEnemigo(int x, int y) {
        this.posicionEnX = x;
        this.posicionEnY = y;
        proyectilEnemigo = new ArrayList<>();
        this.puedeDisparar = true;
        this.movimiento = new MovimientoEnemigo((int) posicionEnX, posicionEnY);
        iniciarEnemigo();
        this.velocidad = 1;
    }

    @Override
    public int obtenerVelocidad() {
        return (int) velocidad;
    }

    @Override
    public void fijarNuevaPosicionEnX(int nuevaPosicionEnX) {
        posicionEnX = nuevaPosicionEnX;
    }

    @Override
    public void fijarNuevaPosicionEnY(int nuevaPosicionEnY) {
        posicionEnY = nuevaPosicionEnY;
    }

    protected void iniciarEnemigo() {
        velocidad = VELOCIDAD_DEL_ENEMIGO; //velocidad de los enemigos, puse un cast en el metodo obtenerHitbox()
    }

    public void mover() {
        movimiento.mover(this);
    }

    private boolean llegoAlLimiteInferior() {
        return posicionEnY > 600 - ANCHO_NAVE * 2;
    }

//    @Override
//    public int obtenerPosicionEnX() {
//        return (int) posicionEnX;//cast para evitar que las naves se queden estaticas
//    }
//
//    @Override
//    public int obtenerPosicionEnY() {
//        return posicionEnY;
//    }

    @Override
    public Rectangle obtenerHitBox() {
        return new Rectangle((int) posicionEnX, posicionEnY, ANCHO_NAVE, ALTO_NAVE); //hice un cast de int para la velocidad de la nave;
    }

    public int obtenerAncho() {
        return ANCHO_NAVE;
    }

    public void disparar() {
        proyectilEnemigo.add(new ProyectilDelEnemigo(obtenerPosicionEnX() + ANCHO_NAVE / 2, obtenerPosicionEnY(),5));
    }

    public boolean debeDisparar() {
        int probabilidadDisparo = random.nextInt(10000);//17180

        return probabilidadDisparo < 3 ; // 5% de disparo aleatorio
    }

    public List<ProyectilDelEnemigo> obtenerProyectiles() {
        return proyectilEnemigo;
    }

    public Rectangle obtenerHitBoxProyectilEnemigo() {
        return new Rectangle((int) posicionEnX, posicionEnY, ANCHO_NAVE, ANCHO_NAVE); //hice un cast de int para la velocidad de la nave;
    }

    @Override
    public int obtenerPosicionEnX() {
        return getPosicionEnX();
    }

    @Override
    public int obtenerPosicionEnY() {
        return getPosicionEnY();
    }
}