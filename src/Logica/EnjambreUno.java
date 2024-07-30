package Logica;

import java.util.ArrayList;

public class EnjambreUno extends NaveEnemigo {

    public static final int PUNTAJE_DEL_ENEMIGO = 100;
    protected ArrayList<EnjambreUno> enjambreDeEnemigos = new ArrayList<>();

    public EnjambreUno(int posiciónDelEnjambreEnX, int posiciónDelEnjambreEny) {
        super(posiciónDelEnjambreEnX,posiciónDelEnjambreEny);
        this.puntajeDelEnemigo = PUNTAJE_DEL_ENEMIGO;
    }

    public void agregarEnjambreUno(int posicionEnX, int posicionEnY) {
        generarEnemigosDelEnjambre(posicionEnX,posicionEnY);
    }

    protected void generarEnemigosDelEnjambre(int posicionEnX, int posicionEnY) {
        for (int i = 0; i < NUMERO_DE_ENEMIGOS_DEL_ENJAMBRE; i++) {
            enjambreDeEnemigos.add(new EnjambreUno(posicionEnX + i * 72, posicionEnY));//--------esto cambie
        }
    }

    public ArrayList<EnjambreUno> obtenerEnjambreDeEnemigos() {
        return enjambreDeEnemigos;
    }
}
