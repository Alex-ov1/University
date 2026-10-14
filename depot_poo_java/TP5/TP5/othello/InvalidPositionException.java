package othello;

/*
 * Cette classe est fournie pour le TP : on la garde dans le paquetage othello.
 */
public class InvalidPositionException extends Exception {
    public InvalidPositionException(String message) {
        super(message);
    }
}
