package com.sancreton.blogs.projects.adminconsole.controller;

import com.sancreton.blogs.projects.adminconsole.controller.LoginBean;
import com.sancreton.blogs.projects.adminconsole.entities.Role;
import com.sancreton.blogs.projects.adminconsole.entities.User;
import com.sancreton.blogs.projects.adminconsole.entities.UserRole;
import com.sancreton.blogs.projects.adminconsole.service.UserService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ManagedBean(name="userMB")
@ViewScoped
public class UserController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3643964266293954999L;
	
	private static final Log logger = LogFactory.getLog(UserController.class);
	
	@ManagedProperty(value="#{UserService}")
	UserService userService;
	
	@ManagedProperty(value="#{loginBean}")
	LoginBean loginBean;
	
	private String clearTxtPwd;
	
	private List<Role> roles;
	private User user = new User();
	
	private List<String> selectedRoles;
	private List<SelectItem> selectItems;
	private boolean editMode = false;
	
	
	@PostConstruct
    private void load() {
        if(FacesContext.getCurrentInstance().isPostback()) return;

        logger.info("loading roles...");
        selectItems = new ArrayList<SelectItem>();

        try {
			roles = userService.getRoles(null);
			
			for( Role role : roles ){
				selectItems.add(new SelectItem(role.getRoleId(), role.getRoleDescription()));
			}
		} catch (Exception e) {
			logger.error("Error while loading roles: ", e);
		}
        logger.info(String.format("Loaded %s roles: ", roles));
        
        initUser();
    }
	
	public void initUser(){
		logger.info("Initializing user...");
		this.user = new User();
		setClearTxtPwd("");
		this.editMode = false;
		
		if( this.selectedRoles != null )
			this.selectedRoles.clear();
		
		//RequestContext.getCurrentInstance().reset("userForm:addUserPanel");
	}
	
	public void saveUser(){
		logger.info("Adding/Updating user: "+ user + "\nRoles: " +selectedRoles);
		
		if( FacesContext.getCurrentInstance().isValidationFailed() ){
			logger.info("User business validation failed...");
			return;
		}
		
		try {
			//TODO: get createby from login bean
			user.setCreatedBy(loginBean.getUsername());
			user.setCreatedDate(new Date());
			
			if( user.isNew() ){
				user.setPasswordExpirationDate(user.resetPasswordExpirtaionDate());
			}
			
			if(!isPasswordMatch()){
				//save encrypted password
				user.setPassword(encodePassword(clearTxtPwd));
			}
			
			setUserRoles();
			
			userService.createOrUpdateUser(user);
		} catch (Exception e) {
			logger.error("Error while adding user: "+user, e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error while saving user"));
			return;
		}
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User saved successfully"));
	}
	
	private boolean isPasswordMatch() {
		if( user.getPassword() == null ){
			return false;
		}
		
		return user.getPassword().equals(clearTxtPwd);
	}

	public void loadUser(long userId){
		logger.info("Loading user with ID: "+userId);
		try {
			this.user = userService.getUser(userId);
			setSelectedUserRoles(this.user.getRoles());
			clearTxtPwd = this.user.getPassword();
			logger.info("Found user with ID: "+ user);
			this.editMode = true;
		} catch (Exception e) {
			logger.error("Error while loadin user with ID: ", e);
		}
		RequestContext.getCurrentInstance().reset("userForm:addUserPanel");
	}
	
	public void deleteUser(User user){
		logger.info("Deleting user: "+ user);
		try {
			user.setActive(false);
			userService.createOrUpdateUser(user);
		} catch (Exception e) {
			logger.error("Error while deleting user: "+ user, e);
		}
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User deleted successfully"));
	}
	
	public void isUserNameExist(FacesContext context, UIComponent uiComponent, Object obj)
			throws ValidatorException {
		logger.info("Performing username validation..."+ obj.toString());
		
		UIInput userName = (UIInput) uiComponent.getAttributes().get("userName");
		
		try {
			if(userService.isUserNameExist(obj.toString())){
				context.addMessage(null, new FacesMessage("Username not available. Please try another one"));
				context.validationFailed();
				userName.setValid(false);
				((UIInput) uiComponent).setValid(false);
								
				return;
/*			      FacesMessage msg =
			              new FacesMessage("Username validation failed.",
			              "Username not available. Please try another one");
			      msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			    
			      throw new ValidatorException(msg);*/
			}
		} catch (ValidatorException ex){
			throw ex;
		}catch (Exception e) {
			logger.error("Error while performing username validation:", e);
		} 
		
	}
	
	
	private void setSelectedUserRoles(List<UserRole> roles){
		if( roles == null || roles.size() == 0 ){
			setSelectedRoles(null);
			return;
		}
		
		List<String> userRoles = new ArrayList<String>();
		
		for( UserRole role : roles ){
			userRoles.add(String.valueOf(role.getRole().getRoleId()));
		}
		
		setSelectedRoles(userRoles);
	}
	
	private void setUserRoles(){
		List<UserRole> roles = new ArrayList<UserRole>();
		
		for( String roleId : selectedRoles ){
			UserRole userRole = new UserRole();
			Role role = new Role();
			role.setRoleId(Long.parseLong(roleId));
			userRole.setRole(role);
			userRole.setUser(user);
			//TODO: get from login bean
			userRole.setCreatedBy("admin");
			
			roles.add(userRole);
		}
		
		user.setRoles(roles);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<Role> getRoles() {
		return roles;
	}

	@SuppressWarnings("unused")
	private void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(List<String> selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	public List<SelectItem> getSelectItems() {
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}
	
	private String encodePassword(String passwordTxt){
		return new BCryptPasswordEncoder().encode(passwordTxt);
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public String getClearTxtPwd() {
		return clearTxtPwd;
	}

	public void setClearTxtPwd(String clearTxtPwd) {
		this.clearTxtPwd = clearTxtPwd;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
}
