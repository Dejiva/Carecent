package com.codecoop.interact.core.util;

public enum ShowInSBARCarePathStepEnum {
	CONSIDER_ORDERS ,MANAGE_IN_FACILITY;

	public static boolean isStepTypeExists(String stepType){
		boolean exists = false;
		for (ShowInSBARCarePathStepEnum stepTypeEnum : ShowInSBARCarePathStepEnum.values()){
			if (stepTypeEnum.toString().equals(stepType)){
				exists = true;
				break;
			}
		}
		return exists;
	}
}
