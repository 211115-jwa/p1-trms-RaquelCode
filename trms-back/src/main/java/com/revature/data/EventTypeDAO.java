package com.revature.data;

import java.util.Set;

import com.revature.beans.EventType;
import com.revature.beans.Status;

public interface EventTypeDAO extends GenericDAO<Object> {
	public EventType getById(int id);
	public Set<Object> getAll();
	public Set<EventType> getByName(String name);
}
