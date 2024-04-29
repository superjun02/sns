package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.mapper.LikeMapper;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;
import com.sns.timeline.domain.Card;
import com.sns.user.entity.UserEntity;
import com.sns.user.repository.UserRepository;

@Service
public class CardBO {
	@Autowired
	private ReplyBO replyBO;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LikeMapper likeMapper;
	
	public List<Card> getCardList() {
		List<Card> cardList = new ArrayList<>();
		List<PostEntity> postList = postRepository.findByOrderByIdDesc();
		
		Iterator<PostEntity> iter = postList.iterator();
		
		while (iter.hasNext()) {
			PostEntity post = iter.next();
			
			Card card = new Card();
			card.setPost(post);
			
			card.setReplyList(replyBO.getReplyList(post.getId()));
			
			UserEntity user = userRepository.findById(post.getUserId());
			card.setPostUserId(user.getLoginId());
			
			card.setLike(false);
			int postId = post.getId();
			card.setLikeNum(likeMapper.countLikeByPostId(postId));
			
			cardList.add(card);
		}
		return cardList;
	}
	
	public List<Card> getCardList(Integer userId) {
		List<Card> cardList = new ArrayList<>();
		List<PostEntity> postList = postRepository.findByOrderByIdDesc();
		
		Iterator<PostEntity> iter = postList.iterator();
		
		while (iter.hasNext()) {
			PostEntity post = iter.next();
			
			Card card = new Card();
			card.setPost(post);
			
			card.setReplyList(replyBO.getReplyList(post.getId()));
			
			UserEntity user = userRepository.findById(post.getUserId());
			card.setPostUserId(user.getLoginId());
			
			int postId = post.getId();
			card.setLike(likeMapper.selectLikeByPostIdUserId(postId, userId));
			card.setLikeNum(likeMapper.countLikeByPostId(postId));
			
			cardList.add(card);
		}
		return cardList;
	}
}
