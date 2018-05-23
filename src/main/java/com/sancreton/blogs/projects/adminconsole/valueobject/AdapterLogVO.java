package com.sancreton.blogs.projects.adminconsole.valueobject;


import java.io.Serializable;
import java.util.Date;

public class AdapterLogVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Date dateFom = null;
	
	Date dateTo = null;

	public Date getDateFom() {
		return dateFom;
	}

	public void setDateFom(Date dateFom) {
		this.dateFom = dateFom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

}
