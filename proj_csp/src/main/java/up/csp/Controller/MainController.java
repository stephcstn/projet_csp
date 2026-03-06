package up.csp.Controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import up.csp.Model.CSP;
import up.csp.Model.Variable;
import up.csp.Solver.Backtracking;
import up.csp.apps.SudokuApp;
import up.csp.apps.DamesApp;
import java.util.List;

public class MainController {

    // BIEN VÉRIFIER CETTE LIGNE (4 paramètres)
    public void handleSolve(String appName, String strategyName, Label status, StackPane container) {
        container.getChildren().clear(); // Nettoie la grille précédente

        // 1. Création du problème
        CSP problem = appName.equals("Sudoku") ? SudokuApp.createSudoku() : DamesApp.createDames(8);

        // 2. Résolution
        Backtracking solver = new Backtracking();
        boolean success = solver.solve(problem);

        if (success) {
            status.setText("Solution trouvée !");
            drawGrid(appName, problem.getVariables(), container);
        } else {
            status.setText("Aucune solution possible.");
        }
    }

    // Méthode pour le Sudoku (Affiche le chiffre)
    private void renderCell(GridPane grid, int row, int col, Variable var, boolean isDark) {
        StackPane cell = new StackPane();
        Rectangle box = new Rectangle(45, 45);
        box.setFill(Color.WHITE);
        box.setStroke(Color.GRAY);

        // Récupération de la valeur (1-9)
        String val = (var.getAssignedValue() != null) ? var.getAssignedValue().toString() : "";
        Text text = new Text(val);
        text.setStyle("-fx-font-size: 18px;");

        cell.getChildren().addAll(box, text);
        grid.add(cell, col, row);
    }

    // Méthode pour les Dames (Affiche la couronne)
    private void renderCellForDames(GridPane grid, int row, int col, boolean hasQueen, boolean isDark) {
        StackPane cell = new StackPane();
        Rectangle box = new Rectangle(50, 50);

        // Couleurs de l'échiquier
        box.setFill(isDark ? Color.web("#b58863") : Color.web("#f0d9b5"));
        box.setStroke(Color.BLACK);

        Text text = new Text(hasQueen ? "👑" : "");
        text.setStyle("-fx-font-size: 24px;");

        cell.getChildren().addAll(box, text);
        grid.add(cell, col, row);
    }


    private void drawGrid(String type, List<Variable> variables, StackPane container) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));

        if (type.equals("Sudoku")) {
            // Sudoku : 81 variables = 81 cases
            for (int i = 0; i < variables.size(); i++) {
                renderCell(grid, i / 9, i % 9, variables.get(i), false);
            }
        } else {
            // Dames : 8 variables = plateau 8x8 (64 cases)
            for (int row = 0; row < 8; row++) {
                Variable rowVar = variables.get(row);
                Integer queenCol = rowVar.getAssignedValue(); // La valeur (1-8) est la colonne

                for (int col = 0; col < 8; col++) {
                    // On place la couronne seulement si la valeur de la variable correspond à cette colonne
                    // Note : On fait col + 1 car ton Domain commence à 1
                    boolean hasQueen = (queenCol != null && queenCol == (col + 1));
                    boolean isDark = (row + col) % 2 == 1; // Couleur alternée pour l'échiquier

                    renderCellForDames(grid, row, col, hasQueen, isDark);
                }
            }
        }
        container.getChildren().add(grid);
    }

}