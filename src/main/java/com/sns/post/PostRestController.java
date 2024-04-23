package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/post-create")
	public Map<String, Object> postCreate(HttpSession session,
			@RequestParam("content") String content,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		Map<String, Object> result = new HashMap<>();
		int userId = (int) session.getAttribute("userId");
		String loginId = (String) session.getAttribute("loginId");
		
		PostEntity post = postBO.addPost(userId, loginId, content, file);
		if (post != null) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "매모가 저장되지 않았습니다.");
		}
		
		return result;
	}
}
