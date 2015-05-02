package com.gaby;

import java.util.List;

public class GameFactory {
	private static List<Game> gameList;

	public static Game createGame(String gameType) {
		if (gameType.equals(Game.onePlayer)) {
			Game game = new OnePlayerGame();
			gameList.add(game);
			return game;
		}
		Game game = new TwoPlayerGame();
		gameList.add(game);
		return game;

	}
}
