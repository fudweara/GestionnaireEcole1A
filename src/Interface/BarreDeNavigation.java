package Interface;

import Interface.Menu.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Barre de navigation avec menus :
 *  - Gérer les personnes : Ajouter, Supprimer, Modifier
 *  - Gérer les cartes Léo : Ajouter, Supprimer
 *  - Gérer les lieux : Créer, Modifier
 *  - Recherche Lieu : Listage, Recherche
 */
class BarreDeNavigation extends JMenuBar{

    private final Fenetre fenetre;

    /**
     * Constructeur d'une barre de navigation
     * @param fenetre (Fenetre)
     */
    BarreDeNavigation(Fenetre fenetre){

        this.fenetre=fenetre;

        JMenu gestionPersonne = new JMenu("Gérer les personnes...");
        JMenu gestionCarteLeo = new JMenu("Gérer les cartes Léo");
        JMenu gestionLieux = new JMenu("Gérer les lieux");
        JMenu rechercheAccesMenu = new JMenu("Recherche accés");

        JMenuItem ajoutPersonne = new JMenuItem("Ajouter une personne");
        JMenuItem modifierPersonne = new JMenuItem("Modifier une personne");
        JMenuItem supprimerPersonne = new JMenuItem("Supprimer une personne");

        JMenuItem ajoutCarteLeo = new JMenuItem("Ajouter une carte Léo");
        JMenuItem supprimerCarteLeo = new JMenuItem("Supprimer une carte Léo");

        JMenuItem creationLieu = new JMenuItem("Créer un lieu");
        JMenuItem modificationLieu = new JMenuItem("Modifier un lieu");
        JMenuItem creationTypeAcces = new JMenuItem("Création type accès");

        JMenuItem rechercheAcces = new JMenuItem("Rechercher un accès");


        gestionPersonne.add(ajoutPersonne);
        gestionPersonne.add(modifierPersonne);
        gestionPersonne.add(supprimerPersonne);

        gestionCarteLeo.add(ajoutCarteLeo);
        gestionCarteLeo.add(supprimerCarteLeo);

        gestionLieux.add(creationLieu);
        gestionLieux.add(modificationLieu);
        gestionLieux.add(creationTypeAcces);

        rechercheAccesMenu.add(rechercheAcces);

        add(gestionPersonne);
        add(gestionCarteLeo);
        add(gestionLieux);
        add(rechercheAccesMenu);

        ajoutPersonne.addActionListener(new boutonAjoutPersonne());
        modifierPersonne.addActionListener(new boutonModifierPersonne());
        supprimerPersonne.addActionListener(new boutonSupprimerPersonne());

        ajoutCarteLeo.addActionListener(new boutonsAjoutCarteLeo());
        supprimerCarteLeo.addActionListener(new boutonSupprimerCarteLeo());

        creationLieu.addActionListener(new boutonCreationLieu());
        modificationLieu.addActionListener(new boutonModificationLieu());
        creationTypeAcces.addActionListener(new boutonCreationTypeAcces());

        rechercheAcces.addActionListener(new boutonRechercheAcces());

    }


    /**
     *  Ecoute du bouton Ajout Personne : si il y a appuie, affiche l'écran menu Ajout personne dans la fenetre
     */
    class boutonAjoutPersonne implements ActionListener {

        /**
         * Affiche le menu Créer une  personne
         * @param arg0 Evenement à faire
         */
        public void actionPerformed(ActionEvent arg0) {
            new CreationPersonne(fenetre);
        }
    }


    /**
     *  Ecoute du bouton Modifier Personne : si il y a appuie, affiche l'écran menu Modifier personne dans la fenetre
     */
    class boutonModifierPersonne implements ActionListener{

        /**
         * Affiche le menu Modifier une personne
         * @param arg0 Evenement à faire
         */
        public void actionPerformed(ActionEvent arg0) {
            new ModifierPersonne(fenetre);
        }



    }


    /**
     *  Ecoute du bouton Supprimer Personne : si il y a appuie, affiche l'écran menu Supprimer personne dans la fenetre
     */
    class boutonSupprimerPersonne implements ActionListener{

        /**
         * Affiche le menu Supprimer une personne
         * @param arg0 Evenement à faire
         */
        public void actionPerformed(ActionEvent arg0) {
            new SupprimerPersonne(fenetre);
        }


    }


    /**
     *  Ecoute du bouton Ajout carteLéo : si il y a appuie, affiche l'écran menu Ajout carteLéo dans la fenetre
     */
    class boutonsAjoutCarteLeo implements ActionListener{

        /**
         * Affiche le menu Ajouter une carte Léo
         * @param arg0 Evenement à faire
         */
        public void actionPerformed(ActionEvent arg0) {
            new CreationCarteLeo( fenetre );
        }

    }


    /**
     *  Ecoute du bouton Supprimer carteLéo : si il y a appuie, affiche l'écran menu Supprimer carteLéo dans la fenetre
     */
    class boutonSupprimerCarteLeo implements ActionListener{

        /**
         * Affiche le menu Supprimer une carte Léo
         * @param arg0 Evenement à faire
         */
        public void actionPerformed(ActionEvent arg0) {
            new SupprimerCarteLeo( fenetre );
        }

    }


    /**
     *  Ecoute du bouton Creation Lieu : si il y a appuie, affiche l'écran menu Creation Lieu dans la fenetre
     */
    class boutonCreationLieu implements ActionListener{

        /**
         * Affiche le menu Créer un lieu
         * @param arg0 Evenement à faire
         */
        public void actionPerformed(ActionEvent arg0) {
            new CreationLieu( fenetre);
        }
    }


    /**
     *  Ecoute du bouton Modification Lieu : si il y a appuie, affiche l'écran menu Modification Lieu dans la fenetre
     */
    class boutonModificationLieu implements ActionListener{

        /**
         * Affiche le menu Modifier un lieu
         * @param arg0 Evenement à faire
         */
        public void actionPerformed(ActionEvent arg0) {
            new ModifierLieu( fenetre );
        }

    }


    /**
     * Ecoute du bouton Creation Type Acces : il y a appuie, affiche l'écran menu Creation Type Acces dans la fenetre
     */
    class boutonCreationTypeAcces implements ActionListener{


        /**
         * Affiche le menu Créer un type d'accès
         * @param arg0 Evenement à faire
         */
        public void actionPerformed(ActionEvent arg0) {
            new CreationTypeAcces( fenetre );
        }
    }


    /**
     *  Ecoute du bouton Listage accés : si il y a appuie, affiche l'écran menu Listage accés dans la fenetre
     */
    class boutonRechercheAcces implements ActionListener{

        /**
         * Affiche le menu Recherche accès
         * @param arg0 Evenement à faire
         */
        public void actionPerformed(ActionEvent arg0) {
            new RechercheAcces( fenetre );
        }

    }


}