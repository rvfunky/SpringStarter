package com.raghu.springstarter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.raghu.springstarter.dao.UserDAO;

@Controller
public class HomeController {
	
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String showHome(){
		
		userDAO.addUser("raghu", "raghu");
		return "home.jsp";
	}

}
