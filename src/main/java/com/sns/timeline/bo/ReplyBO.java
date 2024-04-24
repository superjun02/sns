package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.domain.Comment;
import com.sns.comment.mapper.CommentMapper;
import com.sns.timeline.domain.Reply;
import com.sns.user.entity.UserEntity;
import com.sns.user.repository.UserRepository;

@Service
public class ReplyBO {
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Reply> getReplyList(int postId) {
		List<Reply> replyList = new ArrayList<>();
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		
		Iterator<Comment> iter = commentList.iterator();
		
		while (iter.hasNext()) {
			Comment comment = iter.next();
			
			int userId = comment.getUserId();
			
			UserEntity user = userRepository.findById(userId);
			
			Reply reply = new Reply();
			reply.setComment(comment);
			reply.setCommentUserLoginId(user.getLoginId());
			
			replyList.add(reply);
		}
		
		return replyList;
	}
}
