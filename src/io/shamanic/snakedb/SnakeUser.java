package io.shamanic.snakedb;

import java.sql.Timestamp;

public class SnakeUser {
	
	private String snakeuser;
	private String password;
	private String email;
	private int failed_login_attempts;
	private Timestamp failed_login_time;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSnakeuser() {
		return snakeuser;
	}
	public void setSnakeuser(String snakeuser) {
		this.snakeuser = snakeuser;
	}
	public Timestamp getFailed_login_time() {
		return failed_login_time;
	}
	public void setFailed_login_time(Timestamp failed_login_time) {
		this.failed_login_time = failed_login_time;
	}
	public int getFailed_login_attempts() {
		return failed_login_attempts;
	}
	public void setFailed_login_attempts(int failed_login_attempts) {
		this.failed_login_attempts = failed_login_attempts;
	}
	
	
	
}
