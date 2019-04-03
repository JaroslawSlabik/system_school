/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logics;

import Database.SQLDatabaseExecutor;
import Database.SqlQueryDeleteGroup;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jarek
 */
public class DeleteGroupLogic extends I_Logic
{
    private Long id_grupy;
    
    private ShowAllGroupsLogic show_all_group = new ShowAllGroupsLogic();
    
    public static String getLogicName() 
    {
        return "delete_group";
    }
    
    @Override
    public boolean setRequest(HttpServletRequest request)
    {
        try
        {
            id_grupy = Long.valueOf(request.getParameter("id_grupy"));
        
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
        
        SqlQueryDeleteGroup query_student = new SqlQueryDeleteGroup();
        query_student.where_id_grupy = id_grupy;

        SQLDatabaseExecutor db = new SQLDatabaseExecutor();
        boolean was_ok = db.executeQuery(query_student);
        if(!was_ok)
        {
            m_view = "<P class='bad_message'>Grupa NIE została skasowana. Skontaktuj się z administratorem systemu podając kod błędu: 1</P><BR>";
        }
        else
        {
            m_view = "<P class='good_message'>Grupa została skasowana</P><BR>";
        }
                
        show_all_group.work();
        
        m_view += show_all_group.getView();
        
        return true;
    }
}
