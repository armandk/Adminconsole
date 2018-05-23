package com.sancreton.blogs.projects.adminconsole.controller;

import com.sancreton.blogs.projects.adminconsole.entities.User;
import com.sancreton.blogs.projects.adminconsole.service.UserService;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ManagedBean(name="userSummaryMB")
@ViewScoped
public class UserSummary implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2294388859174306839L;

	private static final Log logger = LogFactory.getLog(UserSummary.class);
	
	@ManagedProperty(value="#{UserService}")
	UserService userService;
	
	private List<User> userList;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<User> getUserList() {
		List<User> userList = null;
		
		try {
			logger.info("Fetching users...");
			userList = userService.getUsers();
			logger.info("Fetched users... "+userList != null ? userList.size():0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setUserList(userList);
		
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
