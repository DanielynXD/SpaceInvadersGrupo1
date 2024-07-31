package Presentacion;

import javax.swing.*;

public class Escenario extends JFrame{

    public Escenario(){

        setTitle("Space Invaders");
        setSize(800, 600 );
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PanelDeJuego gamePanel = new PanelDeJuego();
        setContentPane(gamePanel);
        setVisible(true);
    }

}
