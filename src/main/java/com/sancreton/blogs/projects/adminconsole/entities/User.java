package com.sancreton.blogs.projects.adminconsole.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;


@Entity
@Table(name="ADMIN_USER")
@DynamicUpdate
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Log logger = LogFactory.getLog(User.class);

	@Id
	@GeneratedValue(generator = "user_seq")
	@SequenceGenerator(name = "user_seq", sequenceName = "user_seq")
	@Column(name="user_id", unique = true, nullable = false)
	private long userId;
	
	@Column(name="first_name", nullable = false)
	private String firstName;
	
	@Column(name="last_name", nullable = false)
	private String lastName;
	
	@Column(name="username", nullable = false)
	private String userName;
	
/*	@Column(name="email", nullable = true)
	private String email;*/
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="active_ind", nullable = false)
	@Type(type = "yes_no")
	private boolean isActive = true;
	
	@Column(name="new_ind", nullable = false)
	@Type(type = "yes_no")
	private boolean isNew = true;
	
	@Column(name="pwd_exp_dt", nullable = false)
	private Date passwordExpirationDate;
	
	@Column(name="created_date", nullable = false)
	private Date createdDate = new Date();
	
	@Column(name="created_by", nullable = false)
	private String createdBy;
	
	@Column(name="updated_by", nullable = true)
	private String updatedBy;
	
	@Column(name="updated_date", nullable = true)
	private Date updatedDate = new Date();
	
	@OneToMany(targetEntity=UserRole.class, mappedBy="user", orphanRemoval=true, fetch=FetchType.EAGER)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
	//@JoinColumn(name = "user_role_seq_no")
	private List<UserRole> roles = new ArrayList<UserRole>();
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getUpdatedBy() {
		return updatedBy;
	}
	
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public Date getUpdatedDate() {
		return updatedDate;
	}
	
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {	
		if( this.roles != null ){
			this.roles.clear();
		}
		
		this.roles.addAll(roles);
	}
	
	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public Date getPasswordExpirationDate() {
		return passwordExpirationDate;
	}

	public void setPasswordExpirationDate(Date passwordExpirationDate) {
		this.passwordExpirationDate = passwordExpirationDate;
		logger.info("setPasswordExpirationDate--->"+ isNew()+ "  " + this.passwordExpirationDate);
	}
	
	public Date resetPasswordExpirtaionDate(){
		Calendar  date = Calendar.getInstance();
		date.add(Calendar.DATE, 30);
		return date.getTime();
	}

	public String toString(){
		String userDtls = "userId: "+ userId + ", firstName: " + firstName + ", lastName: " + lastName + "\n" +
			   "userName: "+ userName + ", encryptedPassword: " + password + ", createdDate: " + createdDate + "\n" +
			   "createdBy: "+ createdBy + ", updatedBy: " + updatedBy + ", updatedDate: " + updatedDate + ", isNew: " + isNew() + "\n";
		
		return userDtls;
		
		/*String roleDtls = "Roles={";
		for( UserRole role : roles ) {
			roleDtls += " [ " + role.toString() + " ], "; 
		}
		
		roleDtls += " } ";
		
		return userDtls + roleDtls;*/
	}

}
