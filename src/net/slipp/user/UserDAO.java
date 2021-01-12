package net.slipp.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.slipp.support.SelectjdbcTmplate;
import net.slipp.support.jdbcTemplate;

public class UserDAO {
	public void addUser(User user) throws SQLException {
		jdbcTemplate template = new jdbcTemplate() {

			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
			}
		};

		String sql = "insert into USERS values(?,?,?,?)";
		template.executeUpdate(sql);
	}

	public User findByUserId(String userId) throws SQLException {
		SelectjdbcTmplate template = new SelectjdbcTmplate() {

			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);

			}

			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				if (!rs.next()) {
					return null;
				}

				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
			}
		};
		String sql = "select * from users where userId = ?";
		return (User)template.executeQuery(sql);
	}

	public void removeUser(String userId) throws SQLException {

		jdbcTemplate template = new jdbcTemplate() {

			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);

			}
		};
		String sql = "delete from users where userId = ?";
		template.executeUpdate(sql);
	}

	public void updateUser(User user) throws SQLException {

		jdbcTemplate template = new jdbcTemplate() {

			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getUserId());
			}
		};
		String sql = "update users set password = ?, name = ?, email = ? where userId = ?";
		template.executeUpdate(sql);
	}
}
