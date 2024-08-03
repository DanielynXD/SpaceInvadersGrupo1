package Logica.Naves;

import java.awt.*;

public abstract class Nave {
    public abstract int obtenerPosicionEnX();
    public abstract int obtenerPosicionEnY();
    public abstract Rectangle obtenerHitBox();
    protected abstract void disparar();
    public abstract int obtenerVelocidad();
    public abstract void fijarNuevaPosicionEnX(int posicionEnX);
    public abstract void fijarNuevaPosicionEnY(int posicionEnY);
}