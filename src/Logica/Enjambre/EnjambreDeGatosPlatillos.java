package Logica.Enjambre;

import Logica.Entidades.Enemigos.GatoPlatillo;
import Logica.Entidades.Enemigos.NaveEnemigo;

public class EnjambreDeGatosPlatillos extends Enjambre {
    public EnjambreDeGatosPlatillos(int numeroFilas, int numeroColumnas, int numeroOleada, NaveEnemigo enemigo) {
        super(numeroFilas, numeroColumnas, enemigo, numeroOleada);
    }

    @Override
    public void generarEnemigosDelEnjambre(int posicionEnX, int posicionEnY) {
        for (int numeroDeRepetición = 0; numeroDeRepetición < numeroColumnas; numeroDeRepetición++) {
            enjambre.add(new GatoPlatillo(posicionEnX + numeroDeRepetición * 72, posicionEnY, obtenerNumeroDeOleada()));
        }
        if (numeroFilasGenerado < numeroFilas) {
            numeroFilasGenerado++;
            posicionEnY += 60;
            generarEnemigosDelEnjambre(posicionEnX, posicionEnY);
        }
    }
}
