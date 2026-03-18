package up.csp.Controller;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

// Importation des assertions classiques de JUnit 5
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainControllerTest extends ApplicationTest {

    @Test
    void testHandleSolve_Sudoku_Success() {
        // 1. Préparation (Arrange)
        MainController controller = new MainController();
        Label status = new Label();
        StackPane container = new StackPane();

        // 2. Exécution (Act)
        // On simule l'appel fait par le bouton
        controller.handleSolve("Sudoku", "Standard", status, container);

        // 3. Vérification (Assert)
        // On vérifie que le solveur a bien trouvé une solution et mis à jour le texte
        assertEquals("Solution trouvée !", status.getText(), "Le statut doit indiquer un succès");
        
        // On vérifie que le conteneur n'est plus vide (la grille a été dessinée)
        assertFalse(container.getChildren().isEmpty(), "Le conteneur devrait contenir la grille de Sudoku");
    }

    @Test
    void testHandleSolve_Dames_Success() {
        // 1. Préparation (Arrange)
        MainController controller = new MainController();
        Label status = new Label("Prêt");
        StackPane container = new StackPane();

        // 2. Exécution (Act)
        controller.handleSolve("Jeu de Dames", "Standard", status, container);

        // 3. Vérification (Assert)
        assertEquals("Solution trouvée !", status.getText());
        assertFalse(container.getChildren().isEmpty(), "Le conteneur devrait contenir le plateau d'échecs");
    }
}