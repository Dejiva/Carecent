package com.codecoop.interact.core.util;

import java.util.Random;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class PasswordGenUtil {
    private static final String ALPHA_CAPS  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA   = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUM     = "0123456789";
//    private static final String SPL_CHARS   = "!@#$%^&*_=+-/";
 
    public static String generatePswd() {
        int noOfCAPSAlpha = 1;
        int noOfDigits = 1;
//        int noOfSplChars = 1;
        int len = 8;

        if( (noOfCAPSAlpha + noOfDigits) > len )
            throw new IllegalArgumentException
            ("Min. Length should be atleast sum of (CAPS, DIGITS, SPL CHARS) Length!");
        Random rnd = new Random();
        char[] pswd = new char[len];
        int index = 0;
        for (int i = 0; i < noOfCAPSAlpha; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = ALPHA_CAPS.charAt(rnd.nextInt(ALPHA_CAPS.length()));
        }
        for (int i = 0; i < noOfDigits; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = NUM.charAt(rnd.nextInt(NUM.length()));
        }
/*        for (int i = 0; i < noOfSplChars; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = SPL_CHARS.charAt(rnd.nextInt(SPL_CHARS.length()));
        }
*/        
        for(int i = 0; i < len; i++) {
            if(pswd[i] == 0) {
                pswd[i] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
            }
        }
        return new String(pswd);
    }
     
    private static int getNextIndex(Random rnd, int len, char[] pswd) {
        int index = rnd.nextInt(len);
        while(pswd[index = rnd.nextInt(len)] != 0);
        return index;
    }
    
    public static String encriptPassword(String passCode){
    	Md5PasswordEncoder encoder=new Md5PasswordEncoder();
		String encriptPass=encoder.encodePassword(passCode,null);
    	return encriptPass;
    }

}
