package up.csp.apps;

import up.csp.Model.*;
import java.util.ArrayList;
import java.util.List;

public class DamesApp {
    public static CSP createDames(int n) {
        CSP csp = new CSP();
        List<Variable> queens = new ArrayList<>();

        // Une variable par ligne, la valeur est la colonne
        for (int i = 0; i < n; i++) {
            Variable v = new Variable("Ligne" + i, new Domain(n));
            queens.add(v);
            csp.addVariable(v);
        }

        // Toutes les colonnes doivent être différentes
        csp.addConstraint(new AllDifferentConstraint(queens));

        // Contraintes diagonales (différence de valeur != différence de ligne)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                final int rowDist = j - i;
                final Variable q1 = queens.get(i);
                final Variable q2 = queens.get(j);

                csp.addConstraint(new Constraint() {
                    @Override
                    public boolean isSatisfied(List<Variable> vars) {
                        if (q1.getAssignedValue() == null || q2.getAssignedValue() == null) return true;
                        return Math.abs(q1.getAssignedValue() - q2.getAssignedValue()) != rowDist;
                    }
                    @Override public void reduce(Variable t, List<Variable> v) {}
                });
            }
        }
        return csp;
    }
}