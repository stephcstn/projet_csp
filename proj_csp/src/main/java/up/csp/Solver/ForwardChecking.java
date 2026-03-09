package up.csp.Solver;

import up.csp.Model.Variable;
import up.csp.Model.Constraint;
import up.csp.Model.Assignment;
import java.util.List;

public class ForwardChecking {

    /**
     * Exécute la réduction des domaines et vérifie si la recherche peut continuer.
     */
    public boolean run(Assignment lastAssignment, List<Variable> allVariables, List<Constraint> constraints) {
        Variable trigger = lastAssignment.getVariable();

        // 1. Demander aux contraintes de filtrer les domaines voisins
        for (Constraint constraint : constraints) {
            constraint.reduce(trigger, allVariables);
        }

        // 2. Vérifier si une variable s'est retrouvée avec un domaine vide
        for (Variable v : allVariables) {
            if (v.getDomain().isEmpty()) {
                return false; // Déclenche le retour arrière (backtrack)
            }
        }

        return true; // La réduction est valide, on peut continuer
    }
}