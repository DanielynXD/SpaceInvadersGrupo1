package Logica;

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


    public void actualizarEntidades(NaveJugador nave, ArrayList<NaveEnemigo> enemigos) throws InterruptedException {
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
        boolean cambiarDireccion = false;

        if (descendiendo) {
            for (NaveEnemigo enemigo : enemigos) {
                enemigo.descender();
            }
            unidadesDescendidas++;
            if (unidadesDescendidas >= (enemigos.get(0).obtenerAncho() / 3)) {
                descendiendo = false;
                unidadesDescendidas = 0;
            }
            return;
        }
        //todo: Modificar, ahora los enemigos si o si topan los bordes, TODOS
        for (NaveEnemigo enemigo : enemigos) {
            enemigo.mover(direccionMovimiento);
            if (enemigo.obtenerPosicionEnX() <= 0 || enemigo.obtenerPosicionEnX() >= 800 - enemigo.obtenerAncho() - 20) {//este 20 es para que los enemigos no sobrepasen el lado derecho
                cambiarDireccion = true;
            }
        }

        if (cambiarDireccion) {
            direccionMovimiento = -direccionMovimiento;
            descendiendo = true;
        }
    }

    private void actualizarNave(NaveJugador nave) {
        nave.mover();
    }


}


