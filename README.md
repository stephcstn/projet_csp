# Projet CSP (Sudoku et N-Dames)

Ce projet implemente un solveur de problemes de satisfaction de contraintes (CSP) avec interface JavaFX.

Il propose:

- Sudoku (grille 9x9)
- N-Dames (plateau 8x8 dans l'interface)
- Deux strategies de selection de variable: Standard et MRV
- Deux modes d'exploration: Deterministe et Aleatoire

## Technologies

- Java 17
- Maven Wrapper
- JavaFX 17
- JUnit 5 (tests)

## Structure

- projet racine: `projet_csp/`
- module Maven: `projet_csp/proj_csp/`
- point d'entree Launcher: `projet_csp/proj_csp/src/main/java/up/Launcher.java`
- application JavaFX: `projet_csp/proj_csp/src/main/java/up/csp/View/MainApp.java`

## Prerequis

- JDK 17 installe
- Acces reseau au premier lancement (telechargement des dependances Maven)

Verifier Java:

```bash
java -version
```

## Installation

Depuis la racine du repository:

```bash
cd proj_csp
```

## Compilation

```bash
./mvnw compile
```

## Lancer l'application (via Launcher)

Le `pom.xml` est configure pour lancer `up.csp.Launcher` avec JavaFX.

```bash
./mvnw javafx:run
```

## Exécuter les tests

```bash
./mvnw test
```

## Documentation (Javadoc)

Generer la documentation:

```bash
./mvnw javadoc:javadoc
```

Les fichiers generes sont places ici:

- `target/reports/apidocs/`

Ouvrir la page d'accueil de la doc (macOS):

```bash
open target/reports/apidocs/index.html
```

## Utilisation dans l'interface

1. Choisir le jeu: Sudoku ou Jeu de Dames.
2. Choisir l'heuristique: Standard ou MRV.
3. Choisir le mode: Deterministe ou Aleatoire.
4. Cliquer sur "Lancer la resolution".

## Depannage rapide

- Erreur "no such file or directory: ./mvnw":
  verifier que vous etes dans `projet_csp/proj_csp`.
- Echec au lancement JavaFX avec une mauvaise classe principale:
  verifier la configuration `mainClass` dans `proj_csp/pom.xml`.

Valeur attendue:

```xml
<mainClass>up.csp/up.csp.Launcher</mainClass>
```
