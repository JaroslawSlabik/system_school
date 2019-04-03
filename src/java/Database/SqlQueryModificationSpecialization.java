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
public class SqlQueryModificationSpecialization implements I_Query 
{
    public Integer where_id_specjalizacji;
    
    public String set_nazwa;
    
    @Override
    public String getQuery()
    {
        return  "UPDATE " + //modyfikacja specjalizacji
                "   specjalizacje " +
                "SET " +
                "   nazwa='" + set_nazwa + "' " +
                "WHERE " +
                "   id_specjalizacji=" + where_id_specjalizacji + "; ";
    }
    
    @Override
    public void setResult(ResultSet res)
    {
    }
}
