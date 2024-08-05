package Logica.Naves.Enemigos;

public class PlatilloMalo extends NaveEnemigo {
    protected static final int PUNTOS_DEL_ENEMIGO = 30;
    public PlatilloMalo(int x, int y) {
        super(x, y, PUNTOS_DEL_ENEMIGO);
    }



//    @Override
//    public int obtenerVelocidad() {
//        return 1;
//    }
}
