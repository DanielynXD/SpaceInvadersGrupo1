package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaDePausa extends JPanel {
    private JButton botonReanudar, botonGuardar;
    private PanelDeJuego panelDeJuego;

    public PantallaDePausa(PanelDeJuego panelDeJuego) {
        this.panelDeJuego = panelDeJuego;
        setLayout(null);
        setBackground(new Color(0, 0, 0, 150));

        botonReanudar = new JButton("Reanudar");
        botonReanudar.setBounds(350, 250, 100, 50);
        botonReanudar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelDeJuego.reanudarJuego();
            }
        });

        add(botonReanudar);

        botonGuardar = new JButton("Guardar");
        botonGuardar.setBounds(350, 400, 100, 50);
//        botonGuardar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                panelDeJuegoData.guardarPartida();
//            }
//        });
        add(botonGuardar);


    }
}
