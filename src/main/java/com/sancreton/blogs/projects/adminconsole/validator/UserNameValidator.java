package com.sancreton.blogs.projects.adminconsole.validator;

import com.sancreton.blogs.projects.adminconsole.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@FacesValidator("userNameValidator")
public class UserNameValidator implements Validator {
	
	private static final Log logger = LogFactory.getLog(UserNameValidator.class);
	
	@ManagedProperty(value="#{UserService}")
	UserService userService;

	@Override
	public void validate(FacesContext context, UIComponent uiComponent, Object obj)
			throws ValidatorException {
		logger.info("Performing username validation..."+ obj.toString());
		
		try {
			if(!userService.isUserNameExist(obj.toString())){
			      FacesMessage msg =
			              new FacesMessage("Username validation failed.",
			              "Username not available. Please try another one");
			      msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			    
			      throw new ValidatorException(msg);
			}
		} catch (Exception e) {
			logger.error("Error while performing username validation:", e);
		}
		
	}

}
