package com.revature.data.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Employee;
import com.revature.beans.Role;
import com.revature.data.EmployeeDAO;
import com.revature.utils.ConnectionUtil;

public class EmployeePostgres implements EmployeeDAO {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public int create(Employee dataToAdd) {
		int generatedId=0;
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			String[] keys = {"emp_Id"};
			String sql="insert into employee"
					+ " (first_name,"
					+ " last_name,"
					+ " username,"
					+ " passwd,"
					+ " role_id,"
					+ " funds,"
					+ " supervisor_id,"
					+ " dept_id)"
					+ " values (?,?,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql,keys);
			pStmt.setString(1, dataToAdd.getFirstName());
			pStmt.setString(2, dataToAdd.getLastName());
			pStmt.setString(3, dataToAdd.getUsername());
			pStmt.setString(4, dataToAdd.getPassword());
			pStmt.setInt(5, dataToAdd.getRole().getRoleId());
			pStmt.setDouble(6, dataToAdd.getFunds());
			pStmt.setInt(7, dataToAdd.getSupervisor().getEmpId());
			pStmt.setInt(8, dataToAdd.getDepartment().getDeptId());
			
			pStmt.executeUpdate();
			ResultSet generatedKeys = pStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				generatedId = generatedKeys.getInt(1);
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return generatedId;
	}

	@Override
	public Employee getById(int id) {
		Employee emp = null;
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select emp_id,"
					+ " first_name,"
					+ " last_name,"
					+ " username,"
					+ " passwd,"
					+ " employee.role_id,"
					+ " role_name,"
					+ " funds,"
					+ " supervisor_id,"
					+ " dept_id"
					+ " from employee join user_role on employee.role_id=user_role.role_id"
					+ " where emp_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			
			ResultSet resultSet = pStmt.executeQuery();
			
			if (resultSet.next()) {
				emp = new Employee();
				emp.setEmpId(id);
				emp.setFirstName(resultSet.getString("first_name"));
				emp.setLastName(resultSet.getString("last_name"));
				emp.setUsername(resultSet.getString("username"));
				emp.setPassword(resultSet.getString("passwd"));
				emp.setFunds(resultSet.getDouble("funds"));
				Role role = new Role();
				role.setRoleId(resultSet.getInt("role_id"));
				role.setName(resultSet.getString("role_name"));
				emp.setRole(role);
				emp.getSupervisor().setEmpId(resultSet.getInt("supervisor_id"));
				emp.getDepartment().setDeptId(resultSet.getInt("dept_id"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emp;
	}

	@Override
	public Set<Employee> getAll() {
		Set<Employee> emps = new HashSet<>();
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select emp_id,"
					+ " first_name,"
					+ " last_name,"
					+ " username,"
					+ " passwd,"
					+ " employee.role_id,"
					+ " role_name,"
					+ " funds,"
					+ " supervisor_id,"
					+ " dept_id"
					+ " from employee join user_role on employee.role_id=user_role.role_id";
			Statement stmt = conn.createStatement();
			
			ResultSet resultSet = stmt.executeQuery(sql);
			
			while (resultSet.next()) {
				Employee emp = new Employee();
				emp.setEmpId(resultSet.getInt("emp_id"));
				emp.setFirstName(resultSet.getString("first_name"));
				emp.setLastName(resultSet.getString("last_name"));
				emp.setUsername(resultSet.getString("username"));
				emp.setPassword(resultSet.getString("passwd"));
				emp.setFunds(resultSet.getDouble("funds"));
				Role role = new Role();
				role.setRoleId(resultSet.getInt("role_id"));
				role.setName(resultSet.getString("role_name"));
				emp.setRole(role);
				emp.getSupervisor().setEmpId(resultSet.getInt("supervisor_id"));
				emp.getDepartment().setDeptId(resultSet.getInt("dept_id"));
				
				emps.add(emp);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emps;
	}

	@Override
	public void update(Employee dataToUpdate) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			String sql="update employee set "
					+ " first_name=?,"
					+ " last_name=?,"
					+ " username=?,"
					+ " passwd=?,"
					+ " role_id=?,"
					+ " funds=?,"
					+ " supervisor_id=?,"
					+ " dept_id=?"
					+ " where emp_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, dataToUpdate.getFirstName());
			pStmt.setString(2, dataToUpdate.getLastName());
			pStmt.setString(3, dataToUpdate.getUsername());
			pStmt.setString(4, dataToUpdate.getPassword());
			pStmt.setInt(5, dataToUpdate.getRole().getRoleId());
			pStmt.setDouble(6, dataToUpdate.getFunds());
			pStmt.setInt(7, dataToUpdate.getSupervisor().getEmpId());
			pStmt.setInt(8, dataToUpdate.getDepartment().getDeptId());
			pStmt.setInt(9, dataToUpdate.getEmpId());
			
			int rowsAffected = pStmt.executeUpdate();
			if (rowsAffected<=1) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Employee dataToDelete) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			String sql="delete from employee"
					+ " where emp_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, dataToDelete.getEmpId());
			
			int rowsAffected = pStmt.executeUpdate();
			if (rowsAffected<=1) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Employee getByUsername(String username) {
		Employee emp = null;
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select emp_id,"
					+ " first_name,"
					+ " last_name,"
					+ " username,"
					+ " passwd,"
					+ " employee.role_id,"
					+ " role_name,"
					+ " funds,"
					+ " supervisor_id,"
					+ " dept_id"
					+ " from employee join user_role on employee.role_id=user_role.role_id"
					+ " where username=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, username);
			
			ResultSet resultSet = pStmt.executeQuery();
			
			if (resultSet.next()) {
				emp = new Employee();
				emp.setEmpId(resultSet.getInt("emp_id"));
				emp.setFirstName(resultSet.getString("first_name"));
				emp.setLastName(resultSet.getString("last_name"));
				emp.setUsername(resultSet.getString("username"));
				emp.setPassword(resultSet.getString("passwd"));
				emp.setFunds(resultSet.getDouble("funds"));
				Role role = new Role();
				role.setRoleId(resultSet.getInt("role_id"));
				role.setName(resultSet.getString("role_name"));
				emp.setRole(role);
				emp.getSupervisor().setEmpId(resultSet.getInt("supervisor_id"));
				emp.getDepartment().setDeptId(resultSet.getInt("dept_id"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emp;
	}

}
