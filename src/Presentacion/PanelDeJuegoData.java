package Presentacion;

import Logica.ControlesDeSistema.GestorDePartidas;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class PanelDeJuegoData implements Serializable {

    private ArrayList<int[]> posicionesEnjambre1;
    private ArrayList<int[]> posicionesEnjambre2;
    private ArrayList<int[]> posicionesEnjambre3;
    private int[] posicionNaveJugador;
    private int puntuacion;
    private int numeroDeVidas;
    private int numeroOleada;

    public PanelDeJuegoData() {
        posicionesEnjambre1 = new ArrayList<>();
        posicionesEnjambre2 = new ArrayList<>();
        posicionesEnjambre3 = new ArrayList<>();
        posicionNaveJugador = new int[2];


    }

    public void actualizarDatos(ArrayList<int[]> ints, ArrayList<int[]> ints1, ArrayList<int[]> ints2, int i, int i1, int i2, int puntajeTotal, int obtenerVidasDisponibles, int numeroOleada) {

        posicionesEnjambre1 = ints;
        posicionesEnjambre2 = ints1;
        posicionesEnjambre3 = ints2;
        posicionNaveJugador[0] = i1;
        posicionNaveJugador[1] = i2;
        puntuacion = puntajeTotal;
        numeroDeVidas = obtenerVidasDisponibles;
        this.numeroOleada = numeroOleada;

    }

    public void guardarPartida() {
        String nombre = JOptionPane.showInputDialog( "Ingrese el nombre para guardar la partida:");
        if (nombre != null && !nombre.isEmpty()) {
            GestorDePartidas gestorDePartidas = new GestorDePartidas();
//            nombre = linea.split(" ");
            gestorDePartidas.guardarPartida(this, nombre);

            //JOptionPane.showMessageDialog(, "Partida guardada exitosamente.");
        }

    }

    public int obtenerPuntaje() {
        return puntuacion;
    }

    public int obtenerVidas() {
        return numeroDeVidas;
    }

    public int obtenerNumeroDeOleada() {
        return numeroOleada;
    }
}
