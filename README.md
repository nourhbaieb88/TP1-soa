# TP1 – Manipulation XML, XSD et XPath avec Java

## 1. Présentation du projet

Ce projet a pour objectif de manipuler des documents XML à l'aide de Java. Il permet de valider un fichier XML en utilisant un schéma XSD et d'interroger les données du document XML grâce au langage XPath.

Le projet utilise un exemple de gestion de films dans un cinéma.

## 2. Technologies utilisées

- **Java :** langage de programmation utilisé pour développer l'application.
- **XML (Extensible Markup Language) :** format utilisé pour représenter et stocker les informations des films.
- **XSD (XML Schema Definition) :** permet de définir la structure et les règles de validation du document XML.
- **XPath :** langage permettant de rechercher et de sélectionner des éléments dans un document XML.
- **Maven :** outil de gestion et de configuration du projet.
- **IntelliJ IDEA :** environnement de développement utilisé pour réaliser le TP.

## 3. Structure du projet

```text
TP1-XML-XPath/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── org/
│       │       └── example/
│       │           └── Main.java
│       │
│       └── resources/
│           ├── cinema.xml
│           └── cinema.xsd
│
├── pom.xml
└── README.md
```

## 4. Fonctionnalités réalisées

### 4.1. Validation XML / XSD

Cette fonctionnalité permet de vérifier si le document `cinema.xml` respecte les règles définies dans le fichier `cinema.xsd`.

Le schéma XSD définit notamment :
- La structure du document XML.
- Les éléments obligatoires.
- Les types de données (chaîne de caractères, entier, décimal et booléen).
- Les attributs obligatoires `id` et `genre`.
- La durée strictement positive.
- Une note comprise entre 0 et 10.
- L'unicité des identifiants des films.

Si le document respecte toutes les règles, un message de validation est affiché. Sinon, un message d'erreur est affiché.

### 4.2. Interrogation XPath

Cette fonctionnalité permet d'extraire des informations à partir du document XML.

Deux requêtes XPath sont utilisées :

**Requête 1 : récupérer tous les titres des films**

```xpath
//film/titre
```

**Requête 2 : récupérer les titres des films actuellement en salle**

```xpath
//film[enSalle='true']/titre
```

Les résultats sont affichés dans la console.

## 5. Exécution du projet

Pour exécuter le projet :

1. Ouvrir le projet dans IntelliJ IDEA.
2. Vérifier que les fichiers `cinema.xml` et `cinema.xsd` sont placés dans `src/main/resources`.
3. Ouvrir le fichier `Main.java`.
4. Exécuter la méthode `main()`.

## 6. Résultat attendu

```text
===== 1. VALIDATION XML / XSD =====
Le document XML est VALIDE.

===== 2. INTERROGATION XPATH =====

Tous les titres de films : //film/titre
  - Inception
  - The Pianist
  - Dune: Part Two
  - Intouchables

Films en salle : //film[enSalle='true']/titre
  - Dune: Part Two
  - Intouchables
```

## 7. Conclusion

Ce TP permet de comprendre comment utiliser Java pour manipuler des documents XML, vérifier leur conformité à un schéma XSD et extraire des informations à l'aide de requêtes XPath.