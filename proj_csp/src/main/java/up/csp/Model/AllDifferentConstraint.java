package up.csp.Model;

import java.util.List;

public class AllDifferentConstraint implements Constraint {
    private List<Variable> scope;

    public AllDifferentConstraint(List<Variable> scope) {
        this.scope = scope;
    }

    @Override
    public boolean isSatisfied(List<Variable> allVariables) {
        for (int i = 0; i < scope.size(); i++) {
            Integer val1 = scope.get(i).getAssignedValue();
            if (val1 == null) continue;
            for (int j = i + 1; j < scope.size(); j++) {
                Integer val2 = scope.get(j).getAssignedValue();
                if (val1.equals(val2)) return false;
            }
        }
        return true;
    }

    @Override
    public void reduce(Variable trigger, List<Variable> allVariables) {
        if (!scope.contains(trigger) || trigger.getAssignedValue() == null) return;
        int val = trigger.getAssignedValue();
        for (Variable v : scope) {
            if (v != trigger && v.getAssignedValue() == null) {
                v.getDomain().remove(val);
            }
        }
    }
}