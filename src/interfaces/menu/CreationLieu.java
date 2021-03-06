package interfaces.menu;

import dao.AccesDAO;
import dao.TypeAccesDAO;
import interfaces.FenetreGestionAcces;
import dao.LieuDAO;
import interfaces.Fenetre;
import interfaces.elementGraphique.FormulaireLieu;
import objetStockage.Lieu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Classe pour le menu Création de lieux
 */
public class CreationLieu {

    private final Fenetre fenetre;
    private final FormulaireLieu formulaireLieu;

    private final Lieu lieu;

    private final FenetreGestionAcces fenetreGestionAcces;


    /**
     * Constructeur pour le menu de création de lieux
     *
     * @param fenetre Fenetre qui contient le panel de création de lieux
     */
    public CreationLieu(Fenetre fenetre){

        JButton bouttonAjouterAcces = new JButton("Gestion accès");
        JButton bouttonAjouterLieu = new JButton("Créer");

        lieu = new Lieu();
        formulaireLieu = new FormulaireLieu(bouttonAjouterLieu);
        fenetreGestionAcces = new FenetreGestionAcces(lieu);

        this.fenetre = fenetre;
        fenetre.setTitle("Gestionnaire ESIGELEC - Ajouter un lieu");

        System.out.println(" ");
        System.out.println("Affichage du menu Création de Lieu");

        bouttonAjouterLieu.setEnabled(false);

        //Ajout composants graphiques
        fenetre.getFenetre().removeAll();
        fenetre.getFenetre().setBorder(BorderFactory.createEmptyBorder(65, 100, 100, 65));
        fenetre.getFenetre().add( formulaireLieu );
        fenetre.getFenetre().add( Box.createRigidArea(new Dimension(10, 0)));
        fenetre.getFenetre().add(bouttonAjouterAcces);
        fenetre.getFenetre().add( Box.createRigidArea(new Dimension(10, 0)));
        fenetre.getFenetre().add(bouttonAjouterLieu);
        fenetre.updateAffichage();

        //Ajout fonctions écoute pour les bouttons
        bouttonAjouterLieu.addActionListener( new actionBoutonAjouter() );
        bouttonAjouterAcces.addActionListener( new actionBoutonGestionAcces() );
    }


    /**
     * Ecoute du bouton Ajouter lieu
     */
    class actionBoutonAjouter implements ActionListener{


        /**
         * Lors de l'appuie sur le boutton Ajouter, ajoute le lieu qui est dans les champs du formulaire
         *
         * @param e Evenement
         */
        public void actionPerformed(ActionEvent e) {

            LieuDAO lieuDAO = new LieuDAO();
            System.out.println("Appuie sur le boutton ajouter Lieu");

            lieu.setEmplacement( formulaireLieu.getEmplacement() );
            lieu.setHoraireOuverture( formulaireLieu.getHoraireOuverture() );
            lieu.setHoraireFermeture( formulaireLieu.getHoraireFermeture() );
            lieu.setNombreAcces( formulaireLieu.getNombreAcces() );

            if ( !lieuDAO.presenceDuLieu( lieu ) ){
                System.out.println("Lieu créee");

                if(formulaireLieu.getNombreAcces()>= fenetreGestionAcces.nombreAccesDefini() ){

                    // On compare si l'heure d'ouverture est plus petite que l'heure de fermeture
                   if( Integer.parseInt( formulaireLieu.getHoraireOuverture().getHeures()+formulaireLieu.getHoraireOuverture().getMinutes() ) <  Integer.parseInt( formulaireLieu.getHoraireFermeture().getHeures()+formulaireLieu.getHoraireFermeture().getMinutes() )){
                       AccesDAO accesDAO = new AccesDAO();

                       if( lieuDAO.ajouter( lieu ) && accesDAO.ajouter(lieuDAO.getID(lieu), lieu.getListeAcces())){
                           JOptionPane.showMessageDialog(null, "Lieu crée !", "Message de confirmation",JOptionPane.INFORMATION_MESSAGE);
                           new CreationLieu(fenetre);
                       }
                       else{
                           JOptionPane.showMessageDialog(null, "Erreur avec la base de données", "Erreur",JOptionPane.ERROR_MESSAGE);
                       }
                   }
                   else{
                       JOptionPane.showMessageDialog(null, "L'heure d'ouverture est supérieur ou égal à l'heure de fermeture!", "Erreur",JOptionPane.ERROR_MESSAGE);
                   }

                }
                else{
                    System.out.println("Erreur Ajout accès");
                    JOptionPane.showMessageDialog(null, "Le nombre d'accès défini est supérieur au nombre d'accès du lieu", "Erreur",JOptionPane.ERROR_MESSAGE);
                }


            }
            else{
                JOptionPane.showMessageDialog(null, "Nom du lieu déjà présent dans la base de données", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    /**
     * Ecoute du boutton GestionAcces
     */
    class actionBoutonGestionAcces implements ActionListener{

        /**
         * Affiche le la fenetre pour ajouter un accès si un nombre d'accès valide est entré dans le formulaire
         *
         * @param e Evenement
         */
        public void actionPerformed(ActionEvent e) {

            lieu.setNombreAcces(formulaireLieu.getNombreAcces());

            if(lieu.getNombreAcces()== 0){
                JOptionPane.showMessageDialog(null, "Entrer un nombre d'accès", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
            else{

                TypeAccesDAO typeAccesDAO = new TypeAccesDAO();

                if(typeAccesDAO.recupereToutlesTypes().size() == 0)
                    JOptionPane.showMessageDialog(null, "Aucun type d'accès défini !", "Erreur",JOptionPane.ERROR_MESSAGE);
                else
                    fenetreGestionAcces.fenetreVisible();
            }
        }
    }
}
