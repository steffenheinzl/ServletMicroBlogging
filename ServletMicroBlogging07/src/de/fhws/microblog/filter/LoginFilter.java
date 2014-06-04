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

@WebFilter(/*TODO: URL ergänzen*/)
public class LoginFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
	    HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse resp = (HttpServletResponse) response;
        //TODO: Schreiben Sie einen Filter der HTTP POST-Zugriffe auf die URL /HandleMessages unterbindet, falls der Benutzer nicht eingeloggt ist.
	    //Hinweise: Der Benutzer ist nicht eingeloggt, wenn es noch keine Session gibt.
	    //          Die HTTP Methode kann über req.getMethod abgefragt werden.
	    
        //block user and redirect to login page
        String redirectURL = resp.encodeRedirectURL("Login");
        resp.sendRedirect(redirectURL);
        
        // pass the request along the filter chain
        chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
