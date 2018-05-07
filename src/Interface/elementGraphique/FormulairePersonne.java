package Interface.elementGraphique;

import objetStockage.DateDeNaissance;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;


/**
 * Classe qui représente l'interface de Création d'une personne avec ses composants (JPanel,JTextField...)
 */
public class FormulairePersonne extends JPanel{

    private JTextField champNom;
    private JTextField champPrenom;
    private JTextField champFonction ;

    private  JDatePickerImpl datePicker;
    private  UtilDateModel model;

    private final JButton bouttonCreer;


    /**
     * Constructeur d'un formulaire pour une personne
     */
    public FormulairePersonne(JButton bouttonCreer){

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
        this.bouttonCreer=bouttonCreer;

        configurationAtribut();

        // Ajout champ + texte pour nom et prenom
        nomJPanel.add(texteNom);
        nomJPanel.add(champNom);
        add(nomJPanel);

        prenomJPanel.add(textePrenom);
        prenomJPanel.add(champPrenom);
        add(prenomJPanel);

        // Ajout du texte et calendrier pour la date de naissance
        dateDeNaissanceJPanel.add(textedateDeNaissance);
        dateDeNaissanceJPanel.add(datePicker);
        add(dateDeNaissanceJPanel);

        //Ajout champ + texte pour la fonction
        fonctionJPanel.add(texteFonction);
        fonctionJPanel.add(champFonction);
        add(fonctionJPanel);
    }


    /**
     * Configure les attributs du formulaire
     */
    private void configurationAtribut(){

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        ecouteChampsTexte documentListener = new ecouteChampsTexte();
        bouttonCreer.setEnabled(false);

        champNom = new JTextField("");
        champNom.setPreferredSize(new Dimension(150, 30));
        champNom.getDocument().addDocumentListener(documentListener);

        champPrenom = new JTextField("");
        champPrenom.setPreferredSize(new Dimension(150, 30));
        champPrenom.getDocument().addDocumentListener(documentListener);

        model=new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);
        datePicker.setBounds(220,350,120,30);
        datePicker.getModel().setDate(2000,1,1);
        model.setDate(2000,0,1);
        model.setSelected(true);


        champFonction = new JTextField("");
        champFonction.setPreferredSize(new Dimension(150, 30));
        champFonction.getDocument().addDocumentListener(documentListener);
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


    /**
     * Activation/Désactivation du boutton valider si les champs sont vides. Activé quand on modifie un champ.
     */
    class ecouteChampsTexte implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            activationBouttonDeValidation();
        }

        public void removeUpdate(DocumentEvent e) {
            activationBouttonDeValidation();
        }

        public void changedUpdate(DocumentEvent e) {
            activationBouttonDeValidation();
        }

        void activationBouttonDeValidation(){

            if(champPrenom.getText().equals("")  || champNom.getText().equals("") || champFonction.getText().equals("") ){
                bouttonCreer.setEnabled(false);
            }
            else{
                bouttonCreer.setEnabled(true);
            }
        }
    }
}
