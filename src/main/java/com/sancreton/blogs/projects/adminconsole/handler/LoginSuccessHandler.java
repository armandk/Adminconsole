package com.sancreton.blogs.projects.adminconsole.handler;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	private static final Log logger = LogFactory.getLog(LoginSuccessHandler.class);
 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
    	logger.info("Invoked LoginSuccessHandler.onAuthenticationSuccess()....");
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }
 
    protected void handle(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(request, authentication);
 
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
 
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
 
    /** Builds the target URL according to the logic defined in the main class Javadoc. */
    protected String determineTargetUrl(HttpServletRequest request, Authentication authentication) {
    	logger.info("determineTargetUrl... "+request.getAttribute("resetPwd"));
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        logger.info("User roles : "+ authorities);
        
    	if( request.getAttribute("resetPwd") != null ){
    		return "/pages/login/resetPassword.xhtml";
    	} else if( request.getAttribute("pwdResetFailed") != null ) {
    		return "/pages/login/resetPassword.xhtml";
    	} else {
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals("PARTNER_MANAGEMENT")) {
                	return "/pages/partnermanagement/onboard.xhtml"; 
                 } else if (grantedAuthority.getAuthority().equals("USER_ACCESS_MANAGEMENT")) {
                	 return "/pages/user/userSummary.xhtml";
                } else {
                	throw new IllegalStateException();
                }
            }
    	}
    	return null;
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

}