package up.csp.controller;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MainControllerTest {

    // Cette astuce permet d'initialiser l'environnement JavaFX
    // sans lancer d'interface graphique, évitant ainsi les erreurs de Toolkit.
    @BeforeAll
    static void initJFX() {
        new JFXPanel();
    }

    @Test
    void testHandleSolve_Sudoku_Success() {
        // 1. Préparation
        MainController controller = new MainController();
        Label status = new Label();
        StackPane container = new StackPane();

        // 2. Exécution
        // On passe les 5 arguments désormais requis
        controller.handleSolve("Sudoku", "Standard", "Standard", status, container);

        // 3. Vérification
        assertEquals("Solution trouvée (Standard) !", status.getText());
        assertFalse(container.getChildren().isEmpty(), "La grille devrait être ajoutée au container");
    }

    @Test
    void testHandleSolve_Dames_Success() {
        // 1. Préparation
        MainController controller = new MainController();
        Label status = new Label();
        StackPane container = new StackPane();

        // 2. Exécution
        controller.handleSolve("Jeu De Dames", "Standard", "Standard", status, container);

        // 3. Vérification
        assertEquals("Solution trouvée (Standard) !", status.getText());
        assertFalse(container.getChildren().isEmpty(), "Le plateau de dames devrait être ajouté");
    }
}