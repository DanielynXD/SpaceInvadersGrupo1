package Logica.Naves.Enemigos;

public class GatoPlatillo extends NaveEnemigo {

    public GatoPlatillo(int x, int y) {
        super(x, y);
    }


    @Override
    public int obtenerVelocidad() {
        return 1;
    }
}
