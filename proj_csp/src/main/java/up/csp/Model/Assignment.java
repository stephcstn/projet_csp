package up.csp.Model;
/**
 * Représente une assignation de valeur à une variable
 * avec la raison (motif) pour le backtracking.
 *
 * @author DUFRENE BASILE
 */
public class Assignment {
    private Variable variable;
    private int value;
    private String reason; // (ex: "Backtracking", "Forward-Checking")

    /**
     * Cree une assignation variable/valeur avec son motif.
     *
     * @param variable variable assignee
     * @param value valeur assignee
     * @param reason raison de cette assignation
     */
    public Assignment(Variable variable, int value, String reason) {
        this.variable = variable;
        this.value = value;
        this.reason = reason;
    }

    // Getters
    /**
     * Retourne la variable de l'assignation.
     *
     * @return variable concernee
     */
    public Variable getVariable() { return variable; }

    /**
     * Retourne la valeur assignee.
     *
     * @return valeur assignee
     */
    public int getValue() { return value; }

    /**
     * Retourne le motif de l'assignation.
     *
     * @return motif de l'assignation
     */
    public String getReason() { return reason; }
}