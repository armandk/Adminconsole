package com.sancreton.blogs.projects.adminconsole.controller;

//import gov.va.med.nhin.adminconsole.logview.spring.model.Facilities;
//import gov.va.med.nhin.adminconsole.logview.spring.service.FacilitiesService;
import com.sancreton.blogs.projects.adminconsole.serviceImpl.LogServiceImpl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.springframework.dao.DataAccessException;

@ManagedBean(name="logviewMB")
@SessionScoped
public class LogviewManagedBeans implements Serializable {
/*

    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";
    
    //Spring FacilitiesService Service is injected...
    @ManagedProperty(value="#{FacilitiesService}")
    FacilitiesService facilitiesService;
    
    @ManagedProperty(value="#{LogService}")
    LogService logService;
    
	private int id;
	private String number;
	private String name;
	private String homeCommId;
	private String fullHomeCommId;
	
	List<Facilities> facilitiesList;
	private List<ColumnModel> columns;
	private String columnName;
	
	private boolean show = true;
	
	private String logData;
	
	public FacilitiesService getFacilitiesService() {
		return facilitiesService;
	}
	public void setFacilitiesService(FacilitiesService facilitiesService) {
		this.facilitiesService = facilitiesService;
	}
	
		
	public LogService getLogService() {
		return logService;
	}
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHomeCommId() {
		return homeCommId;
	}
	public void setHomeCommId(String homeCommId) {
		this.homeCommId = homeCommId;
	}
	public String getFullHomeCommId() {
		return fullHomeCommId;
	}
	public void setFullHomeCommId(String fullHomeCommId) {
		this.fullHomeCommId = fullHomeCommId;
	}
	
	
	
	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public List<Facilities> getFacilitiesList() {
		facilitiesList = new ArrayList<Facilities>();
		facilitiesList.addAll(facilitiesService.getFacilities());
		return facilitiesList;
	}
	public void setFacilitiesList(List<Facilities> facilitiesList) {
		this.facilitiesList = facilitiesList;
	}
	
	 public String addFacilities(){
		 
		 try{
			 Facilities facilities = new Facilities(); 
			 facilities.setId(getId());
			 facilities.setName(getName());

			 facilitiesService = getFacilitiesService();
			 facilitiesService.addFacilities(facilities);
			 
			 return SUCCESS;
		 }
		 catch (DataAccessException e){
			 e.printStackTrace();
		 }

		 return ERROR;
	 }
	 
	    public void reset() {
	        this.setId(0);
	        this.setName("");
	    }
		 
	    private void createDynamicColumns() {

	        columns = new ArrayList<ColumnModel>();
	        columns.add(new ColumnModel("Id", "id"));
	        columns.add(new ColumnModel("Number", "number"));
	        columns.add(new ColumnModel("Name", "name"));
	        columns.add(new ColumnModel("HomeCommId", "homeCommId"));
	        columns.add(new ColumnModel("FullHomeCommId", "fullHomeCommId"));
	    }

	    public void display(){
	    	
			createDynamicColumns();
			facilitiesList = getFacilitiesList();
//			
//	        if(show == false){
//	            setShow(true);
//	        }
	    }
	    
	    
	    public String getLogData() {
	    	
	    	logData = getLog();
			return logData;
		}
		public void setLogData(String logData) {
			this.logData = logData;
		}

		private String getLog(){
			
			return logService.retrieveLog();

//			StringBuilder strBuff = new StringBuilder();
//		       try {
//		            //opening file for reading in Java
//		            FileInputStream file = new FileInputStream("C:/springsource/vfabric-tc-server-developer-2.9.5.SR1/base-instance/logs/localhost_access_log.2014-07-14.txt");
//		            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
//		         
//		            //reading file content line by line
//		            String line = reader.readLine();
//		            while(line != null){
//		                strBuff.append(line).append(System.getProperty("line.separator"));
//		                line = reader.readLine();
//		            }
//		                 
//		        } catch (FileNotFoundException ex) {
//		        	ex.printStackTrace();
//		        } catch (IOException ex) {
//		        	ex.printStackTrace();
//		        }
//		       return strBuff.toString();
			
		
		}

		static public class ColumnModel implements Serializable {

	        private String header;
	        private String property;

	        public ColumnModel(String header, String property) {
	            this.header = header;
	            this.property = property;
	        }

	        public String getHeader() {
	            return header;
	        }

	        public String getProperty() {
	            return property;
	        }
	    }	    
*/		
}
