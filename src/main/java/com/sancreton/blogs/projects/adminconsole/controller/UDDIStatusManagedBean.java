package com.sancreton.blogs.projects.adminconsole.controller;

import com.sancreton.blogs.projects.adminconsole.entities.UDDIStatus;
import com.sancreton.blogs.projects.adminconsole.service.UDDIService;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ManagedBean(name="uddiStatusMB")
@ViewScoped
public class UDDIStatusManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7540416283282888662L;
	
	private static final Log logger = LogFactory.getLog(UDDIStatusManagedBean.class);
	
	private List<UDDIStatus> uddiStatusList;
	
	@ManagedProperty(value="#{UDDIService}")
	UDDIService uddiService;
	
	private String statusIndicator;
	
	private void updateUDDIStatusList() {
		List<UDDIStatus> list = uddiService.getUDDIStatusList();
		setUddiStatusList(list);
	}

	public void updateStatusIndicator() {
		if( this.uddiStatusList == null || this.uddiStatusList.size() < 2 ){
			return;
		}
		
		long diffInHours = com.sancreton.blogs.projects.adminconsole.util.DateUtils
							.getDateDifference(((UDDIStatus)this.uddiStatusList.get(0)).getLastFileUpdateTime(), 
											((UDDIStatus)this.uddiStatusList.get(1)).getLastFileUpdateTime(), com.sancreton.blogs.projects.adminconsole.util.DateUtils.TIMEUNIT_HOURS);
		
		logger.info("Difference in hours: "+diffInHours);
		
		if( diffInHours > 24 && diffInHours <= 48 ) {
			this.statusIndicator  = "yellowdot.png";
		} else if( diffInHours > 48 ) {
			this.statusIndicator  = "reddot.png";
		} else {
			this.statusIndicator  = "greendot.png";
		}
	}	

	public List<UDDIStatus> getUddiStatusList() {
		updateUDDIStatusList();
		
		return uddiStatusList;
	}

	public void setUddiStatusList(List<UDDIStatus> uddiStatusList) {
		this.uddiStatusList = uddiStatusList;
	}

	public UDDIService getUddiService() {
		return uddiService;
	}

	public void setUddiService(UDDIService uddiService) {
		this.uddiService = uddiService;
	}

	public String getStatusIndicator() {
		return this.statusIndicator;
	}

	public void setStatusIndicator(String statusIndicator) {
		this.statusIndicator = statusIndicator;
	}

}
