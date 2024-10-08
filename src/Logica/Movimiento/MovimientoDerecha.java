package Logica.Movimiento;

import Logica.Entidades.Entidad;

public class MovimientoDerecha implements Movimiento {

    private int direccion;

    public MovimientoDerecha() {
        this.direccion = 1;
    }

    @Override
    public void mover(Entidad entidad) {
        entidad.fijarNuevaPosicionEnX(entidad.obtenerPosicionEnX() + entidad.obtenerVelocidad() * direccion);
    }
}
