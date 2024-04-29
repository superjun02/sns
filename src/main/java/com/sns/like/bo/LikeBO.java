package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.mapper.LikeMapper;

@Service
public class LikeBO {
	@Autowired
	private LikeMapper likeMapper;
	
	public void toggleLikeByPostIdUserId(int postId, int userId) {
		boolean result = likeMapper.selectLikeByPostIdUserId(postId, userId);
		
		if (result) {
			likeMapper.deleteLikeByPostIdUserId(postId, userId);
		} else {
			likeMapper.insertLikeByPostIdUserId(postId, userId);
		}
	}
	
}
