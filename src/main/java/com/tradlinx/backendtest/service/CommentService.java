package com.tradlinx.backendtest.service;

import java.util.List;

import com.tradlinx.backendtest.entity.Comment;

public interface CommentService {

	List<Comment> deleteComments(long seq, long parseLong);

	long insertComments(long seq, long articleId, String commentsCotents);


}