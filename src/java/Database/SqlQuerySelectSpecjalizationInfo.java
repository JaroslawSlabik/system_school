/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jarek
 */
public class SqlQuerySelectSpecjalizationInfo implements I_Query 
{
    public Integer where_id_specjalizacji;
    
    public class Out
    {        
        public String nazwa_specjalizacji;
        
        public void set(ResultSet res)
        {
            try
            {
                nazwa_specjalizacji = ((res.getString("nazwa_specjalizacji") == null)? "" : res.getString("nazwa_specjalizacji"));
            }
            catch(SQLException ex)
            {
            
            }
        }
    };
    
    @Override
    public String getQuery()
    {
            return  "SELECT " +
                    "	sp.nazwa AS nazwa_specjalizacji " +
                    "FROM " +
                    "   specjalizacje sp " +
                    "WHERE  " +
                    "	sp.id_specjalizacji = " + where_id_specjalizacji + "; ";
    }
    
    @Override
    public void setResult(ResultSet res)
    {
        try
        {
            res.first();
            
            query_result = new Out();
            query_result.set(res);
        }
        catch(SQLException ex)
        {
            
        }
    }
    
    public Out query_result = null;   
}