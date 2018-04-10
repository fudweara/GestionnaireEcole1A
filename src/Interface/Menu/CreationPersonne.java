package Interface.Menu;

import Interface.Fenetre;
import Interface.elementGraphique.FormulairePersonne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreationPersonne {

    private Fenetre fenetre;

    private JLabel texteCreerUnePersonne;
    private JPanel JPanelTexteCreerUnePersonne;
    private JPanel espaceVide;
    private FormulairePersonne formulairePersonne;
    private JButton validerCreation;
    private JPanel JPanelValiderCreation;
    private Font police;

    public CreationPersonne( Fenetre fenetre ){

        this.fenetre=fenetre;

        police = new Font("Arial", Font.BOLD, 30);
        formulairePersonne = new FormulairePersonne();
        texteCreerUnePersonne = new JLabel("Création d'une personne");
        JPanelTexteCreerUnePersonne =new JPanel();
        espaceVide = new JPanel();
        validerCreation = new JButton("Valider");
        JPanelValiderCreation = new JPanel();


        ajoutComposantsGraphiques();

        validerCreation.addActionListener(new ecouteValiderCreation());

    }

    private void ajoutComposantsGraphiques(){

        texteCreerUnePersonne.setFont(police);

        JPanelTexteCreerUnePersonne.add(texteCreerUnePersonne);
        //fenetre.setNewJPanel( JPanelTexteCreerUnePersonne);


        fenetre.setNewJPanel( espaceVide);

        fenetre.addJPanel( formulairePersonne.getJPanel(), BorderLayout.CENTER);

        JPanelValiderCreation.add(validerCreation);
        fenetre.addJPanel(JPanelValiderCreation,BorderLayout.SOUTH);


    }

    class ecouteValiderCreation implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
            System.out.println("Appuie sur boutton validation");
        }
    }
}
