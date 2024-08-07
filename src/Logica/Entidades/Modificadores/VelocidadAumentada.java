package Logica.Entidades.Modificadores;

import Logica.Entidades.Jugador.NaveJugador;
import Logica.Entidades.Nave;

public class VelocidadAumentada extends Modificador{

    public VelocidadAumentada(int posicionEnX, int posicionEnY){
        super(posicionEnX, posicionEnY);
    }

    public void aplicarEfecto(Nave nave){
        ((NaveJugador)nave).actualizarVelocidad(10);
    }


}