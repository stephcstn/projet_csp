package up.csp.solver;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import up.csp.model.CSP;
import up.csp.apps.SudokuApp;

public class BacktrackingTest {

    @Test
    void testSetLabellingStrategy() {
        // 1. Préparation
        Backtracking solver = new Backtracking();

        // 2. Exécution & Vérification
        // Comme la méthode ne renvoie rien (void) et qu'il n'y a pas de "getStrategy()",
        // on s'assure simplement que le changement de stratégie ne fait pas planter l'application.
        assertDoesNotThrow(() -> solver.setLabellingStrategy(Labelling.MRV),
                "Changer la stratégie ne devrait pas lever d'exception");
    }

    @Test
    void testSolve_WithValidSudoku() {
        // 1. Préparation (Arrange)
        Backtracking solver = new Backtracking();
        // On utilise votre générateur de Sudoku pour obtenir un problème complet
        CSP problem = SudokuApp.createSudoku();

        // 2. Exécution (Act)
        boolean result = solver.solve(problem);

        // 3. Vérification (Assert)
        // On vérifie que le solveur renvoie 'true' (solution trouvée)
        assertTrue(result, "Le solveur doit trouver une solution pour une grille de Sudoku valide");

        // (Optionnel mais recommandé) On vérifie qu'il n'y a plus aucune variable vide
        boolean allAssigned = problem.getVariables().stream()
                .noneMatch(v -> v.getAssignedValue() == null);
        
        assertTrue(allAssigned, "Toutes les variables de la grille doivent avoir une valeur assignée");
    }
}
