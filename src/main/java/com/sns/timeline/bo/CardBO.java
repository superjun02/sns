package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.domain.Comment;
import com.sns.comment.mapper.CommentMapper;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;
import com.sns.timeline.domain.Card;

@Service
public class CardBO {
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private PostRepository postRepository;
	
	public List<Card> getCardList() {
		List<Card> cardList = new ArrayList<>();
		List<PostEntity> postList = postRepository.findByOrderByIdDesc();
		
		Iterator<PostEntity> iter = postList.iterator();
		
		while (iter.hasNext()) {
			PostEntity post = iter.next();
			
			Card card = new Card();
			card.setPost(post);
			
			List<Comment> commentList = commentMapper.selectCommentListByPostId(post.getId());
			card.setCommentList(commentList);
			
			cardList.add(card);
		}
		return cardList;
	}
}
