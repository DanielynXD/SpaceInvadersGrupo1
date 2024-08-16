package Logica.Enjambre;

import Logica.Entidades.Enemigos.PlatilloMalo;
import Logica.Entidades.Enemigos.NaveEnemigo;

public class EnjambreDePlatillosMalos extends Enjambre {
    public EnjambreDePlatillosMalos(int numeroFilas, int numeroColumnas, int numeroOleada, NaveEnemigo enemigo) {
        super(numeroFilas, numeroColumnas, enemigo, numeroOleada);
    }

    @Override
    public void generarEnemigosDelEnjambre(int posicionEnX, int posicionEnY) {
        for (int numeroDeRepetición = 0; numeroDeRepetición < numeroColumnas; numeroDeRepetición++) {
            enjambre.add(new PlatilloMalo(posicionEnX + numeroDeRepetición * 72, posicionEnY, obtenerNumeroDeOleada()));
        }
        if (numeroFilasGenerado < numeroFilas) {
            numeroFilasGenerado++;
            posicionEnY += 60;
            generarEnemigosDelEnjambre(posicionEnX, posicionEnY);
        }
    }
}
