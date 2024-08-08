package Logica.Entidades.Modificadores;

import Logica.Entidades.Jugador.NaveJugador;
import Logica.Entidades.Nave;

public class VelocidadDeDisparoAumentada extends Modificador{

    public VelocidadDeDisparoAumentada(int posicionEnX, int posicionEnY){
        super(posicionEnX, posicionEnY);
    }

    public void aplicarEfecto(Nave nave){
        ((NaveJugador)nave).aumentarVelocidadDeDisparo(10);
    }

}
