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

    /**
     * Constructeur d'un formulaire pour une personne
     */
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

    /**
     * Configure les attributs du formulaire
     */
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

    /**
     * Retourne le JPanel contenant un formulaire pour une personne
     * @return JPanel
     */
    public JPanel getJPanel(){
        return formulairePersonne;
    }

    /**
     * Retourne le nom contenu dans le champ nom
     * @return nom (String)
     */
    public String getNom(){
        return champNom.getText();
    }

    /**
     * Retourne le prenom contenu dans le champ prenom
     * @return prenom (String)
     */
    public String getPrenom(){
        return champPrenom.getText();
    }

    /**
     * Retourne la date de naissance contenue dans le champ date de naissance
     * @return dateDeNaissance (DateDeNaissance)
     */
    public DateDeNaissance getDateDeNaissance(){

        String[] temp =  champDateDeNaissance.getText().split("\\s+");
        return new DateDeNaissance( temp[0],temp[1],temp[2]);
    }

    /**
     * Retourne la fonction contenue dans le champ fonction
     * @return fonction (String)
     */
    public String getFonction(){
        return champFonction.getText();
    }

    /**
     * Change le contenu des champs d'entrée de texte
     * @param nom (String)
     * @param prenom (String)
     * @param dateDeNaissance (DateDeNaissance)
     * @param fonction (String)
     */
    public void setAtributs(String nom, String prenom, DateDeNaissance dateDeNaissance, String fonction){

        champNom.setText(nom);
        champPrenom.setText(prenom);
        champDateDeNaissance.setText( dateDeNaissance.getJour()+""+dateDeNaissance.getMois()+""+dateDeNaissance.getAnnee() );
        champFonction.setText(fonction);

    }
}
