package up.csp.Model;
/**
 * Représente une assignation de valeur à une variable
 * avec la raison (motif) pour le backtracking.
 */
public class Assignment {
    private Variable variable;
    private int value;
    private String reason; // La "raison" ou "motif" (ex: "Backtracking", "Forward-Checking")

    public Assignment(Variable variable, int value, String reason) {
        this.variable = variable;
        this.value = value;
        this.reason = reason;
    }

    // Getters
    public Variable getVariable() { return variable; }
    public int getValue() { return value; }
    public String getReason() { return reason; }
}