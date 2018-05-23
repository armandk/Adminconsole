package com.sancreton.blogs.projects.adminconsole.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.sancreton.blogs.projects.adminconsole.dao.UDDIDao;
import com.sancreton.blogs.projects.adminconsole.entities.UDDIStatus;

@Repository
public class UDDIDaoImpl implements UDDIDao {

    @Autowired
    private SessionFactory sessionFactory;
    
	@SuppressWarnings("unchecked")
	public List<UDDIStatus> getUDDIStatusList() throws DataAccessException {
		return (ArrayList<UDDIStatus>) getSessionFactory().getCurrentSession()
				.createQuery("FROM com.sancreton.blogs.projects.adminconsole.entities.UDDIStatus uddiStatus ORDER BY uddiStatus.lastFileUpdateTime desc")
				.list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
