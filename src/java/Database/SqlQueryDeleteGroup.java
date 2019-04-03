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
public class SqlQueryDeleteGroup implements I_Query 
{
    public Long where_id_grupy;
    
    @Override
    public String getQuery()
    {
        return  "UPDATE " +  //Usuniecie grupy
                "   grupy " +
                "SET " +
                "   usunieta=TRUE " +
                "WHERE " +
                "   id_grupy=" + where_id_grupy + "; " +
                "" +
                "UPDATE " + // usuniecie polaczenia grupa-student
                "   student_grupa " +
                "SET " +
                "   usunieta=TRUE " +
                "WHERE " +
                "   id_grupy="+ where_id_grupy + "; "; 
    }
    
    @Override
    public void setResult(ResultSet res)
    {
    }
}