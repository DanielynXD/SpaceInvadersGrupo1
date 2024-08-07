package Logica.Entidades.Modificadores;

import Logica.Entidades.Enemigos.NaveEnemigo;
import Logica.Entidades.Jugador.NaveJugador;
import Logica.Entidades.Nave;

public class ProbabilidadDisparoEnemigosAumentada extends Modificador {
    public ProbabilidadDisparoEnemigosAumentada(int posicionEnX, int posicionEnY){
        super(posicionEnX, posicionEnY);
    }

    public void aplicarEfecto(Nave nave){
        if(nave instanceof NaveJugador){
            return;
        }
        ((NaveEnemigo)nave).aumentarProbabilidadDeDisparo();
    }


}
