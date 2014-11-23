package models;

import java.util.Date;

public class Repertory {

	private static final long serialVersionUID = 1L;

	private String repertoryID;
	private String eventName;
	private Date date;
	private String description;

	public Repertory(String id, String event, Date date, String description) {
		this.repertoryID = id;
		this.eventName = event;
		this.date = date;
		this.description = description;
	}

	public String getRepertoryID() {
		return repertoryID;
	}

	public void setRepertoryID(String repertoryID) {
		this.repertoryID = repertoryID;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean answer = false;
		if (obj instanceof Repertory){
			if(getRepertoryID().equals(((Repertory) obj).getRepertoryID())){
				answer = true;
			}
		}
		return answer;
	}
	
	@Override
	public String toString() {
		return getDate() + " - " + getEventName();
	}
}
