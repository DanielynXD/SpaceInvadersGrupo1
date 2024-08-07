package Presentacion;

import Logica.ControlesDeSistema.ActualizadorEntidades;
import Logica.ControlesDeSistema.ControladorDeTeclas;
import Logica.ControlesDeSistema.GestorDePartidas;
import Logica.Enjambre.EnjambreDeCalaverasMágicas;
import Logica.Enjambre.EnjambreDeGatosPlatillos;
import Logica.Entidades.Modificadores.*;
import Logica.Entidades.Enemigos.CalaveraMágica;
import Logica.Entidades.Enemigos.GatoPlatillo;
import Logica.Entidades.Enemigos.PlatilloMalo;
import Logica.Entidades.Enemigos.NaveEnemigo;
import Logica.Enjambre.Enjambre;
import Logica.Enjambre.EnjambreDePlatillosMalos;
import Logica.Entidades.Jugador.NaveJugador;
import Logica.Proyectiles.Proyectil;
import Logica.Proyectiles.ProyectilDelEnemigo;
import Logica.ControlesDeSistema.VerificadorDeColisiones;


import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.*;
import java.util.List;
import javax.swing.Timer;

public class PanelDeJuego extends JPanel implements ActionListener, Serializable {

    public static final int ANCHO = 800, ALTO = 600;
    private Timer temporizador;
    private NaveJugador nave;
    private Enjambre enjambre1, enjambre2, enjambre3;
    private ArrayList<NaveEnemigo> enemigos;
//    private VerificadorDeColisiones verificadorDeColisiones;
    private int posicioInicialDelEnemigoEnX = 50;
    private int posicioInicialDelEnemigoEnY = 50;
    private Pintor pintor;
//    private ActualizadorEntidades actualizadorEntidades;
    private ArrayList<Modificador> modificadores;
    private JFrame jFrame;
    private int numeroOleada;
    private ReproductorMúsica reproductorDeMúsica;
    private AdministradorGeneral administradorGeneral;
    private ControladorDeTeclas controladorDeTeclas;
    private PanelDeJuegoData panelDeJuegoData;

    private  PantallaDePausa pantallaDePausa;


    public PanelDeJuego(JFrame jFrame)  {
        this.jFrame = jFrame;
        //enPausa = false;
        iniciarPanel();
        this.panelDeJuegoData = new PanelDeJuegoData();
        pantallaDePausa = new PantallaDePausa(this, panelDeJuegoData);

        pintor = new Pintor(this);
    }

    public PanelDeJuego(JFrame jFrame, PanelDeJuegoData panelDeJuegoData)  {
        this.jFrame = jFrame;
        iniciarPanel();
        this.panelDeJuegoData = panelDeJuegoData;
        this.cargarPartida();
        pantallaDePausa = new PantallaDePausa(this, panelDeJuegoData);
        pintor = new Pintor(this);
    }

    private void cargarPartida() {
        administradorGeneral.cargarPartida(panelDeJuegoData);
        nave.actualizarNumeroDeVidas(panelDeJuegoData.obtenerVidas());
        numeroOleada = panelDeJuegoData.obtenerNumeroDeOleada();
    }


    private void iniciarPanel()  {
        setFocusable(true);
        setSize(ANCHO, ALTO);
        nave = new NaveJugador();
        numeroOleada = 0;
        panelDeJuegoData = new PanelDeJuegoData();
        addKeyListener(new TAdapter());
        enemigos = new ArrayList<>();//inicializa el array de enemigos
        agregarEnemigos();//agrega enemigos

        temporizador = new Timer(10, this);
        temporizador.start();
        modificadores = new ArrayList<>();

        reproductorDeMúsica = new ReproductorMúsica("src/Presentacion/MúsicaYSonido/SonidoIntensoPanelJuego.wav");
        reproductorDeMúsica.reproducir();

        administradorGeneral = new AdministradorGeneral(this);

        controladorDeTeclas = new ControladorDeTeclas(nave,this);
    }

