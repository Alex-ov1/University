package othello;

public class Position {
    private final int ligne;
    private final int colonne;

    public Position(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public Position neighbour(Direction direction) {
        return new Position(
            ligne + direction.getDeltaLigne(),
            colonne + direction.getDeltaColonne()
        );
    }
}
