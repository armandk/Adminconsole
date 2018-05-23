package com.sancreton.blogs.projects.adminconsole.common;

import javax.el.ELContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.access.el.SpringBeanELResolver;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

public class AdminConsoleSpringBeanFacesELResolver  extends AdminConsoleSpringBeanELResolver {
	
	/**
	 * This implementation delegates to {@link #getWebApplicationContext}.
	 * Can be overridden to provide an arbitrary BeanFactory reference to resolve
	 * against; usually, this will be a full Spring ApplicationContext.
	 * @param elContext the current JSF ELContext
	 * @return the Spring BeanFactory (never <code>null</code>)
	 */
	@Override
	protected BeanFactory getBeanFactory(ELContext elContext) {
		return getWebApplicationContext(elContext);
	}

	/**
	 * Retrieve the web application context to delegate bean name resolution to.
	 * <p>The default implementation delegates to FacesContextUtils.
	 * @param elContext the current JSF ELContext
	 * @return the Spring web application context (never <code>null</code>)
	 * @see org.springframework.web.jsf.FacesContextUtils#getRequiredWebApplicationContext
	 */
	protected WebApplicationContext getWebApplicationContext(ELContext elContext) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return FacesContextUtils.getRequiredWebApplicationContext(facesContext);
	}	
}
