package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/like")
@RestController
public class LikeRestController {
	@Autowired
	private LikeBO likeBO;
	
	@RequestMapping("/{postId}")
	public Map<String, Object> likeToggle(
			@PathVariable("postId") int postId, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		// 로그인 여부 확인
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			result.put("result", "notSignIn");
			result.put("error_message", "로그인된 사용자만 좋아요를 누를 수 있습니다.");
			
			return result;
		}
		
		// BO 호출
		likeBO.toggleLikeByPostIdUserId(postId, userId);
		
		// 응답값
		result.put("code", 200);
		return result;
	}
}
