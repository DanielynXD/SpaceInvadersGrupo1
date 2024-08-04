package Logica.Movimiento;

import Logica.Entidad;
import Logica.Naves.Enemigos.NaveEnemigo;
import Logica.Naves.Nave;

public class MovimientoEnemigo implements Movimiento{

    Movimiento movimientoIzquierda;
    Movimiento movimientoDerecha;
    Movimiento movimientoAbajo;
    private static final int DISTANCIA_DE_DESCENSO = 1;
    private int posicionEnY;
    private double posicionEnX;
    private int direccion;
    private double velocidad = 0.5;
    private boolean descendiendo;
    private boolean cambiarDireccion = false;
    private int unidadesDescendidas;


    public MovimientoEnemigo(int posicionInicialEnX, int posicionInicialEnY) {
        this.posicionEnX = posicionInicialEnX;
        this.posicionEnY = posicionInicialEnY;
        this.movimientoDerecha = new MovimientoDerecha((int)posicionEnX,posicionEnY);
        this.movimientoIzquierda = new MovimientoIzquierda((int)posicionEnX,posicionEnY);
        this.movimientoAbajo = new MovimientoAbajo((int)posicionEnX,posicionEnY);
        this.direccion = 1;
        this.descendiendo = false;
        this.unidadesDescendidas = 0;
    }

    @Override
    public void mover(Entidad enemigo) {
        if (descendiendo) {
            movimientoAbajo.mover(enemigo);
            unidadesDescendidas++;
            if (unidadesDescendidas >= DISTANCIA_DE_DESCENSO) {
                descendiendo = false;
                unidadesDescendidas = 0;
            }
            //enemigo.actualizarHitBox();
            return;
        }
        movimientoDerecha.mover(enemigo);
        if (enemigo.obtenerPosicionEnX() <= 0 || enemigo.obtenerPosicionEnX() > 732) {//este 20 es para que los enemigos no sobrepasen el lado derecho
            cambiarDireccion = true;
        }
        if (cambiarDireccion) {
            movimientoIzquierda.mover(enemigo);
            descendiendo = true;
        }
        //enemigo.actualizarHitBox();
    }


//    @Override
//    public void moverIzquierda() {
//        posicionEnX -= velocidad * direccion;
//    }
//
//    @Override
//    public void moverAbajo() {
//        posicionEnY += 1;
//    }

    @Override
    public int obtenerPosicionEnX() {
        return (int)posicionEnX;
    }

    @Override
    public int obtenerPosicionEnY() {
        return posicionEnY;
    }

}
