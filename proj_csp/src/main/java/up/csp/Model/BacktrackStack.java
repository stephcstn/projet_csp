package up.csp.Model;
import java.util.Stack;

public class BacktrackStack {
    // La pile stocke désormais des objets de type Assignment
    private Stack<Assignment> stack;

    public BacktrackStack() {
        this.stack = new Stack<>();
    }

    /**
     * Ajoute une nouvelle étape à la pile [cite: 69]
     */
    public void push(Variable var, int val, String reason) {
        stack.push(new Assignment(var, val, reason));
    }

    /**
     * Récupère la dernière assignation pour le retour arrière [cite: 70]
     */
    public Assignment pop() {
        return stack.isEmpty() ? null : stack.pop();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}