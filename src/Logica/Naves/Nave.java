package Logica.Naves;

import Logica.Entidad;

import java.awt.*;


public abstract class Nave extends Entidad {
    public Nave(int posicionEnX, int posicionEnY, int velocidad, int ancho, int alto) {
        super(posicionEnX, posicionEnY, velocidad, ancho, alto);
    }

//    public abstract int obtenerPosicionEnX();
//    public abstract int obtenerPosicionEnY();
//    public abstract Rectangle obtenerHitBox();
    protected abstract void disparar();

    public Rectangle obtenerHitBox() {
        return super.obtenerHitbox();
    }
//    public abstract int obtenerVelocidad();
//    public abstract void fijarNuevaPosicionEnX(int posicionEnX);
//    public abstract void fijarNuevaPosicionEnY(int posicionEnY);
}