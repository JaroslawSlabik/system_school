/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logics;

import Database.SQLDatabaseExecutor;
import Database.SqlQuerySelectAllGroups;
import Database.SqlQuerySelectAllStudents;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jarek
 */
public class ShowAllStudentsLogic extends I_Logic
{
    private enum LinkType
    {
        Modyfication,
        Delete
    };
    
    public static String getLogicName() 
    {
        return "show_all_students";
    }
    
    @Override
    public boolean setRequest(HttpServletRequest request)
    {
        return true;
    }
    
    @Override
    public boolean work()
    {
        m_view = "<DIV class='major'>";
        
        SqlQuerySelectAllStudents query = new SqlQuerySelectAllStudents();
        SQLDatabaseExecutor db = new SQLDatabaseExecutor();
        boolean was_ok = db.executeQuery(query);
        if(!was_ok)
        {
            m_view += "<P class='bad_message'>Lista studentów NIE została wczytana. Skontaktuj się z administratorem systemu podając kod błędu: 13</P><BR>";
        }
        
        m_view += "<P>Lista studentów: </P>";
        m_view += "<TABLE>";
        m_view += "<TR>";
        m_view += "<TH>Imię</TH> <TH>Nazwisko</TH> <TH>Grupa</TH> <TH>Specjalizacja</TH><TH>Modyfikacja</TH> <TH>Usuń</TH>";
        m_view += "</TR>";
        
        for(SqlQuerySelectAllStudents.Out out : query.query_result)
        {
            m_view += "<TR>";
            m_view += "<TD>" + out.imie + "</TD><TD>" + out.nazwisko + "</TD><TD>" + out.nazwa_grupy + "</TD><TD>" + out.nazwa_specjalizacji + "</TD>" + genCellLink(out.id_studenta, LinkType.Modyfication) + genCellLink(out.id_studenta, LinkType.Delete);
            m_view += "</TR>";
        }
        
        m_view += "</TABLE>";
        m_view += "</DIV>";
        
        
        m_view += "<DIV class='minior'>";
        m_view += "<P>Dodaj nowego studenta: </P>";
        m_view += "<FORM action='/system_uczelnia/StudentServlet'>";        
        m_view += "Imię: <INPUT name='imie'> <core:set var='dana' scope='request' value='${param.imie}'/><br> ";
        m_view += "Nazwisko: <INPUT name='nazwisko'> <core:set var='dana' scope='request' value='${param.nazwisko}'/><br> ";
        m_view += "Miasto: <INPUT name='miasto'> <core:set var='dana' scope='request' value='${param.miasto}'/><br> ";
        m_view += "Data: <INPUT type='date' name='data'> <core:set var='dana' scope='request' value='${param.data}'/><br> ";
        
        SqlQuerySelectAllGroups query_all_groups = new SqlQuerySelectAllGroups();
        was_ok = db.executeQuery(query_all_groups);
        if(!was_ok)
        {
            m_view += "<P class='bad_message'>Lista grup NIE została wczytana. Skontaktuj się z administratorem systemu podając kod błędu: 14</P><BR>";
        }
        
        m_view += "Grupa:<SELECT name='id_grupy'>";
        for(SqlQuerySelectAllGroups.Out out : query_all_groups.query_result)
        {
                m_view += "<OPTION value='"+ out.id_grupy +"'>"+ out.nazwa_grupy + " "+ out.nazwa_specjalizacji +"</OPTION>";
        }
        m_view += "</SELECT> <core:set var='dana' scope='request' value='${param.id_grupy}'/>";
        
        m_view += "<INPUT name='logic_name' value='add_new_student' hidden><core:set var='dana' scope='request' value='${param.logic_name}'/>";
        m_view += "<INPUT type='submit' value='Dodaj'/>";
        m_view += "</FORM>";
        m_view += "</DIV>";
        
        return true;
    }
    
    private String genCellLink(Long id, LinkType type)
    {
        String link = "<TD><A href='StudentServlet?id_studenta="+ id +"&logic_name=";
        
        if(type == LinkType.Modyfication)
            link += "show_student'>Modyfikuj</A></TD>";
        if(type == LinkType.Delete)
            link += "delete_student'>Usuń</A></TD>";
        
        return link;
    }
}
