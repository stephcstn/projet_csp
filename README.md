# Solveur de CSP — Sudoku & problème des N-Dames

[![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)](https://www.java.com/)
[![Tests](https://img.shields.io/badge/tests-JUnit%205-25A162?logo=junit5&logoColor=white)](#tests)

Un solveur de **problèmes de satisfaction de contraintes** (*Constraint Satisfaction Problems*, CSP) écrit en Java. Le projet formalise deux problèmes combinatoires — le **Sudoku 9×9** et le **problème des N-Dames** — puis les résout par recherche avec retour arrière, filtrage des domaines (*Forward Checking*) et choix de variable standard ou **MRV**. Une interface JavaFX permet de lancer et visualiser une résolution.

Réalisé en équipe de quatre, le projet met en pratique la modélisation déclarative, la séparation entre modèle et solveur, ainsi que les tests unitaires et d’intégration.

> **Périmètre réel :** le code propose Backtracking, Forward Checking, MRV et un ordre de valeurs déterministe ou aléatoire.

## Sommaire

- [Fonctionnalités](#fonctionnalités)
- [Algorithmes et stratégies](#algorithmes-et-stratégies)
- [Architecture et qualité du code](#architecture-et-qualité-du-code)
- [Travail collaboratif](#travail-collaboratif)
- [Installation et exécution](#installation-et-exécution)
- [Auteurs](#auteurs)

## Fonctionnalités

- **Sudoku** : génération d’un CSP de 81 cases, domaines `1..9` et contraintes de lignes, colonnes et blocs 3×3. Le générateur actuel crée une grille vide, sans indices prédéfinis.
- **N-Dames** : génération d’un problème de taille `n`, avec une reine par ligne et aucune collision en colonne ou en diagonale. L’interface lance le cas `n = 8`.
- **Solveur configurable** : ordre de variables standard ou MRV, valeurs examinées dans l’ordre du domaine ou mélangées aléatoirement.
- **Interface JavaFX** : lancement depuis l’interface et affichage d’une solution sous forme de grille.
- **Tests** : tests unitaires des composants et tests d’intégration des modèles et du solveur avec JUnit 5.

## Algorithmes et stratégies

### Recherche et filtrage

- **Backtracking** : sélectionne une variable non affectée, essaie les valeurs de son domaine, puis revient en arrière si la branche ne peut pas aboutir. Les domaines sont sauvegardés avant chaque essai et restaurés en cas d’échec.
- **Forward Checking** : après chaque affectation, demande aux contraintes concernées de retirer les valeurs incompatibles des domaines voisins. La recherche revient en arrière lorsqu’un domaine devient vide.
- **Ordre standard** : choisit la première variable non affectée dans l’ordre de création du modèle.
- **MRV (*Minimum Remaining Values*)** : choisit la variable non affectée au domaine courant le plus petit. En cas d’égalité, la première rencontrée est conservée.
- **Ordre des valeurs** : l’ordre naturel du domaine est utilisé par défaut ; le mode aléatoire mélange cet ordre avant l’exploration.

### Pourquoi ces stratégies ?

MRV privilégie la variable la plus contrainte : si elle n’a plus de valeur possible, l’échec est détecté rapidement, et si elle a peu de choix, la branchement immédiat reste limité. Forward Checking propage les conséquences locales d’une décision sans attendre que toutes les variables soient affectées. Le mode aléatoire permet d’explorer des ordres de valeurs différents, mais ne constitue pas une heuristique d’optimisation et ne garantit pas une meilleure performance.

## Architecture et qualité du code

Le dépôt sépare le module Maven (`proj_csp/`) du README et de la configuration CI à la racine.

```text
.
├── .gitlab-ci.yml
├── README.md
└── proj_csp/
    ├── pom.xml
    ├── mvnw
    ├── mvnw.cmd
    └── src/
        ├── main/java/
        │   ├── module-info.java
        │   └── up/csp/
        │       ├── Launcher.java
        │       ├── apps/          # Fabriques de modèles Sudoku et N-Dames
        │       ├── controller/    # Liaison interface / modèle / solveur
        │       ├── model/         # CSP, variables, domaines et contraintes
        │       ├── solver/        # Backtracking, Forward Checking, MRV
        │       └── view/          # Interface JavaFX
        └── test/java/up/csp/
            ├── controller/
            ├── integration/
            ├── model/
            └── solver/
```

Le modèle définit des abstractions réutilisables (`CSP`, `Variable`, `Domain`, `Constraint`) et les contraintes concrètes sont séparées des stratégies de résolution. Le projet cible **Java 17**, s’appuie sur Maven et utilise les tests JUnit 5. Les tests couvrent les modèles, les contraintes, le solveur, le contrôleur et des scénarios d’intégration. La CI GitLab définie à la racine prévoit compilation, tests et création d’un paquet.

## Travail collaboratif

Le projet a été réalisé par une équipe de quatre personnes. Le découpage fonctionnel du dépôt facilite le travail parallèle et l’intégration :

| Axe de travail | Périmètre |
|---|---|
| Modélisation | Variables, domaines, interface commune des contraintes |
| Problèmes | Encodage des règles du Sudoku et des N-Dames |
| Résolution | Recherche avec retour arrière, filtrage et sélection de variables |
| Intégration et validation | Interface JavaFX, tests, exécution et documentation |

## Installation et exécution

### Prérequis

- **JDK 17** ;
- le Maven Wrapper fourni (`mvnw` / `mvnw.cmd`) ;
- un accès réseau au premier lancement pour télécharger les dépendances Maven ;
- un environnement graphique pour ouvrir l’interface JavaFX.

Vérifier la version de Java :

```bash
java -version
```

### Compiler et lancer les tests

Depuis la racine du dépôt, entrer dans le module Maven :

```bash
cd proj_csp
```

Sous Windows PowerShell :

```powershell
.\mvnw.cmd clean compile
.\mvnw.cmd test
```

Sous macOS/Linux :

```bash
./mvnw clean compile
./mvnw test
```

### Lancer l’interface

Depuis `proj_csp/` :

```powershell
.\mvnw.cmd javafx:run
```

```bash
./mvnw javafx:run
```

Dans l’interface, choisir **Sudoku** ou **Jeu de Dames**, puis la stratégie (**Standard** ou **MRV**) et le mode de valeurs (**Déterministe** ou **Aléatoire**) avant de lancer la résolution. Le Sudoku proposé est une grille vide ; le jeu de Dames utilise un plateau 8×8.

### Générer la Javadoc

```powershell
.\mvnw.cmd javadoc:javadoc
```

La documentation générée se trouve dans `proj_csp/target/reports/apidocs/`.

## Auteurs

- **Stéphane Castan**
- **Basile Dufrene**
- **Yaniss Harchich**
- **Daniel Mbina**