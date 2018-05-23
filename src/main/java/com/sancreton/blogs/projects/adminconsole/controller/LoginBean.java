package com.sancreton.blogs.projects.adminconsole.controller;

import com.sancreton.blogs.projects.adminconsole.entities.User;
import com.sancreton.blogs.projects.adminconsole.service.UserService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@Component
//@Scope("session")
@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 6526636908373425979L;

	private static final Log logger = LogFactory.getLog(LoginBean.class);

	private String username = "";
	private String password = "";
	private String newPassword = "";
	private boolean rememberMe = false;
	private boolean active;
	private Collection<GrantedAuthority> roles;
	
	@ManagedProperty(value="#{UserService}")
	UserService userService;

	public String doLogin(String eventName) throws IOException, ServletException {

		logger.info("Start login sequence");
		logger.info("Credentials: " + username	+ ", rememberMe:" + rememberMe);

		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();

		ServletRequest request = (ServletRequest) context.getRequest();
		
		if( eventName.equals("resetPwd") ){
			logger.info("setting req attribute resetPwd");
			request.setAttribute("resetPwd", "true");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");

		dispatcher.forward(request,	(ServletResponse) context.getResponse());
		
		FacesContext.getCurrentInstance().responseComplete();
		
		// It's OK to return null here because Faces is just going to exit.
		return null;

	}
	
	public void doPasswordReset() throws Exception {
		logger.info("Resetting password for user: "+this.username+"   "+userService);
		User user = userService.loadUserByUsername(this.username);
		user.setNew(false);
		user.setUpdatedBy(user.getUserName());
		user.setUpdatedDate(new Date());
		user.setPasswordExpirationDate(user.resetPasswordExpirtaionDate());
		user.setPassword(new BCryptPasswordEncoder().encode(this.newPassword));
		try {
			userService.createOrUpdateUser(user);
		} catch (Exception e) {
			logger.error("Error while resetting "+user+" password", e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error while resetting user password"));
			return;
		}
		
		logger.info("Successfully reset password for "+username);
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		logger.info("context name--->"+context.getContextName());
		context.redirect("login.xhtml");
	}
	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public boolean isRememberMe() {
		return this.rememberMe;
	}

	public void setRememberMe(final boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Collection<GrantedAuthority> getRoles() {
		return roles;
	}

	public void setRoles(Collection<GrantedAuthority> roles) {
		this.roles = roles;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}