package com.sancreton.blogs.projects.adminconsole.daoImpl;

import com.sancreton.blogs.projects.adminconsole.dao.DocSpecTypesDao;
import com.sancreton.blogs.projects.adminconsole.entities.DocSpecTypes;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DocSpecTypesDaoImpl implements DocSpecTypesDao {

    @Autowired
    private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.daoImpl.DocSpecTypesDao#getSessionFactory()
	 */
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.daoImpl.DocSpecTypesDao#setSessionFactory(org.hibernate.SessionFactory)
	 */
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    
    /* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.daoImpl.DocSpecTypesDao#getDocSpecTypes()
	 */
    @Override
	public List<DocSpecTypes> getDocSpecTypes(){
    	List list = getSessionFactory().getCurrentSession().createQuery(" from  DocSpecTypes").list();
    	return list;
    }
}
