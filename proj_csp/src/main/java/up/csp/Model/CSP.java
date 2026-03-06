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

    public void addVariable(Variable v) { variables.add(v); }
    public void addConstraint(Constraint c) { constraints.add(c); }

    public void solve() {
        // Logique de lancement de l'algorithme de résolution
        System.out.println("Lancement de la résolution...");
    }
}