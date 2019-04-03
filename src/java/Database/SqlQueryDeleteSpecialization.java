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
public class SqlQueryDeleteSpecialization implements I_Query 
{
    public Integer where_id_specjalizacji;
    
    @Override
    public String getQuery()
    {
        return  "UPDATE " +
                "   specjalizacje " +
                "SET " +
                "   usunieta=TRUE " +
                "WHERE " +
                "   id_specjalizacji=" + where_id_specjalizacji + ";";
    }
    
    @Override
    public void setResult(ResultSet res)
    {
    }
}
