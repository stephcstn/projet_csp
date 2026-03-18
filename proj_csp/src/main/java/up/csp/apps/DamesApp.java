package up.csp.apps;

import up.csp.Model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Fabrique de probleme CSP pour le probleme des N-Dames.
 *
 * @author MBINA DANIEL
 */
public class DamesApp {
    /**
     * Crée un CSP pour le problème des N-Dames.
     *
     * @param n Taille du plateau (ex: 8 pour un 8x8)
     * @return probleme CSP des N-Dames de taille n
     */
    public static CSP createDames(int n) {
        CSP csp = new CSP();
        List<Variable> queens = new ArrayList<>();

        // 1. Création des pièces (une reine par ligne)
        for (int i = 0; i < n; i++) {
            // Chaque variable représente la colonne (1 à n) de la reine sur la ligne i
            Variable v = new Variable("Reine_L" + i, new Domain(n));
            queens.add(v);
            csp.addVariable(v);
        }

        // 2. Gestion des colonnes : Deux reines ne peuvent pas être sur la même colonne
        csp.addConstraint(new AllDifferentConstraint(queens));

        // 3. Gestion des diagonales : Boucle sur toutes les paires de reines
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Distance entre les lignes i et j
                int dist = j - i;
                csp.addConstraint(new DiagonalConstraint(queens.get(i), queens.get(j), dist));
            }
        }

        return csp;
    }
}