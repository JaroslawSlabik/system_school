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
public class SqlQueryInsertNewGroup implements I_Query 
{
    public String set_nazwa;
    public Integer set_rok_akademicki;
    public Integer set_id_specjalizacji;
    
    @Override
    public String getQuery()
    {
        return  "INSERT INTO grupy VALUES(DEFAULT, " + set_id_specjalizacji + ", '" + set_nazwa + "', " + set_rok_akademicki + ", DEFAULT);"; //dodanie nowej grupy
    }
    
    @Override
    public void setResult(ResultSet res)
    {
    }
}

