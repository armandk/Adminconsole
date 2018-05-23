package com.sancreton.blogs.projects.adminconsole.dao;

import com.sancreton.blogs.projects.adminconsole.entities.Facilities;

import java.util.List;

public interface FacilitiesDao {
	
	  public void addFacilities(Facilities facilities) ;

	    public void updateFacilities(Facilities facilities) ;
	    public Facilities getFacilitiesById(int id);
	    
	    public List<Facilities> getFacilities();
	   
}
