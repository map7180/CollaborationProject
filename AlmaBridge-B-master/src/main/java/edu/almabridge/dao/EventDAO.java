package edu.almabridge.dao;

import java.util.List;

import edu.almabridge.model.Event;

public interface EventDAO {
	public boolean saveEvent(Event event);
	public boolean updateEvent(Event event);
	public Event getEvent(int eventId);
	public List<Event> eventList();
	public boolean deleteEvent(int eventId);

}
