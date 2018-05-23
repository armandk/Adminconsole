package com.sancreton.blogs.projects.adminconsole.service;


import com.sancreton.blogs.projects.adminconsole.valueobject.*;
import com.sancreton.blogs.projects.adminconsole.dao.DocSpecTypesDao;
import com.sancreton.blogs.projects.adminconsole.dao.VapAllowedOrgDao;
import com.sancreton.blogs.projects.adminconsole.daoImpl.FacilitiesDaoImpl;
import com.sancreton.blogs.projects.adminconsole.entities.Facilities;
import com.sancreton.blogs.projects.adminconsole.entities.VapAllowedOrg;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface PartnerManagementService {

	public abstract FacilitiesDaoImpl getFacilitiesDAO();

	public abstract void setFacilitiesDAO(FacilitiesDaoImpl facilitiesDAO);

	public abstract VapAllowedOrgDao getVapAllowedOrgDAO();

	public abstract void setVapAllowedOrgDAO(VapAllowedOrgDao vapAllowedOrgDAO);

	public abstract DocSpecTypesDao getDocSpecTypesDAO();

	public abstract void setDocSpecTypesDAO(DocSpecTypesDao docSpecTypesDAO);

	public abstract void addFacilities(Facilities facilities);

	public abstract void updateFacilities(Facilities facilities);

	public abstract Facilities getFacilitiesById(int id);

	public abstract List<Facilities> getFacilities();

	public abstract void addPartner(PartnerVO partnerVO);

	public abstract void updatePartner(PartnerVO partnerVO);

	public abstract PartnerVO getPartnerById(int id);

	public abstract List<PartnerVO> getPartners();

	public abstract void addVapAllowedOrg(VapAllowedOrg vapAllowedOrg);

	public abstract void updateVapAllowedOrg(VapAllowedOrg vapAllowedOrg);

	public abstract VapAllowedOrg getVapAllowedOrgById(int id);

	public abstract List<VapAllowedOrg> getVapAllowedOrg();

	public abstract List<DocSpecTypesVO> getDocSpecTypes();

}