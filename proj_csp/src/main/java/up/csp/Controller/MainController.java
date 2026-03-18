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
import up.csp.Solver.Labelling;
import up.csp.apps.SudokuApp;
import up.csp.apps.DamesApp;
import java.util.List;

public class MainController {

    // BIEN VÉRIFIER CETTE LIGNE (4 paramètres)
    public void handleSolve(String app, String strat, String mode, Label status, StackPane container) {
        container.getChildren().clear();
        CSP problem = app.equals("Sudoku") ? SudokuApp.createSudoku() : DamesApp.createDames(8);

        Backtracking solver = new Backtracking();

        // Configuration dynamique
        solver.setLabellingStrategy(strat.equals("MRV") ? Labelling.MRV : Labelling.STANDARD);
        solver.setRandomized(mode.equals("Aléatoire")); // On active l'aléa ici

        if (solver.solve(problem)) {
            status.setText("Solution trouvée (" + mode + ") !");
            drawGrid(app, problem.getVariables(), container);
        } else {
            status.setText("Échec de la résolution.");
        }
    }

    // DÉFINITION : 4 ARGUMENTS (Correct pour Sudoku)
    private void renderCell(GridPane grid, int row, int col, Variable var) {
        StackPane cell = new StackPane();
        Rectangle box = new Rectangle(45, 45);
        box.setFill(Color.WHITE);
        box.setStroke(Color.GRAY);

        String val = (var.getAssignedValue() != null) ? var.getAssignedValue().toString() : "";
        Text text = new Text(val);
        text.setStyle("-fx-font-size: 18px;");

        cell.getChildren().addAll(box, text);
        grid.add(cell, col, row);
    }

    // DÉFINITION : 5 ARGUMENTS (Correct pour Dames)
    private void renderCellForDames(GridPane grid, int row, int col, boolean hasQueen, boolean isDark) {
        StackPane cell = new StackPane();
        Rectangle box = new Rectangle(50, 50);
        box.setFill(isDark ? Color.web("#b58863") : Color.web("#f0d9b5"));
        box.setStroke(Color.BLACK);

        Text text = new Text();
        if (hasQueen) {
            text.setText("Q"); // Utilise "Q" si l'émoji ne passe pas
            text.setFill(Color.DARKRED); // Couleur vive pour être sûr de la voir
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
            // --- LOGIQUE SUDOKU ---
            // On a 81 variables (une par case), donc on boucle sur la liste
            for (int i = 0; i < variables.size(); i++) {
                int row = i / 9;
                int col = i % 9;
                // Appel à la méthode de rendu à 4 arguments
                renderCell(grid, row, col, variables.get(i));
            }
        } else {
            // --- LOGIQUE DAMES ---
            // On a 8 variables (une par ligne), on dessine 64 cases (8x8)
            for (int row = 0; row < 8; row++) {
                Variable rowVar = variables.get(row);
                Integer queenCol = rowVar.getAssignedValue();

                for (int col = 0; col < 8; col++) {
                    boolean hasQueen = (queenCol != null && queenCol == (col + 1));
                    boolean isDark = (row + col) % 2 == 1;
                    // Appel à la méthode de rendu à 5 arguments
                    renderCellForDames(grid, row, col, hasQueen, isDark);
                }
            }
        }
        container.getChildren().add(grid);
    }

}