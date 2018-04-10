package Interface.elementGraphique;

import Interface.Fenetre;
import objetStockage.DateDeNaissance;
import objetStockage.Personne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RecherchePersonne {

    private Fenetre fenetre;


    private ArrayList<Personne> listePersonne;

    private JPanel recherchePersonne = new JPanel();

    private JTextField champDeRechercheNom;
    private JButton boutton;
    private JPanel JPanelRechercheEtBOuton;

    private JPanel[] resulatsRecherche;
    private JLabel[] texteResulatsRecherche;

    public RecherchePersonne(Fenetre fenetre){

        this.fenetre=fenetre;

        declarationVariables();
        creationInterface();

        boutton.addActionListener(new ecouteBoutonRechercher());

    }


    private void declarationVariables(){
        listePersonne = new ArrayList<Personne>();

        recherchePersonne = new JPanel();
        boutton = new JButton("Ok");
        JPanelRechercheEtBOuton = new JPanel();
        resulatsRecherche = new JPanel[5];;
        texteResulatsRecherche = new JLabel[5];;
        champDeRechercheNom = new JTextField();

        for(int i = 0 ; i<5 ; i++){
            resulatsRecherche[i]=new JPanel();
            texteResulatsRecherche[i]=new JLabel();
        }
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

            System.out.println("Appuie sur boutton validation");

            // FONCTION DE RECUPERATION DE L'ARRAYLIST

            listePersonne.add( new Personne("A","A",new DateDeNaissance(1,1,1),"A") );
            listePersonne.add( new Personne("B","B",new DateDeNaissance(2,2,2),"B") );
            listePersonne.add( new Personne("C","C",new DateDeNaissance(3,3,3),"C") );
            listePersonne.add( new Personne("D","D",new DateDeNaissance(4,4,4),"D") );
            listePersonne.add( new Personne("E","E",new DateDeNaissance(5,5,5),"E") );

            Font police = new Font("Arial", Font.BOLD, 30);

            for(int i=0 ; ( i< listePersonne.size() && i < 5 ) ; i++){
                texteResulatsRecherche[i].setText("LABEL"+i);
                texteResulatsRecherche[i].setFont(police);

                resulatsRecherche[i].add(texteResulatsRecherche[i],BorderLayout.LINE_START);
                recherchePersonne.add(resulatsRecherche[i]);
            }
            fenetre.updateWIndows();


        }
    }


    public JPanel getJPanel(){
        return recherchePersonne;
    }

}
