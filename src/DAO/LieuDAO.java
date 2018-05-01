package DAO;

import java.sql.PreparedStatement;

public class LieuDAO {

    PreparedStatement ps = null;

    public LieuDAO(){
        ConnectionDAO.getInstance();
    }
}
