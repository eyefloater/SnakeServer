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
	private static List<SnakeUser> userList = new ArrayList<SnakeUser>();

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

	public static void SetSnakeDbName() {
		String snakeDbName = "snakeDataBase v.0.1";
	}

	public static String GetSnakeDbName() {
		String snakeDbName = "snakeDataBase v.0.1";
		return snakeDbName;
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
			newGame.setFirstPlayer(user);
			snakeGameList.add(newGame);
			return game;
		}

		if (game.getFirstPlayer() == null) {
			game.setFirstPlayer(user);
		} else if (game.getSecondPlayer() == null) {
			game.setSecondPlayer(user);
		}
		return game;
	}

	private static SnakeGame findGame(String user) {
		for (Iterator<SnakeGame> iterator = snakeGameList.iterator(); iterator
				.hasNext();) {
			SnakeGame game = iterator.next();
			if ((game.getFirstPlayer() != null)
					&& (game.getFirstPlayer().equals(user))) {
				game.setFirstPlayer(user);
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
			if ((game.getFirstPlayer() != null)
					&& (game.getFirstPlayer().equals(user))) {
				return game.getSecondPlayer();
			}
			if ((game.getSecondPlayer() != null)
					&& (game.getSecondPlayer().equals(user))) {
				return game.getSecondPlayer();
			}
		}
		return otherUser;
	}

	public static SnakeGame getTwoPlayerGame(String userName) {
		for (Iterator<SnakeGame> iterator = snakeGameList.iterator(); iterator
				.hasNext();) {
			SnakeGame game = iterator.next();
			if ((game.getFirstPlayer() != null)) {
				game.setSecondPlayer(userName);
				return game;
			}
			// return null;
		}
		SnakeGame game = new SnakeGame();
		game.setFirstPlayer(userName);
		snakeGameList.add(game);
		return null;
	}

	public static SnakeGame GetOnePlayerGame(String userName) {
		SnakeGame game = new SnakeGame();
		game.setFirstPlayer(userName);
		snakeGameList.add(game);
		return game;
	}
}
