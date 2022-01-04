package com.revature.data.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Comment;
import com.revature.data.CommentDAO;
import com.revature.utils.ConnectionUtil;

public class CommentPostgres implements CommentDAO {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public int create(Comment dataToAdd) {
		int generatedId=0;
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			String[] keys = {"comment_id"};
			String sql="insert into comment"
					+ " (comment_text,"
					+ " req_id,"
					+ " approver_id,"
					+ " sent_at)"
					+ " values (?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql,keys);
			pStmt.setString(1, dataToAdd.getCommentText());
			pStmt.setInt(2, dataToAdd.getRequest().getReqId());
			pStmt.setInt(3, dataToAdd.getApprover().getEmpId());
			pStmt.setTimestamp(4, Timestamp.valueOf(dataToAdd.getSentAt()));
			
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
	public Comment getById(int id) {
		Comment comment = null;
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from comment where comment_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			
			ResultSet resultSet = pStmt.executeQuery();
			
			if (resultSet.next()) {
				comment = new Comment();
				comment.setCommentId(id);
				comment.setCommentText(resultSet.getString("comment_text"));
				comment.getApprover().setEmpId(resultSet.getInt("approver_id"));
				comment.getRequest().setReqId(resultSet.getInt("req_id"));
				comment.setSentAt(resultSet.getTimestamp("sent_at").toLocalDateTime());
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comment;
	}

	@Override
	public Set<Comment> getAll() {
		Set<Comment> comments = new HashSet<>();
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from comment";
			Statement stmt = conn.createStatement();
			
			ResultSet resultSet = stmt.executeQuery(sql);
			
			if (resultSet.next()) {
				Comment comment = new Comment();
				comment.setCommentId(resultSet.getInt("comment_id"));
				comment.setCommentText(resultSet.getString("comment_text"));
				comment.getApprover().setEmpId(resultSet.getInt("approver_id"));
				comment.getRequest().setReqId(resultSet.getInt("req_id"));
				comment.setSentAt(resultSet.getTimestamp("sent_at").toLocalDateTime());
				
				comments.add(comment);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}

	@Override
	public Set<Comment> getByRequestId(int reqId) {
		Set<Comment> comments = new HashSet<>();
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from comment where req_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, reqId);
			
			ResultSet resultSet = pStmt.executeQuery();
			
			while (resultSet.next()) {
				Comment comment = new Comment();
				comment.setCommentId(resultSet.getInt("comment_id"));
				comment.setCommentText(resultSet.getString("comment_text"));
				comment.getApprover().setEmpId(resultSet.getInt("approver_id"));
				comment.getRequest().setReqId(resultSet.getInt("req_id"));
				comment.setSentAt(resultSet.getTimestamp("sent_at").toLocalDateTime());
				
				comments.add(comment);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}

}
