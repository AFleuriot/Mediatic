package fr.dta.authentication.util;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

import fr.dta.utilisateur.model.User;


public class SecurityUtils {

	public static void sendResponse(HttpServletResponse response, int httpServletResponse, User user) {
		response.setStatus(httpServletResponse);
		
	}

	public static void sendError(HttpServletResponse response, AuthenticationException exception, int scUnauthorized,
			String string) {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	}

	public static void sendError(HttpServletResponse response, AccessDeniedException exception, int scForbidden,
			String string) {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		
	}

}