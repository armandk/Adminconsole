package com.sancreton.blogs.projects.adminconsole.controller;

import com.sancreton.blogs.projects.adminconsole.valueobject.*;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

@ManagedBean(name="onboardBean")
@ViewScoped
public class OnOffBoardManagedBean implements Serializable {
    

	private static final long serialVersionUID = 1L;

	private List<PartnerVO> partners;
	private List<PartnerVO> filteredPartners;
	private List<PartnerVO> selectedPartners;
	private String name;
	private String number;
	private String homeCommId;
	private String fullHomeCommId;
	
	private String useSpecVersion;
	private String acpCheck;
	private String prefSpec;
	private String docSpecId;

	
	//These values are for VAP table
	private String domain;
	private String communityIdPrifix;
	private String contact;
	private String phoneNumber;
	private String consumerOnly;
        
    @ManagedProperty("#{onOffBoardSupportBean}")
    private OnOffBoardSupportBean service;
    
    @PostConstruct
    public void init() {
    	partners = service.getPartners();
    }



    
    public List<PartnerVO> getPartners() {
        return partners;
    }
    
    public List<String> getStatus() {
        return service.getStatus();
    }

    public void setService(OnOffBoardSupportBean service) {
        this.service = service;
    }
    
    public void onRowEdit(RowEditEvent event) {
    	PartnerVO partnerVO = (PartnerVO) event.getObject();
    	
    	PartnerValidationVO partnerValidationVO = validateEditedPartner(partnerVO);
    	
    	if(partnerValidationVO.isValidationPassed()){
	    	//call database function to update
	    	updatePartner((PartnerVO) event.getObject());
			//updatePartner(partnerVO);
	    	init();
	    	
	        FacesMessage msg = new FacesMessage("Information has been updated for the partner - ", ((PartnerVO) event.getObject()).getName());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
    	}else{
	        FacesMessage msg = new FacesMessage("Edit Failed", partnerValidationVO.getErrorMgs());
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        init();
    	}
    	
    }
    
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled for", ((PartnerVO) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        System.out.println("oldValue = "+oldValue+"----newValue = "+newValue);
        
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



	public String getDocSpecId() {
		return docSpecId;
	}


	public void setDocSpecId(String docSpecId) {
		this.docSpecId = docSpecId;
	}




	public void savePartner(){
    	
		PartnerVO partnerVO = new PartnerVO();
		partnerVO.setNumber(number);
		partnerVO.setHomeCommId(homeCommId);
		partnerVO.setName(name);
		partnerVO.setFullHomeCommId(fullHomeCommId);
		
		partnerVO.setUseSpecVersion(useSpecVersion);
		partnerVO.setAcpCheck(acpCheck);
		partnerVO.setPrefSpec(prefSpec);
		if(docSpecId.trim().equals("")){
			partnerVO.setDocSpecId(null); 
		}
		else{
			partnerVO.setDocSpecId(docSpecId); 

		}

		
		//These values are for VAP table
		partnerVO.setDomain(domain);
		partnerVO.setCommunityIdPrifix(communityIdPrifix);
		partnerVO.setContact(contact);
		partnerVO.setPhoneNumber(phoneNumber);
		partnerVO.setActive("Y");
		partnerVO.setConsumerOnly(consumerOnly);
		
		
	
		PartnerValidationVO partnerValidationVO = validateNewPartner(partnerVO);
		
		if (partnerValidationVO.isValidationPassed()){
			service.addPartner(partnerVO);
			

			init();

	        FacesMessage msg = new FacesMessage("Successful", "New Partner has been saved");
	        FacesContext.getCurrentInstance().addMessage(null, msg);

	        clearFields();
			//refresh();
			//return "/pages/partnermanagement/onboard.xhtml?faces-redirect=true";
			return ;
			
		}else{
			FacesMessage msg = new FacesMessage("Save Failed", partnerValidationVO.getErrorMgs());
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			FacesContext.getCurrentInstance().addMessage(null, msg);
	        FacesContext.getCurrentInstance().validationFailed();

			return ;
		}
    	
    }
    
    public void updatePartner(PartnerVO partnerVO){

    	service.updatePartner(partnerVO);
    }

