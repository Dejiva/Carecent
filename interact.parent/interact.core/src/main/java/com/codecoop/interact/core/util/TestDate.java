package com.codecoop.interact.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {

	public static void main(String[] args) {
		SimpleDateFormat frmt=new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		System.out.println(frmt.format(new Date()));

	}

}
