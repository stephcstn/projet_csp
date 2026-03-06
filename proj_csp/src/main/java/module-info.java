module up.csp {
    requires javafx.controls;
    requires javafx.graphics;

    // On exporte tout pour que JavaFX puisse lancer l'app
    exports up.csp.View;
    exports up.csp.Controller;
    exports up.csp.Model;
    exports up.csp.Solver;
}