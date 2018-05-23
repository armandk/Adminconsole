package com.sancreton.blogs.projects.adminconsole.valueobject;

import java.io.Serializable;

public class DocSpecTypesVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String docSpecId;
	private String docSpecType;
	private String docSpecDesc;
	
	
	public String getDocSpecId() {
		return docSpecId;
	}
	public void setDocSpecId(String docSpecId) {
		this.docSpecId = docSpecId;
	}
	public String getDocSpecType() {
		return docSpecType;
	}
	public void setDocSpecType(String docSpecType) {
		this.docSpecType = docSpecType;
	}
	public String getDocSpecDesc() {
		return docSpecDesc;
	}
	public void setDocSpecDesc(String docSpecDesc) {
		this.docSpecDesc = docSpecDesc;
	}
	
	@Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("DOC_SPEC_ID : ").append(getDocSpecId());
        strBuff.append("\n DOC_SPEC_TYPE : ").append(getDocSpecType());
        strBuff.append("\n DOC_SPEC_DESC : ").append(getDocSpecDesc());
      
        return strBuff.toString();
    }
	
}
