package Interface;

import DAO.IdentificationDAO;
import objetStockage.MotDePasse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Fenetre qui permet l'authentification de la personne autorisée à effectuer la gestion
 */

public class FenetreIdentification extends JFrame {

    private final JPanel fenetre = new JPanel();
    private final JPanel conteneurChampsARemplir = new JPanel();
    private final JTextField champIdentifiant = new JTextField("");
    private final JPasswordField champMDP = new JPasswordField("");
    private final JButton bouton = new JButton("Connexion");


    /**
     * Constructeur de la fenetre d'authentification qui crée tous les composants graphiques
     */

    public FenetreIdentification(){

        creationDeLaFenetre();
        ajoutDesElementsGraphiques();

        bouton.addActionListener(new ecouteBouton());

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
        JLabel labelChampIdnetifiant = new JLabel("Identifiant");
        JLabel labelChampMDP = new JLabel("Mot de passe");

        fenetre.setLayout(new BorderLayout());


        champIdentifiant.setFont(police);
        champIdentifiant.setPreferredSize(new Dimension(150, 30));
        champIdentifiant.setForeground(Color.BLACK);

        champMDP.setFont(police);
        champMDP.setPreferredSize(new Dimension(150, 30));
        champMDP.setForeground(Color.BLACK);

        conteneurChampsARemplir.add(labelChampIdnetifiant);
        conteneurChampsARemplir.add(champIdentifiant);
        conteneurChampsARemplir.add(labelChampMDP);
        conteneurChampsARemplir.add(champMDP);

        fenetre.add(conteneurChampsARemplir);
        conteneurChampsARemplir.setBackground(Color.GRAY);

        fenetre.add(bouton, BorderLayout.SOUTH);

    }

    /**
     * Fonction d'écoute du bouton valider qui va vérifier si il y a appuie sur le bouton 'valider' :
     *  - identifiant/MDP ok : ouverture fenetre du gestionnaire
     *  - identifiant/MDP incorrecte : message d'erreur et l'affichage de kla fenetre d'authentification continue
     */

    class ecouteBouton implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            IdentificationDAO identificationDAO = new IdentificationDAO();

            /*
            Pour ajouter avoir un hash pour un nouvel identifiant


             */

            try {
                if( MotDePasse.verification(String.valueOf( champMDP.getPassword() ), identificationDAO.recuperationMotDePasse( champIdentifiant.getText()) )) {
                    System.out.println("Connection OK");

                    setVisible(false);
                    dispose(); // Destruction JFrame Object

                    Fenetre fenetre = new Fenetre();

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
