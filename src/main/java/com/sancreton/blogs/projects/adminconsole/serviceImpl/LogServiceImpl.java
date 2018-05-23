package com.sancreton.blogs.projects.adminconsole.serviceImpl;

import com.sancreton.blogs.projects.adminconsole.service.LogService;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("LogService")
@Transactional(readOnly = true)

public class LogServiceImpl implements LogService {

	
	/* (non-Javadoc)
	 * @see com.sancreton.blogs.projects.adminconsole.serviceImpl.LogService#retrieveLog()
	 */
	@Override
	public String retrieveLog(){

		StringBuilder strBuff = new StringBuilder();
	       try {
	            //opening file for reading in Java
	            //FileInputStream file = new FileInputStream("C:/springsource/vfabric-tc-server-developer-2.9.5.SR1/base-instance/logs/localhost_access_log.2014-07-14.txt");
	    	   FileInputStream file = new FileInputStream("C:/springsource/vfabric-tc-server-developer-2.9.5.SR1/base-instance/logs/localhost_access_log.2014-07-15.txt");
	            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
	         
	            //reading file content line by line
	            String line = reader.readLine();
	            while(line != null){
	                strBuff.append(line).append(System.getProperty("line.separator"));
	                line = reader.readLine();
	            }
	                 
	        } catch (FileNotFoundException ex) {
	        	ex.printStackTrace();
	        } catch (IOException ex) {
	        	ex.printStackTrace();
	        }
	       return strBuff.toString();
	}
		
}
