package io.shamanic.snakedb;

import java.sql.Timestamp;

public class SnakeGame {
	private int id;
	private String created_by;
	private Timestamp created_on;
	private long duration;
	private boolean onePlayerGame;

	public int getSnakeGameId() {
		return id;
	}

	public void setSnakeGameId(int id) {
		this.id = id;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Timestamp getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Timestamp created_on) {
		this.created_on = created_on;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public boolean isOnePlayerGame() {
		return onePlayerGame;
	}

	public void setOnePlayerGame(boolean onePlayerGame) {
		this.onePlayerGame = onePlayerGame;
	}

}
