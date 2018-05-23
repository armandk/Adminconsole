package com.sancreton.blogs.projects.adminconsole.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="FACILITIES")

public class Facilities implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String number;
	private String name;
	private String homeCommId;
	private String fullHomeCommId;
	
	FacilitiesOperartions facilitiesOperartions;
	
	private String useSpecVersion;
	private String acpCheck;
	private String prefSpec;
	//private int docSpecId;
	private Integer docSpecId;
	
	
    @Id
    @Column(name="FACILITY_ID", unique = true, nullable = false)	
	public int getId() {
		return id;
	}
    
    
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="FACILITY_NUMBER", unique = true, nullable = false)
	public String getNumber() {
		return number;
	}
	
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	@Column(name="FACILITY_NAME", unique = true, nullable = false)
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}

	
	@Column(name="HOME_COMMUNITY_ID", unique = true, nullable = false)
	public String getHomeCommId() {
		return homeCommId;
	}
	

	public void setHomeCommId(String homeCommId) {
		this.homeCommId = homeCommId;
	}

	@Column(name="FULL_HOME_COMMUNITY_ID", unique = true, nullable = false)
	public String getFullHomeCommId() {
		return fullHomeCommId;
	}


	public void setFullHomeCommId(String fullHomeCommId) {
		this.fullHomeCommId = fullHomeCommId;
	}
	
	@Column(name="USE_SPEC_VERSION")
	public String getUseSpecVersion() {
		return useSpecVersion;
	}


	public void setUseSpecVersion(String useSpecVersion) {
		this.useSpecVersion = useSpecVersion;
	}


	@Column(name="ACP_CHECK")
	public String getAcpCheck() {
		return acpCheck;
	}


	public void setAcpCheck(String acpCheck) {
		this.acpCheck = acpCheck;
	}

	@Column(name="PREF_SPEC")
	public String getPrefSpec() {
		return prefSpec;
	}


	public void setPrefSpec(String prefSpec) {
		this.prefSpec = prefSpec;
	}

	
	@Column(name="DOC_SPEC_ID")
	public Integer getDocSpecId() {
		return docSpecId;
	}


	public void setDocSpecId(Integer docSpecId) {
		this.docSpecId = docSpecId;
	}	


	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	public FacilitiesOperartions getFacilitiesOperartions() {
		return facilitiesOperartions;
	}




	public void setFacilitiesOperartions(FacilitiesOperartions facilitiesOperartions) {
		this.facilitiesOperartions = facilitiesOperartions;
	}


	@Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("FACILITY_ID : ").append(getId());
        strBuff.append("\n FACILITY_NUMBER : ").append(getNumber());
        strBuff.append("\n FACILITY_NAME : ").append(getName());
        strBuff.append("\n HOME_COMMUNITY_ID : ").append(getHomeCommId());
        strBuff.append("\n FULL_HOME_COMMUNITY_ID : ").append(getFullHomeCommId());
        if(getFacilitiesOperartions() != null)
        strBuff.append("\n FacilitiesOperartions : ").append(getFacilitiesOperartions().getId());
        return strBuff.toString();
    }	
}
