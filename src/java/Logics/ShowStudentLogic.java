/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logics;

import Database.SQLDatabaseExecutor;
import Database.SqlQuerySelectStudentInfo;
import Database.SqlQuerySelectAllGroups;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jarek
 */
public class ShowStudentLogic extends I_Logic
{
    private Long id_studenta;
    
    public static String getLogicName() 
    {
        return "show_student";
    }
    
    @Override
    public boolean setRequest(HttpServletRequest request)
    {
        try
        {
            id_studenta = Long.valueOf(request.getParameter("id_studenta"));
        
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
        
        SqlQuerySelectStudentInfo query_student = new SqlQuerySelectStudentInfo();
        query_student.where_id_studenta = id_studenta;
        
        SQLDatabaseExecutor db = new SQLDatabaseExecutor();
        boolean was_ok = db.executeQuery(query_student);
        if(!was_ok)
        {
            m_view += "<P class='bad_message'>Student NIE został wczytany. Skontaktuj się z administratorem systemu podając kod błędu: 18</P><BR>";
        }
        
        SqlQuerySelectStudentInfo.Out student = query_student.query_result;
        
        m_view += "Imię: <INPUT value='"+ student.imie +"' name='imie'> <core:set var='dana' scope='request' value='${param.imie}'/><br> ";
        m_view += "Nazwisko: <INPUT value='"+ student.nazwisko +"' name='nazwisko'> <core:set var='dana' scope='request' value='${param.nazwisko}'/><br> ";
        m_view += "Miasto: <INPUT value='"+ student.miasto +"' name='miasto'> <core:set var='dana' scope='request' value='${param.miasto}'/><br> ";
        m_view += "Data: <INPUT type='date' value='"+ student.data_utworzenia +"' name='data'> <core:set var='dana' scope='request' value='${param.data}'/><br> ";
        
        SqlQuerySelectAllGroups query_all_groups = new SqlQuerySelectAllGroups();
        was_ok = db.executeQuery(query_all_groups);
        if(!was_ok)
        {
            m_view += "<P class='bad_message'>Lista grup NIE została wczytana. Skontaktuj się z administratorem systemu podając kod błędu: 19</P><BR>";
        }
        
        m_view += "<SELECT name='id_grupy'>";
        for(SqlQuerySelectAllGroups.Out out : query_all_groups.query_result)
        {
            if(out.id_grupy == student.id_grupy)
                m_view += "<OPTION value='"+ out.id_grupy +"' selected>"+ out.nazwa_grupy + " "+ out.nazwa_specjalizacji +"</OPTION>";
            else
                m_view += "<OPTION value='"+ out.id_grupy +"'>"+ out.nazwa_grupy + " "+ out.nazwa_specjalizacji +"</OPTION>";
        }
        m_view += "</SELECT> <core:set var='dana' scope='request' value='${param.id_grupy}'/>";
        
        m_view += "<INPUT name='id_studenta' value='"+ id_studenta +"' hidden><core:set var='dana' scope='request' value='${param.id_studenta}'/>";
        m_view += "<INPUT name='logic_name' value='modification_student' hidden><core:set var='dana' scope='request' value='${param.logic_name}'/>";
        m_view += "<INPUT type='submit' value='Modyfikuj'/>";
        m_view += "</FORM>";
        
        m_view += "Jeśli chcesz usunąć tego studenta to kliknij <A href='StudentServlet?id_studenta="+ id_studenta +"&logic_name=delete_student'>Tutaj</A>";
        
        return true;
    }
}
