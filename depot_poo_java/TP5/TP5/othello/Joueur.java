package othello;

public class Joueur {
    private final Couleur color;

    public Joueur(Couleur couleur) {
        this.color = couleur;
    }

    public Couleur getColor() {
        return color;
    }
}
