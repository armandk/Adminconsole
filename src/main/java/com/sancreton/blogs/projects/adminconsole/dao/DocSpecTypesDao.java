package com.sancreton.blogs.projects.adminconsole.dao;

import com.sancreton.blogs.projects.adminconsole.entities.DocSpecTypes;

import java.util.List;

import org.hibernate.SessionFactory;

public interface DocSpecTypesDao {

	public abstract SessionFactory getSessionFactory();

	public abstract void setSessionFactory(SessionFactory sessionFactory);

	public abstract List<DocSpecTypes> getDocSpecTypes();

}