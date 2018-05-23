package com.sancreton.blogs.projects.adminconsole.controller;

import com.sancreton.blogs.projects.adminconsole.entities.Documents;
import com.sancreton.blogs.projects.adminconsole.service.DocumentService;




import java.io.*;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultStreamedContent;
import org.springframework.beans.factory.annotation.Autowired;

@ManagedBean(name = "fileViewerMB")
@SessionScoped
public class FileViewerManagedBeans implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(LoginBean.class);
	@ManagedProperty(value = "#{DocumentService}")
	DocumentService documentService;

	private List<Documents> searchResultList;
	

   @Autowired
   Documents doc;
   
	private boolean shouldRender = false;
	private String patientFirstName;

	private String patientLastName;

	private String patientSSN;
	private DefaultStreamedContent fileToDownload;

	public List<Documents> getSearchResultList() {
		
		 shouldRender = true;
		logger.info("Start  getSearchResultList");
		searchResultList = documentService.searchResults(patientFirstName,
				patientLastName, patientSSN);
		if (searchResultList.size() == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("No data found :: Try another search");
			context.addMessage("", msg);
			logger.info("Inside   searchResultList.size() NUL"); 
			setShouldRender(false);
		}
		
		return searchResultList;
	
	}


	public String getPatientFirstName() {
		return patientFirstName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public String getPatientSSN() {
		
	
		return patientSSN;
				
	}

	public void setSearchResultList(List<Documents> searchResultList) {
		this.searchResultList = searchResultList;
	}

	

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public void setPatientSSN(String patientSSN) {
		this.patientSSN = patientSSN;
	}

	public String searchPatient() {
		FacesContext context = FacesContext.getCurrentInstance();
		logger.info("INSIDE  searchPatient ");
		if (("".equals(patientLastName) || patientLastName.length() <= 0) &&
				("".equals(patientFirstName) || patientFirstName.length() <= 0 ) &&
			(	"".equals(patientSSN) || patientSSN.length() <= 0)) {
			FacesMessage msg = new FacesMessage("A least one the fields is required");
			context.addMessage("", msg);
			return "";
		
		}

		else {
			 getSearchResultList();
			return "fileViewer.xhtml";
			
		}
	}

  public void downloadFile() throws Exception
  {
	  
	 
     FacesContext facesContext = FacesContext.getCurrentInstance();
    
     Map<String,String> params =  facesContext.getExternalContext().getRequestParameterMap();
     String rawXml = params.get("documentId");
	logger.info("Start  download  Document with DocumentId  "+params.get("documentId"));

    Integer documentId = Integer.parseInt(rawXml); 
 
    doc = documentService.getSimpleId(documentId);
    
    byte[] xmlData =  doc.getRawData();
    
    //Converting byte[] to Blob
    SerialBlob xmlBlobData =new SerialBlob(xmlData);
	
    
    String documentName =patientFirstName+"."+patientLastName+"_"+rawXml+".xml";
    
    //Creating a Stream from Blob
	 InputStream stream  = (InputStream)xmlBlobData.getBinaryStream(); 

      fileToDownload = new DefaultStreamedContent(stream, "text/xml", documentName);

      
      logger.info("File "+documentName+   "  has been successfully downloaded");

     //The request processing lifecycle should be terminated
     // as soon as the current phase is completed.
     facesContext.responseComplete();
      
 }  

 

	public DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	

	public boolean isShouldRender() {
		return shouldRender;
	}

	public void setShouldRender(boolean shouldRender) {
		this.shouldRender = shouldRender;
	}


	public DefaultStreamedContent getFileToDownload() {
			return fileToDownload;
	}


	public void setFileToDownload(DefaultStreamedContent fileToDownload) {
		this.fileToDownload = fileToDownload;
	}
	

}

