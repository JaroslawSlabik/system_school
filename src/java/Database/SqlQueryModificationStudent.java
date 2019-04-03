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
public class SqlQueryModificationStudent implements I_Query 
{
    public Long where_id_studenta;
    
    public String set_imie;
    public String set_nazwisko;
    public String set_miasto;
    public Long set_id_grupy;
    
    @Override
    public String getQuery()
    {
        return  "UPDATE " + //modyfikacja studenta
                "   studenci " +
                "SET " +
                "   imie='" + set_imie + "', " +
                "   nazwisko='" + set_nazwisko + "', " +
                "   miasto='" + set_miasto + "' " +
                "WHERE " +
                "   id_studenta=" + where_id_studenta+ "; " +
                "" +
                "UPDATE " + // modyfikacja przypisania polaczenia grupa-student
                "   student_grupa " +
                "SET " +
                "   id_grupy=" + set_id_grupy+ " " +
                "WHERE " +
                "   id_studenta="+ where_id_studenta+ "; "; 
    }
    
    @Override
    public void setResult(ResultSet res)
    {
    }
}