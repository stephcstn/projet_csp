package up.csp.Model;

import java.util.ArrayList;
import java.util.List;

public class CSP {
    private List<Variable> variables;
    private List<Constraint> constraints;

    public CSP() {
        this.variables = new ArrayList<>();
        this.constraints = new ArrayList<>();
    }

    public void addVariable(Variable v) {
        variables.add(v);
    }

    public void addConstraint(Constraint c) {
        constraints.add(c);
    }

    /**
     * Retourne la liste des variables du problème.
     * Indispensable pour que le Backtracking puisse itérer dessus.
     */
    public List<Variable> getVariables() {
        return variables;
    }

    /**
     * Retourne la liste des contraintes à respecter.
     */
    public List<Constraint> getConstraints() {
        return constraints;
    }
}