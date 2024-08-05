package Logica.Naves.Enemigos;
import Logica.Movimiento.MovimientoEnemigo;
import Logica.Naves.Nave;
import Logica.Proyectiles.ProyectilDelEnemigo;
import Logica.Modificadores;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class NaveEnemigo extends Nave {
    private static final int ANCHO_NAVE = 48;
    private static final int ALTO_NAVE = 48;
    public static final double VELOCIDAD_DEL_ENEMIGO = 1;//0.30 velocidad ideal
//    private double posicionEnX;
//    private int posicionEnY;
//    private double velocidad;
    private final List<ProyectilDelEnemigo> proyectilEnemigo;
    protected boolean puedeDisparar;
    private Random random = new Random();
    private MovimientoEnemigo movimiento;
    public int puntosDelEnemigo;

//    public int getPosicionEnY() {
//        return posicionEnY;
//    }
//
//    public int getPosicionEnX() {
//        return (int)posicionEnX;
//    }

    public NaveEnemigo(int x, int y, int puntosDelEnemigo) {
//        this.posicionEnX = x;
//        this.posicionEnY = y;
//        this.velocidad = 1;
        super(x,y,1, 48, 48);
        proyectilEnemigo = new ArrayList<>();
        this.puedeDisparar = true;
        this.movimiento = new MovimientoEnemigo(obtenerPosicionEnX(), obtenerPosicionEnY());
        this.puntosDelEnemigo = puntosDelEnemigo;
        //iniciarEnemigo();
    }

//    @Override
//    public int obtenerVelocidad() {
//        return (int) velocidad;
//    }

//    @Override
//    public void fijarNuevaPosicionEnX(int nuevaPosicionEnX) {
//        posicionEnX = nuevaPosicionEnX;
//    }
//
//    @Override
//    public void fijarNuevaPosicionEnY(int nuevaPosicionEnY) {
//        posicionEnY = nuevaPosicionEnY;
//    }

//    protected void iniciarEnemigo() {
//        velocidad = VELOCIDAD_DEL_ENEMIGO; //velocidad de los enemigos, puse un cast en el metodo obtenerHitbox()
//    }

    public void mover() {
        movimiento.mover(this);
    }

    private boolean llegoAlLimiteInferior() {
        return this.obtenerPosicionEnY() > 600 - ANCHO_NAVE * 2;
    }

//    @Override
//    public Rectangle obtenerHitBox() {
//        return new Rectangle((int) this.obtenerPosicionEnX(), this.obtenerPosicionEnY(), ANCHO_NAVE, ALTO_NAVE); //hice un cast de int para la velocidad de la nave;
//    }

//    public int obtenerAncho() {
//        return ANCHO_NAVE;
//    }

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

//    public Rectangle obtenerHitBoxProyectilEnemigo() {
//        return new Rectangle((int) posicionEnX, posicionEnY, ANCHO_NAVE, ANCHO_NAVE); //hice un cast de int para la velocidad de la nave;
//    }

//    @Override
//    public int obtenerPosicionEnX() {
//        return getPosicionEnX();
//    }
//
//    @Override
//    public int obtenerPosicionEnY() {
//        return getPosicionEnY();
//    }

    public Modificadores generarModificador() {

        if(puedeGenerarModificador()){
            return new Modificadores(obtenerPosicionEnX(), obtenerPosicionEnY());
        }
        return null;
    }

    private boolean puedeGenerarModificador() {
        int probabilidadModificador = random.nextInt(10000);
        return true ;
    }

    public int getPuntosDelEnemigo() {
        return puntosDelEnemigo;
    }

}