package edu.almabridge.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@RestController
public class JobDetailsController {

	@Autowired
	JobDetails jobDetails;

	@Autowired
	JobDetailsDAO jobDetailsDAO;

	private static Logger log = LoggerFactory.getLogger(JobDetailsController.class);

	@RequestMapping(value = "/postJobDetails", method = RequestMethod.POST)
	public boolean addNewJobDetails(@RequestBody JobDetails jobDetails) {

		log.debug("Starting of add New Job Details");
		/* jobDetails.setJobId(3); */

		if (jobDetailsDAO.saveJobDetails(jobDetails)) {

			jobDetails.setErrorCode("200");
			jobDetails.setErrorMsg("Seccessfully posted!");
			log.debug("New Job Added");

			return true;
		} else {
			// userDetails.setStatus('N');
			jobDetails.setErrorCode("404");
			jobDetails.setErrorMsg("Not registered!");
			log.debug("Add Job Faield");
			return false;

		}

	}

	@RequestMapping(value = "/removeJobDetails/{id}")
	public boolean removeJobDetails(@PathVariable(value = "id") int jobId) {

		log.debug("Staring of Remoce Job Details");
		if (jobDetailsDAO.removeJobDetails(jobId)) {

			jobDetails.setErrorCode("200");
			jobDetails.setErrorMsg("Seccessfully removed!");
			log.debug("Job Details Removed");
			return true;
		} else {
			// userDetails.setStatus('N');
			jobDetails.setErrorCode("404");
			jobDetails.setErrorMsg("Not removed!");
			log.debug("Job Removal Faield");
			return false;

		}

	}

	@RequestMapping(value = "/getJobDetails/{id}")
	public ResponseEntity<JobDetails> getJobDetails(@PathVariable(value = "id") int jobId) {

		log.debug("Starting of getJobDetails");

		JobDetails jobDetails = jobDetailsDAO.getJobDetails(jobId);
		log.debug("get Job Details Completed");
		return new ResponseEntity<JobDetails>(jobDetails, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateJobDetails/{id}")
	public ResponseEntity<JobDetails> updateJobDetails(@PathVariable(value = "id") int jobId,
			@RequestBody JobDetails jobDetails) {

		log.debug("Starting of UpdateJob Details");
		jobDetailsDAO.updateJobDetails(jobDetails);
		log.debug("update Jobs Completed");
		return new ResponseEntity<JobDetails>(jobDetails, HttpStatus.OK);

	}

	@RequestMapping(value = "/jobDetails", method = RequestMethod.GET)
	public ResponseEntity<List<JobDetails>> getJobList() {
		log.debug("Starting getJobList");
		List<JobDetails> jobList = jobDetailsDAO.jobList();
		if (jobList.isEmpty()) {
			jobDetails.setErrorCode("404");
			jobDetails.setErrorMsg("Jobs are not available");
			jobList.add(jobDetails);
			log.debug("JobList is Empty");
		}
		log.debug("Job List Completed");
		return new ResponseEntity<List<JobDetails>>(jobList, HttpStatus.OK);
	}

}
