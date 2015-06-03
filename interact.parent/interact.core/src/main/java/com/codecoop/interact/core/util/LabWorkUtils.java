package com.codecoop.interact.core.util;

import com.codecoop.interact.core.dto.carepath.LabWorkDto;

public class LabWorkUtils {

	public static boolean isMet(LabWorkDto labwork,String value){
		
		String datatype=labwork.getDatatype();
		String min=labwork.getMin();
		String max=labwork.getMax();
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
