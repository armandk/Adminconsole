package com.sancreton.blogs.projects.adminconsole.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.sancreton.blogs.projects.adminconsole.valueobject.*;
import com.sancreton.blogs.projects.adminconsole.dao.DocSpecTypesDao;
import com.sancreton.blogs.projects.adminconsole.dao.VapAllowedOrgDao;
import com.sancreton.blogs.projects.adminconsole.daoImpl.FacilitiesDaoImpl;
import com.sancreton.blogs.projects.adminconsole.entities.DocSpecTypes;
import com.sancreton.blogs.projects.adminconsole.entities.Facilities;
import com.sancreton.blogs.projects.adminconsole.entities.FacilitiesOperartions;
import com.sancreton.blogs.projects.adminconsole.service.PartnerManagementService;
import com.sancreton.blogs.projects.adminconsole.entities.VapAllowedOrg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("partnerManagementService")
@Transactional(readOnly = true)
public class PartnerManagementServiceImpl implements PartnerManagementService {

	// FacilitiesDAO is injected...
	@Autowired
	FacilitiesDaoImpl facilitiesDAO;
	
	// VapAllowedOrgDAO is injected...
	@Autowired
	VapAllowedOrgDao vapAllowedOrgDAO;
	
	// DocSpecTypesDAOImpl is injected...
	@Autowired
	DocSpecTypesDao DocSpecTypesDAO;

	/* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.serviceImpl.PartnerManagementService#getFacilitiesDAO()
	 */
	@Override
	public FacilitiesDaoImpl getFacilitiesDAO() {
		return facilitiesDAO;
	}

	/* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.serviceImpl.PartnerManagementService#setFacilitiesDAO(com.sancreton.blogs.projects.adminconsole.daoImpl.FacilitiesDaoImpl)
	 */
	@Override
	public void setFacilitiesDAO(FacilitiesDaoImpl facilitiesDAO) {
		this.facilitiesDAO = facilitiesDAO;
	}
	
	
	 /* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.serviceImpl.PartnerManagementService#getVapAllowedOrgDAO()
	 */
	@Override
	public VapAllowedOrgDao getVapAllowedOrgDAO() {
		return vapAllowedOrgDAO;
	}

	/* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.serviceImpl.PartnerManagementService#setVapAllowedOrgDAO(com.sancreton.blogs.projects.adminconsole.dao.VapAllowedOrgDao)
	 */
	@Override
	public void setVapAllowedOrgDAO(VapAllowedOrgDao vapAllowedOrgDAO) {
		this.vapAllowedOrgDAO = vapAllowedOrgDAO;
	}
	
	

	/* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.serviceImpl.PartnerManagementService#getDocSpecTypesDAO()
	 */
	@Override
	public DocSpecTypesDao getDocSpecTypesDAO() {
		return DocSpecTypesDAO;
	}

	/* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.serviceImpl.PartnerManagementService#setDocSpecTypesDAO(com.sancreton.blogs.projects.adminconsole.dao.DocSpecTypesDao)
	 */
	@Override
	public void setDocSpecTypesDAO(DocSpecTypesDao docSpecTypesDAO) {
		DocSpecTypesDAO = docSpecTypesDAO;
	}

	/* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.serviceImpl.PartnerManagementService#addFacilities(com.sancreton.blogs.projects.adminconsole.entities.Facilities)
	 */
	@Override
	@Transactional(readOnly = false)
    public void addFacilities(Facilities facilities){
    	
    	getFacilitiesDAO().addFacilities(facilities);
    }	
    
	 /* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.serviceImpl.PartnerManagementService#updateFacilities(com.sancreton.blogs.projects.adminconsole.entities.Facilities)
	 */
	@Override
	@Transactional(readOnly = false)
    public void updateFacilities(Facilities facilities){
    	
    	getFacilitiesDAO().updateFacilities(facilities);
    } 
    
    /* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.serviceImpl.PartnerManagementService#getFacilitiesById(int)
	 */
    @Override
	public Facilities getFacilitiesById(int id){
    	
    	return getFacilitiesDAO().getFacilitiesById(id);
    }
    
    /* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.serviceImpl.PartnerManagementService#getFacilities()
	 */
    @Override
	public List<Facilities> getFacilities(){
    	
    	return getFacilitiesDAO().getFacilities();
    }
    
