package Logica.ControlesDeSistema;

public class NoExisteLaPartidaGuardadaException extends Throwable {
    public NoExisteLaPartidaGuardadaException() {
        super("No existe la partida");
    }

    public NoExisteLaPartidaGuardadaException(NoExisteLaPartidaGuardadaException e) {
        super(e.getMessage());
    }
}
