package com.codecoop.interact.core.util;

public class CoreUtils {

	public static boolean isLongIDExists(Long id){
		if(id == null || id == 0){
			return false;
		}
		return true;
	}
	
	public static boolean isIntegerIDExists(Integer id){
		if(id == null || id == 0){
			return false;
		}
		return true;
	}
	
}
