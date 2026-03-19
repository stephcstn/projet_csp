package up;

import javafx.application.Application;
import up.csp.view.MainApp;

/**
 * Point d'entree de l'application JavaFX.
 *
 * @author CASTAN STEPHANE
 */
public class Launcher {
    /**
     * Lance l'interface graphique principale.
     *
     * @param args arguments de la ligne de commande
     */
    public static void main(String[] args){
        // On appelle la méthode statique launch en lui passant la classe de l'application
        Application.launch(MainApp.class, args);
    }
}
