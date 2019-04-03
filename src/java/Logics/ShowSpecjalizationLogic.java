/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logics;

import Database.SQLDatabaseExecutor;
import Database.SqlQuerySelectSpecjalizationInfo;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jarek
 */
public class ShowSpecjalizationLogic extends I_Logic
{
    private Integer id_specjalizacji;
    
    public static String getLogicName() 
    {
        return "show_specjalization";
    }
    
    @Override
    public boolean setRequest(HttpServletRequest request)
    {
        try
        {
            id_specjalizacji = Integer.valueOf(request.getParameter("id_specjalizacji"));
        
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
        m_view = "<FORM action='/system_uczelnia/StudentServlet'>";
        
        SqlQuerySelectSpecjalizationInfo query_specjalizacja = new SqlQuerySelectSpecjalizationInfo();
        query_specjalizacja.where_id_specjalizacji = id_specjalizacji;
        
        SQLDatabaseExecutor db = new SQLDatabaseExecutor();
        boolean was_ok = db.executeQuery(query_specjalizacja);
        if(!was_ok)
        {
            m_view += "<P class='bad_message'>Specjalizacja NIE została wczytana. Skontaktuj się z administratorem systemu podając kod błędu: 17</P><BR>";
        }
        
        SqlQuerySelectSpecjalizationInfo.Out specjalizacja = query_specjalizacja.query_result;
        
        m_view += "Nazwa specjalizacji: <INPUT value='"+ specjalizacja.nazwa_specjalizacji +"' name='nazwa_specjalizacji'> <core:set var='dana' scope='request' value='${param.nazwa_specjalizacji}'/><br> ";
        
        m_view += "<INPUT name='id_specjalizacji' value='"+ id_specjalizacji +"' hidden><core:set var='dana' scope='request' value='${param.id_specjalizacji}'/>";
        m_view += "<INPUT name='logic_name' value='modification_specjalization' hidden><core:set var='dana' scope='request' value='${param.logic_name}'/>";
        m_view += "<INPUT type='submit' value='Modyfikuj'/>";
        m_view += "</FORM>";
        
        m_view += "Jeśli chcesz usunąć tą specjalizację to kliknij <A href='StudentServlet?id_specjalizacji="+ id_specjalizacji +"&logic_name=delete_student'>Tutaj</A>";
        
        return true;
    }
}