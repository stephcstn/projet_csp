package up.csp.apps;

import up.csp.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Fabrique de probleme CSP pour Sudoku 9x9.
 *
 * @author HARCHICH YANISS
 */
public class SudokuApp {
    /**
     * Cree un CSP representant un Sudoku 9x9 sans pre-remplissage.
     *
     * @return probleme CSP du Sudoku
     */
    public static CSP createSudoku() {
        CSP csp = new CSP();
        Variable[][] grid = new Variable[9][9];

        // 1. Création des 81 variables
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                grid[r][c] = new Variable("R" + r + "C" + c, new Domain(9));
                csp.addVariable(grid[r][c]);
            }
        }

        // 2. Contraintes de Lignes et Colonnes
        // Pour chaque ligne et colonne du sudoku, tous les nombres doivent être différents
        for (int i = 0; i < 9; i++) {
            // Création des listes pour stocker les variables de la ligne et de la colonne i
            List<Variable> row = new ArrayList<>();  // Contient les 9 variables de la ligne i
            List<Variable> col = new ArrayList<>();  // Contient les 9 variables de la colonne i
            
            for (int j = 0; j < 9; j++) {
                // Ajouter les variables de la ligne i (grid[i][0] à grid[i][8])
                row.add(grid[i][j]);
                // Ajouter les variables de la colonne i (grid[0][i] à grid[8][i])
                col.add(grid[j][i]);
            }
            
            // Ajouter la contrainte : tous les éléments de la ligne doivent être différents
            csp.addConstraint(new AllDifferentConstraint(row));
            // Ajouter la contrainte : tous les éléments de la colonne doivent être différents
            csp.addConstraint(new AllDifferentConstraint(col));
        }

        // 3. Contraintes de Blocs 3x3
        // La grille 9x9 est divisée en 9 blocs de 3x3
        // Chaque bloc doit contenir les chiffres 1-9 une seule fois
        for (int r = 0; r < 9; r += 3) {  // Itérer sur les blocs par ligne (0, 3, 6)
            for (int c = 0; c < 9; c += 3) {  // Itérer sur les blocs par colonne (0, 3, 6)
                // Création d'une liste pour stocker les 9 variables du bloc
                List<Variable> block = new ArrayList<>();
                
                // Parcourir les 3x3 = 9 cases du bloc actuel
                for (int i = 0; i < 3; i++) {  // i : position dans le bloc (ligne)
                    for (int j = 0; j < 3; j++) {  // j : position dans le bloc (colonne)
                        // Ajouter la variable à la position (r+i, c+j) au bloc
                        // Exemple : bloc en haut-à-gauche commence à (0,0)
                        block.add(grid[r + i][c + j]);
                    }
                }
                
                // Ajouter la contrainte : tous les éléments du bloc doivent être différents
                csp.addConstraint(new AllDifferentConstraint(block));
            }
        }
        return csp;
    }
}