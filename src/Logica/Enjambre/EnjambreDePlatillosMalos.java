package Logica.Enjambre;

import Logica.Naves.Enemigos.PlatilloMalo;
import Logica.Naves.Enemigos.NaveEnemigo;

public class EnjambreDePlatillosMalos extends Enjambre {
    public EnjambreDePlatillosMalos(int numeroFilas, int numeroColumnas, NaveEnemigo enemigo) {
        super(numeroFilas, numeroColumnas, enemigo);
    }

    @Override
    public void generarEnemigosDelEnjambre(int posicionEnX, int posicionEnY) {
        for (int i = 0; i < numeroColumnas; i++) {
            enjambre.add(new PlatilloMalo(posicionEnX + i * 72, posicionEnY));//--------esto cambie
        }
        if (numeroFilasGenerado < numeroFilas) {
            numeroFilasGenerado++;
            posicionEnY +=  60;
            generarEnemigosDelEnjambre(posicionEnX, posicionEnY);
        }
    }
}
