package io.shamanic.snakedb;

public interface Mapper {
	SnakeUser getSnakeUser(String name);
	
	SnakeGame getSnakeGame(int id);
}
