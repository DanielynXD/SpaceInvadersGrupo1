package Logica.Naves.Jugador;

import Logica.Movimiento.Movimiento;
import Logica.Movimiento.MovimientoNaveJugador;
import Logica.Naves.Nave;
import Logica.Proyectiles.ProyectilDelJugador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class NaveJugador extends Nave {

    public static final int POSICIÓN_INICIAL_EN_X = 350;
    public static final int POSICIÓN_INCIAL_EN_Y = 500;
    public static final int VELOCIDAD = 4;
    public static final int ANCHO_NAVE = 64;
    public static final int ALTO_NAVE = 64;
    public int numeroDeVidas;
    private Movimiento movimiento;
    private final List<ProyectilDelJugador> proyectiles;
    private boolean puedeDisparar;
    private Timer temporizadorDisparo;

    public NaveJugador() {
        proyectiles = new ArrayList<>();
        movimiento = new MovimientoNaveJugador(POSICIÓN_INICIAL_EN_X, POSICIÓN_INCIAL_EN_Y);
        numeroDeVidas = 3;
        iniciarNave();
    }

    private void iniciarNave() {
        puedeDisparar = true;
        temporizadorDisparo = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puedeDisparar = true;
                temporizadorDisparo.stop();
            }
        });
    }

    public void mover() {
       movimiento.mover();
    }

    public void fijarDistanciaDesplazada(int distanciaDesplazada){
        if (movimiento instanceof MovimientoNaveJugador) {
            ((MovimientoNaveJugador) movimiento).fijarDistanciaDesplazada(distanciaDesplazada);
        }
    }

    public int obtenerPosicionEnX() {
        return movimiento.obtenerPosicionEnX();
    }

    public int obtenerPosicionEnY() {
        return movimiento.obtenerPosicionEnY();
    }

    @Override
    public Rectangle obtenerHitBox() {
        return new Rectangle(obtenerPosicionEnX(), obtenerPosicionEnY(), ANCHO_NAVE, ALTO_NAVE); //hice un cast de int para la velocidad de la nave;
    }

    public List<ProyectilDelJugador> obtenerProyectiles(){
        return proyectiles;
    }

    protected void disparar(){
        if (puedeDisparar) {
            proyectiles.add(new ProyectilDelJugador (obtenerPosicionEnX() + (ANCHO_NAVE / 2) - 8, obtenerPosicionEnY(), 10));
            puedeDisparar = false;
            temporizadorDisparo.start();
        }
    }

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

    public Rectangle obtenerHitbox() {
        return new Rectangle(movimiento.obtenerPosicionEnX(), movimiento.obtenerPosicionEnY(), ANCHO_NAVE, ANCHO_NAVE);
    }

    public void volverAlPuntoDeRespaw() {
        movimiento = new MovimientoNaveJugador(POSICIÓN_INICIAL_EN_X, POSICIÓN_INCIAL_EN_Y);
    }
}
