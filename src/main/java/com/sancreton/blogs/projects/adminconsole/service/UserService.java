package com.sancreton.blogs.projects.adminconsole.service;


import java.util.List;

import com.sancreton.blogs.projects.adminconsole.entities.Role;
import com.sancreton.blogs.projects.adminconsole.entities.User;

public interface UserService {
	
	void createOrUpdateUser(User user) throws Exception;
	List<User> getUsers() throws Exception;
	User getUser(long userSeqNo) throws Exception;
	void updateUser(User user) throws Exception;
	List<Role> getRoles(List<String> roleNames) throws Exception;
	User loadUserByUsername(String userName) throws Exception;
	boolean isUserNameExist(String userName) throws Exception;
}
