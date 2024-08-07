package Logica.Movimiento;

import Logica.Entidades.Enemigos.NaveEnemigo;
import Logica.Entidades.Entidad;

import java.util.ArrayList;

public class MovimientoEnjambre implements Movimiento{

    private final ArrayList<NaveEnemigo> enjambre;
    Movimiento movimientoIzquierda;
    Movimiento movimientoDerecha;
    Movimiento movimientoAbajo;
    private int direccion;
    private boolean descendiendo;
    private int unidadesDescendidas;


    public MovimientoEnjambre(ArrayList<NaveEnemigo> enjambre) {
        this.movimientoDerecha = new MovimientoDerecha();
        this.movimientoIzquierda = new MovimientoIzquierda();
        this.movimientoAbajo = new MovimientoAbajo();
        this.direccion = 1;
        this.descendiendo = false;
        this.unidadesDescendidas = 0;
        this.enjambre = enjambre;
    }

    @Override
    public void mover(Entidad enemigo) {
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

            if (naveEnemigo.obtenerPosicionEnX() <= 0 || naveEnemigo.obtenerPosicionEnX() > 732) {
                cambiarDireccion = true;
            }
        }

        if (cambiarDireccion) {
            direccion = (direccion == 1) ? 0 : 1;
            descendiendo = true;
        }
    }

}
