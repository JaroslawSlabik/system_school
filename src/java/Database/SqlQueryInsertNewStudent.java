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
public class SqlQueryInsertNewStudent implements I_Query 
{
    public String set_imie;
    public String set_nazwisko;
    public String set_miasto;
    public Long set_id_grupy;

    @Override
    public String getQuery()
    {
        return  "INSERT INTO studenci VALUES(DEFAULT, '" + set_imie + "', '" + set_nazwisko + "', '" + set_miasto + "', DEFAULT, DEFAULT);" +//dodanie nowego studenta
                "INSERT INTO student_grupa VALUES(DEFAULT, LAST_INSERT_ID(), " + set_id_grupy + ", DEFAULT, DEFAULT);"; //przypisanie nowego studenta do grupy
    }
    
    @Override
    public void setResult(ResultSet res)
    {
    }
}