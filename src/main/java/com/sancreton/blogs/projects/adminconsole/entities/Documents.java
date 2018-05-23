package com.sancreton.blogs.projects.adminconsole.entities;

import javax.persistence.*;

import com.mysql.jdbc.*;

import java.util.*;
@Entity
@Table(name="DOCUMENTS")

public class Documents implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String patientId;
	private String patientFirstName;
	private String patientLastName;
	private String patientSSN;
	private Integer rawDataSize;
	private byte[] rawData;
	private Date creationTime;
	private Date lastAccessedTime;
	private String formatCodeDisplayName;
	
	
    @Id
    @Column(name="DOCUMENT_ID", unique = true, nullable = false)	
	public int getId() {
		return id;
	}
    
 
	@Column(name="PATIENT_ID", unique = true, nullable = false)
	public String getPatientId() {
		return patientId;
	}


	@Column(name="PATIENT_GIVEN_NAME", unique = true, nullable = false)
	public String getPatientFirstName() {
		return patientFirstName;
	}

	@Column(name="PATIENT_LAST_NAME", unique = true, nullable = false)
	public String getPatientLastName() {
		return patientLastName;
	}

	@Column(name="PATIENT_SSN", unique = true, nullable = false)
	public String getPatientSSN() {
		return patientSSN;
	}
	@Basic(optional = true)
	@Column(name="RAW_DATA_SIZE")
	public Integer getRawDataSize() {
		return rawDataSize;
	}

	@Lob
	@Column(name="RAW_DATA", unique = true, nullable = false)
	public byte[] getRawData() {
		return rawData;
	}
  
	@Column(name = "FORMAT_CODE_DISPLAY_NAME", unique = true, nullable = false)
	public String getFormatCodeDisplayName()
    {
        return formatCodeDisplayName;
    }
	   @Column(name = "CREATION_TIME")
	   @Temporal(TemporalType.TIMESTAMP)
	 public Date getCreationTime() {
		return creationTime;
	 }
 
	   @Column(name = "LAST_ACCESSED_TIME")
	   @Temporal(TemporalType.TIMESTAMP)  
	   public Date getLastAccessedTime() {
			return lastAccessedTime;
		}

	   
	public void setId(int id) {
			this.id = id;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}


	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}


	public void setRawDataSize(Integer rawDataSize) {
		this.rawDataSize = rawDataSize;
	}


	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
	}

	public void setPatientSSN(String patientSSN) {
		this.patientSSN = patientSSN;
	}
	
	 public void setFormatCodeDisplayName(String formatCodeDisplayName)
	    {
	        this.formatCodeDisplayName = formatCodeDisplayName;
	    }
	 
	 
	 


		public void setCreationTime(Date creationTime) {
			this.creationTime = creationTime;
		}


		

		public void setLastAccessedTime(Date lastAccessedTime) {
			this.lastAccessedTime = lastAccessedTime;
		}	
		
		
	 
	@Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("DOCUMENT_ID : ").append(getId());
        strBuff.append("\n PATIENT_ID : ").append(getPatientId());
        strBuff.append("\n PATIENT_GIVEN_NAME : ").append(getPatientFirstName());
        strBuff.append("\n PATIENT_LAST_NAME : ").append(getPatientLastName());
        strBuff.append("\n PATIENT_SSN : ").append(getPatientSSN());
        strBuff.append("\n RAW_DATA_SIZE : ").append(getRawDataSize());
        strBuff.append("\n RAW_DATA : ").append(getRawData());
        strBuff.append("\n FORMAT_CODE_DISPLAY_NAME : ").append(getFormatCodeDisplayName());
        strBuff.append("\n CREATION_TIME : ").append(getCreationTime());
        strBuff.append("\n LAST_ACCESSED_TIME : ").append(getLastAccessedTime());
        return strBuff.toString();
    }


	
	
 }	
