package com.sancreton.blogs.projects.adminconsole.daoImpl;

import java.math.BigDecimal;
import java.util.List;

import com.sancreton.blogs.projects.adminconsole.dao.VapAllowedOrgDao;
import com.sancreton.blogs.projects.adminconsole.entities.VapAllowedOrg;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VapAllowedOrgDaoImpl implements VapAllowedOrgDao {

    @Autowired
    private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.daoImpl.VapAllowedOrgDao#getSessionFactory()
	 */
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.daoImpl.VapAllowedOrgDao#setSessionFactory(org.hibernate.SessionFactory)
	 */
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    
    /* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.daoImpl.VapAllowedOrgDao#addVapAllowedOrg(com.sancreton.blogs.projects.adminconsole.entities.VapAllowedOrg)
	 */
    @Override
	public void addVapAllowedOrg(VapAllowedOrg vapAllowedOrg){
    	
    	int nextId = getNextId();
    	vapAllowedOrg.setId(nextId);
    	getSessionFactory().getCurrentSession().save(vapAllowedOrg);
    }
    

    /* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.daoImpl.VapAllowedOrgDao#updateVapAllowedOrg(com.sancreton.blogs.projects.adminconsole.entities.VapAllowedOrg)
	 */
    @Override
	public void updateVapAllowedOrg(VapAllowedOrg vapAllowedOrg){
    	
    	getSessionFactory().getCurrentSession().update(vapAllowedOrg);
    }    
    
    /* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.daoImpl.VapAllowedOrgDao#getVapAllowedOrgById(int)
	 */
    @Override
	public VapAllowedOrg getVapAllowedOrgById(int id){
    	List list = getSessionFactory().getCurrentSession().createQuery(" from VapAllowedOrg where id=?")
    	.setParameter(0, id).list();
    	
    	if(list.size() > 0){
    		return (VapAllowedOrg)list.get(0);
    	}else{
    		return null;
    	}
    	
    }
    
    /* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.daoImpl.VapAllowedOrgDao#getVapAllowedOrgByOrgNumber(java.lang.String)
	 */
    @Override
	public VapAllowedOrg getVapAllowedOrgByOrgNumber(String number){
    	List list = getSessionFactory().getCurrentSession().createQuery(" from VapAllowedOrg where number=?")
    	.setParameter(0, number).list();
    	
    	if(list.size() > 0){
    		return (VapAllowedOrg)list.get(0);
    	}
    	else{
    		
    		return null;
    	}
    		
    	
    }    
    
    /* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.daoImpl.VapAllowedOrgDao#getVapAllowedOrg()
	 */
    @Override
	public List<VapAllowedOrg> getVapAllowedOrg(){
    	List list = getSessionFactory().getCurrentSession().createQuery(" from VapAllowedOrg").list();
    	return list;
    }
    
    private int getNextId(){
    	
    	List list = getSessionFactory().getCurrentSession().createSQLQuery("select max(ORG_ID) from ALLOWED_ORG").list();
    	int maxId = 0;
    	
    	if((null != list) && (null != list.get(0))){
    		maxId = ((BigDecimal)list.get(0)).intValue();
    	}
    	
    	//int maxId = ((BigDecimal)list.get(0)).intValue();
    	return maxId+1;
    }
}
