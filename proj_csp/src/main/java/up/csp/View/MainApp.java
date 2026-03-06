package up.csp.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Chargement de la structure définie dans les ressources [cite: 71, 72]
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/up/csp/view/main-view.fxml"));// Vérification de sécurité avant le load
        if (loader.getLocation() == null) {
            throw new RuntimeException("Fichier FXML introuvable dans resources/up/csp/View/");
        }
        Parent root = loader.load();

        // Configuration de la fenêtre principale [cite: 15, 17]
        primaryStage.setTitle("Solveur CSP Java - up.csp");

        // Application du style CSS pour le design [cite: 13, 55]
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/up/csp/View/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Lancement de l'application JavaFX
        launch(args);
    }
}