package Interface.elementGraphique;

import DAO.TableLieuDAO;
import Interface.Fenetre;
import objetStockage.Lieu;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * Recherche d'un lieu qui hérite de la classe recherche
 */
public class RechercheLieu extends Recherche {

    private final FormulaireLieu formulaireLieu;

    private ArrayList<Lieu> listeLieu;

    private final TableLieuDAO tableLieuDAO = new TableLieuDAO();


    /**
     * Constructeur pour la recherche d'un lieu
     *
     * @param fenetre Fenetre qui va contenir le champ de recherche
     * @param formulaireLieu Formulaire qui va être mis à jour lors de la selection d'un lieu
     */
    public RechercheLieu(Fenetre fenetre, FormulaireLieu formulaireLieu){

        super(fenetre);

        this.formulaireLieu = formulaireLieu;

        listeLieu = new ArrayList<>();
        boutonRechercher.addActionListener(new actionBoutonRechercher() );

        creationInterface();
    }

    /**
     * Lors de la selection d'un lieu, met ses attributs dans le formulaire
     *
     * @param e Evenement
     */
    public void valueChanged(ListSelectionEvent e) {

        formulaireLieu.setAtributs(tableLieuDAO.getLieu(jListe.getSelectedValue()));
    }


    /**
     * Classe pour les actions du bouton rechercher
     */
    class actionBoutonRechercher implements ActionListener {


        /**
         * Met à jour les résultats de recherche lors de l'appuie sur le bouton valider
         *
         * @param arg0 Action Evenement
         */
        public void actionPerformed(ActionEvent arg0) {

            removeAll();
            creationInterface();

            listeLieu = tableLieuDAO.rechercherLieu( champDeRecherche.getText() );

            for (Lieu aListeLieu : listeLieu) {
                listeModele.addElement(aListeLieu.getEmplacement());
            }

            fenetre.updateAffichage();
        }
    }


    /**
     * Retourne le lieu selectionné dans la liste
     *
     * @return Lieu selectionné dans la liste
     */
    public Lieu lieuSelectionne(){

        if ( jListe.getSelectedValue() == null ){
            return new Lieu(-1,null,null,null,0);
        }
        else{
            return listeLieu.get( jListe.getSelectedIndex() );
        }

    }
}