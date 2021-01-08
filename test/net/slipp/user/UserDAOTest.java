package net.slipp.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

public class UserDAOTest {

	private UserDAO userDao;
	
	@Before
	public void setup() {
		userDao = new UserDAO();
		
	}

	@Test
	public void connection() {
		Connection con = userDao.getConnection();
		assertNotNull(con);
	}
	
	@Test
	public void crud() throws Exception {
		User user = UserTest.TEST_USER;
		userDao.removeUser(user.getUserId());
		userDao.addUser(UserTest.TEST_USER);
		
		User dbUser = userDao.findByUserId("userId");
		assertEquals(user, dbUser);
	}
	
	@Test
	public void zohoi() throws Exception{
		User user = UserTest.TEST_USER;
		userDao.removeUser(user.getUserId());
		
		User dbUser = userDao.findByUserId("userId");
		assertNull(dbUser);
	}

}
