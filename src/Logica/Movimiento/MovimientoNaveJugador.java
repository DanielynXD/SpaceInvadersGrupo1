package Logica.Movimiento;

import Logica.Naves.Nave;

public class MovimientoNaveJugador implements Movimiento {
    private int posicionEnX;
    private final int posicionEnY;
    private int distanciaDesplazada;
    private final int LIMITE_DERECHO = 785;
    private final int ANCHO_NAVE = 64;

    public MovimientoNaveJugador(int posicionInicialEnX, int posicionInicialEnY){
        this.posicionEnX = posicionInicialEnX;
        this.posicionEnY = posicionInicialEnY;
    }

    //@Override
    public void mover() {
        posicionEnX += distanciaDesplazada;
        if (estaEnElLimiteDerecho()) {
            posicionEnX = LIMITE_DERECHO - ANCHO_NAVE;
        }
        if (estaEnElLimiteIzquierdo()) {
            posicionEnX = 0;
        }
    }

    private boolean estaEnElLimiteIzquierdo() {
        return posicionEnX < 0;
    }

    private boolean estaEnElLimiteDerecho() {
        return posicionEnX > LIMITE_DERECHO - ANCHO_NAVE;
    }

    @Override
    public void mover(Nave entidad) {

    }

    @Override
    public int obtenerPosicionEnX() {
        return posicionEnX;
    }

    @Override
    public int obtenerPosicionEnY() {
        return posicionEnY;
    }

    public void fijarDistanciaDesplazada(int distanciaDesplazada){
        this.distanciaDesplazada = distanciaDesplazada;
    }
}
