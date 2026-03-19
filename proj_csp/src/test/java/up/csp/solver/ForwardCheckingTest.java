package up.csp.solver;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import up.csp.model.Variable;
import up.csp.model.Constraint;
import up.csp.model.Assignment;
import up.csp.model.Domain;

import java.util.Arrays;
import java.util.List;

public class ForwardCheckingTest {

    @Test
    void testRun_Succes_AucunDomaineVide() {
        // 1. Préparation (Arrange)
        ForwardChecking fc = new ForwardChecking();

        // Création des fausses dépendances (Mocks)
        Variable var1 = mock(Variable.class);
        Variable var2 = mock(Variable.class);
        Domain domainePlein = mock(Domain.class);
        Constraint contrainte = mock(Constraint.class);
        Assignment assignement = mock(Assignment.class);

        // Configuration des comportements simulés
        when(assignement.getVariable()).thenReturn(var1);
        when(var1.getDomain()).thenReturn(domainePlein);
        when(var2.getDomain()).thenReturn(domainePlein);
        when(domainePlein.isEmpty()).thenReturn(false); // Aucun domaine n'est vide

        List<Variable> variables = Arrays.asList(var1, var2);
        List<Constraint> contraintes = Arrays.asList(contrainte);

        // 2. Exécution (Act)
        boolean resultat = fc.run(assignement, variables, contraintes);

        // 3. Vérifications (Assert)
        assertTrue(resultat, "Le ForwardChecking doit renvoyer true si aucun domaine n'est vide");
        
        // On vérifie que la contrainte a bien été appelée pour réduire les domaines
        verify(contrainte, times(1)).reduce(var1, variables);
    }

    @Test
    void testRun_Echec_UnDomaineVide() {
        // 1. Préparation (Arrange)
        ForwardChecking fc = new ForwardChecking();

        Variable var1 = mock(Variable.class);
        Variable var2 = mock(Variable.class);
        Domain domainePlein = mock(Domain.class);
        Domain domaineVide = mock(Domain.class);
        Constraint contrainte = mock(Constraint.class);
        Assignment assignement = mock(Assignment.class);

        when(assignement.getVariable()).thenReturn(var1);
        
        // On simule que la variable 1 a encore des valeurs, mais la variable 2 est vide
        when(var1.getDomain()).thenReturn(domainePlein);
        when(domainePlein.isEmpty()).thenReturn(false);
        
        when(var2.getDomain()).thenReturn(domaineVide);
        when(domaineVide.isEmpty()).thenReturn(true); // C'est ici que l'impasse se crée

        List<Variable> variables = Arrays.asList(var1, var2);
        List<Constraint> contraintes = Arrays.asList(contrainte);

        // 2. Exécution (Act)
        boolean resultat = fc.run(assignement, variables, contraintes);

        // 3. Vérification (Assert)
        assertFalse(resultat, "Le ForwardChecking doit renvoyer false et déclencher un backtrack car un domaine est vide");
    }
}