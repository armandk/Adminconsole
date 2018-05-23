package com.sancreton.blogs.projects.adminconsole.service;


import com.sancreton.blogs.projects.adminconsole.entities.UDDIStatus;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface UDDIService {
	
	List<UDDIStatus> getUDDIStatusList() throws DataAccessException;

}
