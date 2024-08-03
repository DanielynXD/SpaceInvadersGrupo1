package Logica.Enjambre;

import Logica.Movimiento.MovimientoAbajo;
import Logica.Movimiento.MovimientoDerecha;
import Logica.Movimiento.MovimientoIzquierda;
import Logica.Naves.Enemigos.NaveEnemigo;

import java.util.ArrayList;

public abstract class Enjambre {
    MovimientoDerecha movimientoDerecha;
    MovimientoIzquierda movimientoIzquierda;
    MovimientoAbajo movimientoAbajo;
    protected final int posicionEnemigoEnX;
    protected int posiciónEnemigoEnY;
    protected int numeroFilas;
    protected final int numeroColumnas;
    protected final NaveEnemigo enemigo;
    ArrayList<NaveEnemigo> enjambre = new ArrayList<>();
    protected int numeroFilasGenerado;
    private boolean descendiendo;
    private int unidadesDescendidas;

    public Enjambre(int numeroFilas, int numeroColumnas, NaveEnemigo enemigo) {
        this.enemigo = enemigo;
        this.posicionEnemigoEnX = enemigo.obtenerPosicionEnX();
        this.posiciónEnemigoEnY = enemigo.obtenerPosicionEnY();
        this.numeroFilas = numeroFilas;
        this.numeroFilasGenerado = 1;
        this.numeroColumnas = numeroColumnas;
        this.movimientoDerecha = new MovimientoDerecha(enemigo.obtenerPosicionEnX(), enemigo.obtenerPosicionEnY());
        this.movimientoIzquierda = new MovimientoIzquierda(enemigo.obtenerPosicionEnX(), enemigo.obtenerPosicionEnY());
        this.movimientoAbajo = new MovimientoAbajo(enemigo.obtenerPosicionEnX(), enemigo.obtenerPosicionEnY());
        this.descendiendo = false;
        this.unidadesDescendidas = 0;
    }

    public void agregarEnjambre(int posicionEnX, int posicionEnY) {
        generarEnemigosDelEnjambre(posicionEnX, posicionEnY);
    }

    public void mover(){
        boolean cambiarDireccion = false;
        if (descendiendo) {
            for (int i = 0; i < enjambre.size(); i++) {
                movimientoAbajo.mover(enjambre.get(i));
            }
            unidadesDescendidas++;
            if (unidadesDescendidas >= 16) {
                descendiendo = false;
                unidadesDescendidas = 0;
            }
            return;
        }
        for(int i = 0; i < enjambre.size(); i++){
            movimientoDerecha.mover(enjambre.get(i));
        }
        if (enemigo.obtenerPosicionEnX() <= 0 || enemigo.obtenerPosicionEnX() > 732) {//este 20 es para que los enemigos no sobrepasen el lado derecho
            cambiarDireccion = true;
        }
        if (cambiarDireccion) {
            for (int i = 0; i < enjambre.size(); i++) {
                movimientoIzquierda.mover(enjambre.get(i));
            }
            descendiendo = true;
        }
    }

    public abstract void generarEnemigosDelEnjambre(int posicionEnX, int posicionEnY);

    public ArrayList<NaveEnemigo> obtenerEnjambreDeEnemigos() {
        return enjambre;
    }

}
