package edu.almabridge.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.almabridge.dao.EventDAO;
import edu.almabridge.model.Event;

public class EventDAOTest {
	
	private static ClassPathXmlApplicationContext context; 
	
	@Autowired
	private static EventDAO eventDAO;
	
	@Autowired
	private static Event  event;

	
	@BeforeClass
	public static void init()
	{
		context = new ClassPathXmlApplicationContext("ApplicationContextConfig.xml");
		context.refresh();
		
		event  = (Event) context.getBean("Event");
		eventDAO = (EventDAO) context.getBean("eventDAO");
	}

	//@Test
	public void testSaveEvent()
	{
		event.setEventId(0);
		event.setDescription("This is Event Added by Test Case");
		event.setEventLocation("Mumbai");
		event.setEventDate(new Date().toString());
		
		assertEquals("Event Added", true,eventDAO.saveEvent(event));
	}
	
	
	@Test
	public void testUpdateEvent()
	{
		event.setEventId(21);
		event.setDescription("This is Event Modified By by Test Case");
		event.setEventLocation("Mumbai");
		event.setEventDate(new Date().toString());
		
		assertEquals("Event Updated", true,eventDAO.updateEvent(event));
	}
	
	@Test
	public void testGetEvent()
	{
		//event.setEventId(21);
		assertEquals("Event recived fromDB", null ,eventDAO.getEvent(0));
	}
	
	@Test
	public void testEventList()
	{
		assertEquals("Event recived fromDB", 12,eventDAO.eventList().size());
	}
	
	
}
