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

        // 1. Les contrôles
        ComboBox<String> appSelector = new ComboBox<>();
        appSelector.getItems().addAll("Sudoku", "Jeu de Dames");
        appSelector.setValue("Sudoku");

        ComboBox<String> strategySelector = new ComboBox<>();
        strategySelector.getItems().addAll("Standard", "MRV");
        strategySelector.setValue("Standard");

        Button solveButton = new Button("Lancer la résolution");
        Label statusLabel = new Label("Prêt");

        // 2. Le conteneur pour la grille (C'est lui l'argument "resultContainer")
        StackPane resultContainer = new StackPane();
        resultContainer.setPadding(new Insets(20));

        // 3. L'action du bouton (Appel avec 4 arguments)
        solveButton.setOnAction(e -> controller.handleSolve(
                appSelector.getValue(),
                strategySelector.getValue(),
                statusLabel,
                resultContainer
        ));

        VBox layout = new VBox(15, new Label("CONFIGURATEUR CSP"), appSelector, strategySelector, solveButton, statusLabel, resultContainer);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(20));

        primaryStage.setScene(new Scene(layout, 600, 800));
        primaryStage.setTitle("up.csp - Solveur Graphique");
        primaryStage.show();
    }
}