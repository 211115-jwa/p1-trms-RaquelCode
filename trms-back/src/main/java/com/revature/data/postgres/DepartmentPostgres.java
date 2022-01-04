package com.revature.data.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Department;
import com.revature.data.DepartmentDAO;
import com.revature.utils.ConnectionUtil;

public class DepartmentPostgres implements DepartmentDAO {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public Department getById(int id) {
		Department dept = null;
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from department where dept_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			
			ResultSet resultSet = pStmt.executeQuery();
			
			if (resultSet.next()) {
				dept = new Department();
				dept.setDeptId(id);
				dept.setName(resultSet.getString("dept_name"));
				dept.setDeptHeadId(resultSet.getInt("dept_head_id"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dept;
	}

	@Override
	public Set<Department> getAll() {
		Set<Department> depts = new HashSet<>();
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from department";
			Statement stmt = conn.createStatement();
			
			ResultSet resultSet = stmt.executeQuery(sql);
			
			while (resultSet.next()) {
				Department dept = new Department();
				dept.setDeptId(resultSet.getInt("dept_id"));
				dept.setName(resultSet.getString("dept_name"));
				dept.setDeptHeadId(resultSet.getInt("dept_head_id"));
				
				depts.add(dept);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return depts;
	}

	@Override
	public Set<Department> getByName(String name) {
		Set<Department> depts = new HashSet<>();
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from department where dept_name=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, name);
			
			ResultSet resultSet = pStmt.executeQuery();
			
			while (resultSet.next()) {
				Department dept = new Department();
				dept.setDeptId(resultSet.getInt("dept_id"));
				dept.setName(resultSet.getString("dept_name"));
				dept.setDeptHeadId(resultSet.getInt("dept_head_id"));
				
				depts.add(dept);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return depts;
	}

	@Override
	public int create(Department dataToAdd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Department dataToUpdate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Department dataToDelete) {
		// TODO Auto-generated method stub
		
	}

}
