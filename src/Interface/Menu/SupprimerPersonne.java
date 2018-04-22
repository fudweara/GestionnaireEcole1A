package Interface.Menu;

import DAO.TablePersonneDAO;
import Interface.Fenetre;
import Interface.elementGraphique.RecherchePersonne;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe qui représente le menu supprimer une personne
 */
public class SupprimerPersonne {

    private RecherchePersonne recherchePersonne;

    private JButton bouttonSupprimer;
    private JPanel panelButtonSupprimer;

    /**
     * Constructeur de la classe SupprimerPersonne
     * @param fenetre (Fenetre)
     */
    public SupprimerPersonne(Fenetre fenetre){

        fenetre.setTitle("Gestionnaire ESIGELEC - Supprimer une personne");

        recherchePersonne = new RecherchePersonne(fenetre,0,null);

        fenetre.setNewJPanel( recherchePersonne.getJPanel() );
        bouttonSupprimer = new JButton("Supprimer");
        panelButtonSupprimer = new JPanel();

        panelButtonSupprimer.add(bouttonSupprimer);
        bouttonSupprimer.addActionListener(new actionButtonSupprimer());

        fenetre.addJPanel(panelButtonSupprimer,"");

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
