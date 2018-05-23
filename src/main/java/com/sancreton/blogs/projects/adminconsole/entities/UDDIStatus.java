package com.sancreton.blogs.projects.adminconsole.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.Type;

@Entity
@Table(name="FILE_UPDATE_INFO")
public class UDDIStatus implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9055817500899188170L;
	private static final Log logger = LogFactory.getLog(UDDIStatus.class);
	
	@Id
	@Column(name="id")
	private long id;
	
	@Column(name="last_file_update_time")
	@Type(type = "timestamp")
	private Date lastFileUpdateTime;
	
	@Column(name="updated_by")
	private String updatedBy;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getLastFileUpdateTime() {
		return lastFileUpdateTime;
	}

	public void setLastFileUpdateTime(Date lastFileUpdateTime) {
		this.lastFileUpdateTime = lastFileUpdateTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	

}
