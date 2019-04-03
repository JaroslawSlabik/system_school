/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logics;

import Database.SQLDatabaseExecutor;
import Database.SqlQuerySelectAllGroups;
import Database.SqlQuerySelectAllSpecializations;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jarek
 */
public class ShowAllGroupsLogic extends I_Logic
{
    private enum LinkType
    {
        Modyfication,
        Delete
    };
    
    public static String getLogicName() 
    {
        return "show_all_groups";
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
        
        SqlQuerySelectAllGroups query = new SqlQuerySelectAllGroups();
        SQLDatabaseExecutor db = new SQLDatabaseExecutor();
        boolean was_ok = db.executeQuery(query);
        if(!was_ok)
        {
            m_view += "<P class='bad_message'>Lista grup NIE została wczytana. Skontaktuj się z administratorem systemu podając kod błędu: 10</P><BR>";
        }
        
        m_view += "<P>Lista grup: </P>";
        m_view += "<TABLE>";
        m_view += "<TR>";
        m_view += "<TH>Grupa</TH> <TH>Specjalizacja</TH><TH>Rok akademicki</TH><TH>Modyfikacja</TH> <TH>Usuń</TH>";
        m_view += "</TR>";
        
        for(SqlQuerySelectAllGroups.Out out : query.query_result)
        {
            m_view += "<TR>";
            m_view += "<TD>" + out.nazwa_grupy + "</TD><TD>" + out.nazwa_specjalizacji + "</TD><TD>" + out.rok_akademicki + "</TD>" + genCellLink(out.id_grupy, LinkType.Modyfication) + genCellLink(out.id_grupy, LinkType.Delete);
            m_view += "</TR>";
        }
        
        m_view += "</TABLE>";
        m_view += "</DIV>";
        
        
        m_view += "<DIV class='minior'>";
        m_view += "<P>Dodaj nową grupę: </P>";
        m_view += "<FORM action='/system_uczelnia/StudentServlet'>";
        m_view += "Nazwa grupy: <INPUT name='nazwa_grupy'> <core:set var='dana' scope='request' value='${param.nazwa_grupy}'/><br> ";
        m_view += "Rok akademicki: <INPUT type='number' name='rok_akademicki'> <core:set var='dana' scope='request' value='${param.rok_akademicki}'/><br> ";
        
        SqlQuerySelectAllSpecializations query_all_specjalizatiions= new SqlQuerySelectAllSpecializations();
        was_ok = db.executeQuery(query_all_specjalizatiions);
        if(!was_ok)
        {
            m_view += "<P class='bad_message'>Lista specjalizacji NIE została wczytana. Skontaktuj się z administratorem systemu podając kod błędu: 11</P><BR>";
        }
        
        m_view += "Nazwa specjalizacji:<SELECT name='id_specjalizacji'>";
        query_all_specjalizatiions.query_result.forEach((out) -> {
                m_view += "<OPTION value='"+ out.id_specjalizacji +"'>"+ out.nazwa_specjalizacji +"</OPTION>";
        });
        m_view += "</SELECT> <core:set var='dana' scope='request' value='${param.id_specjalizacji}'/>";
        
        m_view += "<INPUT name='logic_name' value='add_new_group' hidden><core:set var='dana' scope='request' value='${param.logic_name}'/>";
        m_view += "<INPUT type='submit' value='Dodaj'/>";
        m_view += "</FORM>";
        m_view += "</DIV>";
        
        return true;
    }
    
    private String genCellLink(Long id, LinkType type)
    {
        String link = "<TD><A href='StudentServlet?id_grupy="+ id +"&logic_name=";
        
        if(type == LinkType.Modyfication)
            link += "show_group'>Modyfikuj</A></TD>";
        if(type == LinkType.Delete)
            link += "delete_group'>Usuń</A></TD>";
        
        return link;
    }
}