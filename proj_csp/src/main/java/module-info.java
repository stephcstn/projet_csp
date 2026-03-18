module up.csp {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.swing;

    exports up.csp.View;
    exports up.csp.Controller;
    exports up.csp.Model;
    exports up.csp.Solver;
    exports up.csp.apps;

    opens up.csp.View to javafx.graphics;
    opens up.csp.Controller to javafx.controls;
}