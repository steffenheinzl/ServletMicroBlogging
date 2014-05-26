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
    System.out.println("Test");
    String rootPath = getServletContext().getRealPath("/");
    mu = new MessagesUtil(rootPath);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    //In messages befinden sich alle Nachrichten, die gebloggt wurden.
    List<String> messages = mu.readMessages(); //reads all messages from file
    //TODO: Gib alle Nachrichten in einer HTML Seite zurück. Jede Nachricht soll in einem eigenen Paragraph <p> stehen
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    //TODO: Lese den Parameter message vom request aus und ergänze ihn als Aufrufparameter von postMessage
    mu.postMessage(/*TODO*/);  //writes message to file
    doGet(request,response); //show messages
  }
}
