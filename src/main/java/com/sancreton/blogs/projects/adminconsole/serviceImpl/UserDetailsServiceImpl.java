package com.sancreton.blogs.projects.adminconsole.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sancreton.blogs.projects.adminconsole.controller.LoginBean;
import com.sancreton.blogs.projects.adminconsole.dao.UserDao;
import com.sancreton.blogs.projects.adminconsole.entities.User;
import com.sancreton.blogs.projects.adminconsole.entities.UserRole;


@Service("userDetailsService")
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private static final Log logger = LogFactory.getLog(UserDetailsServiceImpl.class);

	@Autowired
	private UserDao dao;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		logger.info("Loading user "+ userName + " for authentication...");
		User user = null;
		try{
			user = dao.loadLoginByUserName(userName);
			logger.info("Successfully loaded user "+ userName + " for authentication...");
		}catch(Exception e){
			logger.error("Error while loading user "+ userName + " for authentication: ",e);
			throw new UsernameNotFoundException("Error while loading user "+ userName + " for authentication: ",e);
		}
		
		logger.info("User authenticated with user name: "+ userName);
		return buildLoginFromUserEntity(user);
	}

	private org.springframework.security.core.userdetails.User buildLoginFromUserEntity(User user) {
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(user.getUserName());
		loginBean.setActive(user.isActive());
		
		String username = user.getUserName();
	    String password = user.getPassword();
	    boolean enabled = user.isActive();
	    boolean accountNonExpired = user.isActive();
	    boolean credentialsNonExpired = user.isActive();
	    boolean accountNonLocked = user.isActive();
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    for (UserRole role : user.getRoles()) {
	      authorities.add(new SimpleGrantedAuthority(role.getRole().getRoleName()));
	    }
	    
	    //If new user or password expired set password reset role
	    if( user.isNew() || user.getPasswordExpirationDate().compareTo(new Date()) == -1 ) {
	    	logger.error(user.isNew() ? "New user must reset default password" : "User password expired. Must reset password");
	    	throw new CredentialsExpiredException( user.isNew() ? "New user must reset default password" : "User password expired. Must reset password" );
	    }
	    
	    org.springframework.security.core.userdetails.User secUser = new org.springframework.security.core.userdetails.User(username, password, enabled,
	    	      accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
		return secUser;
	}

}
