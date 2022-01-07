package com.revature.data;

import java.util.Set;

import com.revature.beans.Comment;

public interface CommentDAO {
	public int create(Comment dataToAdd);
	public Comment getById(int id);
	public Set<Comment> getAll();
	public Set<Comment> getByRequestId(int reqId);
}
