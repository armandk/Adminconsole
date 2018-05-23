package com.sancreton.blogs.projects.adminconsole.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DOC_SPEC_TYPES")
public class DocSpecTypes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer docSpecId;
	private String docSpecType;
	private String docSpecDesc;
	
	@Id
	@Column(name="DOC_SPEC_ID")
	public Integer getDocSpecId() {
		return docSpecId;
	}
	public void setDocSpecId(Integer docSpecId) {
		this.docSpecId = docSpecId;
	}
	
	@Column(name="DOC_SPEC_TYPE")
	public String getDocSpecType() {
		return docSpecType;
	}
	public void setDocSpecType(String docSpecType) {
		this.docSpecType = docSpecType;
	}
	
	@Column(name="DOC_SPEC_DESC")
	public String getDocSpecDesc() {
		return docSpecDesc;
	}
	public void setDocSpecDesc(String docSpecDesc) {
		this.docSpecDesc = docSpecDesc;
	}
	
	
	
	
}
