package othello;

public class Plateau {
    private final int nbli;
    private final int nbcol;
    private final Pions[][] tab;

    public Plateau(int ligne, int colonne) {
        this.nbli = ligne;
        this.nbcol = colonne;
        this.tab = new Pions[ligne][colonne];
    }

    public boolean isInside(Position p) {
        return p != null
            && p.getLigne() >= 0 && p.getLigne() < nbli
            && p.getColonne() >= 0 && p.getColonne() < nbcol;
    }

    public boolean isValidPosition(Position p) {
        return isInside(p) && tab[p.getLigne()][p.getColonne()] == null;
    }

    public void addPions(Pions pions, Position p) throws InvalidPositionException {
        if (!isInside(p) || !isValidPosition(p)) {
            throw new InvalidPositionException("Position invalide ou occupee.");
        }
        else if (pions == null) {
            System.out.println("Le pion ne peut pas etre null.");
        }
        tab[p.getLigne()][p.getColonne()] = pions;
    }

    public void withdrawPions(Position p) {
        // On ne retire un pion que si la position existe et contient un pion.
        if (isInside(p) && tab[p.getLigne()][p.getColonne()] != null) {
            tab[p.getLigne()][p.getColonne()] = null;
        }
    }

    public Pions getPions(Position p) {
        if (!isInside(p)) {
            return null;
        }
        return tab[p.getLigne()][p.getColonne()];
    }

    public boolean canPlayAt(Position p, Couleur couleur) {
        if (!isValidPosition(p) || couleur == null) {
            return false;
        }

        for (Direction d : Direction.values()) {
            if (encadreDansDirection(p, couleur, d)) {
                return true;
            }
        }
        return false;
    }

    private boolean encadreDansDirection(Position p, Couleur couleur, Direction d) {
        Position courante = p.neighbour(d);
        Pions pion = getPions(courante);

        // Il faut au moins un pion adverse juste à côté.
        if (pion == null || pion.getColor() != couleur.adversaire()) {
            return false;
        }

        // On avance sur les pions adverses jusqu'à trouver un pion de notre couleur.
        while (pion != null && pion.getColor() == couleur.adversaire()) {
            courante = courante.neighbour(d);
            pion = getPions(courante);
        }

        return pion != null && pion.getColor() == couleur;
    }

    public void jouer(Position p, Couleur couleur) throws InvalidPositionException {
        if (!canPlayAt(p, couleur)) {
            throw new InvalidPositionException("Coup invalide.");
        }

        addPions(new Pions(couleur), p);

        for (Direction d : Direction.values()) {
            if (encadreDansDirection(p, couleur, d)) {
                Position courante = p.neighbour(d);
                Pions pion = getPions(courante);
                
                while (pion != null && pion.getColor() == couleur.adversaire()) {
                    tab[courante.getLigne()][courante.getColonne()] = new Pions(couleur);
                    courante = courante.neighbour(d);
                    pion = getPions(courante);
                }
            }
        }
    }

    public boolean hasMove(Couleur couleur) {
        for (int i = 0; i < nbli; i++) {
            for (int j = 0; j < nbcol; j++) {
                if (canPlayAt(new Position(i, j), couleur)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int count(Couleur couleur) {
        int total = 0;
        for (int i = 0; i < nbli; i++) {
            for (int j = 0; j < nbcol; j++) {
                if (tab[i][j] != null && tab[i][j].getColor() == couleur) {
                    total++;
                }
            }
        }
        return total;
    }

    public void PrintPlateau() {
        System.out.print("  ");
        for (int j = 0; j < nbcol; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for (int i = 0; i < nbli; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < nbcol; j++) {
                if (tab[i][j] == null) {
                    System.out.print(". ");
                } else if (tab[i][j].getColor() == Couleur.NOIR) {
                    System.out.print("N ");
                } else {
                    System.out.print("B ");
                }
            }
            System.out.println();
        }
        System.out.println("Nombre de pions noirs : " + count(Couleur.NOIR));
        System.out.println("Nombre de pions blancs : " + count(Couleur.BLANC));
    }
}
