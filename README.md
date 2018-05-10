Pour l'ouverture de projet, executer la classe main qui se situe dans Identification.java

HOW TO IMPORT A GITHUB PROJECT INTO ECLIPSE :
https://github.com/collab-uniba/socialcde4eclipse/wiki/How-to-import-a-GitHub-project-into-Eclipse

PROJET QUI UTILISE ORACLE DATABASE 11g Release 2


Pour faire fonctionner le projet :
<li> 1/Créer la BDD avec Create_BDD_Gestionnaire.sql et Create_Identifiant.sql</li>
<li> 2/Créer la auto incrémentation des ID avec Create_AutoIncrementation_ID.sql ( On éxécute chaque bloc de création séparément)</li>
<li> 3/ Exécuter la commande SQL en commentaire dans Create_Identifiant.sql</li>
<li> 4/importer le projet dans l'IDE</li>
<li> 5/Lier la base de données dans l'IDE</li>
<li> 6/ Ajouter à la structure du projet les dépendences correspondant au driver JDBC (http://www.oracle.com/technetwork/apps-tech/jdbc-112010-090769.html) </li>
<li> 7/Executer le main dans src/Main.java</li>

Pour se connecter à l'application :

username : admin
password : esigelec123

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

<li>Ajout de la lecture de personnes dans la base de données ( ATTENTION : La lecture des dates n'est pas fonctionnelle pour le moment) </li>
<li>Ajout de recherche de personne</li>
<li>Ajout de la mise à jour du formulaire de recherche lors de la selection d'une personne avec la recherche</li>

0.0.11:
<li>Fix de la récupération de la date dans la base de données</li>
<li>Ajout d'une fonction de mise à jour de personne dans la base de données</li>
<li>Ajout de la fonction Modifier une personne</li>

0.0.12:
<li>Ajout des comentaires nécéssaires pour la javadoc</li>
<li>Ajout d'un message lors de la modification de personnes</li>

0.0.13:
<li>Ajout d'une identification via base de données</li>
<li>Ajout hashage mot de passe avec SHA512 avec sel</li>
<li>Ajout de la fonction création de personnes</li>
<li>Ajout de changement de titre de la fenetre lors de la navigation dans les différents menus du programme</li>

0.0.14:
<li>Modification du script (table personne et carteLeo)</li>
<li>Ajout de l'interraction avec la table carteLeo ( recherche de la la possession d'une carte par une personne et ajout d'une carte)</li>
<li>Ajout d'une permière version de "Ajout carte Leo</li>
<li>Ajout de la fonction supprimer Personne</li>
<li>Correction d'une erreur qui pouvait se produire lors de la connection</li>
<li>ConnectionDAO est maintenant un Singleton afin d'optimiser le code</li>
<li>Suppression de la fermeture systématique de la connection à la base de données à la fin de chaque action dans la BDD</li>

0.0.15:
<li>Modification du constructeur de la recherche de personne</li>
<li>Ajout de commentaires pour la JAVADOC</li>
<li>Chnagement du paramètre que réenvoie certaines variables ( int -> Boolean )</li>

0.0.16:
<li>Ajout de la suppression dans la fonction Carte léo DAO</li>
<li>Ajout de la fonction supprimer carte Léo</li>
<li>Amélioration de la recherche de personnes</li>

0.0.17:
<li>Ajout d'objet pour représenter un horaire et un lieu</li>
<li>Ajout d'un formulaire adapté à un lieu</li>
<li>Ajout de la fonction d'ajout d'un lieu dans la base de données</li>
<li>Ajout d'une première version d'ajouter un lieu ( ne prends pas encore la possibilité de pouvoir ajouter un type d'accès aux lieux )</li>

0.0.18:
<li>Ajout de la connection avec la BDD d'accès</li>
<li>Ajout d'une fenetre pour lier lieu et type d'acès (accès)</li>
<li> Implémentation JDatePickerImpl ( OPEN SOURCE )</li>

0.1:
<li>Ajout du menu permetant de modifier un lieu</li>
<li>Refonte gestion des interfaces</li>
<li>Optimisation du code</li>

0.2:
<li> Ajout de la gestion totale des accès ( lors de l'ajout ou la modification de lieux )</li>
<li> Correction de problèmes qui pouvaient se poser lors de la modification de lieu</li>

0.3:
<li>Changement de l'interface pour la recherche de personnes</li>
<li>Optimisation du code</li>
<li>Ajout de la JAVADOC</li>

1.0:
<li>Ajout du menu pour le recherche des accès</li>
<li>Correction de fautes d'orthographes dans le code</li>