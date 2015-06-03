package com.codecoop.interact.core.util;

public class StaffUtils {
	
    public static String getStaffFullName(String firstName,String middleName,String lastName){
		String fullName = (firstName +" "+(middleName !=null ?middleName:"")).trim()+" "+(lastName !=null ?lastName:"");
		return fullName.trim();
	}

}
