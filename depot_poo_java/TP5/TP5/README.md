# TP Othello

## Alexandre OVU

## Présentation et objectifs

Ce TP consiste à programmer en Java une version du jeu Othello pour deux joueurs. Le programme représente un plateau de 8 × 8 cases, permet de poser des pions noirs et blancs, vérifie les coups possibles, retourne les pions adverses encadrés, gère les tours et annonce le résultat de la partie.

## Organisation des fichiers

- `src/othello/` : code source du jeu.
- `test/` : tests JUnit.
- `junit-console.jar` : outil fourni pour compiler et exécuter les tests ; le placer à la racine de ce dossier de TP.

## Générer et consulter la documentation

Depuis la racine du dossier `tp5/` :

```bash
javadoc -sourcepath src -subpackages othello -d docs
```

Ouvrir ensuite `docs/index.html` dans un navigateur pour consulter la documentation.

## Compiler les sources

```bash
javac -sourcepath src src/othello/*.java -d classes
```

## Exécuter le programme sans JAR

```bash
java -classpath classes othello.Main
```

Le programme demande une ligne et une colonne, séparées par un espace. Exemple : `2 3`. Les indices vont de `0` à `7`.

## Compiler les tests

```bash
javac -sourcepath src src/othello/*.java -d classes
javac -classpath junit-console.jar:classes -d test test/PosePionsTest.java
```

## Exécuter les tests

Commande présentée dans le cours :

```bash
java -jar junit-console.jar -classpath test:classes -scan-classpath
```

Pour ne lancer que la classe `PosePionsTest` :

```bash
java -jar junit-console.jar -classpath test:classes -select-class test.PosePionsTest
```

## Créer un JAR exécutable

Après avoir compilé les sources, commande courte utilisant la classe principale :

```bash
jar cvfe othello.jar othello.Main -C classes .
```

Autre possibilité, utilisant le manifeste `manifest-tp5` fourni dans ce dossier :

```bash
jar cvfm othello.jar manifest-tp5 -C classes .
```

## Exécuter le JAR

```bash
java -jar othello.jar
```
