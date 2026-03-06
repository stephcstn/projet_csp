package up.csp.Model;

public class Domain {
    private boolean[] presentValues;

    public Domain(int max) {
        this.presentValues = new boolean[max + 1];
        for (int i = 1; i <= max; i++) {
            this.presentValues[i] = true;
        }
    }

    /**
     * Vérifie si le domaine est vide (aucune valeur 'true' dans le tableau).
     */
    public boolean isEmpty() {
        for (int i = 1; i < presentValues.length; i++) {
            if (presentValues[i]) {
                return false; // Il reste au moins une valeur possible
            }
        }
        return true; // Échec : plus aucune valeur possible
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
    public boolean[] getValuesCopy() {
        return presentValues.clone(); // Création d'une copie indépendante
    }
    public void setValues(boolean[] values) {
        this.presentValues = values;
    }
}