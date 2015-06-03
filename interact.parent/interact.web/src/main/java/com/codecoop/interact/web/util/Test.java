package com.codecoop.interact.web.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class Test {

	public static void main(String[] args) {
		Md5PasswordEncoder encoder=new Md5PasswordEncoder();
		String encriptPass=encoder.encodePassword("testuser1",null);
		System.out.println(encriptPass);//6fc42c4388ed6f0c5a91257f096fef3c
	}

}
