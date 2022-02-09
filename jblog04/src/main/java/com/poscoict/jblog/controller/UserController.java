package com.poscoict.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscoict.jblog.service.UserService;
import com.poscoict.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserVo vo) {
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping(value = "/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, UserVo vo) {
		UserVo userVo = userService.login(vo);
		
//		if(userVo == null) {
//			return "redirect:/user/login";
//		}
//		
//		session.setAttribute("authUser", userVo);
		
		return "redirect:/main";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		
		if(session != null) {
			session.removeAttribute("authUser");
			session.invalidate();
		}
		
		return "redirect:/main";
	}
}
