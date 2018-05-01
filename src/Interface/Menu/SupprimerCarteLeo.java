package Interface.Menu;

import DAO.TableCarteLeoDAO;
import Interface.Fenetre;
import Interface.elementGraphique.RecherchePersonne;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe qu représente le menu pour supprimer une carte Léo
 */

public class SupprimerCarteLeo {

    private RecherchePersonne recherchePersonne;

    private JButton bouttonSupprimerCarte;
    private JPanel panelBouttonSupprimerCarte;

    public SupprimerCarteLeo(Fenetre fenetre){

        fenetre.setTitle("Gestionnaire ESIGELEC - Supprimer une carte Léo");

        recherchePersonne = new RecherchePersonne(fenetre, 1, null);

        fenetre.setNewJPanel( recherchePersonne.getJPanel() );
        bouttonSupprimerCarte = new JButton("Supprimer");
        panelBouttonSupprimerCarte = new JPanel();

        panelBouttonSupprimerCarte.add(bouttonSupprimerCarte);
        bouttonSupprimerCarte.addActionListener(new actionBouttonSupprimerCarte());

        fenetre.addJPanel(panelBouttonSupprimerCarte,"");

    }

    class actionBouttonSupprimerCarte implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            System.out.println("Appuie sur boutton supprimer");

            TableCarteLeoDAO tableCarteLeoDAO = new TableCarteLeoDAO();

            if(tableCarteLeoDAO.supprimer(recherchePersonne.getIdPersonneSelectionne()) ){
                System.out.println("Suppression effectuée");
                JOptionPane.showMessageDialog(null, "Suppression effectuée !", "Message de confirmation",JOptionPane.INFORMATION_MESSAGE);

            }
            else{
                System.out.println("Selectionnez une personne");
                JOptionPane.showMessageDialog(null, "Selectionnez une personne", "Erreur",JOptionPane.ERROR_MESSAGE);

            }

        }
    }

}
