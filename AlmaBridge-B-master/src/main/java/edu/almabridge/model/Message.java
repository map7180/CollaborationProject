package edu.almabridge.model;

public class Message {
	private String message ;
	private String fid ;
	private int id ;
	
	

	
	public Message() {
		
	}
	public Message(String message, int id, String fid) {
		
		this.message = message;
		this.id = id;
		this.fid = fid ;
		
	}
	
	
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		System.out.println("setting fid...");
		this.fid = fid;
	}
	public String getMessage() {
		System.out.println("getting fid...");
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
		System.out.println("setter in message class...");
	}
	public int getId() {
		System.out.println("getter id in message class...");
		return id;
		
	}
	public void setId(int id) {
		System.out.println("setter id in message class...");
		this.id = id;
	}
	
	

}
