package up.csp.Integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import up.csp.Model.*;
import up.csp.apps.SudokuApp;
import up.csp.apps.DamesApp;
import up.csp.Solver.Backtracking;

public class AppIntegrationTest {

    // =======================================================
    // 1. TESTS DES GÉNÉRATEURS (Rapporte beaucoup de couverture)
    // =======================================================

    @Test
    void testSudokuGeneration_Structure() {
        // Exécute tout le code de SudokuApp
        CSP sudoku = SudokuApp.createSudoku();
        
        // Vérifie qu'on a bien 9x9 = 81 cases
        assertEquals(81, sudoku.getVariables().size(), "Un Sudoku 9x9 doit avoir 81 variables");
        
        // Vérifie les contraintes : 9 lignes + 9 colonnes + 9 blocs = 27 contraintes
        assertEquals(27, sudoku.getConstraints().size(), "Il doit y avoir 27 contraintes AllDifferent");
    }

    @Test
    void testDamesGeneration_Structure() {
        int n = 8;
        // Exécute tout le code de DamesApp
        CSP dames = DamesApp.createDames(n);
        
        assertEquals(n, dames.getVariables().size(), "Le problème des 8 dames doit avoir 8 variables");

        // Calcul mathématique des contraintes : 1 contrainte AllDifferent + (8 * 7 / 2) diagonales
        int expectedConstraints = 1 + (n * (n - 1) / 2);
        assertEquals(expectedConstraints, dames.getConstraints().size(), "Nombre de contraintes incorrect pour N=8");
    }

    // =======================================================
    // 2. TEST DE BOUT EN BOUT (Valide l'intégration globale)
    // =======================================================

    @Test
    void testSolve_MiniDames() {
        // On utilise un plateau de 4x4. C'est instantané à résoudre 
        // et ça valide que Variable + Constraint + Backtracking fonctionnent ensemble.
        CSP dames4 = DamesApp.createDames(4);
        Backtracking solver = new Backtracking();
        
        boolean success = solver.solve(dames4);

        assertTrue(success, "Le solveur doit trouver une solution pour 4 dames");
        for (Variable v : dames4.getVariables()) {
            assertNotNull(v.getAssignedValue(), "Chaque reine doit avoir été placée sur le plateau");
        }
    }

    // =======================================================
    // 3. TEST DES OUTILS (Couverture rapide des petits fichiers)
    // =======================================================

    @Test
    void testBacktrackStackAndAssignment() {
        // Exécute le code de BacktrackStack et Assignment en un seul test
        BacktrackStack stack = new BacktrackStack();
        Variable var = new Variable("TestVar", new Domain(2));

        assertTrue(stack.isEmpty(), "La pile doit être vide au départ");

        // Empile
        stack.push(var, 5, "Forward-Checking");
        assertFalse(stack.isEmpty(), "La pile ne doit plus être vide");

        // Dépile et vérifie l'Assignment
        Assignment popped = stack.pop();
        assertEquals(var, popped.getVariable());
        assertEquals(5, popped.getValue());
        assertEquals("Forward-Checking", popped.getReason());
        
        // Vérifie que la pile est à nouveau vide
        assertTrue(stack.isEmpty());
    }
}
