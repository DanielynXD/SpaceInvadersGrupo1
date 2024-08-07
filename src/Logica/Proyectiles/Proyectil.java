package Logica.Proyectiles;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Proyectil {
    public static final int ANCHO_PROYECTIL = 16;
    private int posicionEnX;
    private int posicionEnY;
    private int velocidad;
    private boolean visible;

    public Proyectil(int x, int y, int velocidad) {
        this.posicionEnX = x;
        this.posicionEnY = y;
        this.velocidad = velocidad;
        visible = true;

    }

    public void mover() {
        posicionEnY -= velocidad;
        if (proyectilLlegoAlLimite()) {
            visible = false;
        }
    }

    private boolean proyectilLlegoAlLimite() {
        return posicionEnY < 0;
    }

    public int obtenerPosicionEnX(){
        return posicionEnX;
    }

    public int obtenerPosicionEnY(){
        return posicionEnY;
    }

    public boolean esVisible(){
        return visible;
    }

    public Rectangle obtenerHitBox() {
        return new Rectangle(posicionEnX, posicionEnY, ANCHO_PROYECTIL, ANCHO_PROYECTIL);//Funcion que permite obtener la hitbox
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void aumentarVelocidad(int velocidadAumentada) {
        int aux = velocidad;
        velocidad = velocidadAumentada;
        Timer timer = new Timer();
        TimerTask timerDos = new TimerTask() {
            @Override
            public void run() {
                velocidad = aux;
            }
        };
        timer.schedule(timerDos, 5000);
    }
}
