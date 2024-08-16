package Logica.Movimiento;

import Logica.Entidades.Enemigos.NaveEnemigo;
import Logica.Entidades.Entidad;

import java.util.ArrayList;

public class MovimientoEnjambre implements Movimiento {

    public static final int DIRECCION_IZQUIERDA = 1;
    public static final int DIRECCION_DERECHA = 0;
    public static final int LIMITE_INICIAL_EN_X = 0;
    public static final int LIMITE_FINAL_EN_X = 732;
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
        this.direccion = DIRECCION_IZQUIERDA;
        this.descendiendo = false;
        this.unidadesDescendidas = 0;
        this.enjambre = enjambre;
    }

    @Override
    public void mover(Entidad enemigo) {
        boolean cambiarDireccion = false;

        if (descendiendo) {
            for (NaveEnemigo naveEnemigo : enjambre) {
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
            if (direccion == DIRECCION_IZQUIERDA) {
                movimientoIzquierda.mover(naveEnemigo);
            } else {
                movimientoDerecha.mover(naveEnemigo);
            }

            if (naveEnemigo.obtenerPosicionEnX() <= LIMITE_INICIAL_EN_X || naveEnemigo.obtenerPosicionEnX() > LIMITE_FINAL_EN_X) {
                cambiarDireccion = true;
            }
        }

        if (cambiarDireccion) {
            direccion = (direccion == DIRECCION_IZQUIERDA) ? DIRECCION_DERECHA : DIRECCION_IZQUIERDA;
            descendiendo = true;
        }
    }

}
