package com.tradlinx.backendtest.dto;

import javax.validation.constraints.NotEmpty;

public class PostCommentsRequestDto {

    @NotEmpty
    private String articleId;

    @NotEmpty
    private String commentsContents;

	public String getArticleId() {
		return articleId;
	}

	public PostCommentsRequestDto setArticleId(String articleId) {
		this.articleId = articleId;
		return this;
	}

	public String getCommentsContents() {
		return commentsContents;
	}

	public PostCommentsRequestDto setCommentsContents(String commentsContents) {
		this.commentsContents = commentsContents;
		return this;
	}


}
