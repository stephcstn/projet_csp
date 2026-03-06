package up.csp.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import up.csp.Model.CSP;
import up.csp.Solver.Backtracking;
import up.csp.Solver.Labelling;

public class MainController {

    @FXML
    private ComboBox<String> appSelector; // Sudoku, Dames [cite: 23]

    @FXML
    private ComboBox<String> strategySelector; // ex: MRV [cite: 33]

    @FXML
    private Button solveButton;

    @FXML
    private Label statusLabel;

    /**
     * Initialise les options de l'interface au démarrage.
     */
    @FXML
    public void initialize() {
        appSelector.getItems().addAll("Sudoku", "Jeu de Dames");
        strategySelector.getItems().addAll("Standard", "MRV (Minimum Remaining Values)");

        statusLabel.setText("Prêt à résoudre.");
    }

    /**
     * Gère l'action du bouton de résolution selon le scénario principal 1. [cite: 12, 19]
     */
    @FXML
    private void handleSolve() {
        String selectedApp = appSelector.getValue();
        String selectedStrategy = strategySelector.getValue();

        if (selectedApp == null) {
            statusLabel.setText("Veuillez choisir une application.");
            return;
        }

        statusLabel.setText("Résolution en cours...");

        // 1. Initialisation du CSP selon l'application choisie [cite: 12, 42]
        CSP problem = setupProblem(selectedApp);

        // 2. Configuration technique par le développeur/utilisateur [cite: 13, 20]
        Backtracking solver = new Backtracking();
        if ("MRV (Minimum Remaining Values)".equals(selectedStrategy)) {
            solver.setLabellingStrategy(Labelling.MRV);
        }

        // 3. Lancement de la résolution avec Backtracking + FC [cite: 5, 29]
        boolean success = solver.solve(problem);

        // 4. Affichage du résultat ou signalement de l'échec [cite: 31, 35]
        if (success) {
            statusLabel.setText("Succès : Solution trouvée !");
            updateViewWithSolution(problem);
        } else {
            statusLabel.setText("Échec : Aucune solution possible.");
        }
    }

    private CSP setupProblem(String appName) {
        // Logique pour générer les variables et contraintes spécifiques [cite: 42, 48, 53]
        // Exemple : if (appName.equals("Sudoku")) return new SudokuCSP();
        return new CSP();
    }

    private void updateViewWithSolution(CSP solvedCsp) {
        // Met à jour la grille ou le plateau dans la Vue [cite: 31, 55]
    }
}