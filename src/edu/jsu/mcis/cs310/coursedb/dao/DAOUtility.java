package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import java.util.LinkedHashMap;

public class DAOUtility {
    
    public static final int TERMID_FA23 = 1;
    
    public static String getResultSetAsJson(ResultSet x, LinkedHashMap<String, String> jObject) {
        
        JsonArray r = new JsonArray();
        ResultSetMetaData RSMD = null;
        
        try {
        
            if (x != null) {

                // INSERT YOUR CODE HERE
                RSMD = x.getMetaData();
                int Col = RSMD.getColumnCount();
                
                while(x.next()){
                    
                    for(int i = 1; i<= Col;i++){
                        jObject.put(RSMD.getColumnLabel(i),x.getString(i));
                    }
                r.add(jObject);    
                       
                }

            }
            
        }
        catch (SQLException e) {
        }
        
        return Jsoner.serialize(r);
        
    }

    static String getResultSetAsJson(ResultSet R_Set) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
