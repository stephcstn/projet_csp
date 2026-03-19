package up.csp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class ConstraintTest {

    // On crée une implémentation "factice" (anonyme) juste pour ce test
    Constraint dummyConstraint = new Constraint() {
        @Override
        public boolean isSatisfied(List<Variable> variables) {
            return true; // Comportement par défaut pour le test
        }

        @Override
        public void reduce(Variable trigger, List<Variable> allVariables) {
            // Ne fait rien, c'est juste pour tester la signature de la méthode
        }
    };

    @Test
    void testIsSatisfied() {
        // On s'assure que la méthode accepte bien une liste vide et renvoie un booléen
        boolean result = dummyConstraint.isSatisfied(new ArrayList<>());
        assertTrue(result, "L'appel à isSatisfied via l'interface doit fonctionner");
    }

    @Test
    void testReduce() {
        // On s'assure que l'appel à reduce ne déclenche aucune erreur de compilation ou d'exécution
        assertDoesNotThrow(() -> dummyConstraint.reduce(null, new ArrayList<>()), 
            "L'appel à reduce via l'interface ne doit pas lever d'exception");
    }
}
