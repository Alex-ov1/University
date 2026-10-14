package othello;

public enum Direction {
    N(-1, 0), S(1, 0), E(0, 1), O(0, -1),
    NO(-1, -1), NE(-1, 1), SE(1, 1), SO(1, -1);

    private final int deltaLigne;
    private final int deltaColonne;

    Direction(int deltaLigne, int deltaColonne) {
        this.deltaLigne = deltaLigne;
        this.deltaColonne = deltaColonne;
    }

    public int getDeltaLigne() {
        return deltaLigne;
    }

    public int getDeltaColonne() {
        return deltaColonne;
    }
}
