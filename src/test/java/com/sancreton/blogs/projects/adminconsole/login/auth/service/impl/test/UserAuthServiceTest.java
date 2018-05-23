package com.sancreton.blogs.projects.adminconsole.login.auth.service.impl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_Test.xml")
public class UserAuthServiceTest {

	@Autowired
	UserDetailsService userDetailsService;
	
//	@Test
//	public void testLoadLoginByUserName(){
//		userDetailsService.loadUserByUsername("ravik1");
//	}
//	
//	@Test
//	public void testBcryptCheckpw(){
//		System.out.println(BCrypt.checkpw("ravik1", "$2a$10$a0q0IePWjYp2P2VsEfp6LOWDzVu.wdK0OeI6S5YIIkpLi.BR3bME2" ));
//	}
	
	@Test
	public void testLoadLoginByUserName(){
		//userDetailsService.loadUserByUsername("admin");
	}
	
	@Test
	public void testBcryptCheckpw(){
		System.out.println(BCrypt.checkpw("admin", "$2a$10$cAHlIdQVTReEyU1uHEnDMedZTNYtHZFsIdxZ/GHol4oTSXcjErOrS" ));
	}	
}
