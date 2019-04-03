/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logics;

import Database.SQLDatabaseExecutor;
import Database.SqlQuerySelectAllSpecializations;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jarek
 */
public class ShowAllSpecjalizationsLogic extends I_Logic
{
    private enum LinkType
    {
        Modyfication,
        Delete
    };
    
    public static String getLogicName() 
    {
        return "show_all_specjalizations";
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
        
        SqlQuerySelectAllSpecializations query = new SqlQuerySelectAllSpecializations();
        SQLDatabaseExecutor db = new SQLDatabaseExecutor();
        boolean was_ok = db.executeQuery(query);
        if(!was_ok)
        {
            m_view += "<P class='bad_message'>Lista specjalizacji NIE została wczytana. Skontaktuj się z administratorem systemu podając kod błędu: 12</P><BR>";
        }
        
        m_view += "<P>Lista specjalizacji: </P>";
        m_view += "<TABLE>";
        m_view += "<TR>";
        m_view += "<TH>Specjalizacja</TH><TH>Modyfikacja</TH> <TH>Usuń</TH>";
        m_view += "</TR>";
        
        for(SqlQuerySelectAllSpecializations.Out out : query.query_result)
        {
            m_view += "<TR>";
            m_view += "<TD>" + out.nazwa_specjalizacji + "</TD>" + genCellLink(out.id_specjalizacji, LinkType.Modyfication) + genCellLink(out.id_specjalizacji, LinkType.Delete);
            m_view += "</TR>";
        }
        
        m_view += "</TABLE>";
        m_view += "</DIV>";
        
        
        m_view += "<DIV class='minior'>";
        m_view += "<P>Dodaj nową specjalizację: </P>";
        m_view += "<FORM action='/system_uczelnia/StudentServlet'>";  
        m_view += "Nazwa specjalizacji: <INPUT name='nazwa_specjalizacji'> <core:set var='dana' scope='request' value='${param.nazwa_specjalizacji}'/><br> ";
        m_view += "<INPUT name='logic_name' value='add_new_specjalization' hidden><core:set var='dana' scope='request' value='${param.logic_name}'/>";
        m_view += "<INPUT type='submit' value='Dodaj'/>";
        m_view += "</FORM>";
        m_view += "</DIV>";
        
        
        return true;
    }
    
    private String genCellLink(Integer id, LinkType type)
    {
        String link = "<TD><A href='StudentServlet?id_specjalizacji="+ id +"&logic_name=";
        
        if(type == LinkType.Modyfication)
            link += "show_specjalization'>Modyfikuj</A></TD>";
        if(type == LinkType.Delete)
            link += "delete_specjalization'>Usuń</A></TD>";
        
        return link;
    }
}