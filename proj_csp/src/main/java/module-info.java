module up.csp {
    // Nécessite les modules JavaFX pour l'interface graphique
    requires javafx.controls;
    requires javafx.fxml;

    // Autorise JavaFX à accéder aux contrôleurs via la réflexion (FXML)
    opens up.csp.Controller to javafx.fxml;
    opens up.csp.View to javafx.fxml;

    // Expose les packages pour qu'ils soient utilisables
    exports up.csp.Model;
    exports up.csp.View;
    exports up.csp.Controller;
    exports up.csp.Solver;
}