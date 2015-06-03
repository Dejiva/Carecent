package com.codecoop.interact.core.util;

import com.codecoop.interact.core.dto.carepath.VitalSignDto;

public class VitalSignUtils {

	public static boolean isMet(VitalSignDto vitalSign,String value){
		
		String datatype=vitalSign.getDatatype();
		String min=vitalSign.getMin();
		String max=vitalSign.getMax();
		boolean metCondition=false;
		switch(datatype)
		{
		    case "INT":
		    	metCondition=false;
		    	int valu1=Integer.parseInt(value);
		    	if(min!=null && !min.isEmpty())
		    	{	if(Integer.parseInt(min)>valu1)
		    		{metCondition=true;
		    		 break;
		    		}
		    	}
		    	if(max!=null&&!max.isEmpty()){
		    		if(Integer.parseInt(max)<valu1)	{
		    			metCondition=true;
		    		}
		    	}
			   break;
		    case "FLOAT":
		    	metCondition=false;
		    	float valu2=Float.parseFloat(value);
		    	if(min!=null&&!min.isEmpty())
		    	{	if(Float.parseFloat(min)>valu2)
		    		{metCondition=true;
		    		 break;
		    		}
		    	}
		    	if(max!=null&&!max.isEmpty()){
		    		if(Float.parseFloat(max)<valu2)	{
		    			metCondition=true;
		    		}
		    	}
		    	break;
		}
		return metCondition;
	}
	
	
}
