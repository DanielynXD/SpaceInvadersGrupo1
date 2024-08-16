package Presentacion;

import javax.swing.*;

public class Escenario extends JFrame {

    public static final int ANCHO_VENTANA = 800;
    public static final int ALTO_VENTANA = 600;

    public Escenario() {

        setTitle("Space Invaders");
        setSize(ANCHO_VENTANA, ALTO_VENTANA);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PanelDeJuego gamePanel = new PanelDeJuego(this);
        setContentPane(gamePanel);
        setVisible(true);
    }

    public Escenario(PanelDeJuegoData panelDeJuegoData) {
        setTitle("Space Invaders");
        setSize(ANCHO_VENTANA, ALTO_VENTANA);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PanelDeJuego gamePanel = new PanelDeJuego(this, panelDeJuegoData);
        setContentPane(gamePanel);
        setVisible(true);
    }

}
