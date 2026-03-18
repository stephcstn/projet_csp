package up.csp.Model;

import java.util.List;

/**
 * Contrat commun pour les contraintes d'un probleme CSP.
 *
 * @author DUFRENE BASILE
 */
public interface Constraint {
    /**
     * Verifie si la contrainte est satisfaite avec les assignations courantes.
     *
     * @param variables liste des variables du probleme
     * @return true si la contrainte est respectee, false sinon
     */
    // Vérifie si la contrainte est satisfaite avec les assignations actuelles
    boolean isSatisfied(List<Variable> variables);

    /**
     * Reduit les domaines des variables impactees apres une nouvelle assignation.
     *
     * @param trigger variable qui vient d'etre assignee
     * @param allVariables liste de toutes les variables du probleme
     */
    // Réduit les domaines des variables voisines (Forward-Checking)
    void reduce(Variable trigger, List<Variable> allVariables);
}