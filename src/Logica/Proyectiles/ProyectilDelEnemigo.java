package Logica.Proyectiles;

public class ProyectilDelEnemigo extends Proyectil {
    public ProyectilDelEnemigo(int x, int y, int velocidad) {
        super(x, y, -velocidad);
    }
}
