package com.sancreton.blogs.projects.adminconsole.dao;


import java.util.List;

import com.sancreton.blogs.projects.adminconsole.entities.Documents;

public interface DocumentsDao {
	
	public  List<Documents> getDocuments(String patientFirstName,String patientLastName, String patientSSN);
	public Documents getSimpleId(Integer id);
}
