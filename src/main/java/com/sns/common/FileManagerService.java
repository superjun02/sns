package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {
	public static final String FILE_UPLOAD_PATH = "C:\\이상준\\6_spring_project\\sns\\sns_workspace\\images/"; // 학원용
	// public static final String FILE_UPLOAD_PATH = "C:\\이상준\\6_spring_project\\sns\\sns_workspace\\images/"; // 집용
	
	// input: file 원본, 로그인 아이디_현재시간
	// output: imagePath 경로
	public String saveFile(String loginId, MultipartFile file) {
		String directoryName = loginId + "_" + System.currentTimeMillis() + "/";
		String filePath = FILE_UPLOAD_PATH + directoryName; 
		
		File directory = new File(filePath);
		if (directory.mkdir() == false) {
			return null;
		}
		
		try {
			byte[] bytes = file.getBytes();
			// ★★★★ 한글명 파일은 업로드 불가이므로 나중에 영문자로 바꾸기
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "/images/" + directoryName + file.getOriginalFilename();
	}
}
