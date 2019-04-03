/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Logics.LogicWorker;

import Database.SQLDatabaseExecutor;
import Database.SqlQuerySelectAllStudents;
import java.sql.ResultSet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author Jarek
 */
@WebServlet(name = "StudentServlet", urlPatterns = {"/StudentServlet"})
public class StudentServlet extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try
        {
            LogicWorker logic = new LogicWorker();
        
            logic.setRequestParametersAndChoiceLogic(request);
        
            logic.work();
        
            String view = logic.getView();
        
            request.setAttribute("view", view); 
        
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        //Może dodam jakieś 'throws' to wtedy wykorzystam catch
        finally
        {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() 
    {
        return "Short description";
    }
}
