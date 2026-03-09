package up.csp.Solver;

import up.csp.Model.*;
import java.util.List;

public class Backtracking {
    private Labelling strategy = Labelling.STANDARD;
    private ForwardChecking fc = new ForwardChecking();

    private boolean[][] saveDomains(List<Variable> vars) {
        boolean[][] backup = new boolean[vars.size()][];
        for (int i = 0; i < vars.size(); i++) {
            backup[i] = vars.get(i).getDomain().getValuesCopy();
        }
        return backup;
    }

    private void restoreDomains(List<Variable> vars, boolean[][] backup) {
        for (int i = 0; i < vars.size(); i++) {
            vars.get(i).getDomain().setValues(backup[i]);
        }
    }

    public boolean solve(CSP problem) {
        return backtrack(problem.getVariables(), problem.getConstraints());
    }

    private boolean backtrack(List<Variable> vars, List<Constraint> constraints) {
        // 1. Choisir la prochaine variable (Labelling)
        Variable current = selectVariable(vars);
        if (current == null) return true; // Toutes les variables sont assignées

        // 2. Tester les valeurs possibles du domaine
        for (int val = 1; val <= 9; val++) {
            if (current.getDomain().isPresent(val)) {

                boolean[][] backup = saveDomains(vars);
                // Sauvegarder l'état actuel pour un éventuel retour arrière
                // (Ici, on assigne la valeur et on crée l'objet Assignment)
                current.setAssignedValue(val);
                Assignment currentAssign = new Assignment(current, val, "Search");

                // 3. Forward-Checking : Réduire les domaines des voisins
                // On passe l'assignation actuelle pour filtrer les variables liées
                if (fc.run(currentAssign, vars, constraints)) {
                    // Si le FC ne détecte pas de domaine vide, on continue récursivement
                    if (backtrack(vars, constraints)) {
                        return true;
                    }
                }

                // 4. Échec ou Retour Arrière (Backtrack)
                // On réinitialise la variable pour tester la valeur suivante
                restoreDomains(vars, backup);
                current.setAssignedValue(null);

                // Note : Pour un solveur complet, il faudrait ici restaurer les domaines
                // qui ont été modifiés par le FC via la BacktrackStack.
            }
        }
        return false; // Aucune solution dans cette branche
    }

    private Variable selectVariable(List<Variable> vars) {
        // Logique simplifiée : prend la première variable libre
        for (Variable v : vars) {
            if (v.getAssignedValue() == null) return v;
        }
        return null;
    }

    public void setLabellingStrategy(Labelling strategy) {
        this.strategy = strategy;
    }
}