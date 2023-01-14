package com.tradlinx.backendtest.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Data;


@DynamicInsert
@Data
@Entity
@Table(name = "COMMENT")
public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "COMMENTSID", nullable = false)
	private long commentsId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ARTICLEID", nullable = false)
	private Article article;
	
	@Column(name = "COMMENTSCOTENTS", nullable = false)
	private String commentsCotents;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEQ", nullable = false)
	private User user;




	public long getCommentsId() {
		return commentsId;
	}

	public Comment setCommentsId(long commentsId) {
		this.commentsId = commentsId;
		return this;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getCommentsCotents() {
		return commentsCotents;
	}

	public Comment setCommentsCotents(String commentsCotents) {
		this.commentsCotents = commentsCotents;
		return this;
	}

}
