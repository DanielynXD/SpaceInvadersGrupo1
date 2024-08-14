package Logica.Proyectiles;

import Logica.Entidades.Entidad;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Proyectil extends Entidad {
    public static final int ANCHO_PROYECTIL = 16;
    private boolean visible;

    public Proyectil(int x, int y, int velocidad) {
        super(x, y, velocidad, ANCHO_PROYECTIL, ANCHO_PROYECTIL);
        visible = true;

    }

    public void mover() {
        fijarNuevaPosicionEnY(obtenerPosicionEnY() - obtenerVelocidad());
        if (proyectilLlegoAlLimite()) {
            visible = false;
        }
    }

    private boolean proyectilLlegoAlLimite() {
        return obtenerPosicionEnY() < 0;
    }


    public boolean esVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }


}
