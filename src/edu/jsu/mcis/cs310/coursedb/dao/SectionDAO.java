package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import com.github.cliftonlabs.json_simple.*;

public class SectionDAO {
    
    // INSERT YOUR CODE HERE
    private static final String QUERY_FIND = "SELECT * FROM"
            + "section WHERE subjectid = ? AND num = ? ORDER BY ?";
  
    
    private final DAOFactory daoFactory;
    
    SectionDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public String find(int termid, String subjectid, String num) {
        
        String result = "[]";
        
        PreparedStatement prep_state = null;
        ResultSet R_Set = null;
        ResultSetMetaData RSMD = null;
        
        try {
            
            Connection x = daoFactory.getConnection();
            
            if (x.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                prep_state = x.prepareStatement(QUERY_FIND);
                prep_state.setNString(1,subjectid);
                prep_state.setNString(2,num);
                prep_state.setInt(3, termid);
                
                boolean results = prep_state.execute();
                
                if(results){
                    R_Set = prep_state.getResultSet();
                    
                    result = DAOUtility.getResultSetAsJson(R_Set);
                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (R_Set != null) { try { R_Set.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (prep_state != null) { try { prep_state.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
}