    private PartnerValidationVO validateNewPartner(PartnerVO newPartnerVO){
    	boolean validationPassed = true;

    	Iterator<PartnerVO> it ;
    	String errorMsg = "";
    	PartnerValidationVO partnerValidationVO = new PartnerValidationVO();

    	it = getPartners().iterator();
    	while(it.hasNext())
    	{
    		PartnerVO partnerVO = it.next();

    		if (partnerVO.getNumber().trim().equalsIgnoreCase(newPartnerVO.getNumber().trim())){
    			validationPassed = false;
    			errorMsg = errorMsg + "Parner Number,";
    			break;
    		}
    	}

    	it = getPartners().iterator();
    	while(it.hasNext())
    	{
    		PartnerVO partnerVO = it.next();

    		if (partnerVO.getName().trim().equalsIgnoreCase(newPartnerVO.getName().trim())){
    			validationPassed = false;
    			errorMsg = errorMsg + "Parner Name,";
    			break;
    		}
    	}

    	it = getPartners().iterator();
    	while(it.hasNext())
    	{
    		PartnerVO partnerVO = it.next();

    		if (partnerVO.getHomeCommId().trim().equalsIgnoreCase(newPartnerVO.getHomeCommId().trim())){
    			validationPassed = false;
    			errorMsg = errorMsg + "Home Community ID,";

    			break;
    		}
    	}    	

    	it = getPartners().iterator();
    	while(it.hasNext())
    	{
    		PartnerVO partnerVO = it.next();

    		if (partnerVO.getFullHomeCommId().trim().equalsIgnoreCase(newPartnerVO.getFullHomeCommId().trim())){
    			validationPassed = false;
    			errorMsg = errorMsg + "Full Home Community ID ";
    			break;
    		}
    	} 


    	if (errorMsg.length() > 0 && errorMsg.charAt(errorMsg.length()-1)==',') {
    		errorMsg = errorMsg.substring(0, errorMsg.length()-1);
    	}


    	errorMsg = errorMsg+ " already exist";

    	partnerValidationVO.setValidationPassed(validationPassed);
    	partnerValidationVO.setErrorMgs(errorMsg);

    	return partnerValidationVO;

    }
    
    private PartnerValidationVO validateEditedPartner(PartnerVO editedPartnerVO){
    	boolean validationPassed = true;

    	Iterator<PartnerVO> it ;
    	String errorMsg = "";
    	PartnerValidationVO partnerValidationVO = new PartnerValidationVO();

    	it = getPartners().iterator();
    	while(it.hasNext())
    	{
    		PartnerVO partnerVO = it.next();
    		if(editedPartnerVO.getId() != partnerVO.getId()){
    			if (partnerVO.getNumber().trim().equalsIgnoreCase(editedPartnerVO.getNumber().trim())){
    				validationPassed = false;
    				errorMsg = errorMsg + "Parner Number,";
    				break;
    			}
    		}

    	}

    	it = getPartners().iterator();
    	while(it.hasNext())
    	{
    		PartnerVO partnerVO = it.next();

    		if(editedPartnerVO.getId() != partnerVO.getId()){
    			if (partnerVO.getName().trim().equalsIgnoreCase(editedPartnerVO.getName().trim())){
    				validationPassed = false;
    				errorMsg = errorMsg + "Parner Name,";
    				break;
    			}
    		}


    	}

    	it = getPartners().iterator();
    	while(it.hasNext())
    	{
    		PartnerVO partnerVO = it.next();

    		if(editedPartnerVO.getId() != partnerVO.getId()){
    			if (partnerVO.getHomeCommId().trim().equalsIgnoreCase(editedPartnerVO.getHomeCommId().trim())){
    				validationPassed = false;
    				errorMsg = errorMsg + "Home Community ID,";

    				break;
    			}
    		}


    	}    	

    	it = getPartners().iterator();
    	while(it.hasNext())
    	{
    		PartnerVO partnerVO = it.next();

    		if(editedPartnerVO.getId() != partnerVO.getId()){
    			if (partnerVO.getFullHomeCommId().trim().equalsIgnoreCase(editedPartnerVO.getFullHomeCommId().trim())){
    				validationPassed = false;
    				errorMsg = errorMsg + "Full Home Community ID ";
    				break;
    			}
    		}


    	} 

    	if (errorMsg.length() > 0 && errorMsg.charAt(errorMsg.length()-1)==',') {
    		errorMsg = errorMsg.substring(0, errorMsg.length()-1);
    	}    	
    	errorMsg = errorMsg+ " already exist";

    	partnerValidationVO.setValidationPassed(validationPassed);
    	partnerValidationVO.setErrorMgs(errorMsg);

    	return partnerValidationVO;

    }    


	public List<PartnerVO> getFilteredPartners() {
		return filteredPartners;
	}


	public void setFilteredPartners(List<PartnerVO> filteredPartners) {
		this.filteredPartners = filteredPartners;
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



	public String getConsumerOnly() {
		return consumerOnly;
	}


	public void setConsumerOnly(String consumerOnly) {
		this.consumerOnly = consumerOnly;
	}
	
	
    private void clearFields(){
    	name = "";
    	number = "";
    	homeCommId = "";
    	fullHomeCommId = "";
    	domain = "";
    	communityIdPrifix = "";
    	contact = "";
    	phoneNumber = "";
    	consumerOnly = "";
    }


	public List<PartnerVO> getSelectedPartners() {
		return selectedPartners;
	}


	public void setSelectedPartners(List<PartnerVO> selectedPartners) {
		this.selectedPartners = selectedPartners;
	}
    
    
	public List<DocSpecTypesVO> getDocSpecTypes(){
		
		return service.getDocSpecTypes();
	}
	
	public DocSpecTypesVO[] getDocSpecTypesArray(){
		
		List<DocSpecTypesVO> listDocSpecTypesVO = service.getDocSpecTypes();
		
		DocSpecTypesVO[] DocSpecTypesVOArray = new DocSpecTypesVO[listDocSpecTypesVO.size()];
		
		listDocSpecTypesVO.toArray(DocSpecTypesVOArray);
		
		return DocSpecTypesVOArray;
	}
	
	
	
}
