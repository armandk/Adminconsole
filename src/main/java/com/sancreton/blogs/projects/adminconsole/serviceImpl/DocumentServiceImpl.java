package com.sancreton.blogs.projects.adminconsole.serviceImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sancreton.blogs.projects.adminconsole.dao.DocumentsDao;
import com.sancreton.blogs.projects.adminconsole.entities.Documents;
import com.sancreton.blogs.projects.adminconsole.service.DocumentService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Service("DocumentService")
@Transactional(readOnly = true)
public class DocumentServiceImpl implements DocumentService, Serializable{

	private static final long serialVersionUID = 1L;
	//DocumentsDAO is injected...
	@Autowired
	DocumentsDao documentsDao;
	@Override
	@Transactional(readOnly = false)
	public List<Documents> searchResults(String patientFirstName,
			String patientLastName, String patientSSN) {
		List<Documents> restultList= documentsDao.getDocuments(patientFirstName, patientLastName, patientSSN);
	
		return restultList;
	}
	@Override
	public Documents getSimpleId(Integer id) {
		Documents docId = documentsDao.getSimpleId(id);
		return docId;
	}

}
