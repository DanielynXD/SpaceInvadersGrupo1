package Logica;

import Presentacion.PanelDeJuego;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class NaveEnemigoUno extends Logica.Nave {
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int velocidad;
    private Image image;

    public NaveEnemigoUno() {
        iniciarEnemigoUno();
    }

    private void iniciarEnemigoUno() {
        Image imagenEnemigo = new ImageIcon(Objects.requireNonNull(NaveEnemigoUno.class.getResource("/ImagenesJuego/Enemigos/ImagenEnemigoUno.png"))).getImage();
        image = imagenEnemigo;
        x = 350;
        y = 50;
        velocidad = 3;
    }

    @Override
    public Image obtenerImagen() {
        return image;
    }

    @Override
    public int obtenerX() {
        return x;
    }

    @Override
    public int obtenerY() {
        return y;
    }
}
