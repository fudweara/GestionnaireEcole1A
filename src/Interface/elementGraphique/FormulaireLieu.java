package Interface.elementGraphique;

import objetStockage.Horaire;
import objetStockage.Lieu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class FormulaireLieu extends JPanel{

    private JTextField champEmplacement;
    private JTextField champNombreAcces;

    private JComboBox listeDeroulanteHeureOuv;
    private JComboBox listeDeroulanteHeureFerm;

    private JComboBox listeDeroulanteMinuteOuv;
    private JComboBox listeDeroulanteMinuteFerm;

    private JButton bouttonValidation;

    public FormulaireLieu(JButton bouttonValidation){

        this.bouttonValidation = bouttonValidation;

        // Texte devant chaque champ de recherche
        JLabel texteEmplacement = new JLabel("Emplacement :");
        JLabel texteHoraireOuverture = new JLabel("Horaire ouverture :");
        JLabel texteHoraireFermeture = new JLabel("Horaire fermeture :");
        JLabel texteNombreAcces = new JLabel("Nombre accès :");

        // Vont stocker Texte + champ de recherche
        JPanel emplacementJPanel = new JPanel();
        JPanel horaireOuvertureJPanel = new JPanel();
        JPanel horaireFermetureJPanel = new JPanel();
        JPanel nombreAccesJPanel = new JPanel();

        configurationAttribut();

        emplacementJPanel.add(texteEmplacement);
        emplacementJPanel.add(champEmplacement);
        add(emplacementJPanel);

        horaireOuvertureJPanel.add(texteHoraireOuverture);

        horaireOuvertureJPanel.add(listeDeroulanteHeureOuv);
        horaireOuvertureJPanel.add(listeDeroulanteMinuteOuv);
        add(horaireOuvertureJPanel);

        horaireFermetureJPanel.add(texteHoraireFermeture);

        horaireFermetureJPanel.add(listeDeroulanteHeureFerm);
        horaireFermetureJPanel.add(listeDeroulanteMinuteFerm);
        add(horaireFermetureJPanel);

        nombreAccesJPanel.add(texteNombreAcces);
        nombreAccesJPanel.add(champNombreAcces);
        add(nombreAccesJPanel);
    }

    private void configurationAttribut(){

        actionChampTexte documentListener = new actionChampTexte();
        champEmplacement = new JTextField("");
        champEmplacement.getDocument().addDocumentListener(documentListener);

        listeDeroulanteHeureOuv = new JComboBox();
        listeDeroulanteHeureFerm = new JComboBox();

        listeDeroulanteMinuteOuv = new JComboBox();
        listeDeroulanteMinuteFerm = new JComboBox();

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

    public String getEmplacement() {
        return champEmplacement.getText();
    }

    public void setChampEmplacement(String Emplacement) {
        champEmplacement.setText(Emplacement);
    }

    public Horaire getHoraireOuverture() {
        return new Horaire(listeDeroulanteHeureOuv.getSelectedItem().toString() ,listeDeroulanteMinuteOuv.getSelectedItem().toString());
    }

    public void setChampHoraireOuverture(Horaire horaire) {
        listeDeroulanteHeureOuv.setSelectedIndex( Integer.parseInt( horaire.getHeures() ));
        listeDeroulanteMinuteOuv.setSelectedIndex( Integer.parseInt( horaire.getMinutes() )/5);

    }

    public Horaire getHoraireFermeture() {
        return new Horaire( listeDeroulanteHeureFerm.getSelectedItem().toString(),listeDeroulanteMinuteFerm.getSelectedItem().toString() );
    }

    public void setChampHoraireFermeture(Horaire horaire) {
        listeDeroulanteHeureFerm.setSelectedIndex( Integer.parseInt( horaire.getHeures() ));
        listeDeroulanteMinuteFerm.setSelectedIndex( Integer.parseInt( horaire.getMinutes() )/5);
    }

    public int getNombreAcces() {

        System.out.println("Tentative de récupération du nombre accès : "+champNombreAcces.getText());
        if(champNombreAcces.getText().compareTo("") == 0){
            return 0;
        }
        else{
            return Integer.parseInt( champNombreAcces.getText() );
        }

    }

    public void setChampNombreAcces(String nombreAcces) {
        champNombreAcces.setText(nombreAcces);
    }


    class actionChampTexte implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            activationBouttonDeValidation();
        }

        public void removeUpdate(DocumentEvent e) {
            activationBouttonDeValidation();
        }

        public void changedUpdate(DocumentEvent e) {
            activationBouttonDeValidation();
        }

        public void activationBouttonDeValidation(){

            if(champEmplacement.getText().equals("")  || champNombreAcces.getText().equals("") ){
                bouttonValidation.setEnabled(false);
            }
            else{
                bouttonValidation.setEnabled(true);
            }
        }
    }

    public void setAtributs(Lieu lieu){

        champEmplacement.setText(lieu.getEmplacement());

        listeDeroulanteHeureOuv.setSelectedItem(Integer.parseInt( lieu.getHoraireOuverture().getHeures() ));
        listeDeroulanteMinuteOuv.setSelectedItem(Integer.parseInt( lieu.getHoraireOuverture().getMinutes() ));

        listeDeroulanteHeureFerm.setSelectedItem(Integer.parseInt( lieu.getHoraireFermeture().getHeures() ));
        listeDeroulanteMinuteFerm.setSelectedItem(Integer.parseInt( lieu.getHoraireFermeture().getMinutes() ));

        champNombreAcces.setText( ""+lieu.getNombreAcces() );

    }
}
