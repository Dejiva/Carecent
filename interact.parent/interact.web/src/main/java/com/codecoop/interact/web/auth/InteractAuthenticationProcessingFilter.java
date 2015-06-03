package com.codecoop.interact.web.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class InteractAuthenticationProcessingFilter extends UsernamePasswordAuthenticationFilter
{
    private InteractUserDetailsService interactUserDetailsService;
    public InteractUserDetailsService getInteractUserDetailsService() {
		return interactUserDetailsService;
	}

	public void setInteractUserDetailsService(
			InteractUserDetailsService interactUserDetailsService) {
		this.interactUserDetailsService = interactUserDetailsService;
	}


	private Logger logger = Logger.getLogger(getClass());

    @Override
    protected String obtainPassword(HttpServletRequest request)
    {
    	String password = super.obtainPassword(request);
    	interactUserDetailsService.setPassword(password);
        return password;
    }

    @SuppressWarnings("deprecation")
	@Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            Authentication authResult) throws ServletException, IOException
    {
    	request.getSession().invalidate();
        logger.debug("Successful authentication");
        User user = interactUserDetailsService.getCurrentUser();

        request.getSession(true).setAttribute("user", user);
        
        super.successfulAuthentication(request, response,  authResult);
    }

   
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws ServletException, IOException
    {
        logger.debug("Unsuccessful authentication");
        interactUserDetailsService.setCurrentUser(null);
        interactUserDetailsService.setPassword(null);
        super.unsuccessfulAuthentication(request, response, failed);
    }

  }
