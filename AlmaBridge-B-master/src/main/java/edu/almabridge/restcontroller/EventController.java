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

import edu.almabridge.dao.EventDAO;
import edu.almabridge.model.Event;

@RestController
public class EventController {
	
	@Autowired
	private Event event;
	
	@Autowired
	private EventDAO eventDAO;
	//Tested
	@RequestMapping(value = "/eventList", method = RequestMethod.GET)
	public ResponseEntity<List<Event>> getEvents(){
		List <Event> eventList = eventDAO.eventList();
		if(eventList.isEmpty()){
			Event event = new Event();
			event.setErrorCode("404");
			event.setErrorMsg("No Events posted!");
			eventList.add(event);
		}
		return new ResponseEntity<List<Event>>(eventList, HttpStatus.OK);
	}
	//Tested
	@RequestMapping("/event/{id}")
	public ResponseEntity<Event> getEvent(@PathVariable("id") int id){
		event =  eventDAO.getEvent(id);
		if(event == null){
			Event event = new Event();
			event.setErrorCode("404");
			event.setErrorMsg("No Events posted!");
		}
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}
	
	//Tested
	@RequestMapping("/createEvent")
	public ResponseEntity<Event> createEvent(@RequestBody Event newevent){
		eventDAO.saveEvent(newevent);
		return new ResponseEntity<Event>(newevent , HttpStatus.OK);
	}
	//Tested
	@RequestMapping("/deleteEvent/{id}")
	public boolean deleteEvent(@PathVariable("id") int id){
		if(eventDAO.getEvent(id) == null){
			Event event = new Event();
			event.setErrorCode("404");
			event.setErrorMsg("No Events posted!");
		}
		eventDAO.deleteEvent(id);
		return true ;
	}
	//Tested
	@RequestMapping(value="/updateEvent/{id}", method = RequestMethod.POST)
	public ResponseEntity<Event> updateEvent(@PathVariable("id") int id, @RequestBody Event event){
		if(eventDAO.getEvent(id) == null){
			
			event.setErrorCode("404");
			event.setErrorMsg("No Events posted!");
		}
		eventDAO.updateEvent(event);
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}

}
