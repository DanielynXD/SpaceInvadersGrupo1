package Logica;
import java.awt.*;
import java.util.List;

public abstract class Nave {

    public abstract int obtenerPosicionEnX();
    public abstract int obtenerPosicionEnY();
    public abstract Rectangle obtenerHitBox();
    protected abstract void disparar();

}

