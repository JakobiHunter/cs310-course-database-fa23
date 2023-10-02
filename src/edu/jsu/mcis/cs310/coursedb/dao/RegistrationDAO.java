package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationDAO {
    
    // INSERT YOUR CODE HERE
    
    public static final String QUERY_CREATE = "Place";
    
    private static final String QUERY_DELETE_MANY = "Erase";
    
    private static final String QUERY_LIST = "Select";
    
    private static final String QUERY_DELETE_ONE = "Erase";
    
    
    
    

           
    
    private final DAOFactory daoFactory;
    
    RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public boolean create(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement prep_state = null;
        ResultSet R_Set = null;
        
        try {
            
            Connection x = daoFactory.getConnection();
            
            if (x.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                prep_state = x.prepareStatement(QUERY_CREATE);
                prep_state.setInt(1, studentid);
                prep_state.setInt(2, termid);
                prep_state.setInt(3, crn);
                
                int updateCount = prep_state.executeUpdate();
                
                if(updateCount > 0){
                    
                    result = true;
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

    public boolean delete(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement prep_state = null;
        
        try {
            
            Connection x = daoFactory.getConnection();
            
            if (x.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                prep_state = x.prepareStatement(QUERY_DELETE_ONE);
                prep_state.setInt(1, studentid);
                prep_state.setInt(2, termid);
                prep_state.setInt(3, crn);
                
                int updateCount = prep_state.executeUpdate();
                
                if(updateCount > 0){
                    result = true;
                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (prep_state != null) { try { prep_state.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
    public boolean delete(int studentid, int termid) {
        
        boolean result = false;
        
        PreparedStatement prep_state = null;
        
        try {
            
            Connection x = daoFactory.getConnection();
            
            if (x.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                prep_state = x.prepareStatement(QUERY_DELETE_MANY);
                prep_state.setInt(1, studentid);
                prep_state.setInt(2, termid);
                
                int updateCount = prep_state.executeUpdate();
                
                if (updateCount > 0){
                    result = true;
                }
                
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (prep_state != null) { try { prep_state.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public String list(int studentid, int termid) {
        
        String result = "[]";
        
        PreparedStatement prep_state = null;
        ResultSet R_Set = null;
        ResultSetMetaData RSMD = null;
        
        try {
            
            Connection x = daoFactory.getConnection();
            
            if (x.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                prep_state = x.prepareStatement(QUERY_LIST);
                prep_state.setInt(1, studentid);
                prep_state.setInt(2, termid);
                prep_state.setString(3, "crn");
                
                if(prep_state.execute()){
                    R_Set = prep_state.getResultSet();
                    result = DAOUtility.getResultSetAsJson(R_Set);
                }
                
            }
            
        }
        
        catch (SQLException e) { e.printStackTrace(); }
        
        finally {
            
            if (R_Set != null) { try { R_Set.close(); } catch (SQLException e) {} }
            if (prep_state != null) { try { prep_state.close(); } catch (SQLException e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
}
