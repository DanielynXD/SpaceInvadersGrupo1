package Logica.Enjambre;

import Logica.Naves.Enemigos.NaveEnemigo;

import java.util.ArrayList;

public abstract class Enjambre {
    protected final int posicionEnemigoEnX;
    protected int posiciónEnemigoEnY;
    protected int numeroFilas;
    protected final int numeroColumnas;
    protected final NaveEnemigo enemigo;
    ArrayList<NaveEnemigo> enjambre = new ArrayList<>();
    protected int numeroFilasGenerado;

    public Enjambre(int numeroFilas, int numeroColumnas, NaveEnemigo enemigo) {
        this.enemigo = enemigo;
        this.posicionEnemigoEnX = enemigo.obtenerPosicionEnX();
        this.posiciónEnemigoEnY = enemigo.obtenerPosicionEnY();
        this.numeroFilas = numeroFilas;
        this.numeroFilasGenerado = 1;
        this.numeroColumnas = numeroColumnas;
    }

    public void agregarEnjambre(int posicionEnX, int posicionEnY) {
        generarEnemigosDelEnjambre(posicionEnX, posicionEnY);
    }

    public abstract void generarEnemigosDelEnjambre(int posicionEnX, int posicionEnY);

    public ArrayList<NaveEnemigo> obtenerEnjambreDeEnemigos() {
        return enjambre;
    }

}
