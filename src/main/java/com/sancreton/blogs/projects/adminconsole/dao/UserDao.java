package com.sancreton.blogs.projects.adminconsole.dao;

import com.sancreton.blogs.projects.adminconsole.entities.Role;
import com.sancreton.blogs.projects.adminconsole.entities.User;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface UserDao {
	void createOrUpdateUser(User user) throws DataAccessException;
	List<User> getUsers() throws DataAccessException;
	User getUser(long userSeqNo) throws DataAccessException;
	void updateUser(User user) throws DataAccessException;
	List<Role> getRoles(List<String> roleNames) throws DataAccessException;
	User loadLoginByUserName(String userName) throws DataAccessException;
	boolean isUserNameExist(String userName) throws DataAccessException;
}
