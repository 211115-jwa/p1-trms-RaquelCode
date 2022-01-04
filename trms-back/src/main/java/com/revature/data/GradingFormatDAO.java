package com.revature.data;

import java.util.Set;

import com.revature.beans.GradingFormat;


public interface GradingFormatDAO extends GenericDAO<Object> {
	public GradingFormat getById(int id);
	public Set<Object> getAll();
	public Set<GradingFormat> getByName(String name);
}
