package Interface.Menu;

import DAO.TableCarteLeoDAO;
import Interface.Fenetre;
import Interface.elementGraphique.RecherchePersonne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


/**
 * Classe qui représente le menu créer une carte Leo
 */
public class CreationCarteLeo {

    private final Fenetre fenetre;
    private final RecherchePersonne recherchePersonne;


    /**
     * Constructeur de la classe CreationCarteLeo
     *
     * @param fenetre Fenetre qui contient le panel Création carte Léo
     */
    public CreationCarteLeo(Fenetre fenetre){

        this.fenetre = fenetre;
        fenetre.setTitle("Gestionnaire ESIGELEC - Ajouter une carte Léo");

        System.out.println(" ");
        System.out.println("Affichage du menu création de cartes Léo");

        JButton bouttonAjouterCarte = new JButton("Créer");
        recherchePersonne = new RecherchePersonne(fenetre, 2, null);

        //Ajout composants graphiques
        fenetre.getFenetre().removeAll();
        fenetre.getFenetre().setBorder(BorderFactory.createEmptyBorder(15, 100, 15, 100));
        fenetre.getFenetre().add( recherchePersonne );
        fenetre.getFenetre().add( Box.createRigidArea(new Dimension(20, 0)));
        fenetre.getFenetre().add(bouttonAjouterCarte);
        fenetre.updateAffichage();

        //Ajout fonction ecoute pour le boutton ajouter
        bouttonAjouterCarte.addActionListener(new actionBouttonAjouterCarte());
    }


    /**
     * Fonction d'écoute pour le bouton Ajouter
     */
    class actionBouttonAjouterCarte implements ActionListener{


        /**
         * Ajoute la carte léo à la base de données lors de l'appuie sur sur le boutton Ajouter
         * @param e Evenement
         */
        public void actionPerformed(ActionEvent e) {

            TableCarteLeoDAO tableCarteLeoDAO = new TableCarteLeoDAO();
            String numeroDeBadge = generationNumeroBadge() ;
            System.out.println("Appuie sur le boutton Créer carte Léo");

            if(tableCarteLeoDAO.ajouter( recherchePersonne.getIdPersonneSelectionne(), numeroDeBadge )){
                System.out.println("Carte Leo créee");
                JOptionPane.showMessageDialog(null, "Carte Léo créee, le numero de badge est "+numeroDeBadge, "Message de confirmation",JOptionPane.INFORMATION_MESSAGE);
                new CreationCarteLeo(fenetre);
            }
            else{
                JOptionPane.showMessageDialog(null, "Selectionnez une personne !", "Erreur",JOptionPane.ERROR_MESSAGE);
            }

        }
    }


    /**
     * Génére un numero de badge qui n'est pas présent dans la base de données
     *
     * @return Numéro de badge généré
     */
    private String generationNumeroBadge(){
        Random rand = new Random();
        StringBuilder numeroBadge = new StringBuilder();
        TableCarteLeoDAO tableCarteLeoDAO = new TableCarteLeoDAO();

        do{
            for(int i =0;i<12;i++){

                if(rand.nextInt(3)+1 < 2){
                    numeroBadge.append( rand.nextInt(9) );
                }
                else{
                    numeroBadge.append( Character.toUpperCase( (char)(rand.nextInt(26)+97) ) );
                }

            }
        }while( tableCarteLeoDAO.presenceNumeroBadge( numeroBadge.toString() ));

        return numeroBadge.toString();
    }
}
