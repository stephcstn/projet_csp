package up.csp.Model;

import java.util.List;

public interface Constraint {
    // Vérifie si la contrainte est satisfaite avec les assignations actuelles
    boolean isSatisfied(List<Variable> variables);

    // Réduit les domaines des variables voisines (Forward-Checking)
    void reduce(Variable trigger, List<Variable> allVariables);
}