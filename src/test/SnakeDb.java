package test;

//create a hashmap for tracking people's names / passwords.
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JOptionPane;

public class SnakeDb {

	// apparently classes that implement Map need two constructor?,
	// 1) w no-arg creates empty map 2) constructor w one Map<k,V> parameter
	// that
	// creates an object with same element as the constructor argument
	// and an iterator to examine/modify elements.

	private Map<String, String> userInfo;

	public SnakeDb() {

		userInfo = new LinkedHashMap<String, String>();
		//write a method to use HttpPost to pull new user data from a page, rather than
		//pre-populate the map of users.
		userInfo.put("gab", "1234");
		userInfo.put("dav", "4321");
	}

	// public boolean checkUser(String){
	// if String
	// }

	public boolean containsKey(String user) {
		return userInfo.containsKey(user);
	}

	public boolean verifyPassword(String user, String password) {
		String pw = userInfo.get(user);
		return (pw == null) ? false : (pw.equals(password));
	}

	// method to provide list of users
	public List<String> getUsers() {
		List<String> userList = new ArrayList<String>();
		Set<Entry<String, String>> set = userInfo.entrySet();
		for (Iterator<Entry<String, String>> iterator = set.iterator(); iterator.hasNext();) {
			Entry<String, String> entry = (Entry<String, String>) iterator.next();
			userList.add(entry.getKey());

		}
		return userList;
	}

	// method to remove user
	public void deleteUser(String user) {
		int option = JOptionPane
				.showConfirmDialog(null, "Confirm delete user?", "Delete", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			userInfo.remove(user);
		} else if (option == JOptionPane.CANCEL_OPTION) {
			return;
		}

	}

	// method to check if key is unique (public boolean equals(Object obj)??
	public boolean isUserUnique(String user) {
		
		return false;
	}
}
