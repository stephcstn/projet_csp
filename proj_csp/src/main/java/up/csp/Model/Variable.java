package up.csp.Model;

/**
 * Variable d'un CSP avec nom, domaine et valeur eventuellement assignee.
 *
 * @author DUFRENE BASILE
 */
public class Variable {
    private String name;
    private Domain domain;
    private Integer assignedValue = null; // null signifie que la variable n'est pas encore fixée

    /**
     * Construit une variable non assignee.
     *
     * @param name nom de la variable
     * @param domain domaine initial de la variable
     */
    public Variable(String name, Domain domain) {
        this.name = name;
        this.domain = domain;
    }

    /**
     * Fixe la valeur de la variable pendant la recherche.
     */
    public void setAssignedValue(Integer value) {
        this.assignedValue = value;
    }

    /**
     * Récupère la valeur actuelle (utile pour isSatisfied() dans les contraintes).
     *
     * @return valeur assignee, ou null si la variable n'est pas encore fixee
     */
    public Integer getAssignedValue() {
        return assignedValue;
    }

    /**
     * Retourne le domaine de la variable.
     *
     * @return domaine courant
     */
    public Domain getDomain() {
        return domain;
    }

    /**
     * Retourne le nom de la variable.
     *
     * @return nom de la variable
     */
    public String getName() {
        return name;
    }
}