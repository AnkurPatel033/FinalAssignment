package com.xworkz.assignment.entities.assignupload;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "assignupload_table")
public class AssignUploadEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1691720947765443015L;
	private static Logger logger=LoggerFactory.getLogger(AssignUploadEntity.class);
	
	@Id
	@GenericGenerator(name="auto",strategy="increment")
	@GeneratedValue(generator="auto")
	@Column(name = "ID")
	private int id;
	@Column(name = "PIN")
	private int pin;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "CONTACT_NO")
	private long cNo;
	@Column(name = "FILE")
	private String file1;
	@Column(name = "URL")
	private String url;
	@Column(name="IPADDRESS")
	private String ipAddress;
	@Column(name="HOSTNAME")
	private String hostName;

	public AssignUploadEntity() {
		logger.info("Created:" + this.getClass().getSimpleName());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getcNo() {
		return cNo;
	}

	public void setcNo(long cNo) {
		this.cNo = cNo;
	}

	public String getFile() {
		return file1;
	}

	public void setFile(String file) {
		this.file1 = file;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	@Override
	public String toString() {
		return "AssignUploadDTO [pin=" + pin + ", email=" + email + ", cNo=" + cNo + ", file=" + file1 + ", url=" + url
				+ "]";
	}

}
