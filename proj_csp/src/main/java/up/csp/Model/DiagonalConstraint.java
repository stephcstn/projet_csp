package up.csp.Model;

import java.util.List;

public class DiagonalConstraint implements Constraint {
    private Variable v1;
    private Variable v2;
    private int rowDistance;

    public DiagonalConstraint(Variable v1, Variable v2, int rowDistance) {
        this.v1 = v1;
        this.v2 = v2;
        this.rowDistance = rowDistance;
    }

    @Override
    public boolean isSatisfied(List<Variable> allVariables) {
        Integer val1 = v1.getAssignedValue();
        Integer val2 = v2.getAssignedValue();

        if (val1 == null || val2 == null) return true;

        // La condition de non-conflit diagonal : |col1 - col2| != |row1 - row2|
        return Math.abs(val1 - val2) != rowDistance;
    }

    @Override
    public void reduce(Variable trigger, List<Variable> allVariables) {
        // Si la variable qui a changé est l'une des deux de la contrainte
        if (trigger != v1 && trigger != v2) return;
        if (trigger.getAssignedValue() == null) return;

        Variable target = (trigger == v1) ? v2 : v1;
        if (target.getAssignedValue() == null) {
            int triggerVal = trigger.getAssignedValue();

            // On retire les deux valeurs diagonales interdites dans le domaine de la cible
            // v1_val - rowDist et v1_val + rowDist
            target.getDomain().remove(triggerVal - rowDistance);
            target.getDomain().remove(triggerVal + rowDistance);
        }
    }
}