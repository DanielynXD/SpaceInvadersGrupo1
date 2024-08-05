package Logica.Naves.Enemigos;

public class CalaveraMágica extends NaveEnemigo {
    protected static final int PUNTOS_DEL_ENEMIGO = 20;

    public CalaveraMágica(int x, int y) {
        super(x, y, PUNTOS_DEL_ENEMIGO);
    }


//    @Override
//    public int obtenerVelocidad() {
//        return 1;
//    }
}
