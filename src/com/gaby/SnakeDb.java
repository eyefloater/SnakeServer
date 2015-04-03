package com.gaby;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * used to simulate the db
 * 
 */

public class SnakeDb {

	private static Map<String, SnakeData> snakeDataMap = new HashMap<String, SnakeData>();
	private static List<SnakeGame> snakeGameList = new ArrayList<SnakeGame>();

	public static synchronized void setLocation(String user, Point currentLoc) {
		SnakeData data = snakeDataMap.get(user);
		if (data == null) {
			data = new SnakeData();
			data.setUser(user);
			data.setCurrentLoc(currentLoc);
			snakeDataMap.put(user, data);
		} else {
			data.setCurrentLoc(currentLoc);
		}
	}

	public static synchronized Point getLocationOtherUser(String user) {

		SnakeData data = snakeDataMap.get(user);
		if (data != null) {
			return data.getCurrentLoc();
		}
		return null;
	}

	public static synchronized SnakeGame addUser(String user) {
		SnakeData data = snakeDataMap.get(user);
		if (data == null) {
			data = new SnakeData();
			data.setUser(user);
			snakeDataMap.put(user, data);
		}
		// find game with one user or create a new one if none
		SnakeGame game = findGame(user);
		if (game == null) {
			SnakeGame newGame = new SnakeGame();
			newGame.setFirst(user);
			snakeGameList.add(newGame);
			return game;
		}

		if (game.getFirst() == null) {
			game.setFirst(user);
		} else if (game.getSecond() == null) {
			game.setSecond(user);
		}
		return game;
	}

	private static SnakeGame findGame(String user) {
		for (Iterator<SnakeGame> iterator = snakeGameList.iterator(); iterator
				.hasNext();) {
			SnakeGame game = iterator.next();
			if ((game.getFirst() != null) && (game.getFirst().equals(user))) {
				game.setFirst(user);
				return game;
			}
		}
		return null;
	}


	public static String getOtherUser(String user) {
		String otherUser = null;
		for (Iterator<SnakeGame> iterator = snakeGameList.iterator(); iterator
				.hasNext();) {
			SnakeGame game = iterator.next();
			if ((game.getFirst() != null) && (game.getFirst().equals(user))) {
				return game.getSecond();
			}
			if ((game.getSecond() != null) && (game.getSecond().equals(user))) {
				return game.getSecond();
			}
		}
		return otherUser;
	}
	
}
