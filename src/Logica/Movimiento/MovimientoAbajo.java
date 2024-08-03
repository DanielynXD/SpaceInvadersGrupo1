package Logica.Movimiento;

import Logica.Naves.Nave;

public class MovimientoAbajo implements Movimiento{

    private final int posicionEnX;
    private final int posicionEnY;

    public MovimientoAbajo(int posicionEnX, int posicionEnY) {

        this.posicionEnX = posicionEnX;
        this.posicionEnY = posicionEnY;
    }

    @Override
    public void mover(Nave entidad) {
        entidad.fijarNuevaPosicionEnY(entidad.obtenerPosicionEnY() + entidad.obtenerVelocidad());
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
