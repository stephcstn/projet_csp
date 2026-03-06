package up.csp.Solver;

import up.csp.Model.CSP;
import up.csp.Model.Variable;
import java.util.List;

public class Backtracking {

    // On stocke la stratégie choisie (par défaut Standard ou MRV)
    private Labelling strategy = Labelling.STANDARD;

    /**
     * Définit la stratégie de choix de variable (Labelling).
     */
    public void setLabellingStrategy(Labelling strategy) {
        this.strategy = strategy;
    }

    /**
     * Lance la résolution systématique du problème[cite: 4, 29].
     */
    public boolean solve(CSP problem) {
        // Logique de l'algorithme de recherche [cite: 5, 57]
        return backtrack(problem);
    }

    private boolean backtrack(CSP problem) {
        // Utilisation de la stratégie pour choisir la prochaine variable
        // Variable next = strategy.chooseVariable(problem.getVariables());

        // ... reste de la logique récursive ...
        return false;
    }
}