package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {
    
    private static final String QUERY_FIND = "SELECT";
    
    private final DAOFactory daoFactory;
    
    StudentDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public int find(String username) {
        
        int i_d_ = 0;
        
        try {
            
            Connection x = daoFactory.getConnection();
            
            PreparedStatement Prep_State = x.prepareStatement(QUERY_FIND);
            Prep_State.setString(1, username);
            
            boolean results = Prep_State.execute();
            
            if (results ) {
                
                ResultSet resultset = Prep_State.getResultSet();
                
                if (resultset.next())
                    
                    i_d_ = resultset.getInt("id");
                
            }
            
        }
        catch (SQLException e) { e.printStackTrace(); }
        
        return i_d_;
        
    }
        
}