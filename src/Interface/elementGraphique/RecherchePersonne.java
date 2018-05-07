package Interface.elementGraphique;

import DAO.TableCarteLeoDAO;
import Interface.Fenetre;
import DAO.TablePersonneDAO;
import objetStockage.Personne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Objet qui permet une recherche de personne grace à son nom
 */
public class RecherchePersonne extends JPanel{

    private Fenetre fenetre;

    private FormulairePersonne formulairePersonne;

    private ArrayList<Personne> listePersonne;

    private JTextField champDeRechercheNom;
    private JButton boutton;
    private JPanel JPanelRechercheEtBOuton;

    private ButtonGroup groupeButton;
    private JRadioButton[] bouttonJradio;

    private int idPersonneSelectionne;
    private boolean personneSelectionne;

    /**
     * Si /
     * - 0 : recherche sans prise en compte des cartes
     * - 1 : affichage des personnes avec une carte
     * - 2 : affichage des personnes sans carte
     */
    private int typeRecherche;

    /**
     * Constructeur de la classe recherche de personnes
     *
     * @param fenetre (Fenetre)
     * @param formulairePersonne (FormulairePersonne)
     */
    public RecherchePersonne(Fenetre fenetre,int typeRecherche, FormulairePersonne formulairePersonne){

        //Declaration des variables
        this.formulairePersonne = formulairePersonne;
        this.fenetre=fenetre;
        this.typeRecherche = typeRecherche;

        listePersonne = new ArrayList<>();
        boutton = new JButton("Ok");
        JPanelRechercheEtBOuton = new JPanel();
        champDeRechercheNom = new JTextField();
        groupeButton = new ButtonGroup();
        personneSelectionne = false;

        creationInterface();

        boutton.addActionListener(new ecouteBoutonRechercher());
    }


    /**
     * Création de l'interface faite dans la constructeur
     */
    private void creationInterface(){
        setPreferredSize(new Dimension(300,300));
       setBackground(Color.GRAY);

        champDeRechercheNom.setPreferredSize(new Dimension(249, 30));
        champDeRechercheNom.setForeground(Color.BLACK);
        add(champDeRechercheNom,BorderLayout.PAGE_START);

        JPanelRechercheEtBOuton.setPreferredSize(new Dimension(249, 30));
        JPanelRechercheEtBOuton.add(boutton,BorderLayout.CENTER);
        JPanelRechercheEtBOuton.setBackground(Color.GRAY);
        add(JPanelRechercheEtBOuton,BorderLayout.PAGE_START);
    }


    /**
     * Fonction d'écoute pour le bouton rechercher
     *
     */
    class ecouteBoutonRechercher implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            TablePersonneDAO tablePersonneDAO = new TablePersonneDAO();
            TableCarteLeoDAO tableCarteLeoDAO = new TableCarteLeoDAO();

            System.out.println("Appuie sur boutton validation");

            removeAll();
            creationInterface();

            listePersonne =  tablePersonneDAO.rechercher( champDeRechercheNom.getText() );

            bouttonJradio = new JRadioButton[listePersonne.size()];

            for(int i=0 ; ( i< listePersonne.size() && i < 5 ) ; i++){

                System.out.println((tableCarteLeoDAO.connaitrePossession(listePersonne.get(i))));

                if( typeRecherche == 0 || ( typeRecherche == 2 && !(tableCarteLeoDAO.connaitrePossession(listePersonne.get(i))) ) || ( typeRecherche == 1 && tableCarteLeoDAO.connaitrePossession(listePersonne.get(i)))  )  {
                    bouttonJradio[i] = new JRadioButton(listePersonne.get(i).getNom()+" "+listePersonne.get(i).getPrenom());
                    groupeButton.add(bouttonJradio[i]);
                    bouttonJradio[i].setActionCommand( i+"" );

                    bouttonJradio[i].addActionListener( new ecouteSelection() );

                    add( bouttonJradio[i] );
                }
            }
            fenetre.updateAffichage();
        }
    }

    /**
     * Fonction d'écoute pour la sélection d'une personne qui est listée après l'avoir recherchée
     */
    class ecouteSelection implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            int persSelect = Integer.parseInt( groupeButton.getSelection().getActionCommand() );

            personneSelectionne = true;
            idPersonneSelectionne = listePersonne.get(persSelect).getId();

            if (formulairePersonne != null) {
                formulairePersonne.setAtributs( listePersonne.get(persSelect).getNom(), listePersonne.get(persSelect).getPrenom(), listePersonne.get(persSelect).getDateNaissance(), listePersonne.get(persSelect).getFonction());

            }
        }
    }


    /**
     * Récupére l'id de la personne sélectionnée
     * @return Integer
     */
    public int getIdPersonneSelectionne(){
        return idPersonneSelectionne;
    }

    public boolean selectionPersonneEffectuee(){return personneSelectionne;}

}
