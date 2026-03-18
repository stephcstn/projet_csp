package up.csp.Model;

public class Domain {
    private boolean[] presentValues;

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
     */
    public int getMaxValue() {
        return presentValues.length - 1;
    }

    /**
     * Retourne le nombre de valeurs encore possibles (true).
     * Crucial pour l'heuristique MRV (Minimum Remaining Values).
     */
    public int getSize() {
        int count = 0;
        for (int i = 1; i < presentValues.length; i++) {
            if (presentValues[i]) count++;
        }
        return count;
    }

    public boolean isPresent(int value) {
        if (value >= 1 && value < presentValues.length) {
            return presentValues[value];
        }
        return false;
    }

    public void remove(int value) {
        if (value >= 1 && value < presentValues.length) {
            presentValues[value] = false;
        }
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    // --- Méthodes pour la sauvegarde/restauration du Backtracking ---

    public boolean[] getValuesCopy() {
        return presentValues.clone();
    }

    public void setValues(boolean[] values) {
        this.presentValues = values;
    }
}