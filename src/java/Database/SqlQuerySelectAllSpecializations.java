/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jarek
 */
public class SqlQuerySelectAllSpecializations implements I_Query 
{
    public class Out
    {
        public Integer id_specjalizacji;
        public String nazwa_specjalizacji;
        
        public void set(ResultSet res)
        {
            try
            {
                id_specjalizacji = res.getInt("id_specjalizacji");
                nazwa_specjalizacji = res.getString("nazwa_specjalizacji");
            }
            catch(SQLException ex)
            {
            
            }
        }
    };
    
    @Override
    public String getQuery()
    {
        return  "SELECT  " +
                "	id_specjalizacji, nazwa AS nazwa_specjalizacji " +
                "FROM " +
                "	specjalizacje " +
                "WHERE " +
                "	usunieta=FALSE " +
                "ORDER BY " +
                "	nazwa ASC; ";
    }
    
    @Override
    public void setResult(ResultSet res)
    {
        try
        {
            query_result = new ArrayList<Out>();
        
            while(res.next())
            {
                Out o = new Out();
                o.set(res);
                query_result.add(o);
            }
        }
        catch(SQLException ex)
        {
            
        }
    }
    
    public List<Out> query_result = null;
}