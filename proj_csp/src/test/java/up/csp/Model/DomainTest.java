package up.csp.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainTest {

    @Test
    void testIsEmpty() {
        // Préparation d'un domaine de taille 3 (valeurs possibles : 1, 2, 3)
        Domain domain = new Domain(3);
        
        // Vérification initiale
        assertFalse(domain.isEmpty(), "Un domaine nouvellement créé ne doit pas être vide");

        // On retire toutes les valeurs
        domain.remove(1);
        domain.remove(2);
        domain.remove(3);

        // Vérification finale
        assertTrue(domain.isEmpty(), "Le domaine doit être vide après avoir retiré toutes les valeurs");
    }

    @Test
    void testIsPresent() {
        Domain domain = new Domain(5);

        // Les valeurs normales doivent être présentes
        assertTrue(domain.isPresent(1), "La valeur 1 doit être présente");
        assertTrue(domain.isPresent(5), "La valeur 5 doit être présente");

        // Vérification des limites (index 0 et index supérieur au max)
        assertFalse(domain.isPresent(0), "L'index 0 est hors limites et doit renvoyer false");
        assertFalse(domain.isPresent(6), "Une valeur supérieure au max doit renvoyer false");
    }

    @Test
    void testRemove() {
        Domain domain = new Domain(9); // Taille typique pour un Sudoku
        
        domain.remove(5);
        
        // La valeur 5 doit avoir disparu, mais pas les autres
        assertFalse(domain.isPresent(5), "La valeur 5 doit être absente après suppression");
        assertTrue(domain.isPresent(4), "La valeur 4 doit toujours être présente");

        // Robustesse : supprimer une valeur hors limites ne doit pas faire planter l'application
        assertDoesNotThrow(() -> domain.remove(10), "Retirer une valeur hors limites ne doit pas lever d'exception");
    }

    @Test
    void testGetValuesCopy() {
        Domain domain = new Domain(4);
        domain.remove(2);

        // On crée la copie
        boolean[] copy = domain.getValuesCopy();
        
        // On modifie l'original APRÈS avoir fait la copie
        domain.remove(1);

        // Vérifications
        assertFalse(copy[2], "La copie doit avoir conservé la suppression initiale de la valeur 2");
        assertTrue(copy[1], "La copie doit être indépendante : la suppression ultérieure de 1 ne doit pas l'affecter");
    }

    @Test
    void testSetValues() {
        Domain domain = new Domain(3);
        
        // On crée un tableau spécifique (l'index 0 est ignoré par votre logique)
        boolean[] newValues = {false, true, false, true}; 
        
        // Injection du tableau
        domain.setValues(newValues);

        // Vérifications que le domaine a bien adopté ces nouvelles valeurs
        assertTrue(domain.isPresent(1), "La valeur 1 devrait être présente (true)");
        assertFalse(domain.isPresent(2), "La valeur 2 devrait être absente (false)");
        assertTrue(domain.isPresent(3), "La valeur 3 devrait être présente (true)");
    }
}
