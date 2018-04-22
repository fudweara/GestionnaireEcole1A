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
public class RecherchePersonne {

    private Fenetre fenetre;

    private FormulairePersonne formulairePersonne;

    private ArrayList<Personne> listePersonne;

    private JPanel recherchePersonne = new JPanel();

    private JTextField champDeRechercheNom;
    private JButton boutton;
    private JPanel JPanelRechercheEtBOuton;

    private ButtonGroup groupeButton;
    private JRadioButton[] bouttonJradio;

    private int idPersonneSelectionne;
    private boolean personneSelectionne;

    private boolean rechechePersonneAvecCarte;


    public RecherchePersonne(boolean personneAvecCarte, Fenetre fenetre){
        this(fenetre,null);
        rechechePersonneAvecCarte = personneAvecCarte;
    }


    /**
     * Constructeur de la classe recherche de personnes
     *
     * @param fenetre (Fenetre)
     * @param formulairePersonne (FormulairePersonne)
     */
    public RecherchePersonne(Fenetre fenetre,  FormulairePersonne formulairePersonne){


        this.formulairePersonne = formulairePersonne;
        this.fenetre=fenetre;

        declarationVariables();
        creationInterface();

        boutton.addActionListener(new ecouteBoutonRechercher());

        rechechePersonneAvecCarte = false;

    }


    /**
     * Declaration des variables fait dans le constructur
     */
    private void declarationVariables(){
        listePersonne = new ArrayList<>();

        recherchePersonne = new JPanel();
        boutton = new JButton("Ok");
        JPanelRechercheEtBOuton = new JPanel();
        champDeRechercheNom = new JTextField();
        groupeButton = new ButtonGroup();

        personneSelectionne = false;
    }

    /**
     * Création de l'interface faite dans la constructeur
     */
    private void creationInterface(){

        recherchePersonne.setPreferredSize(new Dimension(300,300));
        recherchePersonne.setBackground(Color.GRAY);

        champDeRechercheNom.setPreferredSize(new Dimension(249, 30));
        champDeRechercheNom.setForeground(Color.BLACK);
        recherchePersonne.add(champDeRechercheNom,BorderLayout.PAGE_START);

        JPanelRechercheEtBOuton.setPreferredSize(new Dimension(249, 30));
        JPanelRechercheEtBOuton.add(boutton,BorderLayout.CENTER);
        JPanelRechercheEtBOuton.setBackground(Color.GRAY);
        recherchePersonne.add(JPanelRechercheEtBOuton,BorderLayout.PAGE_START);

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

            recherchePersonne.removeAll();
            creationInterface();

            listePersonne =  tablePersonneDAO.rechercherPersonne( champDeRechercheNom.getText() );



            bouttonJradio = new JRadioButton[listePersonne.size()];

            for(int i=0 ; ( i< listePersonne.size() && i < 5 ) ; i++){

                System.out.println((tableCarteLeoDAO.connaitrePossession(listePersonne.get(i))));

                if( !rechechePersonneAvecCarte || !(tableCarteLeoDAO.connaitrePossession(listePersonne.get(i))) ) {
                    bouttonJradio[i] = new JRadioButton(listePersonne.get(i).getNom()+" "+listePersonne.get(i).getPrenom());
                    groupeButton.add(bouttonJradio[i]);
                    bouttonJradio[i].setActionCommand( i+"" );

                    bouttonJradio[i].addActionListener( new ecouteSelection() );

                    recherchePersonne.add( bouttonJradio[i] );
                }


            }
            fenetre.updateWIndows();


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
     * Récupére le JPanel un formulaire de recherche d'une personne
     * @return JPanel
     */
    public JPanel getJPanel(){
        return recherchePersonne;
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
