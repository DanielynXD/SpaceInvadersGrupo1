package Logica;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Proyectil {
    private int x;
    private int y;
    private int velocidad;
    private Image imagen;
    private boolean visible;

    public Proyectil(int x, int y) {
        this.x = x;
        this.y = y;
        velocidad = 10;
        imagen = new ImageIcon(Objects.requireNonNull(Proyectil.class.getResource("/ImagenesJuego/Proyectiles/ProyectilJugador.png"))).getImage();
        visible = true;
    }

    public void mover() {
        y -= velocidad;
        if (proyectilLlegoAlLimite()) {
            visible = false;
        }
    }

    private boolean proyectilLlegoAlLimite() {
        return y < 0;
    }

    public int obtenerX(){
        return x;
    }

    public int obtenerY(){
        return y;
    }

    public Image obtenerImagen(){
        return imagen;
    }

    public boolean esVisible(){
        return visible;
    }

    public Rectangle obtenerHitBox() {
        return new Rectangle(x, y, imagen.getWidth(null), imagen.getHeight(null));//Funcion que permite obtener la hitbox
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
