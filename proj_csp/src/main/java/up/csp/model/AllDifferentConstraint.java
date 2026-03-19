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
        for (int i = 0; i < scope.size(); i++) {
            Integer val1 = scope.get(i).getAssignedValue();
            if (val1 != null){
                for (int j = i + 1; j < scope.size(); j++) {
                    Integer val2 = scope.get(j).getAssignedValue();
                    if (val1.equals(val2)){
                        return false;
                    }
                }
            }
        }
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
        if (!scope.contains(trigger) || trigger.getAssignedValue() == null){
            return;
        }
        int val = trigger.getAssignedValue();
        for (Variable v : scope) {
            if (v != trigger && v.getAssignedValue() == null) {
                v.getDomain().remove(val);
            }
        }
    }
}