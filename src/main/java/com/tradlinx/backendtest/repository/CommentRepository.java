package com.tradlinx.backendtest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tradlinx.backendtest.entity.Article;
import com.tradlinx.backendtest.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> deleteById(long commentId);

	List<Comment> findByArticle(Article article);

}
