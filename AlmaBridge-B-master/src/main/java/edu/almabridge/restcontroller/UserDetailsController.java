package edu.almabridge.restcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.almabridge.dao.UserDetailsDAO;
import edu.almabridge.model.UserDetails;

@RestController
public class UserDetailsController {

	@Autowired
	private UserDetailsDAO userDetailsDAO;
	@Autowired
	private UserDetails userDetails;

	// Tested
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public ResponseEntity<UserDetails> addNewUser(@RequestBody UserDetails userDetails) {
		userDetails.setStatus('N');
		userDetails.setIsOnline('N');
		
		System.out.println(userDetails.getName());
		if (userDetailsDAO.saveUser(userDetails)) {

			userDetails.setErrorCode("200");
			userDetails.setErrorMsg("Seccessfully registered!");
			return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
		} else {

			UserDetails userDetails1 = new UserDetails();
			userDetails1.setUserId("notRegisted");
			userDetails1.setErrorCode("404");
			userDetails1.setErrorMsg("Not registered!");
			return new ResponseEntity<UserDetails>(userDetails1, HttpStatus.OK);
		}

	}

	// Tested
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public boolean updateUser(@RequestBody UserDetails userDetails) {

		if (userDetailsDAO.updateUser(userDetails)) {

			userDetails.setErrorCode("200");
			userDetails.setErrorMsg("Seccessfully updated!");
			return true;
		} else {
			// userDetails.setStatus('N');
			userDetails.setErrorCode("404");
			userDetails.setErrorMsg("Not updated!");
			return false;

		}

	}

	// Tested
	@RequestMapping(value = "/removeUser/{userId}")
	public boolean removeUser(@PathVariable(value = "userId") String userId) {

		if (userDetailsDAO.removeUser(userId)) {

			userDetails.setErrorCode("200");
			userDetails.setErrorMsg("Seccessfully removed!");
			return true;
		} else {

			userDetails.setErrorCode("404");
			userDetails.setErrorMsg("Not removed!");
			return false;

		}

	}

	// Tested
	@RequestMapping(value = "/makeAdmin/{userId}", method = RequestMethod.GET)
	public boolean makeAdmin(@PathVariable(value = "userId") String userId) {
		System.out.println("user ID" + userId);

		UserDetails user = userDetailsDAO.getUser(userId);
		if (user.getStatus() == 'Y') {
			user.setRoleId("Role_Admin");
			userDetailsDAO.updateUser(user);
			user.setErrorCode("200");
			user.setErrorMsg("Successfully made admin with userId  " + userId);
			return true;
		} else
			return false;

	}

	// Tested
	//@CrossOrigin(origins = "http://localhost:8500")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<UserDetails>> getUserList() {
		List<UserDetails> userList = userDetailsDAO.getUserList();
		if (userList.isEmpty()) {
			userDetails.setErrorCode("404");
			userDetails.setErrorMsg("Users are not available");
			userList.add(userDetails);
		}
		return new ResponseEntity<List<UserDetails>>(userList, HttpStatus.OK);
	}

	// Tested
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public ResponseEntity<UserDetails> getUser(@PathVariable(value = "userId") String userId) {
		userDetails = userDetailsDAO.getUser(userId);
		if (userDetails == null) {
			userDetails = new UserDetails(); // To avoid null pointer exception
			userDetails.setErrorCode("404");
			userDetails.setErrorMsg("User is not available with id " + userId);
		}
		return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
	}

	// Tested
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<UserDetails> authenticate(@RequestBody UserDetails userDetails, HttpSession httpSession) {

		userDetails = userDetailsDAO.authenticate(userDetails.getUserId(), userDetails.getPassword());
		if (userDetails == null) {
			userDetails = new UserDetails();
			userDetails.setErrorCode("404");
			userDetails.setErrorMsg("Incorrect userName and password , please try again...");

		} else {
			// store userId in HTTPSession
			if (userDetails.getStatus() == 'Y') {
				userDetails.setErrorCode("200");
				userDetails.setErrorMsg("You are logged in sussessfully!");
				System.out.println("you are logged in with userid "+ userDetails.getUserId());
				httpSession.setAttribute("loggedInUserId", userDetails.getUserId());
				userDetails.setIsOnline('Y');
				userDetailsDAO.updateUser(userDetails);
			}else{
				userDetails.setErrorCode("200");
				userDetails.setErrorMsg("You are not authorized");
				userDetails.setIsOnline('N');
				userDetails.setStatus('N');
			}
		}
		return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
	}

	// Tested
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<UserDetails> logOut(HttpSession httpSession) {
		String loggedInUser = (String) httpSession.getAttribute("loggedInUserId");

		userDetails = userDetailsDAO.getUser(loggedInUser);
		userDetails.setIsOnline('N');

		if (userDetailsDAO.updateUser(userDetails)) {
			userDetails.setErrorMsg("You successfully logged out");
			userDetails.setErrorCode("200");
		} else {
			userDetails.setErrorMsg("Some problem occured during looging out");
			userDetails.setErrorCode("404");
		}
		// remove the attributed from httpSession
		httpSession.invalidate();
		return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);

	}

	// Tested
	@RequestMapping(value = "/approve/{userId}/")
	
	public ResponseEntity<UserDetails> approve(@PathVariable("userId") String userId) {
		System.out.println("user Id 1: "+ userId);
		userDetails = userDetailsDAO.getUser(userId);
		System.out.println("user Id 2: "+ userDetails.getUserId());

		userDetails.setStatus('Y');
		userDetails.setReason("Welcome to new era");
		if (userDetailsDAO.updateUser(userDetails) == true) {
			userDetails.setErrorCode("200");
			userDetails.setErrorMsg("Successfully updated");

		} else {

			userDetails.setErrorCode("404");
			userDetails.setErrorMsg("Not able to update the status");

		}

		return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
	}

	// Tested
	@RequestMapping(value = "/reject/{userId}/")
	public ResponseEntity<UserDetails> reject(@PathVariable(value = "userId") String userId) {
		userDetails = userDetailsDAO.getUser(userId);
		userDetails.setStatus('N');
		userDetails.setIsOnline('N');
		// userDetails.setStatus('N');
		userDetails.setReason("You didnt provide correct information");
		if (userDetailsDAO.updateUser(userDetails) == true) {
			userDetails.setErrorCode("200");
			userDetails.setErrorMsg("Successfully rejected");
		} else {
			
			userDetails.setErrorCode("404");
			userDetails.setErrorMsg("Not able to reject the user request");
		}
		return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
	}

}