    private PartnerVO mapObject(Facilities facilities){
    	
    	PartnerVO partnerVO = new PartnerVO();
    	
    	if (facilities != null) {
	    	partnerVO.setId(facilities.getId());
	    	partnerVO.setNumber(facilities.getNumber());
	    	partnerVO.setName(facilities.getName());
	    	partnerVO.setHomeCommId(facilities.getHomeCommId());
	    	partnerVO.setFullHomeCommId(facilities.getFullHomeCommId());
	    	
	    	partnerVO.setUseSpecVersion(facilities.getUseSpecVersion());
	    	partnerVO.setAcpCheck(facilities.getAcpCheck());
	    	partnerVO.setPrefSpec(facilities.getPrefSpec());
	    	
	    	if(null != facilities.getDocSpecId())
	        	partnerVO.setDocSpecId(facilities.getDocSpecId().toString());
	        	
	        	
	        	if (facilities.getNumber().contains("-off")){
	        		partnerVO.setOnboard(false);
	        		partnerVO.setOnboardStatus("OffBoard");

	        	}
	        	FacilitiesOperartions facilitiesOperartions = facilities.getFacilitiesOperartions();
	        	
	        	if(facilitiesOperartions != null){
	        		
	            	partnerVO.setInboundPD(facilitiesOperartions.getInboundPD());
	            	partnerVO.setInboundQD(facilitiesOperartions.getInboundQD());
	            	partnerVO.setInboundRD(facilitiesOperartions.getInboundRD());
	            	partnerVO.setOutboundPD(facilitiesOperartions.getOutboundPD());
	            	partnerVO.setOutboundQD(facilitiesOperartions.getOutboundQD());
	            	partnerVO.setOutboundRD(facilitiesOperartions.getOutboundRD());
	        	}
	        	
    	}
    	
    	return partnerVO;
    }
    
    private Facilities mapObject(PartnerVO partnerVO){
    	Facilities facilities = new Facilities();
    	
    	facilities.setId(partnerVO.getId());
    	facilities.setNumber(partnerVO.getNumber());
    	facilities.setName(partnerVO.getName());
    	facilities.setHomeCommId(partnerVO.getHomeCommId());
    	facilities.setFullHomeCommId(partnerVO.getFullHomeCommId());
    	
    	facilities.setUseSpecVersion(partnerVO.getUseSpecVersion());
    	facilities.setAcpCheck(partnerVO.getAcpCheck());
    	facilities.setPrefSpec(partnerVO.getPrefSpec());
    	
    	if(null != partnerVO.getDocSpecId())
    	facilities.setDocSpecId(new Integer(partnerVO.getDocSpecId().trim()));
    	
    	
    	if(partnerVO.getOnboardStatus().equals("OnBoard")){
    		if(facilities.getNumber().contains("-off")){
    			String number = facilities.getNumber();
    			number = number.replaceFirst("-off", "");
    			facilities.setNumber(number);
    		}
    		
    	}
    	else{

    		if(! facilities.getNumber().contains("-off")){
    			facilities.setNumber(partnerVO.getNumber()+"-off");
    		}
    		
    	}
    	
    	FacilitiesOperartions facilitiesOperartions = new FacilitiesOperartions();
    	facilitiesOperartions.setId(partnerVO.getId());
    	
	    	if(null == partnerVO.getInboundPD()){
	    		facilitiesOperartions.setInboundPD("Y");
	    	}else{
	    		facilitiesOperartions.setInboundPD(partnerVO.getInboundPD());
	    	}
	    	
	    	if(null == partnerVO.getInboundQD()){
	    		facilitiesOperartions.setInboundQD("Y");
	    	}else{
	    		facilitiesOperartions.setInboundQD(partnerVO.getInboundQD());
	    	}
	    	
	    	if(null == partnerVO.getInboundRD()){
	    		facilitiesOperartions.setInboundRD("Y");
	    	}else{
	    		facilitiesOperartions.setInboundRD(partnerVO.getInboundRD());
	    	}
	    	if(null == partnerVO.getOutboundPD()){
	    		facilitiesOperartions.setOutboundPD("Y");
	    	}else{
	    		facilitiesOperartions.setOutboundPD(partnerVO.getOutboundPD());
	    	}
	    	
	    	if(null == partnerVO.getOutboundQD()){
	    		facilitiesOperartions.setOutboundQD("Y");
	    	}else{
	    		facilitiesOperartions.setOutboundQD(partnerVO.getOutboundQD());
	    	}
	    	
	    	if(null == partnerVO.getOutboundRD()){
	    		facilitiesOperartions.setOutboundRD("Y");
	    	}else{
	    		facilitiesOperartions.setOutboundRD(partnerVO.getOutboundRD());
	    	}	    	
//    	facilitiesOperartions.setInboundPD(partnerVO.getInboundPD());
//    	facilitiesOperartions.setInboundQD(partnerVO.getInboundQD());
//    	facilitiesOperartions.setInboundRD(partnerVO.getInboundRD());
//    	facilitiesOperartions.setOutboundPD(partnerVO.getOutboundPD());
//    	facilitiesOperartions.setOutboundQD(partnerVO.getOutboundQD());
//    	facilitiesOperartions.setOutboundRD(partnerVO.getOutboundRD());
    	
    	facilities.setFacilitiesOperartions(facilitiesOperartions);
    	
    	return facilities;
    	
    }
    
