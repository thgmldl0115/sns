package com.future.my.web;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.future.my.service.SnsService;
import com.future.my.vo.SearchVO;
import com.future.my.vo.SnsVO;

@RestController
public class SnsRestController {

	@Autowired
	SnsService service;
	
	@PostMapping("/get-posts")
	public ArrayList<SnsVO> getPost(SearchVO searchVO){
		
		System.out.println("요청옴");
		System.out.println(searchVO.getCurrentTime());
		if(searchVO.getCurrentTime() != null && !searchVO.getCurrentTime().isEmpty()){
			ZonedDateTime zonedDateTime = ZonedDateTime.parse(searchVO.getCurrentTime());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String time = 
					zonedDateTime.withZoneSameInstant(
							java.time.ZoneId.systemDefault()).format(formatter);
			searchVO.setCurrentTime(time);
		}
		ArrayList<SnsVO> posts = service.getSns(searchVO);
		return posts;
	}
	
}
