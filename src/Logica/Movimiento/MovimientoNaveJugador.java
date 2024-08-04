package Logica.Movimiento;

import Logica.Entidad;

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

//    //@Override
//    public void mover() {
//        posicionEnX += distanciaDesplazada;
//        if (estaEnElLimiteDerecho()) {
//            posicionEnX = LIMITE_DERECHO - ANCHO_NAVE;
//        }
//        if (estaEnElLimiteIzquierdo()) {
//            posicionEnX = 0;
//        }
//    }

    private boolean estaEnElLimiteIzquierdo(Entidad entidad) {
        return entidad.obtenerPosicionEnX() < 0;
    }

    private boolean estaEnElLimiteDerecho(Entidad entidad) {
        return entidad.obtenerPosicionEnX() > LIMITE_DERECHO - ANCHO_NAVE;
    }

    @Override
    public void mover(Entidad entidad) {

//        if(distanciaDesplazada>0){
//            entidad.fijarNuevaPosicionEnX(entidad.obtenerPosicionEnX() + distanciaDesplazada);
//        }else{
//            entidad.fijarNuevaPosicionEnX(entidad.obtenerPosicionEnX() + distanciaDesplazada);
//        }
        //posicionEnX += distanciaDesplazada;
        if (estaEnElLimiteDerecho(entidad)) {
            entidad.fijarNuevaPosicionEnX(LIMITE_DERECHO-ANCHO_NAVE);
            //posicionEnX = LIMITE_DERECHO - ANCHO_NAVE;
        }
        if (estaEnElLimiteIzquierdo(entidad)) {
            //posicionEnX = 0;
            entidad.fijarNuevaPosicionEnX(0);
        }
        entidad.fijarNuevaPosicionEnX(entidad.obtenerPosicionEnX() + distanciaDesplazada);
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
