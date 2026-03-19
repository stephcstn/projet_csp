package up.csp.model;

import java.util.List;

/**
 * Contrainte de non-conflit diagonal entre deux reines du probleme des N-Dames.
 *
 * @author DUFRENE BASILE
 */
public class DiagonalConstraint implements Constraint {
    private Variable v1;
    private Variable v2;
    private int rowDistance;

    /**
     * Construit une contrainte diagonale entre deux variables.
     *
     * @param v1 premiere variable
     * @param v2 seconde variable
     * @param rowDistance distance entre les lignes des deux variables
     */
    public DiagonalConstraint(Variable v1, Variable v2, int rowDistance) {
        this.v1 = v1;
        this.v2 = v2;
        this.rowDistance = rowDistance;
    }

    /**
     * Verifie l'absence de conflit diagonal pour les deux variables.
     *
     * @param allVariables liste complete des variables du probleme
     * @return true si la contrainte est respectee, false sinon
     */
    @Override
    public boolean isSatisfied(List<Variable> allVariables) {
        // On récupère les colonnes assignées aux deux reines.
        Integer val1 = v1.getAssignedValue();
        Integer val2 = v2.getAssignedValue();

        // Si l'une des deux reines n'est pas posée, il n'y a pas de conflit.
        if (val1 == null || val2 == null){
            return true;
        }

        // On vérifie que l'écart entre colonnes est différent de l'écart entre lignes.
        return Math.abs(val1 - val2) != rowDistance;
    }

    /**
     * Supprime dans le domaine cible les valeurs qui creent un conflit diagonal.
     *
     * @param trigger variable nouvellement assignee
     * @param allVariables liste complete des variables du probleme
     */
    @Override
    public void reduce(Variable trigger, List<Variable> allVariables) {
        // Si la variable modifiée n'appartient pas à cette contrainte, on arrête.
        if (trigger != v1 && trigger != v2){
            return;
        }

        // Si la variable modifiée vient d'être vidée, on arrête.
        if (trigger.getAssignedValue() == null) {
            return;
        }

        Variable target;

        // On identifie laquelle des deux variables doit être filtrée (la cible).
        if (trigger == v1) {
            target = v2;
        } else {
            target = v1;
        }

        // Si la cible est vide, on retire les valeurs créant un conflit diagonal.
        if (target.getAssignedValue() == null) {
            int triggerVal = trigger.getAssignedValue();

            // On supprime les deux positions diagonales interdites dans le domaine de la cible.
            target.getDomain().remove(triggerVal - rowDistance);
            target.getDomain().remove(triggerVal + rowDistance);
        }
    }
}