package up.csp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

public class CSPTest {

    @Test
    void testGetVariables() {
        // 1. Préparation
        CSP csp = new CSP();

        // 2. Vérification de l'initialisation
        // C'est vital : la liste ne doit pas être 'null' au démarrage, mais simplement vide.
        assertNotNull(csp.getVariables(), "La liste des variables ne doit pas être null à la création");
        assertTrue(csp.getVariables().isEmpty(), "La liste des variables doit être vide à l'initialisation");
    }

    @Test
    void testGetConstraints() {
        // 1. Préparation
        CSP csp = new CSP();

        // 2. Vérification de l'initialisation
        assertNotNull(csp.getConstraints(), "La liste des contraintes ne doit pas être null à la création");
        assertTrue(csp.getConstraints().isEmpty(), "La liste des contraintes doit être vide à l'initialisation");
    }

    @Test
    void testAddVariable() {
        // 1. Préparation
        CSP csp = new CSP();
        Variable mockVar1 = mock(Variable.class);
        Variable mockVar2 = mock(Variable.class);

        // 2. Exécution
        csp.addVariable(mockVar1);
        csp.addVariable(mockVar2);

        // 3. Vérification
        List<Variable> vars = csp.getVariables();
        assertEquals(2, vars.size(), "La liste doit contenir exactement 2 variables après 2 ajouts");
        assertTrue(vars.contains(mockVar1), "La liste doit bien contenir la première variable ajoutée");
        assertTrue(vars.contains(mockVar2), "La liste doit bien contenir la deuxième variable ajoutée");
    }

    @Test
    void testAddConstraint() {
        // 1. Préparation
        CSP csp = new CSP();
        Constraint mockConstraint = mock(Constraint.class);

        // 2. Exécution
        csp.addConstraint(mockConstraint);

        // 3. Vérification
        List<Constraint> constraints = csp.getConstraints();
        assertEquals(1, constraints.size(), "La liste doit contenir exactement 1 contrainte");
        assertEquals(mockConstraint, constraints.get(0), "La contrainte récupérée doit être celle que l'on vient d'ajouter");
    }
}
