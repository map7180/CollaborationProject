package edu.almabridge.restcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.almabridge.dao.FriendDAO;
import edu.almabridge.model.Friend;


@RestController
public class FriendController {
	@Autowired
	private Friend friend;
	@Autowired
	private FriendDAO friendDAO;
	@Autowired
	HttpSession hs ;
	
	private static Logger log = LoggerFactory.getLogger(FriendController.class);
	//Tested+1
	@RequestMapping(value = "/addFriend/{friendId}/{loggedInUserId}/", method = RequestMethod.GET)
	public ResponseEntity<Friend> addFriend(@PathVariable("friendId") String friendId,@PathVariable("loggedInUserId") String loggedInUserId, HttpSession hs) {
log.debug("Starting of Add Friend");
		//String loggedInUserId = (String) hs.getAttribute("loggedInUserId");
		if (friendDAO.isRequestAlreadySent(loggedInUserId, friendId) == true) {
			friend.setId(0);
			friend.setUserId(loggedInUserId);
			friend.setFriendId(friendId);
			friend.setStatus('N');
			friend.setIsOnline('N');
			friend.setErrorCode("200");
			friend.setErrorMsg("friend request sent successfully");
			friendDAO.saveFriend(friend);
			log.debug("Add Friend Completed Sucessfully");
		} else {
			friend.setErrorCode("404");
			friend.setErrorMsg("friend request already sent!!");
			log.debug("Adding Friend Failed");
		}
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}

	//Tested+1
	@RequestMapping(value = "/approveFriend/{friendId}/{loggedInUserId}/", method = RequestMethod.GET)
	public ResponseEntity<Friend> approveFriend(@PathVariable("friendId") String friendId, @PathVariable("loggedInUserId") String loggedInUserId,HttpSession hs) {
		//String loggedInUserId = (String) hs.getAttribute("loggedInUserId");
		log.debug("Starting of Approve Frined");
		if (friendDAO.isAlreadyAccepted(loggedInUserId, friendId) == true) {
			friend.setErrorCode("200");
			friend.setErrorMsg("Friend request has been accepted!!");
			friend.setUserId(loggedInUserId);
			friend.setFriendId(friendId);
			friend.setStatus('Y');
			friend.setIsOnline('N');
			friendDAO.saveFriend(friend);
			friendDAO.rejectFriend(friendId, loggedInUserId);
			//Friend friend = friendDAO.getFriendToChangeStatus(friendId, loggedInUserId);
			//friend.setStatus('Y');
			//friendDAO.updateFriend(friend);
			log.debug("Friend Approved");
		} else {
			friend.setErrorCode("404");
			friend.setErrorMsg("Friend request has been already accepted!!");
			log.debug("Friend Approval Failed");
		}
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}

	//Tested+1
	@RequestMapping(value = "/rejectFriend/{friendId}/{loggedInUserId}/", method = RequestMethod.GET)
	public boolean rejectFriend(@PathVariable("friendId") String friendId,@PathVariable("loggedInUserId") String loggedInUserId, HttpSession hs) {
		//String loggedInUserId = (String) hs.getAttribute("loggedInUserId");
		log.debug("Starting of rejeect Friend");
		try {
			friendDAO.rejectFriend(loggedInUserId, friendId);
			log.debug("Friend Rejected");
			return true;
		} catch (Exception e) {
			log.debug("RejectFriend Failed with exception");
			e.printStackTrace();
			return false;
		}
	}
	//Tested+1
	@RequestMapping(value = "/unFriend/{friendId}/{loggedInUserId}/", method = RequestMethod.GET)
	public boolean deleteFriend(@PathVariable("friendId") String friendId,@PathVariable("loggedInUserId") String loggedInUserId, HttpSession hs) {
	//	String loggedInUserId = (String) hs.getAttribute("loggedInUserId");
		log.debug("Starting of delete Friend");
		try {
			//friendDAO.removeFriend(loggedInUserId, friendId);
			friendDAO.unFriend(friendId, loggedInUserId);
			log.debug("Friend Deleted");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Delete Friend Thrown Exception");
			return false;
		}
	}

	//Tested+1
	@RequestMapping(value = "/pendingFriendRequests/{loggedInUserId}/", method = RequestMethod.GET)
	public ResponseEntity<List<Friend>> pendingFriendRequests(@PathVariable("loggedInUserId") String loggedInUserId,HttpSession hs) {
		//String loggedInUserId = (String) hs.getAttribute("loggedInUserId");
		log.debug("Starting of Pending Friend Request");
		List<Friend> pendingFriendList = friendDAO.pendingFriendRequests(loggedInUserId);
		System.out.println("pending friends" +pendingFriendList.size());
		if (pendingFriendList.isEmpty()) {
			friend.setErrorCode("404");
			friend.setErrorMsg("No new Friend requests");
			pendingFriendList.add(friend);
			log.debug("Pending Friend Request not Completed Due to Null List");
		}
		log.debug("Pendig Friend request Completed Sucesfully");
		return new ResponseEntity<List<Friend>>(pendingFriendList, HttpStatus.OK);

	}
	//Tested+1
	@RequestMapping(value = "/friends/{loggedInUserId}/", method = RequestMethod.GET)
	public ResponseEntity<List<Friend>> friends(@PathVariable("loggedInUserId") String loggedInUserId,HttpSession hs) {
		
		log.debug("Starting of friends method");
		//String loggedInUserId = (String) hs.getAttribute("loggedInUserId");
		System.out.println("logged in user in backend friendController .."+loggedInUserId);
		List<Friend> friendList = friendDAO.myFriendsList(loggedInUserId);
		System.out.println(friendList.size());
		
		if (friendList.isEmpty()) {
			Friend friend = new Friend();
			friend.setId(0);
			friend.setErrorCode("404");
			friend.setErrorMsg("No Friends");
			friendList.add(friend);
			log.debug("friend is Empty");
		}
		log.debug("Friends Method Completed");
		return new ResponseEntity<List<Friend>>(friendList, HttpStatus.OK);

	}

}
