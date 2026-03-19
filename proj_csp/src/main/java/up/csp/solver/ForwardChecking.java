package up.csp.solver;

import up.csp.model.Variable;
import up.csp.model.Constraint;
import up.csp.model.Assignment;
import java.util.List;

/**
 * Mecanisme de forward-checking pour filtrer les domaines apres chaque assignation.
 *
 * @author DUFRENE BASILE
 */
public class ForwardChecking {

    /**
     * Exécute la réduction des domaines et vérifie si la recherche peut continuer.
        *
        * @param lastAssignment derniere assignation effectuee
        * @param allVariables ensemble des variables du probleme
        * @param constraints ensemble des contraintes à appliquer
        * @return true si la recherche peut continuer, false si un domaine est vide
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