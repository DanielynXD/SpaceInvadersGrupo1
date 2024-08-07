package Logica.ControlesDeSistema;

import Logica.Entidades.Modificadores.Modificador;
import Logica.Entidades.Enemigos.NaveEnemigo;
import Logica.Entidades.Jugador.NaveJugador;
import Logica.Proyectiles.Proyectil;
import Logica.Proyectiles.ProyectilDelEnemigo;
import Logica.Proyectiles.ProyectilDelJugador;
import Presentacion.AdministradorGeneral;
import Presentacion.VentanaFinDeJuego;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VerificadorDeColisiones {
    private AdministradorGeneral administrador;
    private boolean ventanaFinDeJuegoAbierta = false;
    public int puntajeTotal;

    public VerificadorDeColisiones(AdministradorGeneral administrador) {
        this.administrador = administrador;
        puntajeTotal = 0;
    }

    public void verificarColisiones (NaveJugador nave, ArrayList<NaveEnemigo> enemigos, ArrayList<Modificador> modificadores) {
        Rectangle hitboxNave = nave.obtenerHitbox();
        java.util.List<ProyectilDelJugador> proyectiles = nave.obtenerProyectiles();

        verificarColisionesEnemigosYProyectilesDelJugador(proyectiles, enemigos);
        verificarColisionesJugadorYProyectilEnemigo(hitboxNave, enemigos, nave);
        verificarColisionesProyectilYProyectilEnemigo(proyectiles, enemigos);
        verificarColisionesJugardorModificador(nave, modificadores);
        verificarSiEnemigoLlegoAlLimiteInferior(enemigos);
    }

    private void verificarSiEnemigoLlegoAlLimiteInferior(ArrayList<NaveEnemigo> enemigos) {
        for (NaveEnemigo enemigo: enemigos){
            if (llegoAlLimiteInferior(enemigo)) {
                generarVentanaFinDelJuego(puntajeTotal);
            }
        }
    }

    private void verificarColisionesJugardorModificador(NaveJugador nave, ArrayList<Modificador> modificadores) {

        ArrayList<Modificador> modificadoresPorEliminar = new ArrayList<>();
        for(Modificador modificador : modificadores) {
            if(modificador.obtenerHitbox().intersects(nave.obtenerHitbox())){
                nave.aplicarModificador(modificador);
                modificadoresPorEliminar.add(modificador);
                modificador.setVisible(false);
            }
        }
        modificadores.removeAll(modificadoresPorEliminar);
    }

    private void verificarColisionesEnemigosYProyectilesDelJugador(List<ProyectilDelJugador> proyectiles, List<NaveEnemigo> enemigos) {
        List<NaveEnemigo> enemigosAEliminar = new ArrayList<>();//almacena a los enemigos a eliminar
        for (Proyectil proyectil : proyectiles) {
            Rectangle hitboxProyectil = proyectil.obtenerHitBox();
            for (int i = 0; i < enemigos.size(); i++) {
                NaveEnemigo enemigo = enemigos.get(i);
                Rectangle hitboxEnemigo = enemigo.obtenerHitBox();
                if (hitboxProyectil.intersects(hitboxEnemigo)) {
                    proyectil.setVisible(false);
                    puntajeTotal += enemigo.getPuntosDelEnemigo();
                    enemigosAEliminar.add(enemigo);//añade al enemigo a la lista
                    administrador.agregarModificador(enemigo.generarModificador());
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
                    administrador.detenerMusica();
                    generarVentanaFinDelJuego(puntajeTotal);
                    return;
                }

                if (hitboxNave.intersects(hitboxProyectilEnemigo)) {
                    administrador.limpiarProyectilesDeLaVentana();
                    administrador.pausaDeReaparicion();
                    nave.numeroDeVidas--;
                    if (nave.numeroDeVidas == 0) {
                        generarVentanaFinDelJuego(getPuntajeTotal());
                        return;
                    }
                    return;
                }
            }
        }
    }

    public void generarVentanaFinDelJuego(int puntajeTotal) {
        if (!ventanaFinDeJuegoAbierta) {
            administrador.detenerMusica();
            ventanaFinDeJuegoAbierta = true; // Evitar abrir múltiples ventanas
            administrador.getJFrame().dispose();
            new VentanaFinDeJuego(puntajeTotal);
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

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    private boolean llegoAlLimiteInferior(NaveEnemigo enemigo) {
        return enemigo.obtenerPosicionEnY() > 600 - enemigo.obtenerAncho() * 2;
    }


    public void actualizarPuntaje(int i) {
        puntajeTotal = i;
    }
}
