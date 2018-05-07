package Interface.Menu;

import DAO.TableAccesDAO;
import Interface.FenetreLierLieuAcces;
import DAO.TableLieuDAO;
import Interface.Fenetre;
import Interface.elementGraphique.FormulaireLieu;
import objetStockage.Lieu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreationLieu {

    private final Fenetre fenetre;
    private final FormulaireLieu formulaireLieu;

    private final Lieu lieu;

    private FenetreLierLieuAcces fenetreLierLieuAcces;

    public CreationLieu(Fenetre fenetre){

        JButton bouttonAjouterAcces = new JButton("Gestion accès");
        JButton bouttonAjouterLieu = new JButton("Créer");

        lieu = new Lieu();
        formulaireLieu = new FormulaireLieu(bouttonAjouterLieu);

        this.fenetre = fenetre;
        fenetre.setTitle("Gestionnaire ESIGELEC - Ajouter un lieu");

        bouttonAjouterLieu.setEnabled(false);

        //Ajout composants graphiques
        fenetre.getFenetre().removeAll();
        fenetre.getFenetre().setBorder(BorderFactory.createEmptyBorder(15, 100, 15, 100));
        fenetre.getFenetre().add( formulaireLieu );
        fenetre.getFenetre().add( Box.createRigidArea(new Dimension(10, 0)));
        fenetre.getFenetre().add(bouttonAjouterAcces);
        fenetre.getFenetre().add( Box.createRigidArea(new Dimension(10, 0)));
        fenetre.getFenetre().add(bouttonAjouterLieu);
        fenetre.updateAffichage();

        //Ajout fonctions écoute pour les bouttons
        bouttonAjouterLieu.addActionListener( new actionBouttonAjouterLieu() );
        bouttonAjouterAcces.addActionListener( new actionBouttonAjouterAcces() );
    }

    class actionBouttonAjouterLieu implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            TableLieuDAO tableLieuDAO = new TableLieuDAO();
            System.out.println("Appuie sur le boutton ajouter Lieu");

            lieu.setEmplacement( formulaireLieu.getEmplacement() );
            lieu.setHoraireOuverture( formulaireLieu.getHoraireOuverture() );
            lieu.setHoraireFermeture( formulaireLieu.getHoraireFermeture() );
            lieu.setNombreAcces( formulaireLieu.getNombreAcces() );

            if ( !tableLieuDAO.presenceDuLieu( lieu ) ){
                System.out.println("Lieu créee");

                if(formulaireLieu.getNombreAcces()>= fenetreLierLieuAcces.nombreAccesDefini() ){

                    tableLieuDAO.ajouter( lieu );

                    TableAccesDAO tableAccesDAO = new TableAccesDAO();
                    tableAccesDAO.ajouterListeAcces(tableLieuDAO.getID(lieu), lieu.getListeAcces());

                    JOptionPane.showMessageDialog(null, "Lieu crée !", "Message de confirmation",JOptionPane.INFORMATION_MESSAGE);
                    new CreationLieu(fenetre);
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

    class actionBouttonAjouterAcces implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            lieu.setNombreAcces(formulaireLieu.getNombreAcces());

            if(lieu.getNombreAcces()== 0){
                JOptionPane.showMessageDialog(null, "Entrer un nombre d'accès", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
            else{
                fenetreLierLieuAcces = new FenetreLierLieuAcces(lieu);
                fenetreLierLieuAcces.fenetreVisible();
            }
        }
    }
}
