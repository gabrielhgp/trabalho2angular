
package br.com.reges.aula12backend.rdn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
    
    private String URL = "jdbc:mysql://localhost:3306/db_locadora_reges";    
    private String USER = "root";    
    private String PASSWORD = "G@h30041996";
    
    public Connection getConnection()
    {
        try{
        
            return DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
            
        }
        catch(SQLException ex){
        
       
             throw new RuntimeException(ex);
        }
    }

    
    
  
    
     
    
    
}
