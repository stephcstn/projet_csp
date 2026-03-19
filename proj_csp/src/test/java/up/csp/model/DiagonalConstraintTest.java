package up.csp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DiagonalConstraintTest {

    @Test
    void testIsSatisfied() {
        // 1. Préparation (Arrange)
        Variable v1 = mock(Variable.class);
        Variable v2 = mock(Variable.class);
        
        // Supposons deux reines espacées de 2 lignes
        int rowDistance = 2; 
        DiagonalConstraint constraint = new DiagonalConstraint(v1, v2, rowDistance);

        // --- Scénario 1 : Conflit diagonal (Échec attendu) ---
        // Reine 1 en colonne 3, Reine 2 en colonne 5. 
        // Distance des colonnes = |3 - 5| = 2. C'est égal à la distance des lignes (2) -> CONFLIT !
        when(v1.getAssignedValue()).thenReturn(3);
        when(v2.getAssignedValue()).thenReturn(5);
        assertFalse(constraint.isSatisfied(null), "La contrainte doit échouer si les variables sont sur la même diagonale");

        // --- Scénario 2 : Pas de conflit (Succès attendu) ---
        // Reine 1 en colonne 3, Reine 2 en colonne 6. 
        // Distance des colonnes = |3 - 6| = 3. C'est différent de la distance des lignes (2) -> OK !
        when(v2.getAssignedValue()).thenReturn(6);
        assertTrue(constraint.isSatisfied(null), "La contrainte doit être satisfaite si les variables ne sont pas en diagonale");

        // --- Scénario 3 : Variables non assignées ---
        when(v2.getAssignedValue()).thenReturn(null);
        assertTrue(constraint.isSatisfied(null), "La contrainte doit être ignorée (renvoyer true) si une variable manque");
    }

    @Test
    void testReduce() {
        // 1. Préparation (Arrange)
        Variable v1 = mock(Variable.class);
        Variable v2 = mock(Variable.class);
        Domain d2 = mock(Domain.class);

        // Supposons deux reines sur des lignes adjacentes (distance = 1)
        int rowDistance = 1;
        DiagonalConstraint constraint = new DiagonalConstraint(v1, v2, rowDistance);

        // On simule que la première reine (v1) vient d'être posée sur la colonne 4
        when(v1.getAssignedValue()).thenReturn(4);
        
        // La deuxième reine (v2) n'a pas encore de colonne, mais possède un domaine
        when(v2.getAssignedValue()).thenReturn(null);
        when(v2.getDomain()).thenReturn(d2);

        // 2. Exécution (Act)
        // On demande à la contrainte de faire son travail de filtrage via le Forward Checking
        constraint.reduce(v1, null);

        // 3. Vérification (Assert)
        // Si v1 est en colonne 4, les diagonales adjacentes (ligne du dessous) sont les colonnes 3 et 5.
        // On s'assure que ces deux valeurs sont bien retirées du domaine de v2.
        verify(d2, times(1)).remove(3); // 4 - 1
        verify(d2, times(1)).remove(5); // 4 + 1
    }
}
