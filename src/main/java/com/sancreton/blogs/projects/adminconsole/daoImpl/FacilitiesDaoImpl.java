package com.sancreton.blogs.projects.adminconsole.daoImpl;

import java.math.BigDecimal;
import java.util.List;

import com.sancreton.blogs.projects.adminconsole.entities.Facilities;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FacilitiesDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    
    public void addFacilities(Facilities facilities){
    	
    	int nextId = getNextId();
    	facilities.setId(nextId);
    	facilities.getFacilitiesOperartions().setId(nextId);
    	getSessionFactory().getCurrentSession().save(facilities);
    }
    

    public void updateFacilities(Facilities facilities){
    	
    	getSessionFactory().getCurrentSession().update(facilities);
    }    
    
    public Facilities getFacilitiesById(int id){
    	List list = getSessionFactory().getCurrentSession().createQuery(" from Facilities where id=?")
    	.setParameter(0, id).list();
    	if (!list.isEmpty())
    		return (Facilities)list.get(0);
    	else 
    		return null;
    }
    
    public List<Facilities> getFacilities(){
    	//List list = getSessionFactory().getCurrentSession().createQuery(" from com.sancreton.blogs.projects.adminconsole.entities.Facilities").list();
    	List list = getSessionFactory().getCurrentSession().createQuery(" from Facilities order by id desc").list();
    	return list;
    }
    
    private int getNextId(){
    	
    	List list = getSessionFactory().getCurrentSession().createSQLQuery("select max(FACILITY_ID) from FACILITIES").list();
    	int maxId = 0;
    	
    	if(null != list){
    		maxId = ((BigDecimal)list.get(0)).intValue();
    	}
    	
    	//int maxId = ((BigDecimal)list.get(0)).intValue();
    	return maxId+1;
    }
}
