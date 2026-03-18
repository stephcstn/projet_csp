package up.csp.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class AllDifferentConstraintTest {

    @Test
    void testIsSatisfied() {
        // Préparation (Arrange)
        Variable v1 = mock(Variable.class);
        Variable v2 = mock(Variable.class);
        Variable v3 = mock(Variable.class);

        List<Variable> scope = Arrays.asList(v1, v2, v3);
        AllDifferentConstraint constraint = new AllDifferentConstraint(scope);

        // Scénario 1 : Toutes les variables ont des valeurs différentes
        when(v1.getAssignedValue()).thenReturn(1);
        when(v2.getAssignedValue()).thenReturn(2);
        when(v3.getAssignedValue()).thenReturn(3);
        
        // Note : le paramètre allVariables n'est pas utilisé dans cette implémentation, on peut passer null
        assertTrue(constraint.isSatisfied(null), "La contrainte doit être satisfaite si toutes les valeurs sont distinctes");

        // Scénario 2 : Deux variables ont la même valeur (Conflit)
        when(v3.getAssignedValue()).thenReturn(2); 
        assertFalse(constraint.isSatisfied(null), "La contrainte doit échouer si deux variables ont la même valeur");

        // Scénario 3 : Variables non assignées (null) ignorées
        when(v2.getAssignedValue()).thenReturn(null);
        when(v3.getAssignedValue()).thenReturn(null);
        assertTrue(constraint.isSatisfied(null), "La contrainte doit ignorer les variables non assignées et rester valide");
    }

    @Test
    void testReduce() {
        // Préparation (Arrange)
        Variable trigger = mock(Variable.class);
        Variable v2 = mock(Variable.class);
        Variable v3 = mock(Variable.class);
        
        Domain d2 = mock(Domain.class);
        Domain d3 = mock(Domain.class);

        when(v2.getDomain()).thenReturn(d2);
        when(v3.getDomain()).thenReturn(d3);

        List<Variable> scope = Arrays.asList(trigger, v2, v3);
        AllDifferentConstraint constraint = new AllDifferentConstraint(scope);

        // On simule l'assignation de la valeur "5" au trigger
        when(trigger.getAssignedValue()).thenReturn(5);
        
        // Les autres variables n'ont pas encore de valeur (elles doivent donc être réduites)
        when(v2.getAssignedValue()).thenReturn(null);
        when(v3.getAssignedValue()).thenReturn(null);

        // Exécution (Act)
        constraint.reduce(trigger, null);

        // Vérification (Assert)
        // On s'assure que la valeur "5" a bien été supprimée des domaines des variables voisines
        verify(d2, times(1)).remove(5);
        verify(d3, times(1)).remove(5);
        
        // On s'assure qu'on n'a pas essayé de modifier le domaine du trigger lui-même
        verify(trigger, never()).getDomain();
    }
}
