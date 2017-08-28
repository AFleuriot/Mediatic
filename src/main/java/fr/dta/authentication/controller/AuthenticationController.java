package fr.dta.authentication.controller;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/authentication")
public class AuthenticationController {
	
	  @RequestMapping("/user")
	  @ResponseBody
	  public Principal user(HttpServletResponse response, Principal user) {
		if (user==null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	    return user;
	  }
	

}