package com.sancreton.blogs.projects.adminconsole.common;

import javax.faces.component.UIInput;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ListenerFor;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

@ListenerFor(sourceClass=javax.faces.component.html.HtmlInputText.class, systemEventClass=javax.faces.event.PostValidateEvent.class)
public class PostValidationListener implements SystemEventListener {
    public boolean isListenerForSource(Object source) {
        return true;
    }
 
    public void processEvent(SystemEvent event) throws AbortProcessingException {
        UIInput source = (UIInput) event.getSource();
 
        if(!source.isValid()) {
            source.getAttributes().put("styleClass", "ui-input-invalid");
        }
    }
}
