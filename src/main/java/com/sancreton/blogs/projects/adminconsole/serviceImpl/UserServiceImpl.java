package com.sancreton.blogs.projects.adminconsole.serviceImpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sancreton.blogs.projects.adminconsole.dao.UserDao;
import com.sancreton.blogs.projects.adminconsole.entities.Role;
import com.sancreton.blogs.projects.adminconsole.entities.User;
import com.sancreton.blogs.projects.adminconsole.service.UserService;

@Service("UserService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	
	private static final Log logger = LogFactory.getLog(UserServiceImpl.class);
	
	@Autowired
	UserDao userDAO;

	@Transactional(readOnly = false)
	public void createOrUpdateUser(User user) throws Exception {
		logger.info("Create user initiated with following details# "+user);
		userDAO.createOrUpdateUser(user);
	}

	@Transactional(readOnly = true)
	public List<User> getUsers() throws Exception {
		return userDAO.getUsers();
	}

	@Transactional(readOnly = true)
	public User getUser(long userSeqNo) throws Exception {
		return userDAO.getUser(userSeqNo);
	}

	@Transactional(readOnly = false)
	public void updateUser(User user) throws Exception {
		userDAO.updateUser(user);
	}

	public List<Role> getRoles(List<String> roleNames) throws Exception {
		return userDAO.getRoles(roleNames);
	}

	public UserDao getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDao userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public User loadUserByUsername(String userName) throws Exception {
		return userDAO.loadLoginByUserName(userName);
	}

	@Override
	public boolean isUserNameExist(String userName) throws Exception {
		return userDAO.isUserNameExist(userName);
	}

}
