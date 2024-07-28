package Logica;

import java.util.ArrayList;

public class EnjambreDos extends NaveEnemigo{

    private int numeroDeColumnasEnemigoDos;
    protected ArrayList<EnjambreDos> enjambreDeEnemigos = new ArrayList<>();
    private static final int NUMERO_DE_FILAS = 2;

    public EnjambreDos(int posiciónEnjambreDosEnX, int posiciónEnjambreDosEnY) {
        super(posiciónEnjambreDosEnX, posiciónEnjambreDosEnY);
        this.numeroDeColumnasEnemigoDos = 1;
    }

    public void agregarEnjambreDos(int posicionEnX, int posicionEnY) {
        generarEnemigosDelEnjambre(posicionEnX,posicionEnY);
    }
    protected void generarEnemigosDelEnjambre(int posicionEnX, int posicionEnY) {
        for (int i = 0; i < NUMERO_DE_ENEMIGOS_DEL_ENJAMBRE; i++) {
            enjambreDeEnemigos.add(new EnjambreDos(posicionEnX + i * 100, posicionEnY));
        }
        if (numeroDeColumnasEnemigoDos < NUMERO_DE_FILAS) {
            numeroDeColumnasEnemigoDos++;
            posicionEnY += 60;
            generarEnemigosDelEnjambre(posicionEnX, posicionEnY);
        }
    }
    public ArrayList<EnjambreDos> obtenerEnjambreDeEnemigos() {
        return enjambreDeEnemigos;
    }

}
