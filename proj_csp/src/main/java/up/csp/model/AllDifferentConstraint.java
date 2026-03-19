package up.csp.model;

import java.util.List;

/**
 * Contrainte imposant des valeurs toutes differentes sur un ensemble de variables.
 *
 * @author DUFRENE BASILE
 */
public class AllDifferentConstraint implements Constraint {
    private final List<Variable> scope;

    /**
     * Construit une contrainte AllDifferent sur un scope donne.
     *
     * @param scope variables soumises à la contrainte
     */
    public AllDifferentConstraint(List<Variable> scope) {
        this.scope = scope;
    }

    /**
     * Verifie qu'aucune paire de variables assignees du scope ne partage la meme valeur.
     *
     * @param allVariables liste complete des variables du probleme
     * @return true si la contrainte est satisfaite, false sinon
     */
    @Override
    public boolean isSatisfied(List<Variable> allVariables) {
        // On parcourt chaque variable du groupe.
        for (int i = 0; i < scope.size(); i++) {
            // On récupère la valeur de la première variable de comparaison.
            Integer val1 = scope.get(i).getAssignedValue();

            // Si la case est vide, on passe aux suivantes.
            if (val1 != null){
                for (int j = i + 1; j < scope.size(); j++) {
                    Integer val2 = scope.get(j).getAssignedValue();

                    // Si les deux valeurs sont les memes, la contrainte n'est pas respectée.
                    if (val1.equals(val2)){
                        return false;
                    }
                }
            }
        }
        // Aucune répétition trouvée : la contrainte est respectée.
        return true;
    }

    /**
     * Retire la valeur assignee par trigger des domaines des autres variables du scope.
     *
     * @param trigger variable nouvellement assignee
     * @param allVariables liste complete des variables du probleme
     */
    @Override
    public void reduce(Variable trigger, List<Variable> allVariables) {
        // Si la variable modifiée n'est pas concernée par cette contrainte ou elle n'a pas de valeur.
        if (!scope.contains(trigger) || trigger.getAssignedValue() == null){
            return;
        }
        // On extrait la valeur (ex: le chiffre 5).
        int val = trigger.getAssignedValue();

        // On parcourt toutes les variables liées par cette contrainte (ex: toutes les cases d'une même ligne).
        for (Variable v : scope) {
            // Si la variable 'v' n'est pas celle qui vient de changer ET qu'elle n'est pas encore remplie
            if (v != trigger && v.getAssignedValue() == null) {
                // On retire la valeur du trigger du domaine de la variable.
                v.getDomain().remove(val);
            }
        }
    }
}