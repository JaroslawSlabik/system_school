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

public class SqlQuerySelectGroupInfo implements I_Query 
{
    public Long where_id_grupy;
    
    public class Out
    {        
        public String nazwa_grupy;
        public Long id_grupy;
        public String nazwa_specjalizacji;
        public Integer id_specjalizacji;
        public Integer rok_akademicki;
        
        public void set(ResultSet res)
        {
            try
            {
                nazwa_grupy = ((res.getString("nazwa_grupy") == null)? "" : res.getString("nazwa_grupy"));
                id_grupy = res.getLong("id_grupy");
                nazwa_specjalizacji = ((res.getString("nazwa_specjalizacji") == null)? "" : res.getString("nazwa_specjalizacji"));
                id_specjalizacji = res.getInt("id_specjalizacji");
                rok_akademicki = res.getInt("rok_akademicki");
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
                "	gr.id_grupy, gr.nazwa AS nazwa_grupy, sp.id_specjalizacji, sp.nazwa AS nazwa_specjalizacji, gr.rok_akademicki " +
                "FROM " +
                "	grupy gr " +
                "	INNER JOIN specjalizacje sp ON sp.id_specjalizacji=gr.id_specjalizacji AND sp.usunieta = FALSE " +
                "WHERE  " +
                "	gr.id_grupy="+ where_id_grupy + "; ";
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