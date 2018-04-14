Pour l'ouverture de projet, executer la classe main qui se situe dans Identification.java

HOW TO IMPORT A GITHUB PROJECT INTO ECLIPSE :
https://github.com/collab-uniba/socialcde4eclipse/wiki/How-to-import-a-GitHub-project-into-Eclipse

PROJET QUI UTILISE ORACLE DATABASE 11g Release 2

Pour faire fonctionner le projet :
<li> 1/Créer la BDD avec Create_BDD_Gestionnaire.sql</li>
<li> 2/Créer la auto incrémentation des ID avec Create_AutoIncrementation_ID.sql ( On éxécute chaque bloc de création séparément)</li>
<li> 3/importer le projet dans l'IDE</li>
<li> 4/Lier la base de données dans l'IDE</li>
<li> 5/ Ajouter à la structure du projet les dépendences correspondant au driver JDBC (http://www.oracle.com/technetwork/apps-tech/jdbc-112010-090769.html) </li>
<li> 4/Executer le main dans src/Main.java</li>


PATCHNOTES :

0.0.6 :
<li>Ajout des balises JAVADOC</li>
<li>Création de classe DateDeNaissance</li>
<li>Test pour la récupération des données dans un objet Personne après avoir rentré dans les champs ces caractéristiques</li>
<li>Suppression des import inutiles</li>
<li>Optimisation du code</li>

0.0.7 :
<li>Changement dans la gestion pour la création des JFrame contenant les composants graphiques des menus</li>
<li>Désactivation de la possibilité de redimensionnage des fenêtre</li>

0.0.8 :
<li>Ajout d'une classe pour la recherche d'une personne avec son nom</li>
<li>Correction d'un bug d'affichage dans le menu Modifier personne</li>

0.0.9 :
<li>Réorganisation du code et suppression de nombreuses incohérences</li>

0.0.10 :

<li> Ajout de la lecture de personnes dans la base de données ( ATTENTION : La lecture des dates n'est pas fonctionnelle pour le moment) </li>
<li> Ajout de recherche de personne</li>
<li> Ajout de la mise à jour du formulaire de recherche lors de la selection d'une personne avec la recherche</li>

0.0.11:
<li> Fix de la récupération de la date dans la base de données</li>
<li> Ajout d'une fonction de mise à jour de personne dans la base de données</li>
<li> Ajout de la fonction Modifier une personne</li>
