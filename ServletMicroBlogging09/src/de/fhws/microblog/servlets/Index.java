package de.fhws.microblog.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Index
 */
@WebServlet("/index.html")
public class Index extends HttpServlet
{
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    response.getWriter().append("<!DOCTYPE html>");
    response.getWriter().append("<html><head>");
    response.getWriter().append("<link rel=\"stylesheet\" type=\"text/css\" href=\"site.css\" />");
    response.getWriter().append("</head><body>");
    RequestDispatcher dispatcher;
    HttpSession session = request.getSession(false);
    if (session != null)
    {
      String user = (String) session.getAttribute("user");
      dispatcher = request.getRequestDispatcher("ShowUserMessages?user=" + user);
      dispatcher.include(request, response);
    }
    dispatcher = request.getRequestDispatcher("list.html");
    dispatcher.include(request, response);
    response.getWriter().append("</body></html>");
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
  }

}
