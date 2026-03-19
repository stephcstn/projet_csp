package up.csp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Modele d'un probleme de satisfaction de contraintes.
 *
 * @author DUFRENE BASILE
 */
public class CSP {
    private List<Variable> variables;
    private List<Constraint> constraints;

    /**
     * Cree un CSP vide.
     */
    public CSP() {
        this.variables = new ArrayList<>();
        this.constraints = new ArrayList<>();
    }

    /**
     * Ajoute une variable au probleme.
     *
     * @param v variable a ajouter
     */
    public void addVariable(Variable v) {
        variables.add(v);
    }

    /**
     * Ajoute une contrainte au probleme.
     *
     * @param c contrainte a ajouter
     */
    public void addConstraint(Constraint c) {
        constraints.add(c);
    }

    /**
     * Retourne la liste des variables du problème.
     * Indispensable pour que le Backtracking puisse itérer dessus.
     *
     * @return liste des variables du probleme
     */
    public List<Variable> getVariables() {
        return variables;
    }

    /**
     * Retourne la liste des contraintes à respecter.
     *
     * @return liste des contraintes du probleme
     */
    public List<Constraint> getConstraints() {
        return constraints;
    }
}