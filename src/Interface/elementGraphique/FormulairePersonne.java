package Interface.elementGraphique;

import objetStockage.DateDeNaissance;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

/**
 * Classe qui représente l'interface de Création d'une personne avec ses composants (JPanel,JTextField...)
 */
public class FormulairePersonne{

    private JPanel formulairePersonne;
    private JTextField champNom;
    private JTextField champPrenom;
    private JFormattedTextField champDateDeNaissance;
    private JTextField champFonction ;

    public FormulairePersonne(){

        // Texte devant chaque champ de recherche
        JLabel texteNom = new JLabel("Nom :");
        JLabel textePrenom = new JLabel("Prenom :");
        JLabel textedateDeNaissance = new JLabel("Date :");
        JLabel texteFonction = new JLabel("Fonction :");

        // Vont stocker Texte + champ de recherche
        JPanel nomJPanel = new JPanel();
        JPanel prenomJPanel = new JPanel();
        JPanel dateDeNaissanceJPanel = new JPanel();
        JPanel fonctionJPanel = new JPanel();

        configurationAtribut();

        nomJPanel.add(texteNom);
        nomJPanel.add(champNom);
        formulairePersonne.add(nomJPanel);

        prenomJPanel.add(textePrenom);
        prenomJPanel.add(champPrenom);
        formulairePersonne.add(prenomJPanel);

        dateDeNaissanceJPanel.add(textedateDeNaissance);
        dateDeNaissanceJPanel.add(champDateDeNaissance);
        formulairePersonne.add(dateDeNaissanceJPanel);

        fonctionJPanel.add(texteFonction);
        fonctionJPanel.add(champFonction);
        formulairePersonne.add(fonctionJPanel);

    }

    private void configurationAtribut(){

        formulairePersonne = new JPanel();
        champNom = new JTextField("");
        champPrenom = new JTextField("");
        try{
            MaskFormatter dateDeNaissance = new MaskFormatter("## ## ####");
            champDateDeNaissance = new JFormattedTextField(dateDeNaissance);
        }
        catch(ParseException e){e.printStackTrace();}
        champFonction = new JTextField("");

        formulairePersonne.setLayout(new BoxLayout(formulairePersonne, BoxLayout.PAGE_AXIS));

        champNom.setPreferredSize(new Dimension(150, 30));
        champPrenom.setPreferredSize(new Dimension(150, 30));
        champDateDeNaissance.setPreferredSize(new Dimension(70, 30));
        champFonction.setPreferredSize(new Dimension(150, 30));
    }

    public JPanel getJPanel(){
        return formulairePersonne;
    }

    public String getNom(){
        return champNom.getText();
    }

    public String getPrenom(){
        return champPrenom.getText();
    }

    public DateDeNaissance getDateDeNaissance(){

        String[] temp =  champDateDeNaissance.getText().split("\\s+");
        return new DateDeNaissance( temp[0],temp[1],temp[2]);
    }

    public String getFonction(){
        return champFonction.getText();
    }

    public void setAtributs(String nom, String prenom, DateDeNaissance dateDeNaissance, String fonction){

        champNom.setText(nom);
        champPrenom.setText(prenom);
        champDateDeNaissance.setText( dateDeNaissance.getJour()+""+dateDeNaissance.getMois()+""+dateDeNaissance.getAnnee() );
        champFonction.setText(fonction);

    }
}
