package Logica.Movimiento;

import Logica.Entidad;
import Logica.Naves.Nave;

public class MovimientoIzquierda implements Movimiento{

    private final int posicionEnX;
    private final int posicionEnY;
    private int direccion;

    public MovimientoIzquierda(int posicionEnX, int posicionEnY) {
        this.posicionEnX = posicionEnX;
        this.posicionEnY = posicionEnY;
        this.direccion = 1;
    }

    @Override
    public void mover(Entidad entidad) {
        entidad.fijarNuevaPosicionEnX(entidad.obtenerPosicionEnX() + entidad.obtenerVelocidad() * -direccion);
    }

    @Override
    public int obtenerPosicionEnX() {
        return posicionEnX;
    }

    @Override
    public int obtenerPosicionEnY() {
        return posicionEnY;
    }
}