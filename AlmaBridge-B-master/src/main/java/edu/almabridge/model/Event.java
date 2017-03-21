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
public class Event extends ErrorModel{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eventId_seq")
	@SequenceGenerator(name = "eventId_seq", sequenceName = "Event_seq",allocationSize = 1)
	private int eventId ;
	private String eventLocation ;
	
	private String eventDate ;
	private String description ;
	
	
	
	
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
