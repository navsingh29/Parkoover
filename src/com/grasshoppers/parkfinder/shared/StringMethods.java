package com.grasshoppers.parkfinder.shared;

public class StringMethods {
	public static String retString (String string) {
		string = string.trim();
		if (string.equals(""))
			return null;
		else return string;
	}
}
