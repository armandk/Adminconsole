package com.sancreton.blogs.projects.adminconsole.valueobject;

import java.io.Serializable;

public class PartnerValidationVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private boolean validationPassed;
	private String errorMgs;
	
	public boolean isValidationPassed() {
		return validationPassed;
	}
	public void setValidationPassed(boolean validationPassed) {
		this.validationPassed = validationPassed;
	}
	public String getErrorMgs() {
		return errorMgs;
	}
	public void setErrorMgs(String errorMgs) {
		this.errorMgs = errorMgs;
	}

	
	
}
