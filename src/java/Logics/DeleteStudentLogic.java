/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logics;

import Database.SQLDatabaseExecutor;
import Database.SqlQueryDeleteStudent;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jarek
 */
public class DeleteStudentLogic extends I_Logic
{
    private Long id_studenta;
    
    private ShowAllStudentsLogic show_all_student = new ShowAllStudentsLogic();
    
    public static String getLogicName() 
    {
        return "delete_student";
    }
    
    @Override
    public boolean setRequest(HttpServletRequest request)
    {
        try
        {
            id_studenta = Long.valueOf(request.getParameter("id_studenta"));
        
            show_all_student.setRequest(request);
        
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
        
        SqlQueryDeleteStudent query_student = new SqlQueryDeleteStudent();
        query_student.where_id_studenta = id_studenta;

        SQLDatabaseExecutor db = new SQLDatabaseExecutor();
        boolean was_ok = db.executeQuery(query_student);
        if(!was_ok)
        {
            m_view = "<P class='bad_message'>Student NIE został skasowany. Skontaktuj się z administratorem systemu podając kod błędu: 3</P><BR>";
        }
        else
        {
            m_view = "<P class='good_message'>Student został skasowany</P><BR>";
        }
        
        show_all_student.work();
        
        m_view += show_all_student.getView();
        
        return true;
    }
}
