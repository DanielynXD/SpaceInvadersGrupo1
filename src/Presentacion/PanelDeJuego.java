package Presentacion;

import Logica.ActualizadorEntidades;
import Logica.Enjambre.EnjambreDeCalaverasMágicas;
import Logica.Enjambre.EnjambreDeGatosPlatillos;
import Logica.Modificadores;
import Logica.Naves.Enemigos.CalaveraMágica;
import Logica.Naves.Enemigos.GatoPlatillo;
import Logica.Naves.Enemigos.PlatilloMalo;
import Logica.Naves.Enemigos.NaveEnemigo;
import Logica.Enjambre.Enjambre;
import Logica.Enjambre.EnjambreDePlatillosMalos;
import Logica.Naves.Jugador.NaveJugador;
import Logica.Proyectiles.Proyectil;
import Logica.Proyectiles.ProyectilDelEnemigo;
import Logica.Proyectiles.ProyectilDelJugador;
import Logica.VerificadorDeColisiones;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;
import java.util.List;
import javax.swing.Timer;

public class PanelDeJuego extends JPanel implements ActionListener {

    public static final int ANCHO = 800, ALTO = 600;
    private Timer temporizador;
    private NaveJugador nave;
    private Enjambre enjambre1, enjambre2, enjambre3;
    private ArrayList<NaveEnemigo> enemigos;
    private VerificadorDeColisiones verificadorDeColisiones;
    private int posicioInicialDelEnemigoEnX = 50;
    private int posicioInicialDelEnemigoEnY = 50;
    private Pintor pintor;
    private ActualizadorEntidades actualizadorEntidades;
    private ArrayList<Modificadores> modificadores;
    private JFrame jFrame;
    private int puntajeTotal;
    private ReproductorMúsica reproductorDeMúsica;

    public PanelDeJuego() {
        this.jFrame = jFrame;
        iniciarPanel();
        pintor = new Pintor(this, verificadorDeColisiones);
    }

    public PanelDeJuego(JFrame jFrame) {
        this.jFrame = jFrame;
        puntajeTotal = 0;
        iniciarPanel();
        pintor = new Pintor(this,verificadorDeColisiones);
    }

    public VerificadorDeColisiones getVerificadorDeColisiones() {
        return verificadorDeColisiones;
    }

    private void iniciarPanel() {
        setFocusable(true);
        setSize(ANCHO, ALTO);
        nave = new NaveJugador();
        addKeyListener(new TAdapter());
        enemigos = new ArrayList<>();//inicializa el array de enemigos
        verificadorDeColisiones = new VerificadorDeColisiones(this);
        enjambre1 = new EnjambreDePlatillosMalos(1, 10, new PlatilloMalo(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY));
        enjambre2 = new EnjambreDeCalaverasMágicas(2, 10, new CalaveraMágica(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY));
        enjambre3 = new EnjambreDeGatosPlatillos(2, 10, new GatoPlatillo(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY));

        agregarEnemigos();//agrega enemigos

        temporizador = new Timer(10, this);
        temporizador.start();
        actualizadorEntidades = new ActualizadorEntidades();
        modificadores = new ArrayList<>();

        reproductorDeMúsica = new ReproductorMúsica("src/Presentacion/MúsicaYSonido/OverThinker.wav");
        reproductorDeMúsica.reproducir();
    }

    private void agregarEnemigos() {
        enjambre1.agregarEnjambre(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY);
        enemigos.addAll(enjambre1.obtenerEnjambreDeEnemigos());

        enjambre2.agregarEnjambre(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY + 55);
        enemigos.addAll(enjambre2.obtenerEnjambreDeEnemigos());

        enjambre3.agregarEnjambre(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY + 165);
        enemigos.addAll(enjambre3.obtenerEnjambreDeEnemigos());
    }

    @Override
    public void paintComponent(Graphics g) {

        pintor.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            actualizadorEntidades.actualizarEntidades(nave, enemigos, modificadores , enjambre1, enjambre2, enjambre3);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        repaint();
        pintor.actualizar();
        verificadorDeColisiones.verificarColisiones(nave, enemigos);

        enemigosDisparar();
    }

    private void enemigosDisparar() {
        for (NaveEnemigo enemigo : enemigos) {
            if (enemigo.debeDisparar()) {
                enemigo.disparar();
            }
        }
    }

