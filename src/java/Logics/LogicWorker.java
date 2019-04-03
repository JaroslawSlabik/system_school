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
public class LogicWorker 
{
    private I_Logic m_logic = null;
    private boolean m_support = false;
    private boolean m_was_ok = false;
    
    public boolean setRequestParametersAndChoiceLogic(HttpServletRequest request)
    {
        m_support = false;
        m_was_ok = false;
        
        if(!m_support && ShowAllStudentsLogic.getLogicName().equals(request.getParameter("logic_name")))
        {
            m_support = true;
            setLogic(new ShowAllStudentsLogic());
            m_was_ok = setRequestParameters(request);
        }
        if(!m_support && ShowAllGroupsLogic.getLogicName().equals(request.getParameter("logic_name")))
        {
            m_support = true;
            setLogic(new ShowAllGroupsLogic());
            m_was_ok = setRequestParameters(request);
        }
        if(!m_support && ShowAllSpecjalizationsLogic.getLogicName().equals(request.getParameter("logic_name")))
        {
            m_support = true;
            setLogic(new ShowAllSpecjalizationsLogic());
            m_was_ok = setRequestParameters(request);
        }
        
        if(!m_support && ShowStudentLogic.getLogicName().equals(request.getParameter("logic_name")))
        {
            m_support = true;
            setLogic(new ShowStudentLogic());
            m_was_ok = setRequestParameters(request);
        }
        if(!m_support && ShowGroupLogic.getLogicName().equals(request.getParameter("logic_name")))
        {
            m_support = true;
            setLogic(new ShowGroupLogic());
            m_was_ok = setRequestParameters(request);
        }
        if(!m_support && ShowSpecjalizationLogic.getLogicName().equals(request.getParameter("logic_name")))
        {
            m_support = true;
            setLogic(new ShowSpecjalizationLogic());
            m_was_ok = setRequestParameters(request);
        }
        
        if(!m_support && ModificationStudentLogic.getLogicName().equals(request.getParameter("logic_name")))
        {
            m_support = true;
            setLogic(new ModificationStudentLogic());
            m_was_ok = setRequestParameters(request);
        }
        if(!m_support && ModificationGroupLogic.getLogicName().equals(request.getParameter("logic_name")))
        {
            m_support = true;
            setLogic(new ModificationGroupLogic());
            m_was_ok = setRequestParameters(request);
        }
        if(!m_support && ModificationSpecjalizationLogic.getLogicName().equals(request.getParameter("logic_name")))
        {
            m_support = true;
            setLogic(new ModificationSpecjalizationLogic());
            m_was_ok = setRequestParameters(request);
        }
        
        if(!m_support && DeleteStudentLogic.getLogicName().equals(request.getParameter("logic_name")))
        {
            m_support = true;
            setLogic(new DeleteStudentLogic());
            m_was_ok = setRequestParameters(request);
        }
        if(!m_support && DeleteGroupLogic.getLogicName().equals(request.getParameter("logic_name")))
        {
            m_support = true;
            setLogic(new DeleteGroupLogic());
            m_was_ok = setRequestParameters(request);
        }
        if(!m_support && DeleteSpecializationLogic.getLogicName().equals(request.getParameter("logic_name")))
        {
            m_support = true;
            setLogic(new DeleteSpecializationLogic());
            m_was_ok = setRequestParameters(request);
        }
        
        if(!m_support && AddNewStudentLogic.getLogicName().equals(request.getParameter("logic_name")))
        {
            m_support = true;
            setLogic(new AddNewStudentLogic());
            m_was_ok = setRequestParameters(request);
        }
        if(!m_support && AddNewGroupLogic.getLogicName().equals(request.getParameter("logic_name")))
        {
            m_support = true;
            setLogic(new AddNewGroupLogic());
            m_was_ok = setRequestParameters(request);
        }
        if(!m_support && AddNewSpecjalizationLogic.getLogicName().equals(request.getParameter("logic_name")))
        {
            m_support = true;
            setLogic(new AddNewSpecjalizationLogic());
            m_was_ok = setRequestParameters(request);
        }
        
        return m_support & m_was_ok;
    }
    
    public void setLogic(I_Logic logic)
    {
        m_logic = logic;
    }
    
    public boolean setRequestParameters(HttpServletRequest request)
    {
        if(m_logic != null)
            return m_logic.setRequest(request);
        
        return false;
    }
    
    public boolean work()
    {
        if(m_logic != null && m_support && m_was_ok)
            return m_logic.work();
        
        return false;
    }
    
    public String getView()
    {
        if(m_logic != null && m_support && m_was_ok)
            return m_logic.getView();
        
        return "Coś poszło nie tak. Skontaktuj się z administratorem systemu podając kod błędu: 20";
    }
}
