package Logica.Entidades.Modificadores;

import Logica.Entidades.Entidad;
import Logica.Entidades.Nave;
import Logica.Movimiento.MovimientoAbajo;

public abstract class Modificador extends Entidad {

    private MovimientoAbajo movimientoAbajo;
    private boolean visible;

    public Modificador(double posicionEnX, int posicionEnY) {
        super((int) posicionEnX, posicionEnY, 1, 16, 16);
        visible = true;
        this.movimientoAbajo = new MovimientoAbajo();

    }

    public int[] obtenerPosicion() {
        return new int[] {this.obtenerPosicionEnX(), this.obtenerPosicionEnY()};
    }

    public void mover() {
        movimientoAbajo.mover(this);
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public int obtenerVelocidad() {
        return 1;
    }

    public abstract void aplicarEfecto(Nave nave);

}
