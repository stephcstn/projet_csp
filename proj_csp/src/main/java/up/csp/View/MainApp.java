package up.csp.View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import up.csp.Controller.MainController;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainController controller = new MainController();

        // 1. Sélecteur de Jeu
        ComboBox<String> appSelector = new ComboBox<>();
        appSelector.getItems().addAll("Sudoku", "Jeu de Dames");
        appSelector.setValue("Sudoku");

        // 2. Sélecteur de Stratégie (MRV vs Standard)
        ComboBox<String> strategySelector = new ComboBox<>();
        strategySelector.getItems().addAll("Standard", "MRV");
        strategySelector.setValue("Standard");

        // 3. NOUVEAU : Sélecteur de Mode (Déterministe vs Aléatoire)
        ComboBox<String> modeSelector = new ComboBox<>();
        modeSelector.getItems().addAll("Déterministe", "Aléatoire");
        modeSelector.setValue("Déterministe");

        Button solveButton = new Button("Lancer la résolution");
        Label statusLabel = new Label("Prêt");
        StackPane resultContainer = new StackPane();

        // 4. Mise à jour de l'action du bouton (Passage de 5 arguments)
        solveButton.setOnAction(e -> controller.handleSolve(
                appSelector.getValue(),
                strategySelector.getValue(),
                modeSelector.getValue(), // On envoie le choix du mode ici
                statusLabel,
                resultContainer
        ));

        // Mise en page
        VBox controls = new VBox(10,
                new Label("Jeu :"), appSelector,
                new Label("Heuristique :"), strategySelector,
                new Label("Mode de recherche :"), modeSelector, // Ajout au layout
                solveButton, statusLabel
        );
        controls.setAlignment(Pos.CENTER);
        controls.setPadding(new Insets(15));

        BorderPane root = new BorderPane();
        root.setTop(controls);
        root.setCenter(resultContainer);

        primaryStage.setScene(new Scene(root, 600, 850));
        primaryStage.setTitle("up.csp - Solveur Graphique");
        primaryStage.show();
    }
}