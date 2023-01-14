package com.tradlinx.backendtest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tradlinx.backendtest.entity.Article;
import com.tradlinx.backendtest.entity.Comment;
import com.tradlinx.backendtest.entity.User;
import com.tradlinx.backendtest.exception.CustomdException;
import com.tradlinx.backendtest.repository.ArticleRepository;
import com.tradlinx.backendtest.repository.CommentRepository;
import com.tradlinx.backendtest.service.CommentService;
import com.tradlinx.backendtest.service.UserService;

@Transactional
@Service
public class CommentServiceimpl implements CommentService{

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserService userService;

    public static final long COMMENT_POINTS = 2;
    public static final long COMMENT_ARTICLE_POINTS = 1;



	@Override
	public long insertComments(long seq, long articleId, String commentsCotents) {

		//USER가 쓴글이면 1점 아니면 2점
		Article article = articleRepository.findByArticleId(articleId);
		User user = userService.findBySeq(seq);
		//원본작성자
		if(seq == article.getUser().getSeq()) {
			user.setPoints(user.getPoints() + COMMENT_ARTICLE_POINTS);
		}else {
		//댓글작성자
			user.setPoints(user.getPoints() + COMMENT_POINTS);
		}
		userService.save(user);


		Comment c = new Comment();
		c.setArticle(article);
		c.setCommentsCotents(commentsCotents);
		c.setUser(user);
		return commentRepository.save(c).getCommentsId();

	}

	@Override
	public List<Comment> deleteComments(long seq, long commentId) {
		
		Comment comment = commentRepository.findById(commentId)
		        .orElseThrow(() -> new CustomdException("Article is null", 400));
		User user = null;

		//원본작성자
		if(seq == comment.getArticle().getUser().getSeq()) {
			user = userService.findBySeq(seq);
			user.setPoints(user.getPoints() - COMMENT_ARTICLE_POINTS);	
		}else {
		//댓글작성자
			user = userService.findBySeq(comment.getArticle().getUser().getSeq());
			user.setPoints(comment.getArticle().getUser().getPoints() + COMMENT_POINTS);
		}
		userService.save(user);

		commentRepository.deleteById(commentId);
		return commentRepository.findAll();
	}

}
