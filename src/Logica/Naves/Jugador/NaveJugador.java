package Logica.Naves.Jugador;

import Logica.Movimiento.Movimiento;
import Logica.Movimiento.MovimientoNaveJugador;
import Logica.Naves.Nave;
import Logica.Proyectiles.ProyectilDelJugador;
import Logica.VerificadorDeColisiones;
import Presentacion.ReproductorMúsica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class NaveJugador extends Nave {

    public int POSICIÓN_INICIAL_EN_X = 350;
    public int POSICIÓN_INICIAL_EN_Y = 500;
    public static final int VELOCIDAD = 4;
    public static final int ANCHO_NAVE = 64;
    public static final int ALTO_NAVE = 64;
    public int numeroDeVidas;
    private MovimientoNaveJugador movimiento;
    private final List<ProyectilDelJugador> proyectiles;
    private boolean puedeDisparar;
    private Timer temporizadorDisparo;
    private ReproductorMúsica sonidoDisparo;
//    private int velocidad = 4;

    public NaveJugador() {
        super(350, 500, 4, 64, 64);
        proyectiles = new ArrayList<>();
        movimiento = new MovimientoNaveJugador(POSICIÓN_INICIAL_EN_X, POSICIÓN_INICIAL_EN_Y);
        numeroDeVidas = 3;
        iniciarNave();
    }

    private void iniciarNave() {
        puedeDisparar = true;
        sonidoDisparo = new ReproductorMúsica("src/Presentacion/MúsicaYSonido/DisparoNave.wav");
        temporizadorDisparo = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puedeDisparar = true;
                temporizadorDisparo.stop();
            }
        });
    }

    public void mover() {
       movimiento.mover(this);
    }

    public void fijarDistanciaDesplazada(int distanciaDesplazada){
        movimiento.fijarDistanciaDesplazada(distanciaDesplazada);
    }

//    public int obtenerPosicionEnX() {
//        return movimiento.obtenerPosicionEnX();
//    }
//
//    public int obtenerPosicionEnY() {
//        return movimiento.obtenerPosicionEnY();
//    }

//    @Override
//    public Rectangle obtenerHitBox() {
//        return new Rectangle(obtenerPosicionEnX(), obtenerPosicionEnY(), ANCHO_NAVE, ALTO_NAVE); //hice un cast de int para la velocidad de la nave;
//    }

    public List<ProyectilDelJugador> obtenerProyectiles(){
        return proyectiles;
    }

    protected void disparar(){
        if (puedeDisparar) {
            proyectiles.add(new ProyectilDelJugador (obtenerPosicionEnX() + (ANCHO_NAVE / 2) - 8, obtenerPosicionEnY(), 10));
            sonidoDisparo.reproducir();
            puedeDisparar = false;
            temporizadorDisparo.start();
        }
    }

//    @Override
//    public int obtenerVelocidad() {
//        return velocidad;
//    }
//    @Override
//    public void fijarNuevaPosicionEnX(int nuevaPosicionEnX) {
//
//    }
//
//    @Override
//    public void fijarNuevaPosicionEnY(int nuevaPosicionEnY) {
//
//    }

    public void teclaPresionada(KeyEvent e) {
        int tecla = e.getKeyCode();

        if (sePulsaTeclaIzquierda(tecla)) {
            fijarDistanciaDesplazada(-VELOCIDAD);
        }

        if (sePulsaTeclaDerecha(tecla)) {
            fijarDistanciaDesplazada(VELOCIDAD);
        }

        if (sePulsaBarraEspaciadora(tecla)){
            disparar();
        }
    }

    private static boolean sePulsaBarraEspaciadora(int tecla) {
        return tecla == KeyEvent.VK_SPACE;
    }

    private static boolean sePulsaTeclaDerecha(int tecla) {
        return tecla == KeyEvent.VK_RIGHT || tecla == KeyEvent.VK_D;
    }

    private static boolean sePulsaTeclaIzquierda(int tecla) {
        return tecla == KeyEvent.VK_LEFT || tecla == KeyEvent.VK_A;
    }

    public void teclaLiberada(KeyEvent e) {
        int tecla = e.getKeyCode();

        if (tecla == KeyEvent.VK_LEFT || tecla == KeyEvent.VK_RIGHT || tecla == KeyEvent.VK_A || tecla == KeyEvent.VK_D) {
            fijarDistanciaDesplazada(0);
        }

    }

//    public Rectangle obtenerHitbox() {
//        return new Rectangle(movimiento.obtenerPosicionEnX(), movimiento.obtenerPosicionEnY(), ANCHO_NAVE, ANCHO_NAVE);
//    }

    public void volverAlPuntoDeRespawn() {
        //movimiento = new MovimientoNaveJugador(POSICIÓN_INICIAL_EN_X, POSICIÓN_INICIAL_EN_Y);
        this.fijarNuevaPosicionEnX(0);
    }
}
