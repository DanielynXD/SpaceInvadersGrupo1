package Logica.Enjambre;

import Logica.Entidades.Enemigos.CalaveraMágica;
import Logica.Entidades.Enemigos.NaveEnemigo;

public class EnjambreDeCalaverasMágicas extends Enjambre {

    public EnjambreDeCalaverasMágicas(int numeroFilas, int numeroColumnas, int numeroOleada, NaveEnemigo enemigo) {
        super(numeroFilas, numeroColumnas, enemigo, numeroOleada);
    }

    @Override
    public void generarEnemigosDelEnjambre(int posicionEnX, int posicionEnY) {
        for (int numeroDeRepetición = 0; numeroDeRepetición < numeroColumnas; numeroDeRepetición++) {
            enjambre.add(new CalaveraMágica(posicionEnX + numeroDeRepetición * 72, posicionEnY, obtenerNumeroDeOleada()));
        }
        if (numeroFilasGenerado < numeroFilas) {
            numeroFilasGenerado++;
            posicionEnY += 60;
            generarEnemigosDelEnjambre(posicionEnX, posicionEnY);
        }
    }
}
