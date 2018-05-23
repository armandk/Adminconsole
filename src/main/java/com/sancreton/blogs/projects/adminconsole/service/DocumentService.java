package com.sancreton.blogs.projects.adminconsole.service;

import com.sancreton.blogs.projects.adminconsole.entities.Documents;
import java.util.List;

public interface DocumentService {

	
	public List<Documents> searchResults(String patientFirstName, String patientLastName, String patientSSN);
	public Documents getSimpleId(Integer id);
}
