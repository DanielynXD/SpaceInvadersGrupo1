package Presentacion;

import Logica.ControlesDeSistema.GestorDePartidas;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class VentanaPartidaGuardada extends JFrame{
    GestorDePartidas gestionDePartidas;
    PanelDeJuegoData panelDeJuegoData ;

    public VentanaPartidaGuardada() {
        gestionDePartidas = new GestorDePartidas();

        setTitle("Menú guardar partida");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        panelDeJuegoData = obtenerPartidaGuardada();
        new Escenario(panelDeJuegoData);
    }

    private PanelDeJuegoData obtenerPartidaGuardada() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre de la partida guardada:");
        if (nombre != null && !nombre.isEmpty()) {
            return gestionDePartidas.cargarPartida(nombre);
        } else {
            return null;
        }
    }

    public PanelDeJuegoData obtenerPanelDeJuegoGuardado() {
        return this.panelDeJuegoData;
    }

    /*
    private PanelDeJuego obtenerNombreDeLaPartidaGuardada() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre de la partida guardada:");
        if (nombre != null && !nombre.isEmpty()) {
            return gestionDePartidas.cargarPartida(nombre);
        } else {
            return null;
        }
        this.dispose();
    }


    */

}
