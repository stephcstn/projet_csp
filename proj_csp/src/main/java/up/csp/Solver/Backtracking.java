package up.csp.Solver;

import up.csp.Model.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Backtracking {
    private Labelling strategy = Labelling.STANDARD;
    private boolean useRandom = false; // Le fameux switch
    private ForwardChecking fc = new ForwardChecking();

    public boolean solve(CSP problem) {
        return backtrack(problem.getVariables(), problem.getConstraints());
    }

    private boolean backtrack(List<Variable> vars, List<Constraint> constraints) {
        // 1. Sélection de la variable (Heuristique MRV ou Standard)
        Variable current = selectVariable(vars);
        if (current == null) return true;

        // 2. Préparation des valeurs du domaine
        List<Integer> valuesToTest = new ArrayList<>();
        for (int i = 1; i <= current.getDomain().getMaxValue(); i++) {
            if (current.getDomain().isPresent(i)) {
                valuesToTest.add(i);
            }
        }

        // --- GESTION DE L'ALÉA ---
        if (useRandom) {
            Collections.shuffle(valuesToTest);
        }

        // 3. Exploration des branches
        for (int val : valuesToTest) {
            boolean[][] backup = saveDomains(vars); // Sauvegarde avant FC

            current.setAssignedValue(val);
            Assignment assign = new Assignment(current, val, "Search");

            if (fc.run(assign, vars, constraints)) {
                if (backtrack(vars, constraints)) return true;
            }

            // Backtrack : Restauration de l'état
            restoreDomains(vars, backup);
            current.setAssignedValue(null);
        }
        return false;
    }

    private Variable selectVariable(List<Variable> vars) {
        if (this.strategy == Labelling.MRV) {
            Variable best = null;
            int minSize = Integer.MAX_VALUE;
            for (Variable v : vars) {
                if (v.getAssignedValue() == null) {
                    int size = v.getDomain().getSize();
                    if (size < minSize) { minSize = size; best = v; }
                }
            }
            return best;
        }
        for (Variable v : vars) if (v.getAssignedValue() == null) return v;
        return null;
    }

    // Utilitaires de gestion mémoire
    private boolean[][] saveDomains(List<Variable> vars) {
        boolean[][] backup = new boolean[vars.size()][];
        for (int i = 0; i < vars.size(); i++) backup[i] = vars.get(i).getDomain().getValuesCopy();
        return backup;
    }

    private void restoreDomains(List<Variable> vars, boolean[][] backup) {
        for (int i = 0; i < vars.size(); i++) vars.get(i).getDomain().setValues(backup[i]);
    }

    // Setters pour le contrôleur
    public void setLabellingStrategy(Labelling s) { this.strategy = s; }
    public void setRandomized(boolean r) { this.useRandom = r; }
}