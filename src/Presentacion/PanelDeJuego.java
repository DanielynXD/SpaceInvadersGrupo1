package Presentacion;

import Logica.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;
import java.util.List;

public class PanelDeJuego extends JPanel implements ActionListener {

    public static final int ANCHO = 800, ALTO = 600;
    public static final int NUMERO_DE_FILAS_ENEMIGOS = 5, NUMERO_DE_COLUMNAS_ENEMIGOS = 7;
    private Timer temporizador;
    private NaveJugador nave;
    protected EnjambreUno enemigoUno;
    private EnjambreDos enemigoDos;
    private EnjambreTres enemigoTres;

    private List<NaveEnemigo> enemigos;
    private int posicioInicialDelEnemigoEnX = 50;
    private int posicioInicialDelEnemigoEnY = 50;
    private int direccionMovimiento;
    private boolean descendiendo;
    private int unidadesDescendidas;
    private Pintor pintor;


    public PanelDeJuego() {
        iniciarPanel();
        pintor = new Pintor(this);
    }

    private void iniciarPanel() {
        setFocusable(true);
        setSize(ANCHO, ALTO);
        nave = new NaveJugador();
        addKeyListener(new TAdapter());

        enemigos = new ArrayList<>();//inicializa el array de enemigos
        enemigoUno = new EnjambreUno(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY);
        enemigoDos = new EnjambreDos(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY);
        enemigoTres = new EnjambreTres(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY);
        agregarEnemigos();//agrega enemigos

        temporizador = new Timer(10, this);
        temporizador.start();

        direccionMovimiento = 1; //se inicia con derecha
    }

    private void agregarEnemigos() {
        enemigoUno.agregarEnjambreUno(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY);
        enemigos.addAll(enemigoUno.obtenerEnjambreDeEnemigos());

        enemigoDos.agregarEnjambreDos(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY + 55);
        enemigos.addAll(enemigoDos.obtenerEnjambreDeEnemigos());

        enemigoTres.agregarEnjambreTres(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY + 165);
        enemigos.addAll(enemigoTres.obtenerEnjambreDeEnemigos());
    }

    @Override
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        pintor.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actualizarNave();
        actualizarProyectiles();
        repaint();
        pintor.actualizar();
        verificarColisiones();
        try {
            actualizarEnemigos();//actualiza la posicion de los enemigos
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void actualizarProyectiles() {
        List<Proyectil> proyectiles = nave.obtenerProyectiles();
        for (int i = 0; i < proyectiles.size(); i++) {
            Proyectil proyectil = proyectiles.get(i);
            if (proyectil.esVisible()) {
                proyectil.mover();
            } else {
                proyectiles.remove(i);
            }
        }
    }

    private void actualizarNave() {
        nave.mover();
    }

    private void actualizarEnemigos() throws InterruptedException {
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
            if (enemigo.obtenerPosicionEnX() <= 0 || enemigo.obtenerPosicionEnX() >= ANCHO - enemigo.obtenerAncho()) {
                cambiarDireccion = true;
            }
        }

        if (cambiarDireccion) {
            direccionMovimiento = -direccionMovimiento;
            descendiendo = true;
        }
    }

    private void verificarColisiones() {
        Rectangle hitboxNave = nave.obtenerHitbox();

        List<Proyectil> proyectiles = nave.obtenerProyectiles();

        List<NaveEnemigo> enemigosAEliminar = new ArrayList<>();//almacena a los enemigos a eliminar

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

        for (NaveEnemigo enemigo : enemigos) {
            Rectangle hitboxEnemigo = enemigo.obtenerHitBox();
            if (hitboxNave.intersects(hitboxEnemigo)) {
                System.exit(0); // TERMINA EL JUEGO PORQUE SOLO TIENE 1 VIDA, ese sistem termina el programa
                // TODO: añadir explosión, mas vidas y reducción de vida
            }
        }
    }

    public int obtenerPosicionEnXNave() {
        return nave.obtenerPosicionEnX();
    }

    public int obtenerPosicionEnYNave() {
        return nave.obtenerPosicionEnY();
    }

    public ArrayList<int[]> obtenerPosicionesEnjambreUno() {
        ArrayList<int[]> posicionesEnjambreUno = new ArrayList<>();
        for (NaveEnemigo enemigo : enemigos) {
            if (enemigo instanceof EnjambreUno) {
                int[] aux = {enemigo.obtenerPosicionEnX(), enemigo.obtenerPosicionEnY()};
                posicionesEnjambreUno.add(aux);
            }
        }
        return posicionesEnjambreUno;
    }

    public ArrayList<int[]> obtenerPosicionesEnjambreDos() {
        ArrayList<int[]> posicionesEnjambreDos = new ArrayList<>();
        for (NaveEnemigo enemigo : enemigos) {
            if (enemigo instanceof EnjambreDos) {
                int[] aux = {enemigo.obtenerPosicionEnX(), enemigo.obtenerPosicionEnY()};
                posicionesEnjambreDos.add(aux);
            }
        }
        return posicionesEnjambreDos;
    }

    public ArrayList<int[]> obtenerPosicionesEnjambreTres() {
        ArrayList<int[]> posicionesEnjambreTres = new ArrayList<>();
        for (NaveEnemigo enemigo : enemigos) {
            if (enemigo instanceof EnjambreTres) {
                int[] aux = {enemigo.obtenerPosicionEnX(), enemigo.obtenerPosicionEnY()};
                posicionesEnjambreTres.add(aux);
            }
        }
        return posicionesEnjambreTres;
    }



//------------------------------------------------------------------------------------

    public ArrayList<int[]> obtenerPosicionesProyectiles() {

        ArrayList<int[]> posicionesEnemigos = new ArrayList<>();
        for (Proyectil proyectil : nave.obtenerProyectiles()) {
            int[] aux = {proyectil.obtenerPosicionEnX(), proyectil.obtenerPosicionEnY()};
            posicionesEnemigos.add(aux);
        }
        return posicionesEnemigos;
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
}