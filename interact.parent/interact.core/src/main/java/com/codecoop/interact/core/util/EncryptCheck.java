package com.codecoop.interact.core.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class EncryptCheck {

	public static void main(String[] args) {
		Md5PasswordEncoder encoder=new Md5PasswordEncoder();
	String encriptPass=encoder.encodePassword("MoniGoda1",null);

	System.out.println(encriptPass);//6fc42c4388ed6f0c5a91257f096fef3c
	}

}