    public void limpiarProyectilesDeLaVentana() {
        nave.obtenerProyectiles().clear();
        List<ProyectilDelEnemigo> proyectilesAEliminar = new ArrayList<>();//lista temporal creada
        for (NaveEnemigo enemigo : enemigos) {
            List<ProyectilDelEnemigo> proyectilesEnemigo = enemigo.obtenerProyectiles();// Obtenemos la lista de proyectiles de cada enemigo
            proyectilesAEliminar.addAll(proyectilesEnemigo); // Añadimos todos los proyectiles a eliminar en la lista temporal
        }
        for (ProyectilDelEnemigo proyectil : proyectilesAEliminar) {// Eliminamos todos los proyectiles de la lista temporal
            proyectil.setVisible(false);
        }
    }

    public void pausaDeReaparicion() {
        temporizador.stop();
        Timer pausaParaReaparicion = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nave.volverAlPuntoDeRespawn();
                temporizador.start();
            }
        } );
        pausaParaReaparicion.setRepeats(false);
        pausaParaReaparicion.start();
    }

    public ArrayList<int[]> obtenerPosicionesEnjambreUno() {
        ArrayList<int[]> posicionesEnjambreUno = new ArrayList<>();
        for (NaveEnemigo enemigo : enemigos) {
            if (enemigo instanceof PlatilloMalo) {
                int[] aux = {enemigo.obtenerPosicionEnX(), enemigo.obtenerPosicionEnY()};
                posicionesEnjambreUno.add(aux);
            }
        }
        return posicionesEnjambreUno;
    }

    public ArrayList<int[]> obtenerPosicionesEnjambreDos() {
        ArrayList<int[]> posicionesEnjambreDos = new ArrayList<>();
        for (NaveEnemigo enemigo : enemigos) {
            if (enemigo instanceof CalaveraMágica) {
                int[] aux = {enemigo.obtenerPosicionEnX(), enemigo.obtenerPosicionEnY()};
                posicionesEnjambreDos.add(aux);
            }
        }
        return posicionesEnjambreDos;
    }

    public ArrayList<int[]> obtenerPosicionesEnjambreTres() {
        ArrayList<int[]> posicionesEnjambreTres = new ArrayList<>();
        for (NaveEnemigo enemigo : enemigos) {
            if (enemigo instanceof GatoPlatillo) {
                int[] aux = {enemigo.obtenerPosicionEnX(), enemigo.obtenerPosicionEnY()};
                posicionesEnjambreTres.add(aux);
            }
        }
        return posicionesEnjambreTres;
    }



    public ArrayList<int[]> obtenerPosicionesProyectiles() {
        ArrayList<int[]> posicionesEnemigos = new ArrayList<>();
        for (Proyectil proyectil : nave.obtenerProyectiles()) {
            int[] aux = {proyectil.obtenerPosicionEnX(), proyectil.obtenerPosicionEnY()};
            posicionesEnemigos.add(aux);
        }
        return posicionesEnemigos;
    }

    public ArrayList<int[]> obtenerPosicionesProyectilesEnemigos() {
        ArrayList<int[]> posicionesProyectilesEnemigos = new ArrayList<>();

        for (NaveEnemigo enemigo : enemigos) {
            for (ProyectilDelEnemigo proyectil : enemigo.obtenerProyectiles()) {
                int[] aux = {proyectil.obtenerPosicionEnX(), proyectil.obtenerPosicionEnY()};
                posicionesProyectilesEnemigos.add(aux);
            }
        }
        return posicionesProyectilesEnemigos;
    }

    public int obtenerPosicionEnXNave() {
        return nave.obtenerPosicionEnX();
    }

    public int obtenerPosicionEnYNave() {
        return nave.obtenerPosicionEnY();
    }

    public ArrayList<int[]> obtenerPosicionesModificadores() {
        ArrayList<int[]> posicionesModificadores = new ArrayList<>();
        for(Modificadores modificador : modificadores) {
            posicionesModificadores.add(modificador.obtenerPosicion());
        }
        return posicionesModificadores;
    }

    public void agregarModificador(Modificadores modificador) {
        if(modificador != null){
            this.modificadores.add(modificador);
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            nave.teclaLiberada(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            nave.teclaPresionada(e);
        }
    }

    public JFrame getJFrame() {
        return jFrame;
    }
}