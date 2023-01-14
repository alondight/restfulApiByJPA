package com.tradlinx.backendtest.dto;

import javax.validation.constraints.NotEmpty;

public class PostArticleRequestDto {

    @NotEmpty
    private  String articleTitle;

    @NotEmpty
    private  String articleContents;

	public String getArticleTitle() {
		return articleTitle;
	}

	public PostArticleRequestDto setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
		return this;
	}

	public String getArticleContents() {
		return articleContents;
	}

	public PostArticleRequestDto setArticleContents(String articleContents) {
		this.articleContents = articleContents;
		return this;
	}

    
}
