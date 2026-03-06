package up.csp.Model;

public class Domain {
    private boolean[] presentValues; // [cite: 75, 42]

    public Domain(int min, int max) {
        this.presentValues = new boolean[max + 1];
        for (int i = min; i <= max; i++) {
            presentValues[i] = true;
        }
    }

    /**
     * Vérifie si le domaine est vide (aucune valeur n'est plus possible).
     * Utilisé par le Forward-Checking pour déclencher un retour arrière.
     */
    public boolean isEmpty() {
        for (boolean isPresent : presentValues) {
            if (isPresent) {
                return false; // Il reste au moins une valeur possible
            }
        }
        return true; // Toutes les valeurs ont été éliminées
    }

    /**
     * Retourne le nombre de valeurs encore possibles (utile pour la stratégie MRV). [cite: 13, 33]
     */
    public int getSize() {
        int count = 0;
        for (boolean isPresent : presentValues) {
            if (isPresent) count++;
        }
        return count;
    }

    public boolean isPresent(int value) {
        return value >= 0 && value < presentValues.length && presentValues[value];
    }

    public void remove(int value) {
        if (value >= 0 && value < presentValues.length) {
            presentValues[value] = false;
        }
    }
}