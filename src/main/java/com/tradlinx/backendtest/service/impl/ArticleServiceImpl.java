package com.tradlinx.backendtest.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tradlinx.backendtest.dto.JsonResponse;
import com.tradlinx.backendtest.dto.PostArticleRequestDto;
import com.tradlinx.backendtest.dto.PutArticleRequestDto;
import com.tradlinx.backendtest.entity.Article;
import com.tradlinx.backendtest.entity.Comment;
import com.tradlinx.backendtest.entity.User;
import com.tradlinx.backendtest.exception.CustomdException;
import com.tradlinx.backendtest.repository.ArticleRepository;
import com.tradlinx.backendtest.repository.CommentRepository;
import com.tradlinx.backendtest.service.ArticleService;
import com.tradlinx.backendtest.service.UserService;

@Transactional
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private UserService userService;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CommentRepository commentRepository;


	private static final long POINTS = 3;
	private static final long COMMENT_POINTS = 2;
	private static final long COMMENT_ARTICLE_POINTS = 1;
    
	@Override
	public long createArticle(long seq, PostArticleRequestDto article) {
		// TODO Auto-generated method stub
		User user = userService.findBySeq(seq);
		user.setPoints(user.getPoints() + POINTS);
		userService.save(user);

		Article article2 = new Article();
		article2.setArticleTitle(article.getArticleTitle());
		article2.setArticleContents(article.getArticleContents());
		article2.setUser(user);
		return articleRepository.save(article2).getArticleId();

	}
	

	@Override
	public Article updateArticle(long seq, PutArticleRequestDto article) {
		// TODO Auto-generated method stub

		
		Article article2 = articleRepository.findById(Long.parseLong(article.getArticleId()))
				.orElseThrow(() -> new CustomdException("Article is null", 400));

		article2.setArticleTitle(article.getArticleTitle());
		article2.setArticleContents(article.getArticleContents());
		article2.setArticleId(Long.parseLong(article.getArticleId()));
		return articleRepository.save(article2);
	}

	@Override
	public List<Comment> getArticleWithComments(long articleId) {
		// TODO Auto-generated method stub
		Article article = articleRepository.findById(articleId)
		        .orElseThrow(() -> new CustomdException("Article is null", 400));
		return commentRepository.findByArticle(article);
	}

	@Override
	public JsonResponse deleteArticle(long seq, long articleId) {
		// TODO Auto-generated method stub
		Article article = articleRepository.findByArticleId(articleId);
		JsonResponse result = new JsonResponse();
		if(article == null ) {
			result.setStatus("400");
			result.setData("No article");
        	return result;
		}
		
		
		// 권한확인
		result = checkArticleAuth(seq, article);
		if (  result != null ) return result;


		List<Comment> comments = commentRepository.findByArticle(article);
		for(Comment c : comments ) {
			User user = userService.findBySeq(c.getUser().getSeq());
			if( c.getArticle().getUser().getSeq() == c.getUser().getSeq()) {
				user.setPoints(user.getPoints() - COMMENT_ARTICLE_POINTS);
				userService.save(user);
			} else {
				user.setPoints(user.getPoints() - COMMENT_POINTS);
				userService.save(user);
			}
		}
		User user2 = userService.findBySeq(seq);
		articleRepository.deleteByArticleId(articleId);
		user2.setPoints(user2.getPoints() - POINTS);
		userService.save(user2);

		return result;
	}

    private JsonResponse checkArticleAuth(long seq, Article article) {
        if (article.getUser().getSeq() != seq) {
			JsonResponse result =  new JsonResponse();
			result.setStatus("403");
			result.setData("No Authorized");
        	return result;
        }
		return null;
    }

}
