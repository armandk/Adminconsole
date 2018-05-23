
package com.sancreton.blogs.projects.adminconsole.controller;

import com.sancreton.blogs.projects.adminconsole.valueobject.*;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

@ManagedBean(name="outBoundBean")
@ViewScoped
public class InAndOutBoundManagedBean implements Serializable {
    

	private static final long serialVersionUID = 1L;

	//private List<Partner> partners;
	private List<PartnerVO> partners;
	private List<PartnerVO> filteredPartners;
	private List<PartnerVO> selectedPartners;
	private String name;
	private String number;
	private String homeCommId;
	private String fullHomeCommId;
        
    @ManagedProperty("#{inAndOutBoundSupportBean}")
    private InAndOutBoundSupportBean service;
    
    @PostConstruct
    public void init() {
    	//partners = service.getPartners();
    	partners = service.getPartners();
    }


    public List<PartnerVO> getPartners() {
        return partners;
    }
    
    public List<String> getStatus() {
        return service.getStatus();
    }

    public void setService(InAndOutBoundSupportBean service) {
        this.service = service;
    }
    
    public void onRowEdit(RowEditEvent event) {
    	//call database function to update
    	updatePartner((PartnerVO) event.getObject());
    	init();
    	
        FacesMessage msg = new FacesMessage("Information has been updated for the partner - ", ((PartnerVO) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled for ", ((PartnerVO) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
       
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
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
    
    public String savePartner(){
    	
		PartnerVO partnerVO = new PartnerVO();
		partnerVO.setNumber(number);
		partnerVO.setHomeCommId(homeCommId);
		partnerVO.setName(name);
		partnerVO.setFullHomeCommId(fullHomeCommId);
		
		System.out.println(partnerVO.toString());
		
		service.addPartner(partnerVO);
		
		init();
		//return "";
		return "onboard.xhtml?faces-redirect=true";
		
    	
    }
    
    public void updatePartner(PartnerVO partnerVO){

    	service.updatePartner(partnerVO);
    }


	public List<PartnerVO> getFilteredPartners() {
		return filteredPartners;
	}


	public void setFilteredPartners(List<PartnerVO> filteredPartners) {
		this.filteredPartners = filteredPartners;
	}


	public List<PartnerVO> getSelectedPartners() {
		return selectedPartners;
	}


	public void setSelectedPartners(List<PartnerVO> selectedPartners) {
		this.selectedPartners = selectedPartners;
	}
    
    
}