	 /* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.serviceImpl.PartnerManagementService#addPartner(com.sancreton.blogs.projects.adminconsole.valueobject.PartnerVO)
	 */
	@Override
	@Transactional(readOnly = false)
	    public void addPartner(PartnerVO partnerVO){
	    	
		 	Facilities facilities = mapObject(partnerVO);
	    	getFacilitiesDAO().addFacilities(facilities);
	    	
	    	// for VAP table
	    	VapAllowedOrg vapAllowedOrg = mapObjectForVAPAdd(partnerVO);
	    	getVapAllowedOrgDAO().addVapAllowedOrg(vapAllowedOrg);
	    }	
	    
		 /* (non-Javadoc)
		 * @see com.sancreton.blogs.projects.adminconsole.serviceImpl.PartnerManagementService#updatePartner(com.sancreton.blogs.projects.adminconsole.valueobject.PartnerVO)
		 */
		@Override
		@Transactional(readOnly = false)
	    public void updatePartner(PartnerVO partnerVO){
	    	
			Facilities facilities = mapObject(partnerVO); 
	    	getFacilitiesDAO().updateFacilities(facilities);
	    	
	    	// for VAP table
	    	VapAllowedOrg vapAllowedOrg = mapObjectForVAPUpdate(partnerVO);
	    	
	    	if(vapAllowedOrg != null){
	    		getVapAllowedOrgDAO().updateVapAllowedOrg(vapAllowedOrg);
	    	}

	    } 
	    
	    /* (non-Javadoc)
		 * @see com.sancreton.blogs.projects.adminconsole.serviceImpl.PartnerManagementService#getPartnerById(int)
		 */
	    @Override
		public PartnerVO getPartnerById(int id){
	    	
	    	Facilities facilities = getFacilitiesDAO().getFacilitiesById(id);
	    	PartnerVO partnerVO = mapObject(facilities);
	    	return partnerVO;
	    }
    
	    /* (non-Javadoc)
		 * @see com.sancreton.blogs.projects.adminconsole.serviceImpl.PartnerManagementService#getPartners()
		 */
	    @Override
		public List<PartnerVO> getPartners(){
	    	
	    	List<Facilities>  listFacilities = getFacilitiesDAO().getFacilities();
	    	
	    	List<PartnerVO> listPartnerVO = new ArrayList<PartnerVO>();
	    	
	    	Iterator<Facilities> it = listFacilities.iterator();
	    	while(it.hasNext())
	    	{
	    		Facilities facilities = it.next();
	    		PartnerVO partnerVO = mapObject(facilities);
	    		listPartnerVO.add(partnerVO);
	    	}
	    	
	    	return listPartnerVO;
	    	
	    }
	    
	  //-------------------------------
	    
		/* (non-Javadoc)
		 * @see com.sancreton.blogs.projects.adminconsole.serviceImpl.PartnerManagementService#addVapAllowedOrg(com.sancreton.blogs.projects.adminconsole.entities.VapAllowedOrg)
		 */
		@Override
		@Transactional(readOnly = false)
	    public void addVapAllowedOrg(VapAllowedOrg vapAllowedOrg){
	    	
	    	getVapAllowedOrgDAO().addVapAllowedOrg(vapAllowedOrg);
	    }	
	    
		 /* (non-Javadoc)
		 * @see com.sancreton.blogs.projects.adminconsole.serviceImpl.PartnerManagementService#updateVapAllowedOrg(com.sancreton.blogs.projects.adminconsole.entities.VapAllowedOrg)
		 */
		@Override
		@Transactional(readOnly = false)
	    public void updateVapAllowedOrg(VapAllowedOrg vapAllowedOrg){
	    	
	    	getVapAllowedOrgDAO().updateVapAllowedOrg(vapAllowedOrg);
	    } 
	    
