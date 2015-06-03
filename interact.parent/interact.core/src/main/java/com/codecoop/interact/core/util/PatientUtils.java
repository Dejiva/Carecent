package com.codecoop.interact.core.util;

public class PatientUtils {
	
    public static String getPatientFullName(String firstName,String lastName){
		String fullName = firstName +" "+(lastName !=null ?lastName:"");
		return fullName.trim();
	}

}
