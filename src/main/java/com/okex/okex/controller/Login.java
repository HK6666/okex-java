package com.okex.okex.controller;

import com.okex.okex.server.LoginServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : huangkai @date : 2021/5/6 : 5:45 下午
 */
@RestController
@RequestMapping("/login")
public class Login {
	@Autowired
	LoginServer loginServer;
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login() throws Exception {
		return loginServer.Login();
	}
}
