package com.sns.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

@Service
public class PostBO {
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FileManagerService fileManager;

	public PostEntity addPost(int userId, String loginId, String content, MultipartFile file) {
		String imagePath = null;

		// 업로드 할 이미지가 있을 때 업로드
		if (file != null) {
			imagePath = fileManager.saveFile(loginId, file);
		}
		
		PostEntity post = PostEntity.builder()
									.userId(userId)
									.content(content)
									.imagePath(imagePath)
									.build();

		return postRepository.save(post);
	}

}
