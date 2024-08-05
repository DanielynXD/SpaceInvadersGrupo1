package Logica.Naves.Enemigos;

public class GatoPlatillo extends NaveEnemigo {
    protected static final int PUNTOS_DEL_ENEMIGO = 10;

    public GatoPlatillo(int x, int y) {
        super(x, y, PUNTOS_DEL_ENEMIGO);
    }


//    @Override
//    public int obtenerVelocidad() {
//        return 1;
//    }
}
