package Interface.elementGraphique;

import objetStockage.Horaire;
import objetStockage.Lieu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Objects;

/**
 * Permet de rechercher un lieu grace à son nom dans la base de données
 */
public class FormulaireLieu extends JPanel{

    private JTextField champEmplacement;
    private JTextField champNombreAcces;

    private JComboBox<Integer> listeDeroulanteHeureOuv;
    private JComboBox<Integer> listeDeroulanteHeureFerm;

    private JComboBox<Integer> listeDeroulanteMinuteOuv;
    private JComboBox<Integer> listeDeroulanteMinuteFerm;

    private final JButton boutonValidation;


    /**
     * Constructeur qui crée l'interface et configure les attributs
     *
     * @param bouttonValidation Boutton qui va être activé/desactiver si les champs sont remplis ou non
     */
    public FormulaireLieu(JButton bouttonValidation){

        this.boutonValidation = bouttonValidation;

        // Texte devant chaque champ de recherche
        JLabel texteEmplacement = new JLabel("Emplacement :");
        JLabel texteHoraireOuverture = new JLabel("Horaire ouverture :");
        JLabel texteHoraireFermeture = new JLabel("Horaire fermeture :");
        JLabel texteNombreAcces = new JLabel("Nombre accès :");

        // Vont stocker Texte + champ à remplir
        JPanel emplacementJPanel = new JPanel();
        JPanel horaireOuvertureJPanel = new JPanel();
        JPanel horaireFermetureJPanel = new JPanel();
        JPanel nombreAccesJPanel = new JPanel();

        configurationAttribut();

        //Ajout dans chaque JPanel texte puis le/les champ(s) à remplir
        emplacementJPanel.add(texteEmplacement);
        emplacementJPanel.add(champEmplacement);

        horaireOuvertureJPanel.add(texteHoraireOuverture);
        horaireOuvertureJPanel.add(listeDeroulanteHeureOuv);
        horaireOuvertureJPanel.add(listeDeroulanteMinuteOuv);

        horaireFermetureJPanel.add(texteHoraireFermeture);
        horaireFermetureJPanel.add(listeDeroulanteHeureFerm);
        horaireFermetureJPanel.add(listeDeroulanteMinuteFerm);

        nombreAccesJPanel.add(texteNombreAcces);
        nombreAccesJPanel.add(champNombreAcces);

        //Ajoute les JPanel au formulaire
        add(emplacementJPanel);
        add(horaireOuvertureJPanel);
        add(horaireFermetureJPanel);
        add(nombreAccesJPanel);
    }


    /**
     * Configure les éléments qui composent le formulaire
     */
    private void configurationAttribut(){

        actionChampTexte documentListener = new actionChampTexte();
        champEmplacement = new JTextField("");
        champEmplacement.getDocument().addDocumentListener(documentListener);

        listeDeroulanteHeureOuv = new JComboBox<>();
        listeDeroulanteHeureFerm = new JComboBox<>();

        listeDeroulanteMinuteOuv = new JComboBox<>();
        listeDeroulanteMinuteFerm = new JComboBox<>();

        listeDeroulanteHeureOuv.setPreferredSize(new Dimension(50, 20));
        listeDeroulanteHeureFerm.setPreferredSize(new Dimension(50, 20));
        listeDeroulanteMinuteOuv.setPreferredSize(new Dimension(50, 20));
        listeDeroulanteMinuteFerm.setPreferredSize(new Dimension(50, 20));

        for(int i=0 ; i<60 ; i=i+5){
            listeDeroulanteMinuteFerm.addItem(i);
            listeDeroulanteMinuteOuv.addItem(i);
        }
        for(int i=0 ; i<24 ; i++){
            listeDeroulanteHeureOuv.addItem(i);
            listeDeroulanteHeureFerm.addItem(i);
        }

        champNombreAcces = new JTextField("");
        champNombreAcces.getDocument().addDocumentListener(documentListener);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        champEmplacement.setPreferredSize(new Dimension(150, 30));
        champNombreAcces.setPreferredSize(new Dimension(50, 30));

    }


    /**
     * Retourne l'emplacement entré dans le formulaire
     *
     * @return Emplacement du lieu
     */
    public String getEmplacement() {
        return champEmplacement.getText();
    }


    /**
     * Retourne l'horaire d'ouverture qui est entré dans le formulaire
     *
     * @return Horaire pour l'ouverture
     */
    public Horaire getHoraireOuverture() {
        return new Horaire(Objects.requireNonNull(listeDeroulanteHeureOuv.getSelectedItem()).toString() ,Objects.requireNonNull(listeDeroulanteMinuteOuv.getSelectedItem()).toString());
    }


    /**
     * Retourne l'horaire la fermeture qui est entré dans le formulaire
     *
     * @return Horaire pour la fermture
     */
    public Horaire getHoraireFermeture() {
        return new Horaire( Objects.requireNonNull(listeDeroulanteHeureFerm.getSelectedItem()).toString(),Objects.requireNonNull(listeDeroulanteMinuteFerm.getSelectedItem()).toString() );
    }


    /**
     * Retourne le nombre d'accès entré dans le formulaire
     *
     * @return Nombre d'accès
     */
    public int getNombreAcces() {

        System.out.println("Tentative de récupération du nombre accès : "+champNombreAcces.getText());
        if(champNombreAcces.getText().compareTo("") == 0){
            return 0;
        }
        else{
            return Integer.parseInt( champNombreAcces.getText() );
        }

    }


    /**
     * Classe pour l'écoute des champs de texte
     */
    class actionChampTexte implements DocumentListener {


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
         *Désactive/Active le bouton si il les champs de texte sont vide ou non
         */
        void activationBoutonDeValidation(){

            if(champEmplacement.getText().equals("")  || champNombreAcces.getText().equals("") ){
                boutonValidation.setEnabled(false);
            }
            else{
                boutonValidation.setEnabled(true);
            }
        }
    }


    /**
     * Remplis les champs du fonmulaire par les paramètres du lieu
     *
     * @param lieu Lieu pour lequel on veut mettre les paramètres dans les champs
     */
    void setAtributs(Lieu lieu){

        champEmplacement.setText(lieu.getEmplacement());

        listeDeroulanteHeureOuv.setSelectedItem(Integer.parseInt( lieu.getHoraireOuverture().getHeures() ));
        listeDeroulanteMinuteOuv.setSelectedItem(Integer.parseInt( lieu.getHoraireOuverture().getMinutes() ));

        listeDeroulanteHeureFerm.setSelectedItem(Integer.parseInt( lieu.getHoraireFermeture().getHeures() ));
        listeDeroulanteMinuteFerm.setSelectedItem(Integer.parseInt( lieu.getHoraireFermeture().getMinutes() ));

        champNombreAcces.setText( ""+lieu.getNombreAcces() );

    }
}
