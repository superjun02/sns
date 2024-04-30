package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	public void deleteFile(String imagePath) {
		Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", ""));
		
		if (Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				log.warn("[파일 매니저] 이미지 삭제 실패. path:{}", path.toString());
				return;
			}
			
			path = path.getParent();
			
			if (Files.exists(path)) {
				try {
					Files.delete(path);
				} catch (IOException e) {
					log.warn("[파일 매니저] 이미지폴더 삭제 실패. path:{}", path.toString());
					return;
				}
			}
		}
	}
}
