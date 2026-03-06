package up.csp.Model;

public class Variable {
    private String name;
    private Domain domain;
    private Integer assignedValue = null;

    public Variable(String name, Domain domain) {
        this.name = name;
        this.domain = domain;
    }

    // Getters et Setters pour la résolution
    public Domain getDomain() { return domain; }
    public String getName() { return name; }
}