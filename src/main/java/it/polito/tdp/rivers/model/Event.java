package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event implements Comparable<Event> {
	
	public enum EventType{
		GIORNO
	}
	private LocalDate time;
	private EventType type;
	private Flow flow;
	
	
	public Event(LocalDate time, EventType type,Flow flow) {
		super();
		this.time = time;
		this.type = type;
		this.flow=flow;
	}
	
	
	public Flow getFlow() {
		return flow;
	}

	public void setFlow(Flow flow) {
		this.flow = flow;
	}

	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public LocalDate getTime() {
		return time;
	}
	
	
	@Override
	public int compareTo(Event arg0) {
		return this.time.compareTo(arg0.getTime());
	}


	@Override
	public String toString() {
		return "Event [time=" + time + ", type=" + type + ", flow=" + flow + "]";
	}
	
	
	

}
