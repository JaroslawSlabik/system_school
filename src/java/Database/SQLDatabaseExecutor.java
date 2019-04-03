/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;


/**
 *
 * @author Jarek
 */

public class SQLDatabaseExecutor 
{
    private Connection m_db_connection = null;
    
    private String m_database_server_url = "jdbc:mysql://localhost:3306/"; 
    private String m_database_name = "db_system_uczelnia";
    /*NOTE: potrzebuję 'allowMultiQueries=true' bo wykonuję kilka zapytań na jednej instancji EXECUTE. W innym przypadku musiałbym robić procedury w MySQL*/
    private String m_allow_multi_queries = "?allowMultiQueries=true";
    private String m_database_user = "root";
    private String m_database_password = "";
    
    private boolean initConnection()
    {
        try{
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            m_db_connection = DriverManager.getConnection(m_database_server_url + m_database_name + m_allow_multi_queries, m_database_user, m_database_password);
            return true;
        }
        catch(SQLException ex)
        {
            System.err.println("SQL ERROR: " + ex.getMessage());
            return false;
        }
        catch(ClassNotFoundException ex)
        {
            System.err.println("DRIVER ERROR: " + ex.getMessage());
            return false;
        }
        catch(Exception ex)
        {
            System.err.println("ERROR: " + ex.getMessage());
            return false;
        }
    }
    
    private boolean destroyConnection()
    {
        try
        {
            if(m_db_connection != null && !m_db_connection.isClosed())
            {
                m_db_connection.close();
            } 
            return true;
        }
        catch(SQLException ex)
        {
            System.err.println("ERROR: " + ex.getMessage());
            return false;
        }
    }
    
    public SQLDatabaseExecutor()
    {
        if(!initConnection())
        {
            System.err.println("initConnection - bardzo źle");
        }
    }
    
    protected void finalize( ) throws Throwable
    {
        try 
        {
            if(!destroyConnection())
            {
                System.err.println("destroyConnection - bardzo źle");
            }
        } 
        finally 
        {
            super.finalize();
        }
    }
    
    public boolean executeQuery(I_Query query)
    {
        try
        {
            if(m_db_connection == null || m_db_connection.isClosed())
                return false;
        
            Statement st = m_db_connection.createStatement();
            
            st.execute(query.getQuery());
            
            query.setResult(st.getResultSet());
            
            st.close();
            return true;
        }
        catch(SQLException ex)
        {
            System.err.println("QUERY: " + query.getQuery());
            System.err.println("ERROR: " + ex.getMessage());
            return false;
        }
    }
    
}
