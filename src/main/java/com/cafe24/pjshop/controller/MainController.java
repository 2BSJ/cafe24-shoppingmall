package com.cafe24.pjshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@ResponseBody
	@RequestMapping({"/", "/main"})
	public String main() {
		return "<h1>메인입니다</h1>";
	}
	
}
