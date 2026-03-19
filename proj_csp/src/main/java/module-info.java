module up.csp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires java.desktop;

    // Attention aux MAJUSCULES ici (View, Controller, apps)
    // On n'ouvre que ce qui existe vraiment sur ton image

    opens up.csp.apps to javafx.graphics, javafx.fxml;
    opens up.csp.view to javafx.graphics, javafx.fxml;
    opens up.csp.controller to javafx.fxml;

    exports up.csp.apps;
    exports up.csp.view;
    exports up.csp.model;
    exports up.csp.solver;
}