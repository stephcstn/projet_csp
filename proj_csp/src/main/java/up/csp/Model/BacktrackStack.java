package up.csp.Model;
import java.util.Stack;

/**
 * Pile utilitaire pour memoriser les assignations durant le backtracking.
 *
 * @author DUFRENE BASILE
 */
public class BacktrackStack {
    // La pile stocke désormais des objets de type Assignment
    private Stack<Assignment> stack;

    /**
     * Construit une pile vide d'assignations.
     */
    public BacktrackStack() {
        this.stack = new Stack<>();
    }

    /**
     * Ajoute une nouvelle étape à la pile
     *
     * @param var variable concernee
     * @param val valeur assignee
     * @param reason motif de l'assignation
     */
    public void push(Variable var, int val, String reason) {
        stack.push(new Assignment(var, val, reason));
    }

    /**
     * Récupère la dernière assignation pour le retour arrière
     *
     * @return derniere assignation, ou null si la pile est vide
     */
    public Assignment pop() {
        return stack.isEmpty() ? null : stack.pop();
    }

    /**
     * Indique si la pile est vide.
     *
     * @return true si aucune assignation n'est stockee, false sinon
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}