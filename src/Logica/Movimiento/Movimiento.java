package Logica.Movimiento;

import Logica.Naves.Nave;

public interface Movimiento {
    void mover(Nave ... entidad);
    int obtenerPosicionEnX();
    int obtenerPosicionEnY();
}
