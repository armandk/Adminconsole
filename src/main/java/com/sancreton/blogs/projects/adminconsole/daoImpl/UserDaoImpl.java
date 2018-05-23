package com.sancreton.blogs.projects.adminconsole.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.sancreton.blogs.projects.adminconsole.dao.UserDao;
import com.sancreton.blogs.projects.adminconsole.entities.Role;
import com.sancreton.blogs.projects.adminconsole.entities.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	private static final Log logger = LogFactory.getLog(UserDaoImpl.class);
	
    @Autowired
    private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void createOrUpdateUser(User user) throws DataAccessException {
		logger.info("Creating user with following details# "+user);
		getSessionFactory().getCurrentSession().saveOrUpdate(user);
		logger.info("Successfully created user# "+user);
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() throws DataAccessException {
		return (ArrayList<User>) getSessionFactory().getCurrentSession()
						.createQuery("FROM User WHERE active_ind = 'Y'")
						.list();
						
	}

	public User getUser(long userSeqNo) throws DataAccessException {
		return (User) getSessionFactory().getCurrentSession()
				.createQuery(" FROM User WHERE user_id = ?")
				.setParameter(0, userSeqNo)
				.list().get(0);
	}

	public void updateUser(User user) throws DataAccessException {
		getSessionFactory().getCurrentSession().update(user);
		
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRoles(List<String> roleNames) throws DataAccessException {
		if(roleNames != null && roleNames.size() != 0)
			return (ArrayList<Role>) getSessionFactory().getCurrentSession()
					.createQuery(" FROM Role WHERE role_name IN (:roleNames)")
					.setParameterList("roleNames", roleNames)
					.list();
		else
			return (ArrayList<Role>) getSessionFactory().getCurrentSession()
					.createQuery(" FROM com.sancreton.blogs.projects.adminconsole.entities.Role")
					.list();
	}

	@Override
	public User loadLoginByUserName(String userName)
			throws DataAccessException {
		return (User) getSessionFactory().getCurrentSession()
				.createQuery("FROM User WHERE userName = ? AND active_ind = 'Y' ")
				.setParameter(0, userName)
				.list().get(0);
	}

	@Override
	public boolean isUserNameExist(String userName) throws DataAccessException {
		return !(getSessionFactory().getCurrentSession()
		.createQuery("FROM User WHERE upper(username) = upper(?) ")
		.setParameter(0, userName)
		.list().isEmpty());
	}

}
