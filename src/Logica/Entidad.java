package Logica;

import java.awt.*;

public class Entidad {

    //private Rectangle hitbox;
    private int velocidad;
    private double posicionEnX;
    private int posicionEnY;
    private int ancho;
    private int alto;

    public Entidad(int posicionEnX, int posicionEnY, int velocidad, int ancho, int alto){
        this.posicionEnX = posicionEnX;
        this.posicionEnY = posicionEnY;
        this.velocidad = velocidad;
        this.ancho = ancho;
        this.alto = alto;
        //hitbox = new Rectangle(posicionEnX, posicionEnY, ancho, alto);
    }

    public int obtenerPosicionEnX() {
        return (int)posicionEnX;
    }

    public int obtenerPosicionEnY() {
        return posicionEnY;
    }

    public int obtenerVelocidad() {
        return velocidad;
    }

    public void fijarNuevaPosicionEnX(int posicionEnX){
        this.posicionEnX = posicionEnX;
    }
    public void fijarNuevaPosicionEnY(int posicionEnY){
        this.posicionEnY = posicionEnY;
    }

    public Rectangle obtenerHitbox() {
        return new Rectangle((int)posicionEnX, posicionEnY,ancho, alto );
    }

//    public void actualizarHitBox() {
//        hitbox.x = obtenerPosicionEnX();
//        hitbox.y = obtenerPosicionEnY();
//    }
}
