package Logica.Movimiento;

import Logica.Entidades.Entidad;

public class MovimientoAbajo implements Movimiento{

    @Override
    public void mover(Entidad entidad) {
        entidad.fijarNuevaPosicionEnY(entidad.obtenerPosicionEnY() + entidad.obtenerVelocidad());
    }

}
