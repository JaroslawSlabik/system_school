/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.ResultSet;

/**
 *
 * @author Jarek
 */
public class SqlQueryModificationGroup implements I_Query 
{
    public Long where_id_grupy;
    
    public String set_nazwa;
    public Integer set_rok_akademicki;
    public Integer set_id_specjalizacji;
    
    @Override
    public String getQuery()
    {
        return  "UPDATE " + //modyfikacja grupy
                "   grupy " +
                "SET " +
                "   nazwa='" + set_nazwa + "', " +
                "   rok_akademicki=" + set_rok_akademicki + ", " +
                "   id_specjalizacji="+ set_id_specjalizacji +" "+
                "WHERE " +
                "   id_studenta=" + where_id_grupy.toString() + "; ";
    }
    
    @Override
    public void setResult(ResultSet res)
    {
    }
}
