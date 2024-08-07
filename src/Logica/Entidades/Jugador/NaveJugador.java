package Logica.Entidades.Jugador;

import Logica.ControlesDeSistema.ControladorDeTeclas;
import Logica.Entidades.Modificadores.Modificador;
import Logica.Movimiento.MovimientoNaveJugador;
import Logica.Entidades.Nave;
import Logica.Proyectiles.ProyectilDelJugador;
import Presentacion.ReproductorMúsica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NaveJugador extends Nave {

    public static final int ANCHO_NAVE = 64;
    public int numeroDeVidas;
    private MovimientoNaveJugador movimiento;
    private final List<ProyectilDelJugador> proyectiles;
    private boolean puedeDisparar;
    private Timer temporizadorDisparo;
    private ReproductorMúsica sonidoDisparo;
    private int velocidadDisparo = 1000;
    private java.util.Timer timer;

    public NaveJugador() {
        super(350, 500, 4, 64, 64);
        proyectiles = new ArrayList<>();
        movimiento = new MovimientoNaveJugador();
        numeroDeVidas = 3;
        timer = new java.util.Timer();
        iniciarNave();
    }

    private void iniciarNave() {
        puedeDisparar = true;
        sonidoDisparo = new ReproductorMúsica("src/Presentacion/MúsicaYSonido/DisparoNave.wav");

//        temporizadorDisparo = new Timer(velocidadDisparo, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                puedeDisparar = true;
//                temporizadorDisparo.stop();
//            }
//        });
    }

    public void mover() {
       movimiento.mover(this);
    }

    public void fijarDistanciaDesplazada(int distanciaDesplazada){
        movimiento.fijarDistanciaDesplazada(distanciaDesplazada);
    }

    public void aplicarModificador(Modificador modificador){
        //numeroDeVidas += 1;
        modificador.aplicarEfecto(this);
    }

    public List<ProyectilDelJugador> obtenerProyectiles(){
        return proyectiles;
    }

    public void disparar(){
        temporizadorDisparo = new Timer(velocidadDisparo, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puedeDisparar = true;
                temporizadorDisparo.stop();
            }
        });
        if (puedeDisparar) {
            proyectiles.add(new ProyectilDelJugador (obtenerPosicionEnX() + (ANCHO_NAVE / 2) - 8, obtenerPosicionEnY(), 10));
            sonidoDisparo.reproducir();
            puedeDisparar = false;
            temporizadorDisparo.start();
        }
    }

    public void volverAlPuntoDeRespawn() {
        this.fijarNuevaPosicionEnX(350);
    }

    public int obtenerVidasDisponibles() {
        return numeroDeVidas;
    }



    public void aumentarVelocidadDeDisparo(){
        System.out.println("Se incremento la velicidad");

        this.velocidadDisparo = 1;
        TimerTask timerDos = new TimerTask() {

            @Override
            public void run() {
                restablecerVelocidadDeDisparo();
                System.out.println("Se reestablecio la velocidad normal");
                actualizarValores();
            }
        };
        timer.schedule(timerDos,2000);


        //restablecerVelocidadThread.start();

//        this.velocidadDisparo = 1;
//
//        // Crear un programador
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//
//        // Programar la tarea para que se ejecute después de 5 segundos
//        scheduler.schedule(() -> this.restablecerVelocidadDeDisparo(), 2, TimeUnit.SECONDS);
//
//        // Cerrar el scheduler después de programar la tarea para evitar la fuga de recursos
//        scheduler.shutdown();

    }

    public void restablecerVelocidadDeDisparo() {
        this.velocidadDisparo = 500000;
    }

    public void actualizarValores() {
        temporizadorDisparo.setDelay(velocidadDisparo);
    }

    public void actualizarNumeroDeVidas(int i) {
        numeroDeVidas = i;
    }
}
