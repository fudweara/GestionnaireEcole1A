package Interface.elementGraphique;

import Interface.Fenetre;
import DAO.TablePersonneDAO;
import objetStockage.Personne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RecherchePersonne {

    private Fenetre fenetre;

    FormulairePersonne formulairePersonne;

    private ArrayList<Personne> listePersonne;

    private JPanel recherchePersonne = new JPanel();

    private JTextField champDeRechercheNom;
    private JButton boutton;
    private JPanel JPanelRechercheEtBOuton;

    private ButtonGroup groupeButton;
    private JRadioButton[] bouttonJradio;

    public RecherchePersonne(Fenetre fenetre,  FormulairePersonne formulairePersonne){

        this.fenetre=fenetre;
        this.formulairePersonne=formulairePersonne;

        declarationVariables();
        creationInterface();

        boutton.addActionListener(new ecouteBoutonRechercher());

    }


    private void declarationVariables(){
        listePersonne = new ArrayList<Personne>();

        recherchePersonne = new JPanel();
        boutton = new JButton("Ok");
        JPanelRechercheEtBOuton = new JPanel();
        champDeRechercheNom = new JTextField();
        groupeButton = new ButtonGroup();

    }

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


    class ecouteBoutonRechercher implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            TablePersonneDAO tablePersonneDAO = new TablePersonneDAO();

            System.out.println("Appuie sur boutton validation");

            recherchePersonne.removeAll();
            creationInterface();


            listePersonne =  tablePersonneDAO.rechercherPersonne( champDeRechercheNom.getText() );

            bouttonJradio = new JRadioButton[listePersonne.size()];

            for(int i=0 ; ( i< listePersonne.size() && i < 5 ) ; i++){


                bouttonJradio[i] = new JRadioButton(listePersonne.get(i).getNom()+" "+listePersonne.get(i).getPrenom());
                groupeButton.add(bouttonJradio[i]);
                bouttonJradio[i].setActionCommand( i+"" );

                bouttonJradio[i].addActionListener( new ecouteSelection() );

                recherchePersonne.add( bouttonJradio[i] );

            }
            fenetre.updateWIndows();


        }
    }

    class ecouteSelection implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            int persSelect = Integer.parseInt( groupeButton.getSelection().getActionCommand() );
            System.out.println( persSelect );
            formulairePersonne.setAtributs( listePersonne.get(persSelect).getNom(), listePersonne.get(persSelect).getPrenom(), listePersonne.get(persSelect).getDateNaissance(), listePersonne.get(persSelect).getFonction());

        }
    }


    public JPanel getJPanel(){
        return recherchePersonne;
    }

}
