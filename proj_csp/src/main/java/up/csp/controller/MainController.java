package up.csp.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import up.csp.model.CSP;
import up.csp.model.Variable;
import up.csp.solver.Backtracking;
import up.csp.solver.Labelling;
import up.csp.apps.SudokuApp;
import up.csp.apps.DamesApp;
import java.util.List;

/**
 * Controleur principal de l'interface JavaFX.
 *
 * @author DUFRENE BASILE
 */
public class MainController {

    /**
     * Lance la resolution selon les options choisies dans l'interface.
     *
     * @param app type de probleme selectionne
     * @param strat strategie de labelling selectionnee
     * @param mode mode de recherche (deterministe ou aleatoire)
     * @param status label d'etat a mettre a jour
     * @param container conteneur graphique du resultat
     */
    public void handleSolve(String app, String strat, String mode, Label status, StackPane container) {
        container.getChildren().clear();
        CSP problem = switch (app) {
            case "Sudoku" -> SudokuApp.createSudoku();
            case "Jeu de Dames"  -> DamesApp.createDames(8);
            default      -> throw new IllegalArgumentException("Application inconnue : " + app);
        };

        Backtracking solver = new Backtracking();

        // Configuration dynamique
        if (strat.equals("MRV")) {
            solver.setLabellingStrategy(Labelling.MRV);
        } else {
            // Par défaut, on utilise la stratégie STANDARD
            solver.setLabellingStrategy(Labelling.STANDARD);
        }

        solver.setRandomized(mode.equals("Aléatoire"));

        if (solver.solve(problem)) {
            status.setText("Solution trouvée (" + mode + ") !");
            drawGrid(app, problem.getVariables(), container);
        } else {
            status.setText("Échec de la résolution.");
        }
    }

    // DÉFINITION : 4 ARGUMENTS Sudoku
    private void renderCell(GridPane grid, int row, int col, Variable var) {
        StackPane cell = new StackPane();
        Rectangle box = new Rectangle(45, 45);
        box.setFill(Color.WHITE);
        box.setStroke(Color.GRAY);

        String val;
        if (var.getAssignedValue() != null) {
            // Si la case a un chiffre, on le transforme en texte pour l'affichage
            val = var.getAssignedValue().toString();
        } else {
            // Si la case est vide (null), on affiche une chaîne vide ""
            val = "";
        }

        Text text = new Text(val);
        text.setStyle("-fx-font-size: 18px;");

        cell.getChildren().addAll(box, text);
        grid.add(cell, col, row);
    }

    // DÉFINITION : 5 ARGUMENTS Dames
    private void renderCellForDames(GridPane grid, int row, int col, boolean hasQueen, boolean isDark) {
        StackPane cell = new StackPane();
        Rectangle box = new Rectangle(50, 50);
        box.setFill(isDark ? Color.web("#b58863") : Color.web("#f0d9b5"));
        box.setStroke(Color.BLACK);

        Text text = new Text();
        if (hasQueen) {
            text.setText("Q");
            text.setFill(Color.DARKRED);
            text.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
        }

        cell.getChildren().addAll(box, text);
        grid.add(cell, col, row);
    }

    private void drawGrid(String type, List<Variable> variables, StackPane container) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));

        if (type.equals("Sudoku")) {
            for (int i = 0; i < variables.size(); i++) {
                int row = i / 9;
                int col = i % 9;
                renderCell(grid, row, col, variables.get(i));
            }
        } else {
            for (int row = 0; row < 8; row++) {
                Variable rowVar = variables.get(row);
                Integer queenCol = rowVar.getAssignedValue();

                for (int col = 0; col < 8; col++) {
                    boolean hasQueen = (queenCol != null && queenCol == (col + 1));
                    boolean isDark = (row + col) % 2 == 1;
                    renderCellForDames(grid, row, col, hasQueen, isDark);
                }
            }
        }
        container.getChildren().add(grid);
    }

}