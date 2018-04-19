package DAO;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe qui permet la connection avec la base de données
 */
class ConnectionDAO {

    Connection connection = null;
    PreparedStatement ps = null;

    /**
     * Constructeur qui connecte le programme à la base de données
     */
    public ConnectionDAO(){

        System.out.println("-------- Oracle JDBC Connection Test ------");

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {

            System.out.println(" Oracle JDBC Driver introuvable");
            e.printStackTrace();
            return;
        }

        System.out.println("Oracle JDBC Driver trouvé!");



        try {

            String PASS = "esigelec123";
            String LOGIN = "GestionnaireESIG";
            String URL = "jdbc:oracle:thin:@localhost:1521:XE";

            connection = DriverManager.getConnection(URL, LOGIN, PASS);

        } catch (SQLException e) {

            System.out.println("Erreur Connection ......");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            System.out.println("Connection à la base effectuée, modification possible!");
        }
        else {
            System.out.println("Erreur lors de la connection!");
        }


    }

}
