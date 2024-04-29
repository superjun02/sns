package com.sns.timeline.domain;

import java.util.List;

import com.sns.post.entity.PostEntity;

import lombok.Data;

@Data
public class Card {
	private String postUserId;
	private PostEntity post;
	private List<Reply> replyList;
	private boolean like;
	private int likeNum;
}
