package up.csp;

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
    public static void main(String[] args) {
            // On ne fait PAS Application.launch ici.
            // On appelle une méthode dans MainApp qui, elle, s'occupera de JavaFX.
            MainApp.main(args);
    }
}
