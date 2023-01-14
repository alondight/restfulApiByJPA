package com.tradlinx.backendtest.service;

import java.util.List;

import com.tradlinx.backendtest.dto.JsonResponse;
import com.tradlinx.backendtest.dto.PostArticleRequestDto;
import com.tradlinx.backendtest.dto.PutArticleRequestDto;
import com.tradlinx.backendtest.entity.Article;
import com.tradlinx.backendtest.entity.Comment;

public interface ArticleService {

	List<Comment> getArticleWithComments(long parseLong);

	long createArticle(long seqm, PostArticleRequestDto article);

	Article updateArticle(long seq, PutArticleRequestDto article);

	JsonResponse deleteArticle(long seq, long articleId);

}