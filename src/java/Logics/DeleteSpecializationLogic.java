/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logics;

import Database.SQLDatabaseExecutor;
import Database.SqlQueryDeleteSpecialization;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jarek
 */
public class DeleteSpecializationLogic extends I_Logic
{
    private Integer id_specjalizacji;
    
    private ShowAllSpecjalizationsLogic show_all_specializations = new ShowAllSpecjalizationsLogic();
    
    public static String getLogicName() 
    {
        return "delete_specjalization";
    }
    
    @Override
    public boolean setRequest(HttpServletRequest request)
    {
        try
        {
            id_specjalizacji = Integer.valueOf(request.getParameter("id_specjalizacji"));
        
            show_all_specializations.setRequest(request);
        
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
        
        SqlQueryDeleteSpecialization query_specjalization = new SqlQueryDeleteSpecialization();
        query_specjalization.where_id_specjalizacji = id_specjalizacji;

        SQLDatabaseExecutor db = new SQLDatabaseExecutor();
        boolean was_ok = db.executeQuery(query_specjalization);
        if(!was_ok)
        {
            m_view = "<P class='bad_message'>Specjalizacja NIE została skasowana. Skontaktuj się z administratorem systemu podając kod błędu: 2</P><BR>";
        }
        else
        {
            m_view = "<P class='good_message'>Specjalizacja została skasowana</P><BR>";
        }
        
        show_all_specializations.work();
        
        m_view += show_all_specializations.getView();
        
        return true;
    }
}
