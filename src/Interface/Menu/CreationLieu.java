package Interface.Menu;

import DAO.TableAccesDAO;
import Interface.FenetreGestionAcces;
import DAO.TableLieuDAO;
import Interface.Fenetre;
import Interface.elementGraphique.FormulaireLieu;
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
        bouttonAjouterLieu.addActionListener( new actionBouttonAjouter() );
        bouttonAjouterAcces.addActionListener( new actionBouttonGestionAcces() );
    }


    /**
     * Ecoute du bouton Ajouter lieu
     */
    class actionBouttonAjouter implements ActionListener{


        /**
         * Lors de l'appuie sur le boutton Ajouter, ajoute le lieu qui est dans les champs du formulaire
         *
         * @param e Evenement
         */
        public void actionPerformed(ActionEvent e) {

            TableLieuDAO tableLieuDAO = new TableLieuDAO();
            System.out.println("Appuie sur le boutton ajouter Lieu");

            lieu.setEmplacement( formulaireLieu.getEmplacement() );
            lieu.setHoraireOuverture( formulaireLieu.getHoraireOuverture() );
            lieu.setHoraireFermeture( formulaireLieu.getHoraireFermeture() );
            lieu.setNombreAcces( formulaireLieu.getNombreAcces() );

            if ( !tableLieuDAO.presenceDuLieu( lieu ) ){
                System.out.println("Lieu créee");

                if(formulaireLieu.getNombreAcces()>= fenetreGestionAcces.nombreAccesDefini() ){

                    TableAccesDAO tableAccesDAO = new TableAccesDAO();

                    if( tableLieuDAO.ajouter( lieu ) && tableAccesDAO.ajouter(tableLieuDAO.getID(lieu), lieu.getListeAcces())){
                        JOptionPane.showMessageDialog(null, "Lieu crée !", "Message de confirmation",JOptionPane.INFORMATION_MESSAGE);
                        new CreationLieu(fenetre);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Erreur avec la base de données", "Erreur",JOptionPane.ERROR_MESSAGE);
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
    class actionBouttonGestionAcces implements ActionListener{

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
                fenetreGestionAcces.fenetreVisible();
            }
        }
    }
}
