package com.tugasakhir.sisurat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("/login")
	public String login() {
		return "form-login";
	}
}
