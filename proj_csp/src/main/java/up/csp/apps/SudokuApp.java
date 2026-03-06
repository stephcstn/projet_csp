package up.csp.apps;

import up.csp.Model.*;
import java.util.ArrayList;
import java.util.List;

public class SudokuApp {
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
        for (int i = 0; i < 9; i++) {
            List<Variable> row = new ArrayList<>();
            List<Variable> col = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                row.add(grid[i][j]);
                col.add(grid[j][i]);
            }
            csp.addConstraint(new AllDifferentConstraint(row));
            csp.addConstraint(new AllDifferentConstraint(col));
        }

        // 3. Contraintes de Blocs 3x3
        for (int r = 0; r < 9; r += 3) {
            for (int c = 0; c < 9; c += 3) {
                List<Variable> block = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) block.add(grid[r + i][c + j]);
                }
                csp.addConstraint(new AllDifferentConstraint(block));
            }
        }
        return csp;
    }
}