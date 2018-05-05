package Interface.elementGraphique;

import objetStockage.DateDeNaissance;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;


/**
 * Classe qui représente l'interface de Création d'une personne avec ses composants (JPanel,JTextField...)
 */
public class FormulairePersonne{

    private JPanel formulairePersonne;
    private JTextField champNom;
    private JTextField champPrenom;
    private JTextField champFonction ;

    private JDatePickerImpl datePicker;
    private UtilDateModel model;

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
        //dateDeNaissanceJPanel.add(champDateDeNaissance);

        model=new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);
        datePicker.setBounds(220,350,120,30);
        dateDeNaissanceJPanel.add(datePicker);
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
        champFonction = new JTextField("");

        formulairePersonne.setLayout(new BoxLayout(formulairePersonne, BoxLayout.PAGE_AXIS));

        champNom.setPreferredSize(new Dimension(150, 30));
        champPrenom.setPreferredSize(new Dimension(150, 30));
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

        Date selectedDate = (Date) datePicker.getModel().getValue();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = simpleDateFormat.format(selectedDate);

        String[] temp =  date.split("-");

        System.out.println("Date : "+temp[0]+" "+temp[1]+" "+temp[2]);
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
        System.out.println(Integer.parseInt( dateDeNaissance.getMois()));
        model.setDate(Integer.parseInt( dateDeNaissance.getAnnee() ), Integer.parseInt( dateDeNaissance.getMois() )-1, Integer.parseInt( dateDeNaissance.getJour() ));
        model.setSelected(true);
        champFonction.setText(fonction);

    }

}
