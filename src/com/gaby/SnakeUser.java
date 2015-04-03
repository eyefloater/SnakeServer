package com.gaby;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SnakeUser {

	private static Map<String, String> map;

	static {
		map = new HashMap<String, String>();
		map.put("tino", "tino");
		map.put("gabe", "gabe");
		map.put("dave", "dave");
	}
	
	private String user;
	private String password;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static boolean validateUser(String userName, String password)	{
		if ((userName == null) || (password == null))	{
			return false;
		}
		Set<Entry<String, String>> set = map.entrySet();
		for (Iterator<Entry<String, String>> iterator = set.iterator(); iterator.hasNext();) {
			Entry<String, String> entry = iterator.next();
			if ((entry.getKey().equals(userName)) && (entry.getValue().equals(password)))	{
				return true;
			}
		}
		return false;
	}
}
