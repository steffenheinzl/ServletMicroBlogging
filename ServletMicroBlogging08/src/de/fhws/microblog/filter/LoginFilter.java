package de.fhws.microblog.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/HandleMessages")
public class LoginFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
        //Schreiben eines Filters der Postzugriffe auf HandleMessages unterbindet  
	    HttpServletRequest req = (HttpServletRequest) request;
	    HttpSession session = req.getSession(false);
	    //if HTTP Post is used and session does not exist
	    if (req.getMethod().equalsIgnoreCase("POST") && (session == null)) 
        {
          //block user and redirect to login page
          HttpServletResponse resp = (HttpServletResponse) response;
          String redirectURL = resp.encodeRedirectURL("list.html");
          resp.sendRedirect(redirectURL);
        }
        else chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
