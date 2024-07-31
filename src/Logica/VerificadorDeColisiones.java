package Logica;

import Logica.Naves.Enemigos.NaveEnemigo;
import Logica.Naves.Jugador.NaveJugador;
import Logica.Proyectiles.Proyectil;
import Logica.Proyectiles.ProyectilDelEnemigo;
import Logica.Proyectiles.ProyectilDelJugador;
import Presentacion.PanelDeJuego;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VerificadorDeColisiones {

    private PanelDeJuego panelDeJuego;

    public VerificadorDeColisiones(PanelDeJuego panelDeJuego) {
        this.panelDeJuego = panelDeJuego;
    }

    public void verificarColisiones (NaveJugador nave, ArrayList<NaveEnemigo> enemigos) {
        Rectangle hitboxNave = nave.obtenerHitbox();
        java.util.List<ProyectilDelJugador> proyectiles = nave.obtenerProyectiles();
        List<NaveEnemigo> enemigosAEliminar = new ArrayList<>();//almacena a los enemigos a eliminar


        verificarColisionesEnemigosYProyectilesDelJugador(proyectiles, enemigosAEliminar, enemigos);
        verificarColisionesJugadorYProyectilEnemigo(hitboxNave, enemigos, nave);
        verificarColisionesProyectilYProyectilEnemigo(proyectiles, enemigos);
    }


    public void verificarColisionesEnemigosYProyectilesDelJugador(List<ProyectilDelJugador> proyectiles,
                                                                  List<NaveEnemigo> enemigosAEliminar, List<NaveEnemigo> enemigos) {
        for (Proyectil proyectil : proyectiles) {
            Rectangle hitboxProyectil = proyectil.obtenerHitBox();
            for (int i = 0; i < enemigos.size(); i++) {
                NaveEnemigo enemigo = enemigos.get(i);
                Rectangle hitboxEnemigo = enemigo.obtenerHitBox();

                if (hitboxProyectil.intersects(hitboxEnemigo)) {
                    proyectil.setVisible(false);
                    enemigosAEliminar.add(enemigo);//añade al enemigo a la lista
                }
            }
        }
        enemigos.removeAll(enemigosAEliminar);//elimina a los enemigos en la lista
    }

    private void verificarColisionesJugadorYProyectilEnemigo(Rectangle hitboxNave, List<NaveEnemigo> enemigos, NaveJugador nave) {
        for (NaveEnemigo enemigo : enemigos) {
            Rectangle hitboxEnemigo = enemigo.obtenerHitBox();
            List<ProyectilDelEnemigo> proyectilesEnemigo = enemigo.obtenerProyectiles();
            for (ProyectilDelEnemigo proyectilEnemigo : proyectilesEnemigo) {
                Rectangle hitboxProyectilEnemigo = proyectilEnemigo.obtenerHitBox();
                if (hitboxNave.intersects(hitboxEnemigo)) {
                    System.exit(0); // Termina el juego porque lo tocaron
                }
                if (hitboxNave.intersects(hitboxProyectilEnemigo)) {
                    panelDeJuego.limpiarProyectilesDeLaVentana();
                    panelDeJuego.pausaDeReaparicion();
                    nave.numeroDeVidas--;
                    if (nave.numeroDeVidas == 0) {
                        System.exit(0); // Termina el juego porque pierde las 3 vidas
                    }
                }
            }
        }
    }

    private void verificarColisionesProyectilYProyectilEnemigo(List<ProyectilDelJugador> proyectiles, ArrayList<NaveEnemigo> enemigos) {
        for (NaveEnemigo enemigo : enemigos) {
            List<ProyectilDelEnemigo> proyectilesEnemigo = enemigo.obtenerProyectiles();
            for (ProyectilDelEnemigo proyectilEnemigo : proyectilesEnemigo) {
                Rectangle hitboxProyectilEnemigo = proyectilEnemigo.obtenerHitBox();
                for (ProyectilDelJugador proyectilJugador : proyectiles) {
                    if (hitboxProyectilEnemigo.intersects(proyectilJugador.obtenerHitBox())) {
                        proyectilJugador.setVisible(false);
                        proyectilEnemigo.setVisible(false);
                    }
                }
            }
        }
    }

}
