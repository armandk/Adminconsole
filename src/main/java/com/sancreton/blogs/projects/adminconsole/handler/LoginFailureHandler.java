package com.sancreton.blogs.projects.adminconsole.handler;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailureHandler implements AuthenticationFailureHandler {

	private static final Log logger = LogFactory.getLog(LoginFailureHandler.class);
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException ex) throws IOException, ServletException {
        String userName = req.getParameter("j_username");
        logger.info("[AuthenticationFailure]:" + " [Username]:" + userName + " [Error message]: " + ex + "  " + ex.getMessage());
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage msg = new FacesMessage();
        
        if (ex instanceof BadCredentialsException) {
        	msg.setDetail("Invalid username or password");
        	msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        	context.addMessage(null, msg);
        	context.getExternalContext().redirect("login.xhtml");
        } else if (ex.getMessage().contains("New user must reset default password") || ex.getMessage().contains("User password expired")) {
        	logger.info("<<<CredentialsExpiredException>>>>");
        	msg.setDetail(ex.getMessage());
        	msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        	context.addMessage(null, msg);
        	context.getExternalContext().redirect("resetPassword.xhtml");
        } else if (ex instanceof DisabledException) {
        	msg.setDetail("User inactive");
        	msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        	context.addMessage(null, msg);
        	context.getExternalContext().redirect("login.xhtml");
        } else if (ex instanceof LockedException) {
        	msg.setDetail("User locked");
        	msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        	context.addMessage(null, msg);
        	context.getExternalContext().redirect("login.xhtml");
        }
    }
}
