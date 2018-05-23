package com.sancreton.blogs.projects.adminconsole.common;

import java.beans.FeatureDescriptor;
import java.util.Iterator;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ELResolver;
import javax.el.PropertyNotWritableException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.application.exceptionhandler.ExceptionInfo;
import org.springframework.beans.factory.BeanFactory;

import javax.faces.context.FacesContext;


public abstract class AdminConsoleSpringBeanELResolver extends ELResolver {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	public static final String EL_NAME = "pfExceptionHandler";


	@Override
	public Object getValue(ELContext elContext, Object base, Object property) throws ELException {
		if (base == null) {
			String beanName = property.toString();
			BeanFactory bf = getBeanFactory(elContext);
			if (bf.containsBean(beanName)) {
				if (logger.isTraceEnabled()) {
					logger.trace("Successfully resolved variable '" + beanName + "' in Spring BeanFactory");
				}
				elContext.setPropertyResolved(true);
				return bf.getBean(beanName);
			}
		}
        if (EL_NAME.equals(property)) {
            elContext.setPropertyResolved(true);
           
            FacesContext context = FacesContext.getCurrentInstance();
            ExceptionInfo info = (ExceptionInfo) context.getAttributes().get(ExceptionInfo.ATTRIBUTE_NAME);
           
            if (info == null) {
                info = (ExceptionInfo) context.getExternalContext().getFlash().get(ExceptionInfo.ATTRIBUTE_NAME);
            }
           
            return info;
        }

		return null;
	}

	@Override
	public Class<?> getType(ELContext elContext, Object base, Object property) throws ELException {
		if (base == null) {
			String beanName = property.toString();
			BeanFactory bf = getBeanFactory(elContext);
			if (bf.containsBean(beanName)) {
				elContext.setPropertyResolved(true);
				return bf.getType(beanName);
			}
		}
		return null;
	}

	@Override
	public void setValue(ELContext elContext, Object base, Object property, Object value) throws ELException {
		if (base == null) {
			String beanName = property.toString();
			BeanFactory bf = getBeanFactory(elContext);
			if (bf.containsBean(beanName)) {
				throw new PropertyNotWritableException(
						"Variable '" + beanName + "' refers to a Spring bean which by definition is not writable");
			}
		}
	}

	@Override
	public boolean isReadOnly(ELContext elContext, Object base, Object property) throws ELException {
		if (base == null) {
			String beanName = property.toString();
			BeanFactory bf = getBeanFactory(elContext);
			if (bf.containsBean(beanName)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext elContext, Object base) {
		return null;
	}

	@Override
	public Class<?> getCommonPropertyType(ELContext elContext, Object base) {
		return Object.class;
	}


	/**
	 * Retrieve the Spring BeanFactory to delegate bean name resolution to.
	 * @param elContext the current ELContext
	 * @return the Spring BeanFactory (never <code>null</code>)
	 */
	protected abstract BeanFactory getBeanFactory(ELContext elContext);

}

