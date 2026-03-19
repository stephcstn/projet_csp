package up.csp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VariableTest {

    @Test
    void testGetName() {
        // Préparation
        Domain mockDomain = mock(Domain.class);
        Variable var = new Variable("Case_1_1", mockDomain);

        // Vérification
        assertEquals("Case_1_1", var.getName(), "Le nom de la variable doit correspondre à celui passé au constructeur");
    }

    @Test
    void testGetDomain() {
        // Préparation
        Domain mockDomain = mock(Domain.class);
        Variable var = new Variable("A1", mockDomain);

        // Vérification
        assertEquals(mockDomain, var.getDomain(), "Le domaine renvoyé doit être l'instance exacte passée au constructeur");
    }

    @Test
    void testGetAssignedValue() {
        // Préparation
        Domain mockDomain = mock(Domain.class);
        Variable var = new Variable("A1", mockDomain);

        // Vérification cruciale : par défaut, une variable de CSP ne doit pas avoir de valeur
        assertNull(var.getAssignedValue(), "Au moment de l'instanciation, la valeur assignée doit strictement être null");
    }

    @Test
    void testSetAssignedValue() {
        // Préparation
        Domain mockDomain = mock(Domain.class);
        Variable var = new Variable("A1", mockDomain);

        // 1. On assigne une valeur (Simulation d'une étape de l'algorithme)
        var.setAssignedValue(5);
        assertEquals(5, var.getAssignedValue(), "La méthode doit correctement enregistrer la valeur 5");

        // 2. On retire la valeur (Simulation d'un retour en arrière / Backtracking)
        var.setAssignedValue(null);
        assertNull(var.getAssignedValue(), "La méthode doit permettre de réinitialiser la valeur à null");
    }
}
