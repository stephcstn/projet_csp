module fr.univ_poitiers.proj_csp {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.univ_poitiers.proj_csp to javafx.fxml;
    exports fr.univ_poitiers.proj_csp;
}