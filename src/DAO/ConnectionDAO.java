package DAO;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe qui permet la connection avec la base de données
 */
class ConnectionDAO {

    private static Connection connection;

    /** Constructeur privé */
    public static Connection getInstance(){

        if(connection == null){
            try {

                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException e) {

                System.out.println(" Oracle JDBC Driver introuvable");
                e.printStackTrace();
            }
            try {

                String PASS = "esigelec123";
                String LOGIN = "GestionnaireESIG";
                String URL = "jdbc:oracle:thin:@localhost:1521:XE";

                connection = DriverManager.getConnection(URL, LOGIN, PASS);

            } catch (SQLException e) {

                System.out.println("Erreur Connection ......");
                e.printStackTrace();

            }
        }
        return connection;
    }
}

/**
 *
 try {
 if (ConnectionDAO.getInstance() != null)
 ConnectionDAO.getInstance().close();
 } catch (Exception ignore) {
 }
 */
