package com.codecoop.interact.web.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.codecoop.interact.web.model.StopAndWatchForm;

public class InteractUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//    protected Log logger = LogFactory.getLog(this.getClass());
 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
      HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }
 
    protected void handle(HttpServletRequest request,
      HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication, request);
 
        if (response.isCommitted()) {
//            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
 
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
 
    /** Builds the target URL according to the logic defined in the main class Javadoc. */
    protected String determineTargetUrl(Authentication authentication, HttpServletRequest request) {
        boolean isGuest = false;
        boolean isNonGuest = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_GUEST") ) {
                isGuest = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")	|| 
                	grantedAuthority.getAuthority().equals("ROLE_CNA") 		|| 
                	grantedAuthority.getAuthority().equals("ROLE_RN")  		||
                	grantedAuthority.getAuthority().equals("ROLE_LPN") 		|| 
                	grantedAuthority.getAuthority().equals("ROLE_PA")  		||
                	grantedAuthority.getAuthority().equals("ROLE_NP")  		||
                	grantedAuthority.getAuthority().equals("ROLE_SUPER_ADMIN")||
                	grantedAuthority.getAuthority().equals("ROLE_MD")) {
            	isNonGuest = true;
                break;
            }
        }
 
        if (isGuest) {
        	request.setAttribute("stopAndWatchForm", new StopAndWatchForm());
            return "/guest";
        } else if (isNonGuest) {
            return "/";
        } else {
            throw new IllegalStateException();
        }
    	
    }
 
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
    
    public List<String> rolesList(){
    	
    	List<String> role=new ArrayList<String>();
    	role.add("ROLE_ADMIN");
    	role.add("ROLE_CNA");
    	role.add("ROLE_RN");
    	role.add("ROLE_LPN");
    	role.add("ROLE_PA");
    	role.add("ROLE_MD");
    	role.add("ROLE_NP");
    	return role;
    
    }
}
