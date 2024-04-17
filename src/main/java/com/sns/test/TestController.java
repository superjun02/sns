package com.sns.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sns.post.mapper.PostMapper;

@Controller
public class TestController {
	@Autowired
	private PostMapper postMapper;
	
	@GetMapping("/test1")
	@ResponseBody
	public String test1() {
		return "Hello world";
	}
	
	@GetMapping("/test2")
	@ResponseBody
	public Map<String, Object> test2() {
		Map<String, Object> map = new HashMap<>();
		map.put("a", 12);
		map.put("b", 22);
		map.put("c", 32);
		return map;
	}
	
	@GetMapping("/test3")
	public String test3() {
		return "test/test3";
	}
	
	@GetMapping("/test4")
	@ResponseBody
	public List<Map<String, Object>> test4() {
		return postMapper.selectPostList();
		// 클론 테스트 확인
	}
}
