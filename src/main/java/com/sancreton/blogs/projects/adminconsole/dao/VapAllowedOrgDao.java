package com.sancreton.blogs.projects.adminconsole.dao;

import com.sancreton.blogs.projects.adminconsole.entities.VapAllowedOrg;

import java.util.List;

import org.hibernate.SessionFactory;

public interface VapAllowedOrgDao {

	public abstract SessionFactory getSessionFactory();

	public abstract void setSessionFactory(SessionFactory sessionFactory);

	public abstract void addVapAllowedOrg(VapAllowedOrg vapAllowedOrg);

	public abstract void updateVapAllowedOrg(VapAllowedOrg vapAllowedOrg);

	public abstract VapAllowedOrg getVapAllowedOrgById(int id);

	public abstract VapAllowedOrg getVapAllowedOrgByOrgNumber(String number);

	public abstract List<VapAllowedOrg> getVapAllowedOrg();

}