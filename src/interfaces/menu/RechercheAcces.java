package interfaces.menu;

import interfaces.Fenetre;
import interfaces.FenetreFiltrageAcces;
import interfaces.elementGraphique.ListeAcces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe qui représente le menu Recherche Accès
 */
public class RechercheAcces {

    private final FenetreFiltrageAcces fenetreFiltrageAcces;

    /**
     * Constructeur du menu recherche accès
     *
     * @param fenetre Fenetre qui contient le menu recherche accès
     */
    public RechercheAcces(Fenetre fenetre){

        ListeAcces listeAcces = new ListeAcces();
        fenetreFiltrageAcces= new FenetreFiltrageAcces(listeAcces);

        JButton boutonFiltrer = new JButton("Filtres textuels ...");

        //Ajout composants graphiques
        fenetre.getFenetre().removeAll();
        fenetre.getFenetre().setBorder(BorderFactory.createEmptyBorder(50, 50, 20, 50));
        fenetre.getFenetre().add(listeAcces);
        fenetre.getFenetre().add(boutonFiltrer);
        fenetre.updateAffichage();

        boutonFiltrer.addActionListener(new actionBoutonFiltrer());
    }


    /**
     * Classe d'écoute pour le bouton Filter
     */
    class actionBoutonFiltrer implements ActionListener{


        /**
         * Affiche la fenetre pour choisir le filtrage lors de l'appuie sur le bouton filter
         *
         * @param e Evenement
         */
        public void actionPerformed(ActionEvent e) {
            fenetreFiltrageAcces.setVisible(true);
        }
    }
}
