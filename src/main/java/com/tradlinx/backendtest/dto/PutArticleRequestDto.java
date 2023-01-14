package com.tradlinx.backendtest.dto;

import javax.validation.constraints.NotEmpty;

public class PutArticleRequestDto {

    @NotEmpty
    private String articleId;

    @NotEmpty
    private String articleTitle;

    @NotEmpty
    private String articleContents;

	public String getArticleId() {
		return articleId;
	}

	public PutArticleRequestDto setArticleId(String articleId) {
		this.articleId = articleId;
		return this;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public PutArticleRequestDto setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
		return this;
	}

	public String getArticleContents() {
		return articleContents;
	}

	public PutArticleRequestDto setArticleContents(String articleContents) {
		this.articleContents = articleContents;
		return this;
	}

}
