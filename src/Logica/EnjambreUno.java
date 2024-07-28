package Logica;

import java.util.ArrayList;

public class EnjambreUno extends NaveEnemigo {

    protected ArrayList<EnjambreUno> enjambreDeEnemigos = new ArrayList<>();

    public EnjambreUno(int posiciónDelEnjambreEnX, int posiciónDelEnjambreEny) {
        super(posiciónDelEnjambreEnX,posiciónDelEnjambreEny);
    }

    public void agregarEnjambreUno(int posicionEnX, int posicionEnY) {
        generarEnemigosDelEnjambre(posicionEnX,posicionEnY);
    }

    protected void generarEnemigosDelEnjambre(int posicionEnX, int posicionEnY) {
        for (int i = 0; i < NUMERO_DE_ENEMIGOS_DEL_ENJAMBRE; i++) {
            enjambreDeEnemigos.add(new EnjambreUno(posicionEnX + i * 100, posicionEnY));
        }
    }

    public ArrayList<EnjambreUno> obtenerEnjambreDeEnemigos() {
        return enjambreDeEnemigos;
    }
}
