package interfaces.menu;

import dao.CarteLeoDAO;
import interfaces.Fenetre;
import interfaces.elementGraphique.RecherchePersonne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Classe qu représente le menu pour supprimer une carte Léo
 */
public class SupprimerCarteLeo {

    private final RecherchePersonne recherchePersonne;
    private final Fenetre fenetre;


    /**
     * Constructeur pour le menu pour supprimer une carte Léo
     *
     * @param fenetre Fenetre qui va contenir le JPanel du menu pour supprimer une carte Léo
     */
    public SupprimerCarteLeo(Fenetre fenetre){

        JButton boutonSupprimerCarte = new JButton("Supprimer");

        this.fenetre = fenetre;
        fenetre.setTitle("Gestionnaire ESIGELEC - Supprimer une carte Léo");

        System.out.println(" ");
        System.out.println("Affichage du menu supprimer une carte Léo");

        recherchePersonne = new RecherchePersonne(fenetre, 1, null);

        //Ajout éléments graphiques
        fenetre.getFenetre().removeAll();
        fenetre.getFenetre().setBorder(BorderFactory.createEmptyBorder(15, 100, 15, 100));
        fenetre.getFenetre().add( recherchePersonne );
        fenetre.getFenetre().add( Box.createRigidArea(new Dimension(20, 0)));
        fenetre.getFenetre().add(boutonSupprimerCarte);
        fenetre.updateAffichage();

        //Ajout fonction ecoute pour le boutton
        boutonSupprimerCarte.addActionListener(new actionBoutonSupprimer());
    }


    /**
     * Fonction d'écoute pour le boutton Supprimer
     */
    class actionBoutonSupprimer implements ActionListener{


        /**
         * Supprime la carte léo pour la personne selectionnés dans la recherche lors de l'appuie sur le boutton Supprimer
         *
         * @param e Evenement
         */
        public void actionPerformed(ActionEvent e) {
            System.out.println("Appuie sur boutton supprimer");

            CarteLeoDAO carteLeoDAO = new CarteLeoDAO();

            if(carteLeoDAO.supprimer(recherchePersonne.getIdPersonneSelectionne()) ){
                System.out.println("Suppression effectuée");
                JOptionPane.showMessageDialog(null, "Suppression effectuée !", "Message de confirmation",JOptionPane.INFORMATION_MESSAGE);
                new SupprimerCarteLeo(fenetre);
            }
            else{
                System.out.println("Selectionnez une personne");
                JOptionPane.showMessageDialog(null, "Selectionnez une personne", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
