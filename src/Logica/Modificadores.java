package Logica;

import Logica.Movimiento.MovimientoAbajo;
import Logica.Naves.Nave;

import java.awt.*;

public class Modificadores extends Entidad {


    private MovimientoAbajo movimientoAbajo;
//    private double posicionEnX;
//    private int posicionEnY;

    public Modificadores(double posicionEnX, int posicionEnY) {
        super(posicionEnY, posicionEnY, 1, 16, 16);
//        this.posicionEnX = posicionEnX;
//        this.posicionEnY = posicionEnY;
        this.movimientoAbajo = new MovimientoAbajo((int)posicionEnX, posicionEnY);

    }

    public int[] obtenerPosicion() {
        return new int[] {this.obtenerPosicionEnX(), this.obtenerPosicionEnY()};
    }

    public void mover() {
        movimientoAbajo.mover(this);
    }


//    @Override
//    public int obtenerPosicionEnX() {
//        return (int)posicionEnX;
//    }
//
//    @Override
//    public int obtenerPosicionEnY() {
//        return posicionEnY;
//    }
//
//    @Override
//    public Rectangle obtenerHitBox() {
//        return null;
//    }

//    @Override
//    protected void disparar() {
//
//    }

    @Override
    public int obtenerVelocidad() {
        return 1;
    }

//    @Override
//    public void fijarNuevaPosicionEnX(int posicionEnX) {
//        this.posicionEnX = posicionEnX;
//    }
//
//    @Override
//    public void fijarNuevaPosicionEnY(int posicionEnY) {
//        this.posicionEnY = posicionEnY;
//    }
}
