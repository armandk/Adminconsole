package com.sancreton.blogs.projects.adminconsole.valueobject;

import java.io.Serializable;

public class PartnerVO implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private int id;
	private String number;
	private String name;
	private String homeCommId;
	private String fullHomeCommId;
	private boolean onboard = true;
	private String onboardStatus = "OnBoard";
	
	private String inboundPD;
	private String inboundQD;
	private String inboundRD;
	private String outboundPD;
	private String outboundQD;
	private String outboundRD;

	private String useSpecVersion;
	private String acpCheck;
	private String prefSpec;
	private String docSpecId;
	private String docSpecType;
	
	
	//These values are for VAP table
	private String domain;
	private String communityIdPrifix;
	private String contact;
	private String phoneNumber;
	private String active;
	private String consumerOnly;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHomeCommId() {
		return homeCommId;
	}
	public void setHomeCommId(String homeCommId) {
		this.homeCommId = homeCommId;
	}
	public String getFullHomeCommId() {
		return fullHomeCommId;
	}
	public void setFullHomeCommId(String fullHomeCommId) {
		this.fullHomeCommId = fullHomeCommId;
	}
	public boolean isOnboard() {
		return onboard;
	}
	public void setOnboard(boolean onboard) {
		this.onboard = onboard;
	}
	
	
	public String getOnboardStatus() {
		return onboardStatus;
	}
	public void setOnboardStatus(String onboardStatus) {
		this.onboardStatus = onboardStatus;
	}
	
	
	
	public String getInboundPD() {
		return inboundPD;
	}
	public void setInboundPD(String inboundPD) {
		this.inboundPD = inboundPD;
	}
	public String getInboundQD() {
		return inboundQD;
	}
	public void setInboundQD(String inboundQD) {
		this.inboundQD = inboundQD;
	}
	public String getInboundRD() {
		return inboundRD;
	}
	public void setInboundRD(String inboundRD) {
		this.inboundRD = inboundRD;
	}
	public String getOutboundPD() {
		return outboundPD;
	}
	public void setOutboundPD(String outboundPD) {
		this.outboundPD = outboundPD;
	}
	public String getOutboundQD() {
		return outboundQD;
	}
	public void setOutboundQD(String outboundQD) {
		this.outboundQD = outboundQD;
	}
	public String getOutboundRD() {
		return outboundRD;
	}
	public void setOutboundRD(String outboundRD) {
		this.outboundRD = outboundRD;
	}
	
	
	public String getUseSpecVersion() {
		return useSpecVersion;
	}
	public void setUseSpecVersion(String useSpecVersion) {
		this.useSpecVersion = useSpecVersion;
	}
	public String getAcpCheck() {
		return acpCheck;
	}
	public void setAcpCheck(String acpCheck) {
		this.acpCheck = acpCheck;
	}
	public String getPrefSpec() {
		return prefSpec;
	}
	public void setPrefSpec(String prefSpec) {
		this.prefSpec = prefSpec;
	}

	
	
	public String getDocSpecType() {
		return docSpecType;
	}
	public void setDocSpecType(String docSpecType) {
		this.docSpecType = docSpecType;
	}
	public String getDocSpecId() {
		return docSpecId;
	}
	public void setDocSpecId(String docSpecId) {
		this.docSpecId = docSpecId;
	}
	
	@Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("FACILITY_ID : ").append(getId());
        strBuff.append("\n FACILITY_NUMBER : ").append(getNumber());
        strBuff.append("\n FACILITY_NAME : ").append(getName());
        strBuff.append("\n HOME_COMMUNITY_ID : ").append(getHomeCommId());
        strBuff.append("\n FULL_HOME_COMMUNITY_ID : ").append(getFullHomeCommId());
        strBuff.append("\n onboard : ").append(isOnboard());
        strBuff.append("\n DOC_SPEC_ID : ").append(getDocSpecId());
        return strBuff.toString();
    }
	
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getCommunityIdPrifix() {
		return communityIdPrifix;
	}
	public void setCommunityIdPrifix(String communityIdPrifix) {
		this.communityIdPrifix = communityIdPrifix;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getConsumerOnly() {
		return consumerOnly;
	}
	public void setConsumerOnly(String consumerOnly) {
		this.consumerOnly = consumerOnly;
	}	
	
	
}
