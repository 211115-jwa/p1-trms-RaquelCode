package com.revature.beans;

import java.util.Objects;

public class Department {
	private int deptId;
	private String name;
	private int deptHeadId;
	
	public Department() {
		deptId = 0;
		name = "";
		deptHeadId = 0;
	}
	
	public Department(int deptId, String name, int deptHeadId) {
		super();
		this.deptId= deptId;
		this.name = name;
		this.deptHeadId = deptHeadId;
	}
	
	

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDeptHeadId() {
		return deptHeadId;
	}

	public void setDeptHeadId(int deptHead) {
		this.deptHeadId = deptHead;
	}

	@Override
	public int hashCode() {
		return Objects.hash(deptHeadId, deptId, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return deptHeadId == other.deptHeadId && deptId == other.deptId && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", name=" + name + ", deptHeadId=" + deptHeadId + "]";
	}
}
