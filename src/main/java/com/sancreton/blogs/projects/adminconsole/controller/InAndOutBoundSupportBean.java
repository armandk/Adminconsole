
package com.sancreton.blogs.projects.adminconsole.controller;

import com.sancreton.blogs.projects.adminconsole.valueobject.*;
import com.sancreton.blogs.projects.adminconsole.serviceImpl.PartnerManagementServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;


@ManagedBean(name = "inAndOutBoundSupportBean")
@ApplicationScoped
public class InAndOutBoundSupportBean {
    
    private final static String[] status;
    
	//Spring OnOffBoardService Service is injected...
    @ManagedProperty(value="#{partnerManagementService}")
	PartnerManagementServiceImpl partnerManagementService;
	
    static {
    	status = new String[2];
    	status[0] = "Y";
    	status[1] = "N";
    	
	}
    
    public List<PartnerVO> getPartners(){
    	List<PartnerVO> filteredPartnersList = new ArrayList<PartnerVO>();
    	
    	List<PartnerVO> partnersList = getPartnerList();
    	Iterator<PartnerVO> it = partnersList.iterator();
    	
    	while(it.hasNext())
    	{
    		PartnerVO partnerVO = it.next();
    		
    		if(partnerVO.getOnboardStatus().equals("OnBoard")){
    			filteredPartnersList.add(partnerVO);
    		}

    	}
    	
    	return filteredPartnersList;
    }
    
    
    public List<String> getStatus() {
        return Arrays.asList(status);
    }
    

    
//	public PartnerManagementService getOnOffBoardService() {
//		return partnerManagementService;
//	}
//
//	public void setOnOffBoardService(PartnerManagementService partnerManagementService) {
//		this.partnerManagementService = partnerManagementService;
//	}
    
    
	public List<PartnerVO> getPartnerList() {
		return partnerManagementService.getPartners();
	}
	
	public PartnerManagementServiceImpl getPartnerManagementService() {
		return partnerManagementService;
	}


	public void setPartnerManagementService(
			PartnerManagementServiceImpl partnerManagementService) {
		this.partnerManagementService = partnerManagementService;
	}


	public void addPartner(PartnerVO partnerVO){
		partnerManagementService.addPartner(partnerVO);
	}
	
	public void updatePartner(PartnerVO partnerVO){
		partnerManagementService.updatePartner(partnerVO);
	}
}
