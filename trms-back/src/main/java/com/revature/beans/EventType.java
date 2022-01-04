package com.revature.beans;

import java.util.Objects;

public class EventType {
	private int eventId;
	private String name;
	private double percentCovered;
	
	public EventType() {
		eventId=6;
		name="Other";
		percentCovered=30.0;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPercentCovered() {
		return percentCovered;
	}

	public void setPercentCovered(double percentCovered) {
		if (percentCovered <= 0.0) this.percentCovered = 0;
		else if (percentCovered >= 100.0) this.percentCovered = 100.0;
		else this.percentCovered = percentCovered;
	}

	@Override
	public int hashCode() {
		return Objects.hash(eventId, name, percentCovered);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventType other = (EventType) obj;
		return eventId == other.eventId && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(percentCovered) == Double.doubleToLongBits(other.percentCovered);
	}

	@Override
	public String toString() {
		return "EventType [eventId=" + eventId + ", name=" + name + ", percentCovered=" + percentCovered + "]";
	}
}
