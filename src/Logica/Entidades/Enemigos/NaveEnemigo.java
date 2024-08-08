package Logica.Entidades.Enemigos;
import Logica.Entidades.Modificadores.*;
import Logica.Entidades.Nave;
import Logica.Proyectiles.ProyectilDelEnemigo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public abstract class NaveEnemigo extends Nave {
    private static final int ANCHO_NAVE = 48;
    private static int modificadorPorOleada = 1;
    private int probabilidadDeDisparo = 3;
    private final List<ProyectilDelEnemigo> proyectilEnemigo;
    protected boolean puedeDisparar;
    private Random random = new Random();
//    private MovimientoEnjambre movimiento;
    public int puntosDelEnemigo;

    public NaveEnemigo(int x, int y, int puntosDelEnemigo, int numeroOleada) {
        super(x,y, 1 + modificadorPorOleada * numeroOleada , 48, 48);
        proyectilEnemigo = new ArrayList<>();
        this.puedeDisparar = true;;
        this.puntosDelEnemigo = puntosDelEnemigo;

    }

//    public void mover() {
//        movimiento.mover(this);
//    }

//    private boolean llegoAlLimiteInferior() {
//        return this.obtenerPosicionEnY() > 600 - ANCHO_NAVE * 2;
//    }

    public void disparar() {
        proyectilEnemigo.add(new ProyectilDelEnemigo(obtenerPosicionEnX() + ANCHO_NAVE / 2, obtenerPosicionEnY(),5));
    }

    public boolean debeDisparar() {
        int probabilidadDisparo = random.nextInt(10000);//17180

        return probabilidadDisparo < this.probabilidadDeDisparo ; // 5% de disparo aleatorio
    }

    public List<ProyectilDelEnemigo> obtenerProyectiles() {
        return proyectilEnemigo;
    }

    public Modificador generarModificador() {
        if(puedeGenerarModificador()){
            return establecerTipoDeModificador();
        }
        return null;
    }

    private Modificador establecerTipoDeModificador() {
        int probabilidadModificador = random.nextInt(4);
        return switch (probabilidadModificador) {
            case 1 -> new VidaExtra(obtenerPosicionEnX(), obtenerPosicionEnY());
            case 2 -> new VelocidadAumentada(obtenerPosicionEnX(), obtenerPosicionEnY());
            case 3 -> new VelocidadDeDisparoAumentada(obtenerPosicionEnX(), obtenerPosicionEnY());
            case 4 -> new Politecnico(obtenerPosicionEnX(), obtenerPosicionEnY());
            default -> null;
        };
//        return new VelocidadDeDisparoAumentada(obtenerPosicionEnX(), obtenerPosicionEnY());
    }

    private boolean puedeGenerarModificador() {
        int probabilidadModificador = random.nextInt(10000);
        return probabilidadModificador < 1000 ;

    }

    public int getPuntosDelEnemigo() {
        return puntosDelEnemigo;
    }

    public void aplicarModificador(Modificador modificador){
        modificador.aplicarEfecto(this);
    }

    public void aumentarProbabilidadDeDisparo(){
        probabilidadDeDisparo = 50;
        Timer timer = new Timer();
        TimerTask timerDos = new TimerTask() {
            @Override
            public void run() {
                probabilidadDeDisparo = 3;
            }
        };
        timer.schedule(timerDos, 5000);
    }

}