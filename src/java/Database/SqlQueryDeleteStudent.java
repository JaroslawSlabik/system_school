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
public class SqlQueryDeleteStudent implements I_Query 
{
    public Long where_id_studenta;
    
    @Override
    public String getQuery()
    {
        return  "UPDATE " + //usuniecie studenta
                "   studenci " +
                "SET " +
                "   usuniety=TRUE " +
                "WHERE " +
                "   id_studenta=" + where_id_studenta.toString() + "; " +
                "" +
                "UPDATE " + // usuniecie polaczenia grupa-student
                "   student_grupa " +
                "SET " +
                "   usunieta=TRUE " +
                "WHERE " +
                "   id_studenta="+ where_id_studenta.toString() + "; "; 
    }
    
    @Override
    public void setResult(ResultSet res)
    {
    }
}