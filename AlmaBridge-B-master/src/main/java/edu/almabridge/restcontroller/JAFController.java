package edu.almabridge.restcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.almabridge.dao.JAFDAO;
import edu.almabridge.dao.UserDetailsDAO;
import edu.almabridge.model.JAF;
import edu.almabridge.model.UserDetails;

@RestController
public class JAFController {

	@Autowired
	private JAFDAO jAFDAO;
	@Autowired
	private JAF jAF;

	// Tested
	@RequestMapping(value = "/signJAF/{jobId}", method = RequestMethod.GET)
	public ResponseEntity<JAF> signJAF(HttpSession hs, @PathVariable(value = "jobId") int jobId) {
		String userId = hs.getAttribute("loggedInUserId").toString();
		jAF.setUserId(userId);
		if (  (!jAFDAO.ifAlreadySigned(userId, jobId))&& jAFDAO.saveJAF(jAF)) {
			jAF.setjAFId(1);
			jAF.setStatus('Y');
			jAF.setJobId(jobId);
			jAF.setErrorCode("200");
			jAF.setErrorMsg("Seccessfully signed JAF");
			return new ResponseEntity<JAF>(jAF, HttpStatus.OK);
		} else {
			jAF.setErrorCode("404");
			jAF.setErrorMsg("You have already signed for this job!");
			return new ResponseEntity<JAF>(jAF, HttpStatus.ALREADY_REPORTED);
		}

	}

	// Tested
	@RequestMapping(value = "/unSignJAF/{jAFId}")
	public boolean unSignJAF(@PathVariable(value = "jAFId") int jAFId) {

		if (jAFDAO.removeJAF(jAFId)) {
			// userDetails.setStatus('Y');
			jAF.setErrorCode("200");
			jAF.setErrorMsg("Seccessfully unSigned!");
			return true;
		} else {
			// userDetails.setStatus('N');
			jAF.setErrorCode("404");
			jAF.setErrorMsg("Not unSigned!");
			return false;

		}

	}

	// Tested
	@RequestMapping(value = "/JAFs", method = RequestMethod.GET)
	public ResponseEntity<List<JAF>> getJAFList(HttpSession hs) {

		String loggedInUser = hs.getAttribute("loggedInUserId").toString();
		System.out.println("loggedInUserId" + loggedInUser);

		List<JAF> jAFList = jAFDAO.JAFList(loggedInUser);
		if (jAFList.isEmpty()) {
			jAF.setErrorCode("404");
			jAF.setErrorMsg("No JAFs are available");
			jAFList.add(jAF);
		}
		return new ResponseEntity<List<JAF>>(jAFList, HttpStatus.OK);
	}

	// Tested
	@RequestMapping(value = "/signedJAFs", method = RequestMethod.GET)
	public ResponseEntity<List<JAF>> getSignedJAFList(HttpSession hs) {

		List<JAF> jAFList = jAFDAO.signedJAFList(hs.getAttribute("loggedInUserId").toString());
		if (jAFList.isEmpty()) {
			jAF.setErrorCode("404");
			jAF.setErrorMsg("Users are not available");
			jAFList.add(jAF);
		}
		return new ResponseEntity<List<JAF>>(jAFList, HttpStatus.OK);
	}

	// Tested
	@RequestMapping(value = "/notSignedJAFs", method = RequestMethod.GET)
	public ResponseEntity<List<JAF>> getUnSignedJAFList(HttpSession hs) {

		List<JAF> jAFList = jAFDAO.notSignedJAFList(hs.getAttribute("loggedInUserId").toString());
		if (jAFList.isEmpty()) {
			jAF.setErrorCode("404");
			jAF.setErrorMsg("Users are not available");
			jAFList.add(jAF);
		}
		return new ResponseEntity<List<JAF>>(jAFList, HttpStatus.OK);
	}

}
