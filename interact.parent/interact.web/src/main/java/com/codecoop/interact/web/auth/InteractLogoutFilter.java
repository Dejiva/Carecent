package com.codecoop.interact.web.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

public class InteractLogoutFilter extends LogoutFilter
{
    private Logger logger = Logger.getLogger(InteractLogoutFilter.class);

    public InteractLogoutFilter(String logoutSuccessUrl, LogoutHandler... handlers)
    {
        super(logoutSuccessUrl, handlers);
    }

    @Override
    protected boolean requiresLogout(HttpServletRequest request, HttpServletResponse response)
    {
        logger.debug("LogoutFilter.requiresLogout");
        // Normal logout processing (i.e. detect logout URL)
        if (super.requiresLogout(request, response)){
            HttpSession session = request.getSession();
            session.invalidate();
            return true;
        }

        return false;
    }
}
