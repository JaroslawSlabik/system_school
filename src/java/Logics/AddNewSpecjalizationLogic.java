/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logics;

import Database.SQLDatabaseExecutor;
import Database.SqlQueryInsertNewSpecialization;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jarek
 */
public class AddNewSpecjalizationLogic extends I_Logic
{
    private String nazwa_specjalizacji;
    
    private ShowAllSpecjalizationsLogic show_all_specjalizations = new ShowAllSpecjalizationsLogic();
    
    public static String getLogicName() 
    {
        return "add_new_specjalization";
    }
    
    @Override
    public boolean setRequest(HttpServletRequest request)
    {
        try
        {
            nazwa_specjalizacji = request.getParameter("nazwa_specjalizacji");
        
            show_all_specjalizations.setRequest(request);
        
            return true;
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
    
    @Override
    public boolean work()
    {
        
        SqlQueryInsertNewSpecialization query_specjalization = new SqlQueryInsertNewSpecialization();
        query_specjalization.set_nazwa = nazwa_specjalizacji;
        
        SQLDatabaseExecutor db = new SQLDatabaseExecutor();
        boolean was_ok = db.executeQuery(query_specjalization);
        if(!was_ok)
        {
            m_view = "<P class='bad_message'>Specjalizacja NIE została dodana. Skontaktuj się z administratorem systemu podając kod błędu: 5</P><BR>";
        }
        else
        {
            m_view = "<P class='good_message'>Specjalizacja została dodana</P><BR>";
        }        
        
        show_all_specjalizations.work();
        
        m_view += show_all_specjalizations.getView();
        
        return true;
    }
}