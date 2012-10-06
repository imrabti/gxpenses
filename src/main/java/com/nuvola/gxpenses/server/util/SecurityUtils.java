package com.nuvola.gxpenses.server.util;

import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class SecurityUtils {
	
	public static String encodePasswordSha1(String password, String salt) {
		PasswordEncoder encoder = new ShaPasswordEncoder();
		return encoder.encodePassword(password, salt);
	}
}
