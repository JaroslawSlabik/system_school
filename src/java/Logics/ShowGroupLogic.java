/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logics;

import Database.SQLDatabaseExecutor;
import Database.SqlQuerySelectAllSpecializations;
import Database.SqlQuerySelectGroupInfo;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jarek
 */
public class ShowGroupLogic extends I_Logic
{
    private Long id_grupy;
    
    public static String getLogicName() 
    {
        return "show_group";
    }
    
    @Override
    public boolean setRequest(HttpServletRequest request)
    {
        try
        {
            id_grupy = Long.valueOf(request.getParameter("id_grupy"));
        
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
        
        SqlQuerySelectGroupInfo query_student = new SqlQuerySelectGroupInfo();
        query_student.where_id_grupy = id_grupy;
        
        SQLDatabaseExecutor db = new SQLDatabaseExecutor();
        boolean was_ok = db.executeQuery(query_student);
        if(!was_ok)
        {
            m_view += "<P class='bad_message'>Grupa NIE została wczytana. Skontaktuj się z administratorem systemu podając kod błędu: 15</P><BR>";
        }
        
        SqlQuerySelectGroupInfo.Out grupa = query_student.query_result;
        
        m_view += "Nazwa grupy: <INPUT value='"+ grupa.nazwa_grupy +"' name='nazwa_grupy'> <core:set var='dana' scope='request' value='${param.nazwa_grupy}'/><br> ";
        m_view += "Rok akademicki: <INPUT value='"+ grupa.rok_akademicki +"' name='rok_akademicki'> <core:set var='dana' scope='request' value='${param.rok_akademicki}'/><br> ";
        
        SqlQuerySelectAllSpecializations query_all_specjalizatiions= new SqlQuerySelectAllSpecializations();
        was_ok = db.executeQuery(query_all_specjalizatiions);
        if(!was_ok)
        {
            m_view += "<P class='bad_message'>Lista specjalizacji NIE została wczytana. Skontaktuj się z administratorem systemu podając kod błędu: 16</P><BR>";
        }
        
        m_view += "Nazwa specjalizacji:<SELECT name='id_specjalizacji'>";
        query_all_specjalizatiions.query_result.forEach((out) -> {
            if(Objects.equals(out.id_specjalizacji, grupa.id_specjalizacji))
                m_view += "<OPTION value='"+ out.id_specjalizacji +"' selected>"+ out.nazwa_specjalizacji +"</OPTION>";
            else
                m_view += "<OPTION value='"+ out.id_specjalizacji +"'>"+ out.nazwa_specjalizacji +"</OPTION>";
        });
        m_view += "</SELECT> <core:set var='dana' scope='request' value='${param.id_specjalizacji}'/>";
        
        m_view += "<INPUT name='id_grupy' value='"+ id_grupy +"' hidden><core:set var='dana' scope='request' value='${param.id_grupy}'/>";
        m_view += "<INPUT name='logic_name' value='modification_group' hidden><core:set var='dana' scope='request' value='${param.logic_name}'/>";
        m_view += "<INPUT type='submit' value='Modyfikuj'/>";
        m_view += "</FORM>";
        
        m_view += "Jeśli chcesz usunąć tą grupę to kliknij <A href='StudentServlet?id_grupy="+ id_grupy +"&logic_name=delete_student'>Tutaj</A>";
        
        return true;
    }
}