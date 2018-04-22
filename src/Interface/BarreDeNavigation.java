package Interface;

import Interface.Menu.CreationCarteLeo;
import Interface.Menu.CreationPersonne;
import Interface.Menu.ModifierPersonne;
import Interface.Menu.SupprimerPersonne;

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

    private JMenuItem ajoutPersonne;
    private JMenuItem modifierPersonne;
    private JMenuItem supprimerPersonne;

    private JMenuItem ajoutCarteLeo;
    private JMenuItem supprimerCarteLeo;

    private JMenuItem creationLieu;
    private JMenuItem modificationLieu;

    private JMenuItem listeAcces;
    private JMenuItem rechercheAcces;

    private Fenetre fenetre;



    /**
     * Constructeur d'une barre de navigation
     * @param fenetre (Fenetre)
     */
    public BarreDeNavigation(Fenetre fenetre){

        this.fenetre=fenetre;
        creationDesMenus();
        ecouteDesChoix();
    }


    /**
     * Création des menus de la JMenuBar
     */
    private void creationDesMenus(){

        JMenu gestionPersonne = new JMenu("Gérer les personnes...");
        JMenu gestionCarteLeo = new JMenu("Gérer les cartes Léo");
        JMenu gestionLieux = new JMenu("Gérer les lieux");
        JMenu gestionAcces = new JMenu("Recherche accés");

        ajoutPersonne = new JMenuItem("Ajouter une personne");
        modifierPersonne = new JMenuItem("Modifier une personne");
        supprimerPersonne = new JMenuItem("Supprimer une personne");

        ajoutCarteLeo = new JMenuItem("Ajouter une carte Léo");
        supprimerCarteLeo = new JMenuItem("Supprimer une carte Léo");

        creationLieu = new JMenuItem("Créer un lieu");
        modificationLieu = new JMenuItem("Modifier un lieu");

        listeAcces = new JMenuItem("Lister");
        rechercheAcces = new JMenuItem("Rechercher");

        gestionPersonne.add(ajoutPersonne);
        gestionPersonne.add(modifierPersonne);
        gestionPersonne.add(supprimerPersonne);

        gestionCarteLeo.add(ajoutCarteLeo);
        gestionCarteLeo.add(supprimerCarteLeo);

        gestionLieux.add(creationLieu);
        gestionLieux.add(modificationLieu);

        gestionAcces.add(listeAcces);
        gestionAcces.add(rechercheAcces);

        add(gestionPersonne);
        add(gestionCarteLeo);
        add(gestionLieux);
        add(gestionAcces);
    }


    /**
     * Associations des fonctions d'écoute avec les élements graphiques
     */
    private void ecouteDesChoix(){
        ajoutPersonne.addActionListener(new boutonAjoutPersonne());
        modifierPersonne.addActionListener(new boutonModifierPersonne());
        supprimerPersonne.addActionListener(new boutonSupprimerPersonne());

        ajoutCarteLeo.addActionListener(new boutonsAjoutCarteLeo());
        supprimerCarteLeo.addActionListener(new boutonSupprimerCarteLeo());

        creationLieu.addActionListener(new boutonCreationLieu());
        modificationLieu.addActionListener(new boutonModificationLieu());

        listeAcces.addActionListener(new boutonListeAcces());
        rechercheAcces.addActionListener(new boutonRechercheAcces());

    }

    /**
     *  Ecoute du bouton Ajout Personne : si il y a appuie, affiche l'écran menu Ajout personne dans la fenetre
     */
    class boutonAjoutPersonne implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            System.out.println("Affichage écran ajout personne");

            new CreationPersonne(fenetre);
        }
    }


    /**
     *  Ecoute du bouton Modifier Personne : si il y a appuie, affiche l'écran menu Modifier personne dans la fenetre
     */
    class boutonModifierPersonne implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {

            System.out.println("Modifier personne");
            new ModifierPersonne(fenetre);
        }



    }


    /**
     *  Ecoute du bouton Supprimer Personne : si il y a appuie, affiche l'écran menu Supprimer personne dans la fenetre
     */
    class boutonSupprimerPersonne implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {
            System.out.println("Supprimer personne");
            new SupprimerPersonne(fenetre);
        }


    }





    /**
     *  Ecoute du bouton Ajout carteLéo : si il y a appuie, affiche l'écran menu Ajout carteLéo dans la fenetre
     */
    class boutonsAjoutCarteLeo implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {
            System.out.println("Ajout carte Léo");
            new CreationCarteLeo( fenetre );
        }

    }


    /**
     *  Ecoute du bouton Supprimer carteLéo : si il y a appuie, affiche l'écran menu Supprimer carteLéo dans la fenetre
     */
    class boutonSupprimerCarteLeo implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {
            System.out.println("Supprimer carte Léo");
        }

    }





    /**
     *  Ecoute du bouton Creation Lieu : si il y a appuie, affiche l'écran menu Creation Lieu dans la fenetre
     */
    class boutonCreationLieu implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {
            System.out.println("Création Lieu");
        }

    }


    /**
     *  Ecoute du bouton Modification Lieu : si il y a appuie, affiche l'écran menu Modification Lieu dans la fenetre
     */
    class boutonModificationLieu implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {
            System.out.println("Modification Lieu");
        }

    }





    /**
     *  Ecoute du bouton Listage accés : si il y a appuie, affiche l'écran menu Listage accés dans la fenetre
     */
    class boutonListeAcces implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {
            System.out.println("Liste accès");
        }

    }


    /**
     *  Ecoute du bouton Recherche accés : si il y a appuie, affiche l'écran menu Recherche accés dans la fenetre
     */
    class boutonRechercheAcces implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {
            System.out.println("Recherche accès");
        }

    }


}
