package com.tradlinx.backendtest.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tradlinx.backendtest.dto.JsonResponse;
import com.tradlinx.backendtest.dto.PostArticleRequestDto;
import com.tradlinx.backendtest.dto.PutArticleRequestDto;
import com.tradlinx.backendtest.entity.Article;
import com.tradlinx.backendtest.entity.Comment;
import com.tradlinx.backendtest.entity.User;
import com.tradlinx.backendtest.service.ArticleService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "API FOR ARTICLE", description = "과제와 관련된 API 입니다.")
@Slf4j
@RestController
public class ArticleController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ArticleService articleService;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;


	//ARTICLE CRUD
	@SuppressWarnings("finally")
	@PostMapping("/article")
	public JsonResponse postArticle(
		Authentication authentication,
		@RequestBody  @Valid PostArticleRequestDto article) {
		
		log.info("API Page - postArticle");
		
		User userDetails = (User) authentication.getPrincipal();
		long articleId = articleService.createArticle(userDetails.getSeq(), article);

		JsonResponse result =  new JsonResponse();
		
		try{
			result.setStatus("200");
			result.setData(articleId);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return result;
		}

	}
	
	@SuppressWarnings("finally")
	@GetMapping("/article/{articleId}")
	public JsonResponse getArticle( @PathVariable @Valid String articleId , Authentication authentication) {

		log.info("API Page - getArticle");
	
		JsonResponse result =  new JsonResponse();
		try{
			Set<String> set = new HashSet<String>();
			for (Comment c : articleService.getArticleWithComments(Long.parseLong(articleId))) {
				set.add(String.valueOf(c.getCommentsId()));
			}

			result.setStatus("200");
			result.setData(set);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return result;
		}
	}


	@SuppressWarnings("finally")
	@PutMapping("/article")
	public JsonResponse putArticle(
		Authentication authentication,
		@RequestBody PutArticleRequestDto dto
	) {
		log.info("API Page - putArticle");

		User userDetails = (User) authentication.getPrincipal();
		long seq = userDetails.getSeq();
		
		Article article = articleService.updateArticle(seq, dto);
		JsonResponse result =  new JsonResponse();
		try{
			result.setStatus("200");
			result.setData(article.getArticleId());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return result;
		}
	}
	
	@DeleteMapping("/article/{articleId}")
	public JsonResponse deleteArticle( 
			Authentication authentication,
			@PathVariable String articleId) {
		
			log.info("API Page - deleteArticle");
			User userDetails = (User) authentication.getPrincipal();
			JsonResponse result = articleService.deleteArticle(userDetails.getSeq(), Long.parseLong(articleId));
			if (null != result) {
				JsonResponse result2 =  new JsonResponse();
				return result2;
			} else {
				JsonResponse result2 =  new JsonResponse();
				result2.setStatus("200");
				result2.setData(1);
				return result2;
			}
	}
}
