package edu.almabridge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class JAF extends ErrorModel{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jAFId_seq")
	@SequenceGenerator(name = "jAFId_seq", sequenceName = "JAF_seq",allocationSize = 1)
	private int jAFId ;
	private String userId ;
	private char status ;
	private int jobId ;
	
	
	
	public int getjAFId() {
		return jAFId;
	}
	public void setjAFId(int jAFId) {
		this.jAFId = jAFId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	
	

}
