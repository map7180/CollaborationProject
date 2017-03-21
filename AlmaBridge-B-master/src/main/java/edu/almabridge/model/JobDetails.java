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
public class JobDetails extends ErrorModel{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobId_seq")
	@SequenceGenerator(name = "jobId_seq", sequenceName = "Job_seq",allocationSize = 1)
	private int jobId ;
	private long salary;
	private String description ;
	private String profile ;
	private String location ;
	private String qualification ;
	private String slectionProcess ;
	
	
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getSlectionProcess() {
		return slectionProcess;
	}
	public void setSlectionProcess(String slectionProcess) {
		this.slectionProcess = slectionProcess;
	}

}
