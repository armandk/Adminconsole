package com.sancreton.blogs.projects.adminconsole.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ALLOWED_ORG")
public class VapAllowedOrg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String number;
	private String oid;
	private String name;
	private String domain;
	private String communityIdPrifix;
	private String contact;
	private String phoneNumber;
	private String active;
	private String consumerOnly;
	
	
    @Id
    @Column(name="ORG_ID", unique = true, nullable = false)	
	public int getId() {
		return id;
	}
    
    
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="ORG_NUMBER")
	public String getNumber() {
		return number;
	}
	
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	@Column(name="ORG_OID")
	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}	
	
	@Column(name="ORG_NAME")
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}

	
	@Column(name="ORG_DOMAIN", unique = true, nullable = false)
	public String getDomain() {
		return domain;
	}


	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name="ORG_COMMUNITY_ID_PREFIX")
	public String getCommunityIdPrifix() {
		return communityIdPrifix;
	}


	public void setCommunityIdPrifix(String communityIdPrifix) {
		this.communityIdPrifix = communityIdPrifix;
	}

	@Column(name="ORG_CONTACT")
	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name="ORG_PHONE_NUMBER")
	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name="ACTIVE")
	public String getActive() {
		return active;
	}


	public void setActive(String active) {
		this.active = active;
	}

	@Column(name="ORG_CONSUMER_ONLY")
	public String getConsumerOnly() {
		return consumerOnly;
	}


	public void setConsumerOnly(String consumerOnly) {
		this.consumerOnly = consumerOnly;
	}



	@Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("ORG_ID : ").append(getId());
        strBuff.append("\n ORG_NUMBER : ").append(getNumber());
        strBuff.append("\n ORG_OID : ").append(getOid());
        strBuff.append("\n ORG_NAME : ").append(getName());
        strBuff.append("\n ORG_DOMAIN : ").append(getDomain());
        strBuff.append("\n ORG_COMMUNITY_ID_PREFIX : ").append(getCommunityIdPrifix());
        strBuff.append("\n ORG_CONTACT : ").append(getContact());
        strBuff.append("\n ORG_PHONE_NUMBER : ").append(getPhoneNumber());
        strBuff.append("\n ACTIVE : ").append(getActive());
        strBuff.append("\n ORG_CONSUMER_ONLY : ").append(getConsumerOnly());
        return strBuff.toString();
    }	
}
