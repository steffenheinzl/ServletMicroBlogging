package de.fhws.microblog.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String user = request.getParameter("user");
	  if (user == null) response.getWriter().append("<html><body>Login failed! No user provided!</body></html>");
      //TODO: Erzeuge eine Session falls noch nicht vorhanden 
	  
	  //TODO: setze den User als Attribut in die Session
	  
	  //TODO: Leite den Client auf die index.html um
      
	}

}
