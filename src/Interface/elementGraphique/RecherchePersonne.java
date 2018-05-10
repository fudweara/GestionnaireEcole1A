package Interface.elementGraphique;

import DAO.TableCarteLeoDAO;
import Interface.Fenetre;
import DAO.TablePersonneDAO;
import objetStockage.Personne;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * Objet qui permet une recherche de personne grace à son nom
 */
public class RecherchePersonne extends Recherche {

    private final FormulairePersonne formulairePersonne;

    private ArrayList<Personne> listePersonne;

    private int idPersonneSelectionne;
    private boolean personneSelectionne;


    /**
     * Si /
     * - 0 : recherche sans prise en compte des cartes
     * - 1 : affichage des personnes avec une carte
     * - 2 : affichage des personnes sans carte
     */
    private final int typeRecherche;


    /**
     * Constructeur de la classe recherche de personnes
     *
     * @param fenetre Fenetre qui va contenir la recherche de personne
     * @param formulairePersonne Formulaire de personnes qui va être mis à jour lors de la séléction du personne dans la recherche
     */
    public RecherchePersonne(Fenetre fenetre,int typeRecherche, FormulairePersonne formulairePersonne){

        //Declaration des variables
        super(fenetre);
        this.formulairePersonne = formulairePersonne;
        this.typeRecherche = typeRecherche;

        listePersonne = new ArrayList<>();
        personneSelectionne = false;

        creationInterface();

        boutonRechercher.addActionListener(new ecouteBoutonRechercher());
    }


    /**
     * Après la selection d'une personne dans la liste, met ses paramètres dans le formulaire
     *
     * @param e Evenement
     */
    public void valueChanged(ListSelectionEvent e) {

        int persSelect = jListe.getSelectedIndex();

        personneSelectionne = true;
        idPersonneSelectionne = listePersonne.get(persSelect).getId();

        if (formulairePersonne != null) {
            formulairePersonne.setAtributs( listePersonne.get(persSelect) );
        }
    }


    /**
     * Fonction d'écoute pour le bouton rechercher
     */
    class ecouteBoutonRechercher implements ActionListener {

        /**
         * Met à jour les résultats de recherche lors de l'appuie sur le boutton valider
         *
         * @param arg0 Action Evenement
         */
        public void actionPerformed(ActionEvent arg0) {

            TablePersonneDAO tablePersonneDAO = new TablePersonneDAO();
            TableCarteLeoDAO tableCarteLeoDAO = new TableCarteLeoDAO();

            System.out.println("Appuie sur boutton validation");

            removeAll();
            creationInterface();

            listePersonne =  tablePersonneDAO.rechercher( champDeRecherche.getText() );


            for(int i=0 ; ( i< listePersonne.size() && i < 5 ) ; i++){

                System.out.println((tableCarteLeoDAO.connaitrePossession(listePersonne.get(i))));

                if( typeRecherche == 0 || ( typeRecherche == 2 && !(tableCarteLeoDAO.connaitrePossession(listePersonne.get(i))) ) || ( typeRecherche == 1 && tableCarteLeoDAO.connaitrePossession(listePersonne.get(i)))  )  {
                    listeModele.addElement(listePersonne.get(i).getNom()+" "+listePersonne.get(i).getPrenom());
                }
            }
            fenetre.updateAffichage();
        }
    }


    /**
     * Récupére l'id de la personne sélectionnée
     *
     * @return ID de la personne selectionnee
     */
    public int getIdPersonneSelectionne(){
        return idPersonneSelectionne;
    }

    /**
     * Retourne si oui ou non la personne a été selectionnée
     *
     * @return Oui/Non
     */
    public boolean selectionPersonneEffectuee(){return personneSelectionne;}

}
