module up.csp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires java.desktop;

    // On ouvre les packages pour JavaFX (réflexion)
    opens up.csp.apps to javafx.graphics, javafx.fxml;
    opens up.csp.view to javafx.graphics, javafx.fxml;
    opens up.csp.controller to javafx.fxml;

    // On exporte pour que le Launcher et les tests puissent voir le code
    exports up.csp;
    exports up.csp.apps;
    exports up.csp.view;
    exports up.csp.model;
    exports up.csp.solver;
}