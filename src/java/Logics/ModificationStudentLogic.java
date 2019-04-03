/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logics;

import Database.SQLDatabaseExecutor;
import Database.SqlQueryModificationStudent;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jarek
 */
public class ModificationStudentLogic extends I_Logic
{
    private Long id_studenta;
    private Long id_grupy;
    private String imie;
    private String nazwisko;
    private String miasto;
    private String data;
    
    private ShowAllStudentsLogic show_all_student = new ShowAllStudentsLogic();
    
    public static String getLogicName() 
    {
        return "modification_student";
    }
    
    @Override
    public boolean setRequest(HttpServletRequest request)
    {
        try
        {
            id_studenta = Long.valueOf(request.getParameter("id_studenta"));
            id_grupy = Long.valueOf(request.getParameter("id_grupy"));
            imie = request.getParameter("imie");
            nazwisko = request.getParameter("nazwisko");
            miasto = request.getParameter("miasto");
            data = request.getParameter("data");

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
        
        SqlQueryModificationStudent query_student = new SqlQueryModificationStudent();
        query_student.where_id_studenta = id_studenta;
        query_student.set_id_grupy = id_grupy;
        query_student.set_imie = imie;
        query_student.set_nazwisko = nazwisko;
        query_student.set_miasto = miasto;
        //data not channge
        
        SQLDatabaseExecutor db = new SQLDatabaseExecutor();
        boolean was_ok = db.executeQuery(query_student);
        if(!was_ok)
        {
            m_view = "<P class='bad_message'>Student NIE został skasowany. Skontaktuj się z administratorem systemu podając kod błędu: 9</P><BR>";
        }
        else
        {
            m_view = "<P class='good_message'>Student został zmodyfikowany</P><BR>";
        }
        
        show_all_student.work();
        
        m_view += show_all_student.getView();
        
        return true;
    }
}