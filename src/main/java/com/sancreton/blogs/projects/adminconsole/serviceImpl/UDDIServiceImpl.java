package com.sancreton.blogs.projects.adminconsole.serviceImpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sancreton.blogs.projects.adminconsole.dao.UDDIDao;
import com.sancreton.blogs.projects.adminconsole.entities.UDDIStatus;
import com.sancreton.blogs.projects.adminconsole.service.UDDIService;

@Service("UDDIService")
@Transactional(readOnly = true)
public class UDDIServiceImpl implements UDDIService {
	
	private static final Log logger = LogFactory.getLog(UDDIServiceImpl.class);
	
	@Autowired
	UDDIDao uddiDao;

	@Transactional(readOnly = true)
	public List<UDDIStatus> getUDDIStatusList() throws DataAccessException {
		return uddiDao.getUDDIStatusList();
	}

}