	    /* (non-Javadoc)
		 * @see com.sancreton.blogs.projects.adminconsole.serviceImpl.PartnerManagementService#getVapAllowedOrgById(int)
		 */
	    @Override
		public VapAllowedOrg getVapAllowedOrgById(int id){
	    	
	    	return getVapAllowedOrgDAO().getVapAllowedOrgById(id);
	    }
	    
	    /* (non-Javadoc)
		 * @see com.sancreton.blogs.projects.adminconsole.serviceImpl.PartnerManagementService#getVapAllowedOrg()
		 */
	    @Override
		public List<VapAllowedOrg> getVapAllowedOrg(){
	    	
	    	return getVapAllowedOrgDAO().getVapAllowedOrg();
	    }
		
	//---------------------------------	

	   private VapAllowedOrg mapObjectForVAPAdd(PartnerVO partnerVO){
		   
		   VapAllowedOrg vapAllowedOrg = new VapAllowedOrg();
		   
		   vapAllowedOrg.setId(partnerVO.getId());
		   vapAllowedOrg.setNumber(partnerVO.getNumber());
		   vapAllowedOrg.setOid(partnerVO.getHomeCommId());
		   vapAllowedOrg.setName(partnerVO.getName());
		   vapAllowedOrg.setDomain(partnerVO.getDomain());
		   vapAllowedOrg.setCommunityIdPrifix(partnerVO.getCommunityIdPrifix());
		   vapAllowedOrg.setContact(partnerVO.getContact());
		   vapAllowedOrg.setPhoneNumber(partnerVO.getPhoneNumber());
		   //vapAllowedOrg.setActive(partnerVO.getActive());
		   vapAllowedOrg.setConsumerOnly(partnerVO.getConsumerOnly());
		   
		   if(partnerVO.getOnboardStatus().equals("OnBoard")){
			   vapAllowedOrg.setActive("Y");
		   }else{
			   vapAllowedOrg.setActive("N");
		   }
		   
		   
		   return vapAllowedOrg;
	   }
	   
	   private VapAllowedOrg mapObjectForVAPUpdate(PartnerVO partnerVO){
		   
		   String orgNumber = partnerVO.getNumber();
		   
		   VapAllowedOrg vapAllowedOrg = vapAllowedOrgDAO.getVapAllowedOrgByOrgNumber(orgNumber);
		   
		   if(vapAllowedOrg == null){
			   return null;
		   }
		   
		   //vapAllowedOrg.setId(partnerVO.getId());
		   vapAllowedOrg.setNumber(partnerVO.getNumber());
		   vapAllowedOrg.setOid(partnerVO.getHomeCommId());
		   vapAllowedOrg.setName(partnerVO.getName());
		   vapAllowedOrg.setDomain(partnerVO.getDomain());
		   vapAllowedOrg.setCommunityIdPrifix(partnerVO.getCommunityIdPrifix());
		   vapAllowedOrg.setContact(partnerVO.getContact());
		   vapAllowedOrg.setPhoneNumber(partnerVO.getPhoneNumber());
		   vapAllowedOrg.setConsumerOnly(partnerVO.getConsumerOnly());
		   
		   if(partnerVO.getOnboardStatus().equals("OnBoard")){
			   vapAllowedOrg.setActive("Y");
		   }else{
			   vapAllowedOrg.setActive("N");
		   }
		   
		   
		   return vapAllowedOrg;
	   }	   
	   
	    /* (non-Javadoc)
		 * @see com.sancreton.blogs.projects.adminconsole.serviceImpl.PartnerManagementService#getDocSpecTypes()
		 */
	    @Override
		public List<DocSpecTypesVO> getDocSpecTypes(){
	    	
	    	List<DocSpecTypes>  docSpecTypesList = getDocSpecTypesDAO().getDocSpecTypes();
	    	
	    	List<DocSpecTypesVO> docSpecTypesVOList = new ArrayList<DocSpecTypesVO>();
	    	
	    	Iterator<DocSpecTypes> it = docSpecTypesList.iterator();
	    	while(it.hasNext())
	    	{
	    		DocSpecTypes docSpecTypes = (DocSpecTypes)it.next();
	    		
	    		DocSpecTypesVO docSpecTypesVO = new DocSpecTypesVO();
	    		docSpecTypesVO.setDocSpecId(docSpecTypes.getDocSpecId().toString());
	    		docSpecTypesVO.setDocSpecType(docSpecTypes.getDocSpecType());
	    		docSpecTypesVO.setDocSpecDesc(docSpecTypes.getDocSpecDesc());
	    		
	    		docSpecTypesVOList.add(docSpecTypesVO);
	    		
	    	}
	    	
	    	return docSpecTypesVOList;
	    }	   
}
