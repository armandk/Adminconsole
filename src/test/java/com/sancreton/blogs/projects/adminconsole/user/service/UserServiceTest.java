package com.sancreton.blogs.projects.adminconsole.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sancreton.blogs.projects.adminconsole.entities.Role;
import com.sancreton.blogs.projects.adminconsole.entities.User;
import com.sancreton.blogs.projects.adminconsole.entities.UserRole;
import com.sancreton.blogs.projects.adminconsole.service.UserService;
import com.sancreton.blogs.projects.adminconsole.serviceImpl.UserServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext_Test.xml")
public class UserServiceTest {

	@Autowired
	UserService UserService;
	
	@Test
	public void createOrUpdateUser() throws Exception{
		User user = new User();
		prepareUser(user);
		
		System.out.println("User---> "+user);
		
		UserService.createOrUpdateUser(user);
	}
	
	@Test
	public void testGetRoles() throws Exception{
		List<String> roleNames = new ArrayList<String>();
		roleNames.add("Super_Admin");
		roleNames.add("Admin");
		List<Role> roles = UserService.getRoles(roleNames);
		System.out.println("Roles--> "+roles);
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		User user = UserService.getUser(18);
		user.setActive(false);
		
		//user.getRoles().remove(0);
		Date date = new Date(System.currentTimeMillis());
		UserRole role1 = new UserRole();
		role1.setCreatedBy("Admin");
		role1.setCreatedDate(date);
		List<String> superAdmin = new ArrayList<String>();
		superAdmin.add("Super_Admin");
		role1.setRole(UserService.getRoles(superAdmin).get(0));
		role1.setUpdatedBy("Admin");
		role1.setUpdatedDate(date);
		role1.setUser(user);
		user.getRoles().add(role1);
		System.out.println("User--> "+user);
		
		UserService.updateUser(user);
		
	}
	
	@Test
	public void testGetUsers() throws Exception {
		List<User> users = UserService.getUsers();
		
		System.out.println(users.size()+"\n"+users.get(0).getRoles());
	}

	private void prepareUser(User user) throws Exception {
		Date date = new Date(System.currentTimeMillis());
		user.setActive(true);
		user.setCreatedBy("Admin");
		user.setCreatedDate(date);
		user.setPassword(new BCryptPasswordEncoder().encode("admin"));
		user.setFirstName("Ravi");
		user.setLastName("Kumar");
		user.setUpdatedBy("Admin");
		user.setUpdatedDate(date);
		user.setUserName("ravik");
		
		List<UserRole> roles = new ArrayList<UserRole>();
		
		UserRole role1 = new UserRole();
		role1.setCreatedBy("Admin");
		role1.setCreatedDate(date);
		List<String> superAdmin = new ArrayList<String>();
		superAdmin.add("Super_Admin");
		role1.setRole(UserService.getRoles(superAdmin).get(0));
		role1.setUpdatedBy("Admin");
		role1.setUpdatedDate(date);
		role1.setUser(user);
		roles.add(role1);
		
		UserRole role2 = new UserRole();
		role2.setCreatedBy("Admin");
		role2.setCreatedDate(date);
		List<String> admin = new ArrayList<String>();
		admin.add("Admin");
		role2.setRole(UserService.getRoles(admin).get(0));
		role2.setUpdatedBy("Admin");
		role2.setUpdatedDate(date);
		role2.setUser(user);
		roles.add(role2);
		System.out.println("Roles--> "+roles);
		user.setRoles(roles);
	}
	
	@Test
	public void encodePassword(){
		System.out.println(new BCryptPasswordEncoder().encode("ravik"));
	}

}
