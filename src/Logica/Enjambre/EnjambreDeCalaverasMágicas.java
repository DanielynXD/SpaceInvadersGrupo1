package Logica.Enjambre;

import Logica.Entidades.Enemigos.CalaveraMágica;
import Logica.Entidades.Enemigos.NaveEnemigo;

public class EnjambreDeCalaverasMágicas extends Enjambre {

    public EnjambreDeCalaverasMágicas(int numeroFilas, int numeroColumnas, int numeroOleada, NaveEnemigo enemigo) {
        super(numeroFilas, numeroColumnas, enemigo, numeroOleada);
    }

    @Override
    public void generarEnemigosDelEnjambre(int posicionEnX, int posicionEnY) {
        for (int i = 0; i < numeroColumnas; i++) {
            enjambre.add(new CalaveraMágica(posicionEnX + i * 72, posicionEnY, obtenerNumeroDeOleada()));//--------esto cambie
        }
        if (numeroFilasGenerado < numeroFilas) {
            numeroFilasGenerado++;
            posicionEnY += 60;
            generarEnemigosDelEnjambre(posicionEnX, posicionEnY);
        }
    }
}
