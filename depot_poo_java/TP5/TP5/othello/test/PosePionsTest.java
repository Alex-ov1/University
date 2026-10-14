package test;

import othello.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PosePionsTest {
    @Test
    public void ajouteUnPionSurUneCaseLibre() throws InvalidPositionException {
        Plateau plateau = new Plateau(8, 8);
        Position pos = new Position(7, 2);

        plateau.addPions(new Pions(Couleur.BLANC), pos);

        assertEquals(Couleur.BLANC, plateau.getPions(pos).getColor());
    }

    @Test
    public void positionInitialeEstJouablePourNoir() {
        Plateau plateau = plateauInitial();
        assertTrue(plateau.canPlayAt(new Position(2, 3), Couleur.NOIR));
    }

    @Test
    public void positionSansEncadrementEstInvalide() {
        Plateau plateau = plateauInitial();
        assertFalse(plateau.canPlayAt(new Position(0, 0), Couleur.NOIR));
    }

    @Test
    public void jouerRetourneLesPionsEncadres() throws InvalidPositionException {
        Plateau plateau = plateauInitial();
        plateau.jouer(new Position(2, 3), Couleur.NOIR);

        assertEquals(Couleur.NOIR, plateau.getPions(new Position(2, 3)).getColor());
        assertEquals(Couleur.NOIR, plateau.getPions(new Position(3, 3)).getColor());
    }

    private Plateau plateauInitial() {
        Plateau plateau = new Plateau(8, 8);
        try {
            plateau.addPions(new Pions(Couleur.BLANC), new Position(3, 3));
            plateau.addPions(new Pions(Couleur.NOIR), new Position(3, 4));
            plateau.addPions(new Pions(Couleur.NOIR), new Position(4, 3));
            plateau.addPions(new Pions(Couleur.BLANC), new Position(4, 4));
        } catch (InvalidPositionException e) {
            throw new AssertionError(e);
        }
        return plateau;
    }
}
