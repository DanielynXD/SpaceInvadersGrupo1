package Logica;

import java.util.ArrayList;

public class EnjambreDos extends NaveEnemigo{

    public static final int PUNTAJE_DEL_ENEMIGO = 200;
    private int numeroDeFilasEnemigoDos;
    protected ArrayList<EnjambreDos> enjambreDeEnemigos = new ArrayList<>();
    private static final int NUMERO_DE_FILAS = 2;

    public EnjambreDos(int posiciónEnjambreDosEnX, int posiciónEnjambreDosEnY) {
        super(posiciónEnjambreDosEnX, posiciónEnjambreDosEnY);
        this.numeroDeFilasEnemigoDos = 1;
        this.puntajeDelEnemigo = PUNTAJE_DEL_ENEMIGO;
    }

    public void agregarEnjambreDos(int posicionEnX, int posicionEnY) {
        generarEnemigosDelEnjambre(posicionEnX,posicionEnY);
    }
    protected void generarEnemigosDelEnjambre(int posicionEnX, int posicionEnY) {
        for (int i = 0; i < NUMERO_DE_ENEMIGOS_DEL_ENJAMBRE; i++) {
            enjambreDeEnemigos.add(new EnjambreDos(posicionEnX + i * 72, posicionEnY));//-----esto cambie
        }
        if (numeroDeFilasEnemigoDos < NUMERO_DE_FILAS) {
            numeroDeFilasEnemigoDos++;
            posicionEnY += 60;
            generarEnemigosDelEnjambre(posicionEnX, posicionEnY);
        }
    }
    public ArrayList<EnjambreDos> obtenerEnjambreDeEnemigos() {
        return enjambreDeEnemigos;
    }

}
