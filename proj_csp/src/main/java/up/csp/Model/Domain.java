package up.csp.Model;

/**
 * Domaine de valeurs possibles pour une variable.
 *
 * @author DUFRENE BASILE
 */
public class Domain {
    private boolean[] presentValues;

    /**
     * Construit un domaine initial contenant toutes les valeurs de 1 a max.
     *
     * @param max valeur maximale du domaine
     */
    public Domain(int max) {
        // On crée un tableau de taille max + 1 pour utiliser les index de 1 à max
        this.presentValues = new boolean[max + 1];
        for (int i = 1; i <= max; i++) {
            this.presentValues[i] = true;
        }
    }

    /**
     * Retourne la valeur maximale possible (ex: 9 pour Sudoku).
     * Utilisé pour la boucle de récupération des valeurs dans Backtracking.
     *
     * @return valeur maximale du domaine
     */
    public int getMaxValue() {
        return presentValues.length - 1;
    }

    /**
     * Retourne le nombre de valeurs encore possibles (true).
     * Crucial pour l'heuristique MRV (Minimum Remaining Values).
     *
     * @return nombre de valeurs encore presentes dans le domaine
     */
    public int getSize() {
        int count = 0;
        for (int i = 1; i < presentValues.length; i++) {
            if (presentValues[i]) count++;
        }
        return count;
    }

    /**
     * Indique si une valeur est encore presente dans le domaine.
     *
     * @param value valeur a verifier
     * @return true si la valeur est disponible, false sinon
     */
    public boolean isPresent(int value) {
        if (value >= 1 && value < presentValues.length) {
            return presentValues[value];
        }
        return false;
    }

    /**
     * Retire une valeur du domaine.
     *
     * @param value valeur a retirer
     */
    public void remove(int value) {
        if (value >= 1 && value < presentValues.length) {
            presentValues[value] = false;
        }
    }

    /**
     * Indique si le domaine ne contient plus aucune valeur possible.
     *
     * @return true si le domaine est vide, false sinon
     */
    public boolean isEmpty() {
        return getSize() == 0;
    }

    // --- Méthodes pour la sauvegarde/restauration du Backtracking ---

    /**
     * Retourne une copie de l'etat interne du domaine.
     *
     * @return copie du tableau des valeurs presentes
     */
    public boolean[] getValuesCopy() {
        return presentValues.clone();
    }

    /**
     * Remplace l'etat interne du domaine par un etat sauvegarde.
     *
     * @param values tableau de presence des valeurs
     */
    public void setValues(boolean[] values) {
        this.presentValues = values;
    }
}