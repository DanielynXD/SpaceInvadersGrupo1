package Logica;

import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.*;

public class NaveJugador extends Nave {

    public static final int POSICIÓN_INICIAL_EN_X = 350;
    public static final int POSICIÓN_INCIAL_EN_Y = 500;
    public static final int VELOCIDAD = 4;
    public static final int LIMITE_DERECHO = 785;
    public static final int ANCHO_NAVE = 64;
    private int posiciónEnX;
    private int posiciónEnY;
    private int distanciaDesplazada;
    private int velocidad;
    private final List<Proyectil> proyectiles;
    private boolean puedeDisparar;
    private Timer temporizadorDisparo;

    public NaveJugador() {

        proyectiles = new ArrayList<>();
        iniciarNave();
    }

    private void iniciarNave() {
        posiciónEnX = POSICIÓN_INICIAL_EN_X;
        posiciónEnY = POSICIÓN_INCIAL_EN_Y;
        velocidad = VELOCIDAD; // Velocidad de movimiento del jugador
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
        posiciónEnX += distanciaDesplazada;
        if (estaEnElLimiteIzquierdo()) {
            posiciónEnX = 0;
        }
        if (estaEnElLimiteDerecho()) {//limites x
            posiciónEnX = LIMITE_DERECHO - ANCHO_NAVE;
        }

    }

    private boolean estaEnElLimiteIzquierdo() {
        return posiciónEnX < 0;
    }

    private boolean estaEnElLimiteDerecho() {
        return posiciónEnX > 785 - ANCHO_NAVE;
    }

    public int obtenerPosicionEnX() {
        return posiciónEnX;
    }

    public int obtenerPosicionEnY() {
        return posiciónEnY;
    }

    @Override
    public Rectangle obtenerHitBox() {
        return new Rectangle(posiciónEnX, posiciónEnY, ANCHO_NAVE, ANCHO_NAVE); //hice un cast de int para la velocidad de la nave;
    }

    public List<Proyectil> obtenerProyectiles(){
        return proyectiles;
    }

    public void disparar(){
        if (puedeDisparar) {
            proyectiles.add(new Proyectil (posiciónEnX + (ANCHO_NAVE / 2) - 8, posiciónEnY));
            puedeDisparar = false;
            temporizadorDisparo.start();
        }
    }

    public void teclaPresionada(KeyEvent e) {
        int tecla = e.getKeyCode();

        if (sePulsaTeclaIzquierda(tecla)) {
            distanciaDesplazada = -velocidad;
        }

        if (sePulsaTeclaDerecha(tecla)) {
            distanciaDesplazada = velocidad;
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
            distanciaDesplazada = 0;
        }

    }

    public Rectangle obtenerHitbox() {
        return new Rectangle(posiciónEnX, posiciónEnY, ANCHO_NAVE, ANCHO_NAVE);
    }
}