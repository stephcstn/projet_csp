package up.csp.Solver;

import up.csp.Model.Variable;
import up.csp.Model.Constraint;
import up.csp.Model.Assignment;
import java.util.List;

public class ForwardChecking {

    /**
     * Exécute la réduction des domaines suite à une nouvelle assignation.
     * @return true si tous les domaines restent non vides, false sinon (échec).
     */
    public boolean run(Assignment lastAssignment, List<Variable> allVariables, List<Constraint> constraints) {
        Variable trigger = lastAssignment.getVariable();

        // Pour chaque contrainte impliquant la variable qui vient d'être modifiée
        for (Constraint constraint : constraints) {
            // On demande à la contrainte de réduire les domaines des variables voisines
            constraint.reduce(trigger, allVariables);
        }

        // Vérification : si un domaine devient vide, c'est un échec (retourArriere)
        for (Variable v : allVariables) {
            if (v.getDomain().isEmpty()) { // Suppose une méthode isEmpty() dans Domain
                return false;
            }
        }

        return true; // Succès de la réduction
    }
}