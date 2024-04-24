package com.sns.timeline.domain;

import com.sns.comment.domain.Comment;

import lombok.Data;

@Data
public class Reply {
	private Comment comment;
	private String commentUserLoginId;
}
