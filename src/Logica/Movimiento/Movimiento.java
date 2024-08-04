package Logica.Movimiento;

import Logica.Entidad;
import Logica.Naves.Nave;

public interface Movimiento {
    void mover(Entidad entidad);
    int obtenerPosicionEnX();
    int obtenerPosicionEnY();
}
