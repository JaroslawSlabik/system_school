/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Jarek
 */
public class SqlQuerySelectAllStudents implements I_Query 
{
    public class Out
    {
        public Long id_studenta;
        public String imie;
        public String nazwisko;
        public String nazwa_grupy;
        public String nazwa_specjalizacji;
        public Integer rok_akademicki;
        
        public void set(ResultSet res)
        {
            try
            {
                id_studenta = res.getLong("id_studenta");
                imie = ((res.getString("imie") == null)? "" : res.getString("imie"));
                nazwisko = ((res.getString("nazwisko") == null)? "" : res.getString("nazwisko"));
                nazwa_grupy = ((res.getString("nazwa_grupy") == null)? "" : res.getString("nazwa_grupy"));
                nazwa_specjalizacji = ((res.getString("nazwa_specjalizacji") == null)? "" : res.getString("nazwa_specjalizacji"));
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
        return "SELECT " +
               "	st.id_studenta, st.imie, st.nazwisko, gr.nazwa AS nazwa_grupy, sp.nazwa AS nazwa_specjalizacji, gr.rok_akademicki " +
                "FROM " +
                "	studenci st " +
                "	LEFT JOIN student_grupa st_gr ON st_gr.id_studenta=st.id_studenta AND st_gr.usunieta=FALSE AND st.usuniety=FALSE " +
                "	INNER JOIN grupy gr ON gr.id_grupy=st_gr.id_grupy AND gr.usunieta=FALSE " +
                "	LEFT JOIN specjalizacje sp ON sp.id_specjalizacji=gr.id_specjalizacji AND sp.usunieta = FALSE " +
                "ORDER BY " +
                "	st.nazwisko ASC; ";
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
