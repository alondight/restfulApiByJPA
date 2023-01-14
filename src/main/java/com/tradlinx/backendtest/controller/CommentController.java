package com.tradlinx.backendtest.controller;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradlinx.backendtest.dto.JsonResponse;
import com.tradlinx.backendtest.dto.PostCommentsRequestDto;
import com.tradlinx.backendtest.entity.Comment;
import com.tradlinx.backendtest.entity.User;
import com.tradlinx.backendtest.service.CommentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "API FOR COMMENT", description = "과제와 관련된 API 입니다.")
@Slf4j
@RestController
public class CommentController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommentService commentService;


	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	//댓글은 자유롭게 지울수 있음. - 권한 체크 X
	//COMMENTSCD
	@PostMapping("/comments")
	public JsonResponse postComment(
		Authentication authentication,
			PostCommentsRequestDto postCommentsRequestDto) {
			log.info("API Page - postComment");

		User userDetails = (User) authentication.getPrincipal();
		long rtn  = commentService.insertComments(userDetails.getSeq(), Long.parseLong(postCommentsRequestDto.getArticleId()), postCommentsRequestDto.getCommentsContents());
				
		JsonResponse result =  new JsonResponse();
		result.setStatus("200");
		result.setData(rtn);
		return result;
    }

    @SuppressWarnings("finally")
	@DeleteMapping("/comments/{commentsId}")
    public JsonResponse deleteComment(
            Authentication authentication,
            @PathVariable String commentsId
    ) {
    	log.info("API Page - deleteComment");
    	User userDetails = (User) authentication.getPrincipal();

    	JsonResponse result =  new JsonResponse();
		try{
			Set<String> set = new HashSet<String>();
			for (Comment c : commentService.deleteComments(userDetails.getSeq(), Long.parseLong(commentsId))) {
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
}
