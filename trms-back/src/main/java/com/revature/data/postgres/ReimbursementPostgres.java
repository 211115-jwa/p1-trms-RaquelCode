package com.revature.data.postgres;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Employee;
import com.revature.beans.EventType;
import com.revature.beans.GradingFormat;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.ReimbursementDAO;
import com.revature.utils.ConnectionUtil;

public class ReimbursementPostgres implements ReimbursementDAO {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public int create(Reimbursement dataToAdd) {
		int generatedId=0;
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			String[] keys = {"req_Id"};
			String sql="insert into reimbursement"
					+ " (emp_id,"
					+ " event_date,"
					+ " event_time,"
					+ " location,"
					+ " description,"
					+ " cost,"
					+ " grading_format_id,"
					+ " event_type_id,"
					+ " status_id,"
					+ " submitted_at)"
					+ " values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql,keys);
			pStmt.setInt(1, dataToAdd.getRequestor().getEmpId());
			pStmt.setDate(2, Date.valueOf(dataToAdd.getEventDate()));
			pStmt.setTime(3, Time.valueOf(dataToAdd.getEventTime()));
			pStmt.setString(4, dataToAdd.getLocation());
			pStmt.setString(5, dataToAdd.getDescription());
			pStmt.setDouble(6, dataToAdd.getCost());
			pStmt.setInt(7, dataToAdd.getGradingFormat().getFormatId());
			pStmt.setInt(8, dataToAdd.getEventType().getEventId());
			pStmt.setInt(9, dataToAdd.getStatus().getStatusId());
			pStmt.setTimestamp(10, Timestamp.valueOf(dataToAdd.getSubmittedAt()));
			
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
	public Reimbursement getById(int id) {
		Reimbursement request = null;
		try (Connection conn = connUtil.getConnection()) {
			String sql="select" + 
					" req_id," + 
					" emp_id," + 
					" event_date," + 
					" event_time," + 
					" location," + 
					" description," + 
					" cost," + 
					" grading_format_id," + 
					" format_name," + 
					" example as format_example," + 
					" event_type_id," + 
					" type_name," + 
					" percent_coverage," + 
					" r.status_id," + 
					" status_name," + 
					" approver," + 
					" submitted_at " + 
					" from reimbursement r" + 
					" join grading_format gf on r.grading_format_id=gf.format_id" + 
					" join event_type et on r.event_type_id=et.type_id" + 
					" join status s on r.status_id=s.status_id"
					+ " where req_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			
			ResultSet resultSet = pStmt.executeQuery();
			while (resultSet.next()) {
				request = new Reimbursement();
				request.setReqId(resultSet.getInt("req_id"));
				request.getRequestor().setEmpId(resultSet.getInt("emp_id"));
				request.setEventDate(resultSet.getDate("event_date").toLocalDate());
				request.setEventTime(resultSet.getTime("event_time").toLocalTime());
				request.setLocation(resultSet.getString("location"));
				request.setDescription(resultSet.getString("description"));
				request.setCost(resultSet.getDouble("cost"));
				GradingFormat gf = new GradingFormat();
				gf.setFormatId(resultSet.getInt("grading_format_id"));
				gf.setName(resultSet.getString("format_name"));
				gf.setExample(resultSet.getString("format_example"));
				request.setGradingFormat(gf);
				EventType et = new EventType();
				et.setEventId(resultSet.getInt("event_type_id"));
				et.setName(resultSet.getString("type_name"));
				et.setPercentCovered(resultSet.getDouble("percent_coverage"));
				request.setEventType(et);
				Status s = new Status();
				s.setStatusId(resultSet.getInt("status_id"));
				s.setName(resultSet.getString("status_name"));
				s.setApprover(resultSet.getString("approver"));
				request.setStatus(s);
				request.setSubmittedAt(resultSet.getTimestamp("submitted_at").toLocalDateTime());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return request;
	}

	@Override
	public Set<Reimbursement> getAll() {
		Set<Reimbursement> requests = new HashSet<>();
		try (Connection conn = connUtil.getConnection()) {
			String sql="select" + 
					" req_id," + 
					" emp_id," + 
					" event_date," + 
					" event_time," + 
					" location," + 
					" description," + 
					" cost," + 
					" grading_format_id," + 
					" format_name," + 
					" example as format_example," + 
					" event_type_id," + 
					" type_name," + 
					" percent_coverage," + 
					" r.status_id," + 
					" status_name," + 
					" approver," + 
					" submitted_at " + 
					" from reimbursement r" + 
					" join grading_format gf on r.grading_format_id=gf.format_id" + 
					" join event_type et on r.event_type_id=et.type_id" + 
					" join status s on r.status_id=s.status_id";
			Statement stmt = conn.createStatement();
			
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				Reimbursement request = new Reimbursement();
				request.setReqId(resultSet.getInt("req_id"));
				request.getRequestor().setEmpId(resultSet.getInt("emp_id"));
				request.setEventDate(resultSet.getDate("event_date").toLocalDate());
				request.setEventTime(resultSet.getTime("event_time").toLocalTime());
				request.setLocation(resultSet.getString("location"));
				request.setDescription(resultSet.getString("description"));
				request.setCost(resultSet.getDouble("cost"));
				GradingFormat gf = new GradingFormat();
				gf.setFormatId(resultSet.getInt("grading_format_id"));
				gf.setName(resultSet.getString("format_name"));
				gf.setExample(resultSet.getString("format_example"));
				request.setGradingFormat(gf);
				EventType et = new EventType();
				et.setEventId(resultSet.getInt("event_type_id"));
				et.setName(resultSet.getString("type_name"));
				et.setPercentCovered(resultSet.getDouble("percent_coverage"));
				request.setEventType(et);
				Status s = new Status();
				s.setStatusId(resultSet.getInt("status_id"));
				s.setName(resultSet.getString("status_name"));
				s.setApprover(resultSet.getString("approver"));
				request.setStatus(s);
				request.setSubmittedAt(resultSet.getTimestamp("submitted_at").toLocalDateTime());
				
				requests.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requests;
	}

	@Override
	public void update(Reimbursement dataToUpdate) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			String sql="update reimbursement set"
					+ " emp_id=?,"
					+ " event_date=?,"
					+ " event_time=?,"
					+ " location=?,"
					+ " description=?,"
					+ " cost=?,"
					+ " grading_format_id=?,"
					+ " event_type_id=?,"
					+ " status_id=?,"
					+ " submitted_at=?"
					+ " where req_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, dataToUpdate.getRequestor().getEmpId());
			pStmt.setDate(2, Date.valueOf(dataToUpdate.getEventDate()));
			pStmt.setTime(3, Time.valueOf(dataToUpdate.getEventTime()));
			pStmt.setString(4, dataToUpdate.getLocation());
			pStmt.setString(5, dataToUpdate.getDescription());
			pStmt.setDouble(6, dataToUpdate.getCost());
			pStmt.setInt(7, dataToUpdate.getGradingFormat().getFormatId());
			pStmt.setInt(8, dataToUpdate.getEventType().getEventId());
			pStmt.setInt(9, dataToUpdate.getStatus().getStatusId());
			pStmt.setTimestamp(10, Timestamp.valueOf(dataToUpdate.getSubmittedAt()));
			pStmt.setInt(10, dataToUpdate.getReqId());
			
			int rowsAffected = pStmt.executeUpdate();
			if (rowsAffected <= 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Reimbursement dataToDelete) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			String sql="delete from reimbursement"
					+ " where req_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, dataToDelete.getReqId());
			
			int rowsAffected = pStmt.executeUpdate();
			if (rowsAffected <= 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Set<Reimbursement> getByRequestor(Employee requestor) {
		Set<Reimbursement> requests = new HashSet<>();
		try (Connection conn = connUtil.getConnection()) {
			String sql="select" + 
					" req_id," + 
					" emp_id," + 
					" event_date," + 
					" event_time," + 
					" location," + 
					" description," + 
					" cost," + 
					" grading_format_id," + 
					" format_name," + 
					" example as format_example," + 
					" event_type_id," + 
					" type_name," + 
					" percent_coverage," + 
					" r.status_id," + 
					" status_name," + 
					" approver," + 
					" submitted_at " + 
					" from reimbursement r" + 
					" join grading_format gf on r.grading_format_id=gf.format_id" + 
					" join event_type et on r.event_type_id=et.type_id" + 
					" join status s on r.status_id=s.status_id"
					+ " where emp_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, requestor.getEmpId());
			
			ResultSet resultSet = pStmt.executeQuery();
			while (resultSet.next()) {
				Reimbursement request = new Reimbursement();
				request.setReqId(resultSet.getInt("req_id"));
				request.getRequestor().setEmpId(resultSet.getInt("emp_id"));
				request.setEventDate(resultSet.getDate("event_date").toLocalDate());
				request.setEventTime(resultSet.getTime("event_time").toLocalTime());
				request.setLocation(resultSet.getString("location"));
				request.setDescription(resultSet.getString("description"));
				request.setCost(resultSet.getDouble("cost"));
				GradingFormat gf = new GradingFormat();
				gf.setFormatId(resultSet.getInt("grading_format_id"));
				gf.setName(resultSet.getString("format_name"));
				gf.setExample(resultSet.getString("format_example"));
				request.setGradingFormat(gf);
				EventType et = new EventType();
				et.setEventId(resultSet.getInt("event_type_id"));
				et.setName(resultSet.getString("type_name"));
				et.setPercentCovered(resultSet.getDouble("percent_coverage"));
				request.setEventType(et);
				Status s = new Status();
				s.setStatusId(resultSet.getInt("status_id"));
				s.setName(resultSet.getString("status_name"));
				s.setApprover(resultSet.getString("approver"));
				request.setStatus(s);
				request.setSubmittedAt(resultSet.getTimestamp("submitted_at").toLocalDateTime());
				
				requests.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requests;
	}

	@Override
	public Set<Reimbursement> getByStatus(Status status) {
		Set<Reimbursement> requests = new HashSet<>();
		try (Connection conn = connUtil.getConnection()) {
			String sql="select" + 
					" req_id," + 
					" emp_id," + 
					" event_date," + 
					" event_time," + 
					" location," + 
					" description," + 
					" cost," + 
					" grading_format_id," + 
					" format_name," + 
					" example as format_example," + 
					" event_type_id," + 
					" type_name," + 
					" percent_coverage," + 
					" r.status_id," + 
					" status_name," + 
					" approver," + 
					" submitted_at " + 
					" from reimbursement r" + 
					" join grading_format gf on r.grading_format_id=gf.format_id" + 
					" join event_type et on r.event_type_id=et.type_id" + 
					" join status s on r.status_id=s.status_id"
					+ " where status_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, status.getStatusId());
			
			ResultSet resultSet = pStmt.executeQuery();
			while (resultSet.next()) {
				Reimbursement request = new Reimbursement();
				request.setReqId(resultSet.getInt("req_id"));
				request.getRequestor().setEmpId(resultSet.getInt("emp_id"));
				request.setEventDate(resultSet.getDate("event_date").toLocalDate());
				request.setEventTime(resultSet.getTime("event_time").toLocalTime());
				request.setLocation(resultSet.getString("location"));
				request.setDescription(resultSet.getString("description"));
				request.setCost(resultSet.getDouble("cost"));
				GradingFormat gf = new GradingFormat();
				gf.setFormatId(resultSet.getInt("grading_format_id"));
				gf.setName(resultSet.getString("format_name"));
				gf.setExample(resultSet.getString("format_example"));
				request.setGradingFormat(gf);
				EventType et = new EventType();
				et.setEventId(resultSet.getInt("event_type_id"));
				et.setName(resultSet.getString("type_name"));
				et.setPercentCovered(resultSet.getDouble("percent_coverage"));
				request.setEventType(et);
				Status s = new Status();
				s.setStatusId(resultSet.getInt("status_id"));
				s.setName(resultSet.getString("status_name"));
				s.setApprover(resultSet.getString("approver"));
				request.setStatus(s);
				request.setSubmittedAt(resultSet.getTimestamp("submitted_at").toLocalDateTime());
				
				requests.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requests;
	}

}
