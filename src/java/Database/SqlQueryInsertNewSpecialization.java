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
public class SqlQueryInsertNewSpecialization implements I_Query 
{
    public String set_nazwa;

    @Override
    public String getQuery()
    {
        return  "INSERT INTO specjalizacje VALUES(DEFAULT, '" + set_nazwa + "', DEFAULT);"; //dodanie nowej grupy
    }
    
    @Override
    public void setResult(ResultSet res)
    {
    }
}