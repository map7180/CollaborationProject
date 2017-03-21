package edu.almabridge.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.almabridge.dao.JobDetailsDAO;
import edu.almabridge.model.JobDetails;
import edu.almabridge.model.UserDetails;

@RestController
public class JobDetailsController {
	
	@Autowired
	JobDetails jobDetails ;
	
	@Autowired
	JobDetailsDAO jobDetailsDAO ;
	
	
	@RequestMapping(value = "/postJobDetails", method = RequestMethod.POST)
	public boolean addNewJobDetails(@RequestBody JobDetails jobDetails) {
			/*jobDetails.setJobId(3);*/

		if (jobDetailsDAO.saveJobDetails(jobDetails)) {
			
			jobDetails.setErrorCode("200");
			jobDetails.setErrorMsg("Seccessfully posted!");
			return true;
		} else {
			// userDetails.setStatus('N');
			jobDetails.setErrorCode("404");
			jobDetails.setErrorMsg("Not registered!");
			return false;

		}

	}
	@RequestMapping(value = "/removeJobDetails/{id}")
	public boolean removeJobDetails(@PathVariable (value="id")int jobId) {

		if (jobDetailsDAO.removeJobDetails(jobId)) {
			
			jobDetails.setErrorCode("200");
			jobDetails.setErrorMsg("Seccessfully removed!");
			return true;
		} else {
			// userDetails.setStatus('N');
			jobDetails.setErrorCode("404");
			jobDetails.setErrorMsg("Not removed!");
			return false;

		}

	}
	
	@RequestMapping(value = "/getJobDetails/{id}")
	public ResponseEntity<JobDetails> getJobDetails(@PathVariable (value="id")int jobId) {

		JobDetails jobDetails = jobDetailsDAO.getJobDetails(jobId);
		return new ResponseEntity<JobDetails>(jobDetails, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/updateJobDetails/{id}")
	public ResponseEntity<JobDetails> updateJobDetails(@PathVariable (value="id")int jobId,@RequestBody JobDetails jobDetails) {

		jobDetailsDAO.updateJobDetails(jobDetails);
		return new ResponseEntity<JobDetails>(jobDetails, HttpStatus.OK);

	}
	@RequestMapping(value = "/jobDetails", method = RequestMethod.GET)
	public ResponseEntity<List<JobDetails>> getJobList() {
		List<JobDetails> jobList = jobDetailsDAO.jobList();
		if (jobList.isEmpty()) {
			jobDetails.setErrorCode("404");
			jobDetails.setErrorMsg("Jobs are not available");
			jobList.add(jobDetails);
		}
		return new ResponseEntity<List<JobDetails>>(jobList, HttpStatus.OK);
	}

}
