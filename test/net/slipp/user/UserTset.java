package net.slipp.user;

import static org.junit.Assert.*;
import org.junit.Test;

import net.slipp.db.Database;

public class UserTset {
	public static User TEST_USER = new User("userId","password", "name", "asdasd@nasd.com");
	
	@Test
	public void matchPassword() {
		assertTrue(TEST_USER.matchPassword("password"));
	}
	
	@Test
	public void notMatchPassword() {
		assertFalse(TEST_USER.matchPassword("password2"));
	}

	@Test
	public void login() throws Exception{
		User user = UserTset.TEST_USER;
		Database.addUser(user);
		assertTrue(User.login(TEST_USER.getUserId(), TEST_USER.getPassword()));
	}
	
	@Test(expected=UserNotFountException.class)
	public void loginWhenNotExistedUser() throws Exception{
		User.login("userId2", TEST_USER.getPassword());
	}
	
	@Test(expected=UserNotFountException.class)
	public void loginWhenPasswordMissmatch() throws Exception{
		User user = UserTset.TEST_USER;
		Database.addUser(user);
		User.login(TEST_USER.getUserId(), "password2");
	}

}
