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

    public void actualizarEntidades(NaveJugador nave, ArrayList<NaveEnemigo> enemigos, ArrayList<Modificadores> modificadores, Enjambre ... enjambres) throws InterruptedException {
        actualizarProyectilesDelEnemigo(enemigos);
        actualizarProyectilesDelJugador(nave);
        //actualizarEnemigos(enemigos);
        actualizarNave(nave);
        actualizarEnjambres(enjambres);
        actualizarModificadores(modificadores);
    }

    private void actualizarModificadores(ArrayList<Modificadores> modificadores) {
        for (Modificadores modificador : modificadores) {
            modificador.mover();
        }
    }

    private void actualizarEnjambres(Enjambre[] enjambres) {
        for(Enjambre enjambre : enjambres) {
            enjambre.mover();
        }
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


    private void actualizarNave(NaveJugador nave) {
        nave.mover();
    }


}


