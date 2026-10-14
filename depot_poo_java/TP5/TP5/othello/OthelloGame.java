package othello;
import java.util.Scanner;

public class OthelloGame {
    private final Plateau plateau;
    private Couleur joueurActif;

    public OthelloGame() {
        plateau = new Plateau(8, 8);
        joueurActif = Couleur.NOIR;

        try {
            plateau.addPions(new Pions(Couleur.BLANC), new Position(3, 3));
            plateau.addPions(new Pions(Couleur.NOIR), new Position(3, 4));
            plateau.addPions(new Pions(Couleur.NOIR), new Position(4, 3));
            plateau.addPions(new Pions(Couleur.BLANC), new Position(4, 4));
        } catch (InvalidPositionException e) {
            // Ces quatre positions sont fixes et valides sur un plateau 8x8.
            throw new IllegalStateException(e);
        }
    }

    public void jouerPartie() {
        Scanner scanner = new Scanner(System.in);

        while (plateau.hasMove(Couleur.NOIR) || plateau.hasMove(Couleur.BLANC)) {
            plateau.PrintPlateau();

            if (!plateau.hasMove(joueurActif)) {
                System.out.println("Le joueur " + joueurActif + " ne peut pas jouer : il passe son tour.");
                joueurActif = joueurActif.adversaire();
                continue;
            }

            System.out.println("Au joueur " + joueurActif + " de jouer.");
            System.out.print("Entrez ligne et colonne (ex. : 2 3) : ");

            if (!scanner.hasNextInt()) {
                System.out.println("Saisie invalide : entrez deux nombres.");
                scanner.nextLine();
                continue;
            }
            int ligne = scanner.nextInt();
            if (!scanner.hasNextInt()) {
                System.out.println("Saisie invalide : entrez deux nombres.");
                scanner.nextLine();
                continue;
            }
            int colonne = scanner.nextInt();
            scanner.nextLine();

            try {
                plateau.jouer(new Position(ligne, colonne), joueurActif);
                joueurActif = joueurActif.adversaire();
            } catch (InvalidPositionException e) {
                System.out.println(e.getMessage() + " Réessayez.");
            }
        }

        plateau.PrintPlateau();
        int noirs = plateau.count(Couleur.NOIR);
        int blancs = plateau.count(Couleur.BLANC);
        
        if (noirs > blancs) {
            System.out.println("Les noirs gagnent !");
        } else if (blancs > noirs) {
            System.out.println("Les blancs gagnent !");
        } else {
            System.out.println("Égalité !");
        }
    }
}
