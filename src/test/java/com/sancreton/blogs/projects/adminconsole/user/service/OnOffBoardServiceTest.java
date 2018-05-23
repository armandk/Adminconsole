package com.sancreton.blogs.projects.adminconsole.user.service;

import java.util.Iterator;
import java.util.List;

import com.sancreton.blogs.projects.adminconsole.valueobject.*;
import com.sancreton.blogs.projects.adminconsole.entities.Facilities;
import com.sancreton.blogs.projects.adminconsole.entities.VapAllowedOrg;
import com.sancreton.blogs.projects.adminconsole.service.PartnerManagementService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_Test.xml")
public class OnOffBoardServiceTest {

	@Autowired
	PartnerManagementService onOffBoardService;
	
	@Test
	public void testGetFacilitiesById() {
		
		Facilities facilities = (Facilities)onOffBoardService.getFacilitiesById(1);
		if ( facilities != null)
			System.out.println(facilities.toString());
		
	//assertEquals("1", facilitiesService.getFacilitiesById(1).getId());
	}
	
	@Test
	public void testupdateFacilities() {
		
//		Facilities facilities = (Facilities)onOffBoardService.getFacilitiesById(16);
//		String facilityNumber = facilities.getNumber();
//		facilities.setNumber(facilityNumber+"-off");
//		
//		System.out.println(facilities.toString());
//		
//		onOffBoardService.updateFacilities(facilities);
		
		
	}
	
	@Test
	public void testAddFacilities() {
		
//		Facilities facilities = new Facilities();
//		facilities.setNumber("NUMBER_1");
//		facilities.setHomeCommId("1.2.3.4.5.6.7");
//		facilities.setName("TEST-1");
//		facilities.setFullHomeCommId("test1");
//		
//		System.out.println(facilities.toString());
//		
//		onOffBoardService.addFacilities(facilities);
		
		
	}
	
	@Test
	public void testGetPartnerById() {
		
		PartnerVO partnerVO = onOffBoardService.getPartnerById(1);
		System.out.println(partnerVO.toString());
		
		partnerVO = onOffBoardService.getPartnerById(2);
		System.out.println(partnerVO.toString());
		
	}	
	
	@Test
	public void testGetPartners() {
		
		List<PartnerVO> listPartnerVO  = onOffBoardService.getPartners();
		
    	Iterator<PartnerVO> it = listPartnerVO.iterator();
    	while(it.hasNext())
    	{
    		PartnerVO partnerVO = it.next();
    		System.out.println(partnerVO.toString());

    	}
		
	}	
	
	@Test
	public void testGetDocSpecTypes() {
		
		List<DocSpecTypesVO>  listDocSpecTypesVO  = onOffBoardService.getDocSpecTypes();
		
    	Iterator<DocSpecTypesVO> it = listDocSpecTypesVO.iterator();
    	while(it.hasNext())
    	{
    		DocSpecTypesVO docSpecTypesVO = it.next();
    		System.out.println(docSpecTypesVO.toString());

    	}
		
	}	
	
	@Test
	public void testAddPartner() {
		
//		PartnerVO partnerVO = new PartnerVO();
//		partnerVO.setNumber("NUMBER_2");
//		partnerVO.setHomeCommId("NUMBER_2");
//		partnerVO.setName("NUMBER_2");
//		partnerVO.setFullHomeCommId("NUMBER_2");
//		partnerVO.setAcpCheck("Y");
//		partnerVO.setUseSpecVersion("test1");
//		partnerVO.setPrefSpec("Y");
//		partnerVO.setDocSpecId("1");
//		
//		System.out.println(partnerVO.toString());
//		
//		onOffBoardService.addPartner(partnerVO);
		
	}	
	
	@Test
	public void testUpdatePartner() {
		
//		PartnerVO partnerVO = (PartnerVO)onOffBoardService.getPartnerById(17);
//		
//		partnerVO.setOnboard(true);
//		
//		System.out.println(partnerVO.toString());
//		
//		onOffBoardService.updatePartner(partnerVO);
		
		
	}	
	
