package net.slipp.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.PrepareStatementSetter;
import core.jdbc.RowMapper;
import core.jdbc.jdbcTemplate;

public class UserDAO {
	public void addUser(User user)  {
		jdbcTemplate template = new jdbcTemplate();
		String sql = "insert into USERS values(?,?,?,?)";
		template.executeUpdate(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}

	public User findByUserId(String userId) {
		RowMapper<User> rm = rs -> 
				new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
		jdbcTemplate template = new jdbcTemplate();
		String sql = "select * from users where userId = ?";
		return template.executeQuery(sql, rm, userId);
	}

	public void removeUser(String userId) {
		jdbcTemplate template = new jdbcTemplate();
		String sql = "delete from users where userId = ?";
		template.executeUpdate(sql, userId);
	}

	public void updateUser(User user) {
		jdbcTemplate template = new jdbcTemplate();
		String sql = "update users set password = ?, name = ?, email = ? where userId = ?";
		template.executeUpdate(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
	}

	public List<User> findUsers() throws SQLException {
		RowMapper<User> rm = rs ->
				new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
		jdbcTemplate template = new jdbcTemplate();
		String sql = "select * from users";
		return template.list(sql, rm);
	}
}
