package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaDePausa extends JPanel {
    private JButton botonReanudar, botonGuardar, botonRegresarAlMenú, botonSalir;
    private PanelDeJuego panelDeJuego;
    private PanelDeJuegoData panelDeJuegoData;

    public VentanaDePausa(PanelDeJuego panelDeJuego, PanelDeJuegoData panelDeJuegoData) {
        this.panelDeJuego = panelDeJuego;
        this.panelDeJuegoData = panelDeJuegoData;
        setLayout(null);
        setBackground(new Color(0, 0, 0, 150));

        botonReanudar = new JButton("Reanudar");
        botonReanudar.setBounds(350, 200, 100, 50);
        botonReanudar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelDeJuego.reanudarJuego();
            }
        });

        add(botonReanudar);

        botonRegresarAlMenú = new JButton("Menú");
        botonRegresarAlMenú.setBounds(350, 300, 100, 50);
        botonRegresarAlMenú.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Menú();

            }
        });
        add(botonRegresarAlMenú);

        botonSalir = new JButton("Salir");
        botonSalir.setBounds(350, 400, 100, 50);
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(botonSalir);

        botonGuardar = new JButton("Guardar");
        botonGuardar.setBounds(350, 500, 100, 50);
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelDeJuegoData.guardarPartida();
            }
        });
        add(botonGuardar);


    }
}
