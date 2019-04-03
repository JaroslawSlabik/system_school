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
public class SqlQuerySelectAllGroups implements I_Query 
{
    public class Out
    {
        public Long id_grupy;
        public String nazwa_grupy;
        public String nazwa_specjalizacji;
        public Integer rok_akademicki;
        
        public void set(ResultSet res)
        {
            try
            {
                id_grupy = res.getLong("id_grupy");
                nazwa_grupy = res.getString("nazwa_grupy");
                nazwa_specjalizacji = res.getString("nazwa_specjalizacji");
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
                "	gr.id_grupy, gr.nazwa AS nazwa_grupy, sp.nazwa AS nazwa_specjalizacji, gr.rok_akademicki " +
                "FROM " +
                "	grupy gr " +
                "	LEFT JOIN specjalizacje sp ON sp.id_specjalizacji=gr.id_specjalizacji AND sp.usunieta = FALSE " +
                "WHERE  " +
                "	gr.usunieta=FALSE " +
                "ORDER BY  " +
                "	gr.rok_akademicki ASC, gr.nazwa ASC; ";
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
