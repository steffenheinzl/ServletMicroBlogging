package de.fhws.microblog.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fhws.microblog.utils.MessagesUtil;

@WebServlet("/HandleMessages")
public class HandleMessages extends HttpServlet
{
  MessagesUtil mu; //reads messages from and writes messages to a file

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
    List<String> messages = mu.readMessages(); //reads all messages from file
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
    String message = request.getParameter("message");
    mu.postMessage(message); //writes message to file
    String redirectURL = response.encodeRedirectURL("list.html");
    response.sendRedirect(redirectURL);
  }
}
