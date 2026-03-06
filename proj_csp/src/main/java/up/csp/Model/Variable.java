package up.csp.Model;

public class Variable {
    private String name;
    private Domain domain;
    private Integer assignedValue = null; // null signifie que la variable n'est pas encore fixée [cite: 57, 77]

    public Variable(String name, Domain domain) {
        this.name = name;
        this.domain = domain;
    }

    /**
     * Fixe la valeur de la variable pendant la recherche[cite: 57, 69].
     */
    public void setAssignedValue(Integer value) {
        this.assignedValue = value;
    }

    /**
     * Récupère la valeur actuelle (utile pour isSatisfied() dans les contraintes)[cite: 57, 76].
     */
    public Integer getAssignedValue() {
        return assignedValue;
    }

    public Domain getDomain() {
        return domain;
    }

    public String getName() {
        return name;
    }
}