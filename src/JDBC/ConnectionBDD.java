package JDBC;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ConnectionBDD {

    private String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private String LOGIN = "Gestionnaire";  //exemple BDD1
    private String PASS = "vic1997";   //exemple BDD1

    Connection connection = null;
    PreparedStatement ps = null;


    public ConnectionBDD(){

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
