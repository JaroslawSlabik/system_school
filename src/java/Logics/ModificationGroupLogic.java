/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logics;

import Database.SQLDatabaseExecutor;
import Database.SqlQueryModificationGroup;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jarek
 */
public class ModificationGroupLogic extends I_Logic
{
    private Long id_grupy;
    private String nazwa_grupy;
    private Integer id_specjalizacji;
    private Integer rok_akademicki;
    
    private ShowAllGroupsLogic show_all_group = new ShowAllGroupsLogic();
    
    public static String getLogicName() 
    {
        return "modification_group";
    }
    
    @Override
    public boolean setRequest(HttpServletRequest request)
    {
        try
        {
            id_grupy = Long.valueOf(request.getParameter("id_grupy"));
            nazwa_grupy = request.getParameter("nazwa_grupy");
            id_specjalizacji = Integer.valueOf(request.getParameter("id_specjalizacji"));
            rok_akademicki = Integer.valueOf(request.getParameter("rok_akademicki"));

            show_all_group.setRequest(request);
        
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
        
        SqlQueryModificationGroup query_qroup = new SqlQueryModificationGroup();
        query_qroup.where_id_grupy = id_grupy;
        query_qroup.set_nazwa = nazwa_grupy;
        query_qroup.set_id_specjalizacji = id_specjalizacji;
        query_qroup.set_rok_akademicki = rok_akademicki;
        
        SQLDatabaseExecutor db = new SQLDatabaseExecutor();
        boolean was_ok = db.executeQuery(query_qroup);
        if(!was_ok)
        {
            m_view = "<P class='bad_message'>Grupa NIE została skasowana. Skontaktuj się z administratorem systemu podając kod błędu: 7</P><BR>";
        }
        else
        {
            m_view = "<P class='good_message'>Grupa została zmodyfikowana</P><BR>";
        }

        show_all_group.work();
        
        m_view += show_all_group.getView();
        
        return true;
    }
}