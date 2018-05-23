package com.sancreton.blogs.projects.adminconsole.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="FACILITIES_OPERATIONS")
public class FacilitiesOperartions implements Serializable{


	private static final long serialVersionUID = 1L;

	private int id;
    
	private String inboundPD;
	private String inboundQD;
	private String inboundRD;
	
	private String outboundPD;
	private String outboundQD;
	private String outboundRD;
	Facilities facilities;
	
	public FacilitiesOperartions(){
		
	}
	
	public FacilitiesOperartions(int id,String inboundPD,String inboundQD,String inboundRD, String outboundPD,String outboundQD,String outboundRD){
		
		this.id = id;
		this.inboundPD = inboundPD;
		this.inboundQD = inboundQD;
		this.inboundRD = inboundRD;
		this.outboundPD = outboundPD;
		this.outboundQD = outboundQD;
		this.outboundRD = outboundRD;
	}
	
	
    @Id
    @Column(name="FACILITY_ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="IN_ALLOW_PATIENT_DICOVERY", unique = true, nullable = false)
	public String getInboundPD() {
		return inboundPD;
	}
	
	public void setInboundPD(String inboundPD) {
		this.inboundPD = inboundPD;
	}
	
	@Column(name="IN_ALLOW_DOC_QUERY", unique = true, nullable = false)
	public String getInboundQD() {
		return inboundQD;
	}
	
	public void setInboundQD(String inboundQD) {
		this.inboundQD = inboundQD;
	}
	
	@Column(name="IN_ALLOW_DOC_RETRIEVE", unique = true, nullable = false)
	public String getInboundRD() {
		return inboundRD;
	}
	
	public void setInboundRD(String inboundRD) {
		this.inboundRD = inboundRD;
	}
	
	@Column(name="OUT_ALLOW_PATIENT_DICOVERY", unique = true, nullable = false)
	public String getOutboundPD() {
		return outboundPD;
	}
	
	public void setOutboundPD(String outboundPD) {
		this.outboundPD = outboundPD;
	}
	
	@Column(name="OUT_ALLOW_DOC_QUERY", unique = true, nullable = false)
	public String getOutboundQD() {
		return outboundQD;
	}
	
	public void setOutboundQD(String outboundQD) {
		this.outboundQD = outboundQD;
	}
	
	@Column(name="OUT_ALLOW_DOC_RETRIEVE", unique = true, nullable = false)
	public String getOutboundRD() {
		return outboundRD;
	}
	
	public void setOutboundRD(String outboundRD) {
		this.outboundRD = outboundRD;
	}

	@OneToOne(mappedBy="facilitiesOperartions", cascade=CascadeType.ALL)
	public Facilities getFacilities() {
		return facilities;
	}

	public void setFacilities(Facilities facilities) {
		this.facilities = facilities;
	}
	
	
	
}
