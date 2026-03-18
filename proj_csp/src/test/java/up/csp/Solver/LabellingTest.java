package up.csp.Solver;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LabellingTest {

    @Test
    void testValueOf() {
        // Vérifie que les chaînes de caractères exactes renvoient les bonnes constantes
        assertEquals(Labelling.STANDARD, Labelling.valueOf("STANDARD"), "La chaîne 'STANDARD' doit renvoyer Labelling.STANDARD");
        assertEquals(Labelling.MRV, Labelling.valueOf("MRV"), "La chaîne 'MRV' doit renvoyer Labelling.MRV");

        // Vérification de sécurité : s'assure qu'une chaîne invalide lève bien la bonne exception
        assertThrows(IllegalArgumentException.class, () -> Labelling.valueOf("INCONNU"), 
            "Une chaîne inexistante doit déclencher une IllegalArgumentException");
    }

    @Test
    void testValues() {
        // Récupère toutes les valeurs disponibles dans l'énumération
        Labelling[] values = Labelling.values();

        // 1. Vérifie qu'il y a exactement 2 stratégies définies
        assertEquals(2, values.length, "L'énumération doit contenir exactement 2 stratégies");

        // 2. Vérifie la présence et l'ordre (utile si vous bouclez dessus ailleurs dans le code)
        assertEquals(Labelling.STANDARD, values[0]);
        assertEquals(Labelling.MRV, values[1]);
    }
}
