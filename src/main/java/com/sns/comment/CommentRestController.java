package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.Comment;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	@Autowired
	private CommentBO commentBO;
	
	@PostMapping("/comment-create")
	public Map<String, Object> commentCreate(
			@RequestParam("content") String content,
			@RequestParam("postId") int postId,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		int userId = (int) session.getAttribute("userId");
		
		int rowCount = commentBO.addComment(postId, userId, content);
		if (rowCount > 0) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "댓글이 저장되지 않았습니다.");
		}
		
		return result;
	}
}