    public void agregarEnemigos() {

        enjambre1 = new EnjambreDePlatillosMalos(1, 10, numeroOleada, new PlatilloMalo(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY, numeroOleada));
        enjambre2 = new EnjambreDeCalaverasMágicas(2, 10, numeroOleada, new CalaveraMágica(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY, numeroOleada));
        enjambre3 = new EnjambreDeGatosPlatillos(2, 10, numeroOleada, new GatoPlatillo(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY, numeroOleada));

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
        repaint();
        pintor.actualizar();
        try {
            administradorGeneral.iniciarCompetencias(modificadores,nave,enemigos, enjambre1, enjambre2, enjambre3);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        administradorGeneral.verficarExistenciaEnemigos(enemigos);
        panelDeJuegoData.actualizarDatos(obtenerPosicionesEnjambreUno(), obtenerPosicionesEnjambreDos(), obtenerPosicionesEnjambreTres(), obtenerPosicionEnXNave(), obtenerPosicionEnYNave(), obtenerVidasJugador(), getPuntajeTotal(), nave.obtenerVidasDisponibles(), numeroOleada);

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
        for(NaveEnemigo enemigo : enemigos){
            enemigo.obtenerProyectiles().clear();
        }

    }

    public void pausaDeReaparicion() {

        temporizador.stop();
        Timer pausaParaReaparicion = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nave.volverAlPuntoDeRespawn();
                temporizador.start();
            }
        });
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
        for (Modificador modificador : modificadores) {
            posicionesModificadores.add(modificador.obtenerPosicion());
        }
        return posicionesModificadores;
    }

    public void agregarModificador(Modificador modificador) {
        if (modificador != null) {
            modificadores.add(modificador);
        }
    }

    public void detenerMusica() {
        reproductorDeMúsica.detener();
    }

    public void aumentarNumeroOleada() {
        numeroOleada++;
    }

    public int obtenerVidasJugador() {
        return nave.obtenerVidasDisponibles();
    }

    public ArrayList<Modificador> obtenerModificadores() {
        return modificadores;
    }


    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            controladorDeTeclas.teclaLiberada(e);
        }
        @Override
        public void keyPressed(KeyEvent e) {
            controladorDeTeclas.teclaPresionada(e);
        }
    }

    public void pausarJuego() {
        //enPausa = true;
        reproductorDeMúsica.detener();
        temporizador.stop();
        add(pantallaDePausa);
        pantallaDePausa.setBounds(0, 0, ANCHO, ALTO);
        pantallaDePausa.setVisible(true);
        repaint();
    }

    public void reanudarJuego() {
        //enPausa = false;
        reproductorDeMúsica.reproducir();
        remove(pantallaDePausa);
        temporizador.start();
        repaint();
    }

    public JFrame getJFrame() {
        return jFrame;
    }

    public int getPuntajeTotal() {
        return administradorGeneral.obtenerPuntuaciones();
    }


//    public void guardarPartida() {
//        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre para guardar la partida:");
//        if (nombre != null && !nombre.isEmpty()) {
//            GestorDePartidas gestorDePartidas = new GestorDePartidas();
////            nombre = linea.split(" ");
//
//            gestorDePartidas.guardarPartida(this, nombre);
//
//            JOptionPane.showMessageDialog(this, "Partida guardada exitosamente.");
//        }
//        new Menú();
//        getJFrame().dispose();
//    }

    /*
    public void guardarPartida() {
    String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre para guardar la partida:");
        if (nombre != null && !nombre.isEmpty()) {
            GestorDePartidas gestorDePartidas = new GestorDePartidas();

            gestorDePartidas.guardarPartida(panelDeJuego, nombre);

            JOptionPane.showMessageDialog(this, "Partida guardada exitosamente.");
        }
    }
     */
}