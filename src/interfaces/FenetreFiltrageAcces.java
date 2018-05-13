package interfaces;

import interfaces.elementGraphique.ListeAcces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Fenetre pour choisir le type de filtrage dans le menu Recherche Acces
 */
public class FenetreFiltrageAcces extends JFrame {

    private final JTextField champTexte;
    private final ListeAcces listeAcces;
    private final JRadioButton[] jRadioButtons;


    /**
     * Créer la fenetre pour choisir le type de filtrage et le nom du lieu/type d'accès pour filtrer
     *
     * @param listeAcces Liste des accès à mettre à jour
     */
    public FenetreFiltrageAcces(ListeAcces listeAcces){

        this.setTitle("Filtrage");
        this.setSize(300, 130);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        JPanel conteneur = new JPanel();
        this.listeAcces = listeAcces;

        ButtonGroup groupeButton = new ButtonGroup();
        jRadioButtons = new JRadioButton[2];
        jRadioButtons[0] = new JRadioButton("Pour un  lieu");
        jRadioButtons[0].setSelected(true);
        jRadioButtons[1] = new JRadioButton("Pour un type d'accès");
        groupeButton.add(jRadioButtons[0]);
        groupeButton.add(jRadioButtons[1]);

        JButton buttonFiltrer = new JButton("Filtrer");

        JPanel panelButtons = new JPanel();
        panelButtons.add(jRadioButtons[0]);
        panelButtons.add(jRadioButtons[1]);

        champTexte = new JTextField("");
        champTexte.setPreferredSize(new Dimension(150,30));

        //Ajout compsants graphiques
        conteneur.add(panelButtons);
        conteneur.add(champTexte);
        conteneur.add(buttonFiltrer);

        buttonFiltrer.addActionListener(new ecouteBoutonFiltrer());

        this.setContentPane(conteneur);
        this.setVisible(false);
    }


    /**
     * Ecoute pour le bouton filtrer
     */
    class ecouteBoutonFiltrer implements ActionListener{

        /**
         * Lors de l'appuie sur le bouton filtrer :
         * - Si le champ est vide : affiche tous les accès
         * - SI le champ n'est pas vide : filtre avec le nom du type d'accès/lieu entré ( en fonction du choix avec le JRadioButton )
         *
         * @param e Evenement
         */
        public void actionPerformed(ActionEvent e) {

            if(champTexte.getText().equals("")){
                listeAcces.affichageTousLesAcces();
            }
            else{
                if(jRadioButtons[0].isSelected())
                    listeAcces.filtragePourUnLieu( champTexte.getText() );
                else
                    listeAcces.filtragePourUnTypeAcces( champTexte.getText() );
            }
        }
    }
}
