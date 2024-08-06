package Logica.Puntaje;

import java.util.Comparator;

public class ComparadorDePuntajes implements Comparator<Puntaje> {

    @Override
    public int compare(Puntaje o1, Puntaje o2) {
        return o2.getPuntaje() - o1.getPuntaje();
    }
}
