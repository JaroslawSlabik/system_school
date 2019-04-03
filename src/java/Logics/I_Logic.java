/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logics;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jarek
 */
public abstract class I_Logic 
{
    protected String m_view; 
    
    public abstract boolean setRequest(HttpServletRequest request);
    public abstract boolean work();
    public String getView()
    {
        return m_view;
    }
    
    public static String getLogicName() 
    {
        return "no_implement";
    }
}
