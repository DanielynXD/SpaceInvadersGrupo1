package Logica;

import Logica.Enjambre.Enjambre;
import Logica.Naves.Enemigos.NaveEnemigo;
import Logica.Naves.Jugador.NaveJugador;
import Logica.Proyectiles.Proyectil;
import Logica.Proyectiles.ProyectilDelEnemigo;
import Logica.Proyectiles.ProyectilDelJugador;

import java.util.ArrayList;
import java.util.List;

public class ActualizadorEntidades {
    private int unidadesDescendidas;
    private boolean descendiendo = false;
    private int direccionMovimiento = 1;


    public void actualizarEntidades(NaveJugador nave, ArrayList<NaveEnemigo> enemigos, Enjambre enjambre) throws InterruptedException {
        actualizarProyectilesDelEnemigo(enemigos);
        actualizarProyectilesDelJugador(nave);
        actualizarEnemigos(enemigos);
        actualizarNave(nave);
    }

    private void actualizarProyectilesDelEnemigo(ArrayList<NaveEnemigo> enemigos) {
        for (NaveEnemigo enemigo : enemigos) {
            List<ProyectilDelEnemigo> proyectiles = enemigo.obtenerProyectiles();
            for (int i = 0; i < proyectiles.size(); i++) {
                Proyectil proyectil = proyectiles.get(i);
                if (proyectil.esVisible()) {
                    proyectil.mover();
                } else {
                    proyectiles.remove(i);
                }
            }
        }
    }

    private void actualizarProyectilesDelJugador(NaveJugador nave) {
        List<ProyectilDelJugador> proyectiles = nave.obtenerProyectiles();
        for (int i = 0; i < proyectiles.size(); i++) {
            Proyectil proyectil = proyectiles.get(i);
            if (proyectil.esVisible()) {
                proyectil.mover();
            } else {
                proyectiles.remove(i);
            }
        }
    }

    private void actualizarEnemigos(ArrayList<NaveEnemigo> enemigos) throws InterruptedException {
        for (NaveEnemigo enemigo : enemigos) {
            enemigo.mover();
        }
    }

    private void actualizarNave(NaveJugador nave) {
        nave.mover();
    }


}


