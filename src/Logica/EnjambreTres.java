package Logica;

import java.util.ArrayList;


public class EnjambreTres extends NaveEnemigo {

    public static final int PUNTAJE_DEL_ENEMIGO = 300;
    private int numeroDeFilasEnemigoTres;
    protected ArrayList<EnjambreTres> enjambreDeEnemigos = new ArrayList<>();
    private static final int NUMERO_DE_FILAS_TOTALES = 2;

    public EnjambreTres(int posiciónEnjambreDosEnX, int posiciónEnjambreDosEnY) {
        super(posiciónEnjambreDosEnX, posiciónEnjambreDosEnY);
        this.numeroDeFilasEnemigoTres = 1;
        this.puntajeDelEnemigo = PUNTAJE_DEL_ENEMIGO;
    }

    public void agregarEnjambreTres(int posicionEnX, int posicionEnY) {
        generarEnemigosDelEnjambre(posicionEnX,posicionEnY);
    }

    protected void generarEnemigosDelEnjambre(int posicionEnX, int posicionEnY) {
        for (int i = 0; i < NUMERO_DE_ENEMIGOS_DEL_ENJAMBRE; i++) {
            enjambreDeEnemigos.add(new EnjambreTres(posicionEnX + i * 72, posicionEnY));//------esto cambie
        }
        if (numeroDeFilasEnemigoTres < NUMERO_DE_FILAS_TOTALES) {
            numeroDeFilasEnemigoTres++;
            posicionEnY += 55;
            generarEnemigosDelEnjambre(posicionEnX, posicionEnY);
        }
    }

    public ArrayList<EnjambreTres> obtenerEnjambreDeEnemigos() {
        return enjambreDeEnemigos;
    }

}
