package Logica.Entidades.Modificadores;

import Logica.Entidades.Jugador.NaveJugador;
import Logica.Entidades.Nave;

public class VidaExtra extends Modificador{

    public VidaExtra(int posicionEnX, int posicionEnY){
        super(posicionEnX, posicionEnY);
    }

    public void aplicarEfecto(Nave nave){
        ((NaveJugador)nave).actualizarNumeroDeVidas(((NaveJugador)nave).obtenerVidasDisponibles()+1);
    }


}
