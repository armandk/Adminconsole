package com.sancreton.blogs.projects.adminconsole.dao;

import com.sancreton.blogs.projects.adminconsole.entities.UDDIStatus;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface UDDIDao {
	
	List<UDDIStatus> getUDDIStatusList() throws DataAccessException;

}
