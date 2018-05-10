package Interface;

import DAO.IdentificationDAO;
import objetStockage.MotDePasse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Fenetre qui permet l'authentification de la personne autorisée à effectuer la gestion
 */
public class FenetreIdentification extends JFrame {

    private final JPanel fenetre;

    private final JPanel conteneurChampsARemplir;
    private final JTextField champIdentifiant;
    private final JPasswordField champMDP;

    private final JButton bouton;
    private final JPanel panelBouton;


    /**
     * Constructeur de la fenetre d'authentification qui crée tous les composants graphiques
     */
    public FenetreIdentification(){

        bouton = new JButton("Connexion");
        champMDP = new JPasswordField("");
        fenetre = new JPanel();
        conteneurChampsARemplir = new JPanel();
        champIdentifiant = new JTextField("");
        panelBouton = new JPanel();

        creationDeLaFenetre();
        ajoutDesElementsGraphiques();

        bouton.addActionListener( new ecouteBouton() );
        champMDP.addKeyListener( new ecouteChampMDP() );

        this.setContentPane(fenetre);
        this.setVisible(true);
    }


    /**
     * Attribution des paramètres de la JFrame
     */
    private void creationDeLaFenetre(){

        this.setTitle("Identification");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }


    /**
     * Ajout des éléments graphiques sur la fenetre d'identification
     */
    private void ajoutDesElementsGraphiques(){

        Font police = new Font("Arial", Font.BOLD, 14);
        JLabel labelChampIdentifiant = new JLabel("Identifiant :");
        JLabel labelChampMDP = new JLabel("Mot de passe :");

        fenetre.setLayout( new BorderLayout() );

        champIdentifiant.setFont(police);
        champIdentifiant.setForeground(Color.BLACK);
        champMDP.setFont(police);
        champMDP.setForeground(Color.BLACK);

        // Configuration du panel qui contient texte + champs entrées
        conteneurChampsARemplir.setLayout(new BoxLayout(conteneurChampsARemplir, BoxLayout.PAGE_AXIS));
        conteneurChampsARemplir.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        conteneurChampsARemplir.add(labelChampIdentifiant);
        conteneurChampsARemplir.add(Box.createRigidArea(new Dimension(0, 5)));
        conteneurChampsARemplir.add(champIdentifiant);

        conteneurChampsARemplir.add(Box.createRigidArea(new Dimension(0, 10)));
        conteneurChampsARemplir.add(labelChampMDP);
        conteneurChampsARemplir.add(Box.createRigidArea(new Dimension(0, 5)));
        conteneurChampsARemplir.add(champMDP);
        conteneurChampsARemplir.add(Box.createRigidArea(new Dimension(0, 30)));

        // Configuration du panel contenant le boutton qui se situe en bas de la fenetre
        panelBouton.setLayout(new BorderLayout() );
        panelBouton.setBorder(BorderFactory.createEmptyBorder(0, 60, 40, 60));
        panelBouton.add(bouton,BorderLayout.CENTER);

        // Ajout des JPanel à la Fenetre
        fenetre.add(conteneurChampsARemplir,BorderLayout.CENTER);
        fenetre.add(panelBouton, BorderLayout.PAGE_END);
    }


    /**
     * Fonction d'écoute pour le champ de texte "mot de passe' : Si on appuie sur entrée en étant dans celui-ci on déclanche le boutton connexion
     */
    class ecouteChampMDP implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        /**
         * Lors de l'appuie sur une touche, si c'est entrée alors on active le boutton Connexion
         *
         * @param e Evenement
         */
        public void keyPressed(KeyEvent e) {

            int keyCode = e.getKeyCode();

            //
            if(keyCode == 10 ){
                bouton.doClick();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }


    /**
     * Fonction d'écoute du bouton valider
     */
    class ecouteBouton implements ActionListener {

        /**
         * Vérifie si il y a appuie sur le bouton 'valider' :
         * - identifiant/MDP ok : ouverture fenetre du gestionnaire
         * - identifiant/MDP incorrecte : message d'erreur et l'affichage de la fenetre d'authentification continue
         *
         * @param e Evenement
         */
        public void actionPerformed(ActionEvent e) {

            IdentificationDAO identificationDAO = new IdentificationDAO();

            // Pour ajouter avoir un hash pour un nouvel identifiant
            if ( String.valueOf( champMDP.getPassword() ).equals("") || champIdentifiant.getText().equals("") ){
                System.out.println("Remplissez les champs");
                JOptionPane.showMessageDialog( null, "Remplissez les champs !","Erreur", JOptionPane.WARNING_MESSAGE);
            }
            else{
                try {
                    if( MotDePasse.verification(String.valueOf( champMDP.getPassword() ), identificationDAO.recuperationMotDePasse( champIdentifiant.getText()) )) {
                        System.out.println("Connection OK");

                        setVisible(false);
                        dispose(); // Destruction JFrame Object

                        new Fenetre();

                    } else{
                        System.out.println("Erreur combinaison login/MDP");
                        JOptionPane.showMessageDialog( null, "Combinaison login/MDP incorrecte","Erreur", JOptionPane.WARNING_MESSAGE);
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
