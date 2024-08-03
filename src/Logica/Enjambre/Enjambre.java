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
    private boolean descendiendo = false;
    private int direccion = 0;
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
        //this.unidadesDescendidas = 0;
    }

    public void agregarEnjambre(int posicionEnX, int posicionEnY) {
        generarEnemigosDelEnjambre(posicionEnX, posicionEnY);
    }

    public void mover(){
        boolean cambiarDireccion = false;

        if (descendiendo) {
            for(NaveEnemigo naveEnemigo : enjambre){
                movimientoAbajo.mover(naveEnemigo);
            }
            unidadesDescendidas++;
            if (unidadesDescendidas >= 16) {
                descendiendo = false;
                unidadesDescendidas = 0;
            }
            return;
        }

        for (NaveEnemigo naveEnemigo : enjambre) {
            if (direccion == 1) {
                movimientoIzquierda.mover(naveEnemigo);
            } else {
                movimientoDerecha.mover(naveEnemigo);
            }
            cambiarDireccion = seDebeCambiarDireccion(naveEnemigo, cambiarDireccion);
        }

        if (cambiarDireccion) {
            direccion = (direccion == 1) ? 0 : 1;
            descendiendo = true;
        }

    }

    private static boolean seDebeCambiarDireccion(NaveEnemigo naveEnemigo, boolean cambiarDireccion) {
        if (naveEnemigo.obtenerPosicionEnX() <= 0 || naveEnemigo.obtenerPosicionEnX() > 732) {
            cambiarDireccion = true;
        }
        return cambiarDireccion;
    }

    public abstract void generarEnemigosDelEnjambre(int posicionEnX, int posicionEnY);

    public ArrayList<NaveEnemigo> obtenerEnjambreDeEnemigos() {
        return enjambre;
    }

}
