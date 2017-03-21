package edu.almabridge.dao;

import java.util.List;

import edu.almabridge.model.JobDetails;

public interface JobDetailsDAO {
	public boolean saveJobDetails(JobDetails job);
	public boolean removeJobDetails(int jobId);
	public JobDetails getJobDetails(int jobId);
	public boolean updateJobDetails(JobDetails job);
	public List<JobDetails> jobList();

}
