package com.ymagis.restwebservicejersey.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyResponse {

	private boolean status;
	private String message;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
        
        public String toString(){
            return " Query is "+this.status+" "+this.message;
        }
}