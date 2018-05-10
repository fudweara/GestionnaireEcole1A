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
import objetStockage.Personne;


/**
 * Classe qui représente l'interface de Création d'une personne avec ses composants (JPanel,JTextField...)
 */
public class FormulairePersonne extends JPanel{

    private JTextField champNom;
    private JTextField champPrenom;
    private JTextField champFonction ;

    private  JDatePickerImpl datePicker;
    private  UtilDateModel model;

    private final JButton boutonCreer;


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
        this.boutonCreer=bouttonCreer;

        configurationAtribut();

        // Ajout champ + texte pour nom et prenom
        nomJPanel.add(texteNom);
        nomJPanel.add(champNom);


        prenomJPanel.add(textePrenom);
        prenomJPanel.add(champPrenom);


        // Ajout du texte et calendrier pour la date de naissance
        dateDeNaissanceJPanel.add(textedateDeNaissance);
        dateDeNaissanceJPanel.add(datePicker);


        //Ajout champ + texte pour la fonction
        fonctionJPanel.add(texteFonction);
        fonctionJPanel.add(champFonction);

        //Ajout des JPanel au formulaire
        add(nomJPanel);
        add(prenomJPanel);
        add(dateDeNaissanceJPanel);
        add(fonctionJPanel);
    }


    /**
     * Configure les attributs du formulaire
     */
    private void configurationAtribut(){

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        ecouteChampsTexte documentListener = new ecouteChampsTexte();
        boutonCreer.setEnabled(false);

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
     *
     * @return Nom rempli dans le formualire
     */
    public String getNom(){


        return champNom.getText();
    }


    /**
     * Retourne le prenom contenu dans le champ prenom
     *
     * @return Prenom rempli dans le formulaire
     */
    public String getPrenom(){
        return champPrenom.getText();
    }


    /**
     * Retourne la date de naissance contenue dans le champ date de naissance
     *
     * @return Date de naissance remplie dans le formulaire
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
     *
     * @return Fonction remplie dans le formulaire
     */
    public String getFonction(){
        return champFonction.getText();
    }


    /**
     * Affiche des attributs de la personne dans les champs du formulaire
     *
     * @param personne Personne dont on veut afficher les attributs dans les champs
     */
    void setAtributs(Personne personne){

        champNom.setText( personne.getNom() );
        champPrenom.setText( personne.getPrenom() );
        model.setDate(Integer.parseInt( personne.getDateNaissance().getAnnee() ), Integer.parseInt( personne.getDateNaissance().getMois() )-1, Integer.parseInt( personne.getDateNaissance().getJour() ));
        model.setSelected(true);
        champFonction.setText(personne.getFonction() );
    }


    /**
     * Activation/Désactivation du bouton valider si les champs sont vides. Activé quand on modifie un champ.
     */
    class ecouteChampsTexte implements DocumentListener {


        @Override
        public void insertUpdate(DocumentEvent e) {
            activationBoutonDeValidation();
        }


        @Override
        public void removeUpdate(DocumentEvent e) {
            activationBoutonDeValidation();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            activationBoutonDeValidation();
        }


        /**
         * Désactive/Active le bouton si il les champs de texte sont vide ou non
         */
        void activationBoutonDeValidation(){

            if(champPrenom.getText().equals("")  || champNom.getText().equals("") || champFonction.getText().equals("") ){
                boutonCreer.setEnabled(false);
            }
            else{
                boutonCreer.setEnabled(true);
            }
        }
    }
}
