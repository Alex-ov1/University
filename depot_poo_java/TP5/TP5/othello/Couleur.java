package othello;

public enum Couleur {
    BLANC,
    NOIR;

    public Couleur adversaire() {
        return this == BLANC ? NOIR : BLANC;
    }
}
