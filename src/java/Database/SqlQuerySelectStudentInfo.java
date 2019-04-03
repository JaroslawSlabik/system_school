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
public class SqlQuerySelectStudentInfo implements I_Query 
{
    public Long where_id_studenta;
    
    public class Out
    {        
        public String imie;
        public String nazwisko;
        public String miasto;
        public String data_utworzenia;
        public String nazwa_grupy;
        public Long id_grupy;
        public String nazwa_specjalizacji;
        public Integer id_specjalizacji;
        public Integer rok_akademicki;
        
        public void set(ResultSet res)
        {
            try
            {
                imie = ((res.getString("imie") == null)? "" : res.getString("imie"));
                nazwisko = ((res.getString("nazwisko") == null)? "" : res.getString("nazwisko"));
                miasto = ((res.getString("miasto") == null)? "" : res.getString("miasto"));
                data_utworzenia = ((res.getString("data_utworzenia") == null)? "" : res.getString("data_utworzenia"));
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
        return "SELECT " +
               "	st.imie, st.nazwisko, st.miasto, st.data_utworzenia,  gr.nazwa AS nazwa_grupy, gr.id_grupy, sp.nazwa AS nazwa_specjalizacji, sp.id_specjalizacji, gr.rok_akademicki " +
                "FROM " +
                "	studenci st " +
                "	LEFT JOIN student_grupa st_gr ON st_gr.id_studenta=st.id_studenta AND st_gr.usunieta=FALSE AND st.usuniety=FALSE AND st.id_studenta=" + where_id_studenta + 
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
