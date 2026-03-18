package up;

import javafx.application.Application;
import up.csp.View.MainApp;

public class Launcher {
    public static void main(String[] args){
        // On appelle la méthode statique launch en lui passant la classe de l'application
        Application.launch(MainApp.class, args);
    }
}