	@Test
	public void testAddFacilitiesAndOperations() {
		
//		Facilities facilities = new Facilities();
//		facilities.setNumber("NUMBER_1");
//		facilities.setHomeCommId("1.2.3.4.5.6.7");
//		facilities.setName("TEST-1");
//		facilities.setFullHomeCommId("test1");
//		
//		FacilitiesOperartions operation = new FacilitiesOperartions(-1,"Y","Y","Y","Y","Y","Y");
//		facilities.setFacilitiesOperartions(operation);
//		
//		System.out.println(facilities.toString());
//		
//		onOffBoardService.addFacilities(facilities);
		
		
	}	
	
	@Test
	public void testAddPartnerWithOperations() {
		
//		PartnerVO partnerVO = new PartnerVO();
//		partnerVO.setNumber("NUMBER_2");
//		partnerVO.setHomeCommId("1.2.3.4.5.6.7");
//		partnerVO.setName("TEST-1");
//		partnerVO.setFullHomeCommId("test1");
//		
//		partnerVO.setInboundPD("Y");
//		partnerVO.setInboundQD("Y");
//		partnerVO.setInboundRD("Y");
//		partnerVO.setOutboundPD("Y");
//		partnerVO.setOutboundQD("Y");
//		partnerVO.setOutboundRD("Y");		
//		
//		System.out.println(partnerVO.toString());
//		
//		onOffBoardService.addPartner(partnerVO);
		
	}	
	
	@Test
	public void testupdateFacilitiesAndOperations() {
		
//		Facilities facilities = (Facilities)onOffBoardService.getFacilitiesById(16);
//		String facilityNumber = facilities.getNumber();
//		facilities.setNumber(facilityNumber+"-off");
//		
//		FacilitiesOperartions operation = facilities.getFacilitiesOperartions();
//		operation.setInboundPD("N");
//		
//		System.out.println(facilities.toString());
//		
//		onOffBoardService.updateFacilities(facilities);
		
		
	}
	
	@Test
	public void testUpdatePartnerWithOperations() {
		
//		PartnerVO partnerVO = (PartnerVO)onOffBoardService.getPartnerById(17);
//		
//		partnerVO.setInboundPD("N");
//		partnerVO.setInboundQD("N");
//		partnerVO.setInboundRD("N");
//		partnerVO.setOutboundPD("N");
//		partnerVO.setOutboundQD("N");
//		partnerVO.setOutboundRD("N");		
//		partnerVO.setActive("N");
//		System.out.println(partnerVO.toString());
//		
//		onOffBoardService.updatePartner(partnerVO);
		
		
	}
	
	@Test
	public void testGetVapAllowedOrgById() {
		
		VapAllowedOrg vapAllowedOrg = (VapAllowedOrg)onOffBoardService.getVapAllowedOrgById(1);
		
		if(null != vapAllowedOrg){
			System.out.println(vapAllowedOrg.toString());

		}else{
			
			System.out.println("No data found");
		}
		
	}	
	
	@Test
	public void testGetVapAllowedOrg() {
		
		List<VapAllowedOrg> listVapAllowedOrg = onOffBoardService.getVapAllowedOrg();
		
		System.out.println(listVapAllowedOrg.toString());
		
	}		
	
	@Test
	public void testAddVapAllowedOrg() {
		
//		VapAllowedOrg vapAllowedOrg = new VapAllowedOrg();
//		
//		vapAllowedOrg.setNumber("200NHP");
//		vapAllowedOrg.setOid("1.2.840.114350.1.13.105.3.7.3.688884.100");
//		vapAllowedOrg.setName("Hawaii Pacific Health");
//		vapAllowedOrg.setDomain("*NID");
//		vapAllowedOrg.setCommunityIdPrifix("urn:oid:");
//		vapAllowedOrg.setContact("Conatct Someone");
//		vapAllowedOrg.setPhoneNumber("808-535-7082");
//		vapAllowedOrg.setActive("Y");
//		vapAllowedOrg.setConsumerOnly("N");
//		
//		onOffBoardService.addVapAllowedOrg(vapAllowedOrg);
		
		
	}		
	
	@Test
	public void testUpdateVapAllowedOrg(){
		
//		VapAllowedOrg vapAllowedOrg = (VapAllowedOrg)onOffBoardService.getVapAllowedOrgById(1);
//		vapAllowedOrg.setActive("N");
//		onOffBoardService.updateVapAllowedOrg(vapAllowedOrg);
	}

}
