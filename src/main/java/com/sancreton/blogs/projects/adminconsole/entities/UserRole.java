package com.sancreton.blogs.projects.adminconsole.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="USER_ROLE")

public class UserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "userrole_seq")
	@SequenceGenerator(name = "userrole_seq", sequenceName = "userrole_seq")
	@Column(name="user_role_id", unique = true, nullable = false)		
	private long userRoleId;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.EAGER)
	//@Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn(name = "user_id")
	//@Column(name="user_seq_no", nullable = false)
	private User user;
	
	@ManyToOne(targetEntity=Role.class, fetch=FetchType.EAGER)
	//@Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn(name = "role_id")
	//@Column(name="role_seq_no", nullable = false)
	private Role role;
	
	@Column(name="created_date", nullable = false)
	private Date createdDate = new Date();
	
	@Column(name="created_by", nullable = false)
	private String createdBy;
	
	@Column(name="updated_by", nullable = true)
	private String updatedBy;
	
	@Column(name="updated_date", nullable = true)
	private Date updatedDate;

	public long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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
	
	public String toString(){
		return "userRoleSeqNo: "+ userRoleId + ", userSeqNo: " + user.getUserId() + ", role: " + role.getRoleName();
		
	}	

}
