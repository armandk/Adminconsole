package com.sancreton.blogs.projects.adminconsole.daoImpl;

import com.sancreton.blogs.projects.adminconsole.dao.DocumentsDao;
import com.sancreton.blogs.projects.adminconsole.entities.Documents;

import java.io.Serializable;
import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentsDaoImpl implements DocumentsDao, Serializable{
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
    private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    
	
	
	@SuppressWarnings({ "unchecked"})
	public List<Documents> getDocuments(String patientFirstName,
			String patientLastName, String patientSSN) {
		
			  Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Documents.class)
					    .setProjection(Projections.projectionList()
					    .add(Projections.property("id"), "id")
					 //   .add(Projections.property("patientId"), "patientId")
					    .add(Projections.property("rawDataSize"), "rawDataSize")
					    .add(Projections.property("creationTime"), "creationTime")
					    .add(Projections.property("lastAccessedTime"), "lastAccessedTime"))
					  
					     .add( Restrictions.disjunction()
     
        .add( Restrictions.eq("patientLastName", patientLastName ) )
        .add( Restrictions.eq("patientFirstName", patientFirstName) )
        .add( Restrictions.eq("patientSSN", patientSSN ) )
    ) 
					    .setResultTransformer(Transformers.aliasToBean(Documents.class));

					  List<Documents> list = cr.list();
		  return list;
		  
		  
	}

	@Override
	public Documents getSimpleId(Integer id) {
		Session session = sessionFactory.getCurrentSession();
        return (Documents)session.get(Documents.class, id);
	}

}
