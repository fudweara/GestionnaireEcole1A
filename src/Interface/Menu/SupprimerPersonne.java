package Interface.Menu;

import DAO.TablePersonneDAO;
import Interface.Fenetre;
import Interface.elementGraphique.RecherchePersonne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe qui représente le menu supprimer une personne
 */
public class SupprimerPersonne {

    private final RecherchePersonne recherchePersonne;
    private final Fenetre fenetre;

    /**
     * Constructeur de la classe SupprimerPersonne
     * @param fenetre (Fenetre)
     */
    public SupprimerPersonne(Fenetre fenetre){
        JButton bouttonSupprimer = new JButton("Supprimer");

        this.fenetre = fenetre;
        fenetre.setTitle("Gestionnaire ESIGELEC - Supprimer une personne");

        recherchePersonne = new RecherchePersonne(fenetre,0,null);

        //Ajout élements graphiques
        fenetre.getFenetre().removeAll();
        fenetre.getFenetre().setBorder(BorderFactory.createEmptyBorder(15, 100, 15, 100));
        fenetre.getFenetre().add( recherchePersonne );
        fenetre.getFenetre().add( Box.createRigidArea(new Dimension(20, 0)));
        fenetre.add(bouttonSupprimer);
        bouttonSupprimer.addActionListener(new actionButtonSupprimer());

        fenetre.updateAffichage();
    }


    /**
     * Fonction d'écoute pour le bouton Supprimer
     */
    class actionButtonSupprimer implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            TablePersonneDAO tablePersonneDAO = new TablePersonneDAO();

            if( recherchePersonne.selectionPersonneEffectuee() ){
                if( tablePersonneDAO.supprimerPersonne( recherchePersonne.getIdPersonneSelectionne() ) ){
                    System.out.println("Supression effectuée : ID personne supprimée : "+recherchePersonne.getIdPersonneSelectionne());
                    JOptionPane.showMessageDialog(null, "Suppression effectuée !", "Message de confirmation",JOptionPane.INFORMATION_MESSAGE);
                    new SupprimerPersonne(fenetre);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Problème lors de la suppression dans la base de données. Contactez l'administrateur.", "Erreur",JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Choississez une personne dans la recherche", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
