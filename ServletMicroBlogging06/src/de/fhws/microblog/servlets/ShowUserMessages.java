package de.fhws.microblog.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fhws.microblog.utils.MessagesUtil;

@WebServlet("/ShowUserMessages")
public class ShowUserMessages extends HttpServlet
{
  MessagesUtil mu; // reads messages from and writes messages to a file

  @Override
  public void init() throws ServletException
  {
    super.init();
    String rootPath = getServletContext().getRealPath("/");
    mu = MessagesUtil.getInstance(rootPath);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    response.getWriter().append("<html><head>");
    response.getWriter().append("<link rel=\"stylesheet\" type=\"text/css\" href=\"site.css\" />");
    response.getWriter().append("</head><body>");
    String user = request.getParameter("user");
    response.getWriter().append("<h1>" + user + "'s Messages</h1>");
    List<String> messages = mu.readUserMessages(user);
    String returnMessage = "";
    for (String message : messages)
    {
      returnMessage = "<p>" + message + "</p>" + returnMessage;
    }
    response.getWriter().append(returnMessage);
    response.getWriter().append("</body></html>");
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {

  }
}
