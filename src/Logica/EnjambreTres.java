package Logica;

import java.util.ArrayList;


public class EnjambreTres extends NaveEnemigo {

    private int numeroDeColumnasEnemigoTres;
    protected ArrayList<EnjambreTres> enjambreDeEnemigos = new ArrayList<>();
    private static final int NUMERO_DE_FILAS = 2;

    public EnjambreTres(int posiciónEnjambreDosEnX, int posiciónEnjambreDosEnY) {
        super(posiciónEnjambreDosEnX, posiciónEnjambreDosEnY);
        this.numeroDeColumnasEnemigoTres = 1;
    }

    public void agregarEnjambreTres(int posicionEnX, int posicionEnY) {
        generarEnemigosDelEnjambre(posicionEnX,posicionEnY);
    }

    protected void generarEnemigosDelEnjambre(int posicionEnX, int posicionEnY) {
        for (int i = 0; i < NUMERO_DE_ENEMIGOS_DEL_ENJAMBRE; i++) {
            enjambreDeEnemigos.add(new EnjambreTres(posicionEnX + i * 100, posicionEnY));
        }
        if (numeroDeColumnasEnemigoTres < NUMERO_DE_FILAS) {
            numeroDeColumnasEnemigoTres++;
            posicionEnY += 55;
            generarEnemigosDelEnjambre(posicionEnX, posicionEnY);
        }
    }

    public ArrayList<EnjambreTres> obtenerEnjambreDeEnemigos() {
        return enjambreDeEnemigos;
    }

}
