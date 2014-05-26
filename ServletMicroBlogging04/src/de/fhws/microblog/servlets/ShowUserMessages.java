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
  private static final long serialVersionUID = 1L;

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
    //TODO: Benutzernamen aus Servlet Request Parametern auslesen
    List<String> messages = mu.readUserMessages(/*Benutzername ergänzen*/);
    //TODO: Alle Nachrichten des Nutzers in HTML-Seite zurückgeben
    response.getWriter().append("</body></html>");
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
  }

}
