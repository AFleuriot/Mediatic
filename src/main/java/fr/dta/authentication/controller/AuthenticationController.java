package fr.dta.authentication.controller;
import java.security.Principal;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/authentication")
public class AuthenticationController {
	
	  @RequestMapping("/user")
	  @ResponseBody
	  public Principal user(Principal user) {
		  System.out.println("======= Controller Authentication =======");
	    return user;
	  }
	

}