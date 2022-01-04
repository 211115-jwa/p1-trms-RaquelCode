package com.revature.data;

import java.util.Set;

import com.revature.beans.Status;

public interface StatusDAO {
	public Status getById(int id);
	public Set<Status> getAll();
	public Set<Status> getByName(String name);
}
