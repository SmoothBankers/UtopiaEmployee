package com.ss.utopia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ss.utopia.de.User;

@Repository
public class UserDAO extends AbstractDAO<User> implements ResultSetExtractor<List<User>> {
	
	@Autowired
	UserRoleDAO urdao;

	@Override
	public List<User> extractData(ResultSet rs) throws SQLException {
		List<User> list = new ArrayList<>();
		while (rs.next()) {
			User obj = new User();

			obj.setId(rs.getInt("id"));
			obj.setGivenName(rs.getString("given_name"));
			obj.setFamilyName(rs.getString("family_name"));
			obj.setUsername(rs.getString("username"));
			obj.setEmail(rs.getString("email"));
			obj.setPassword(rs.getString("password"));
			obj.setPhone(rs.getString("phone"));

			try {
				obj.setRole(urdao.get(rs.getInt("role_id")));
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

			list.add(obj);
		}
		return list;
	}

	@Override
	public Integer create(User obj) throws SQLException, ClassNotFoundException{
		String query = "INSERT INTO user (role_id, given_name, family_name, username, email, password, phone) VALUES (?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query, new String[] { "id" });
			ps.setInt(1, obj.getRole().getId());
			ps.setString(2, obj.getGivenName());
			ps.setString(3, obj.getFamilyName());
			ps.setString(4, obj.getUsername());
			ps.setString(5, obj.getEmail());
			ps.setString(6, obj.getPassword());
			ps.setString(7, obj.getPhone());
			return ps;
		}, keyHolder);
		Integer key = keyHolder.getKey().intValue();
		obj.setId(key);
		return key;
	}

	@Override
	public void update(User obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update(
				"UPDATE user set role_id = ?, given_name = ?, family_name = ?, username = ?, email = ?, password = ?, phone = ? where id = ?",
				obj.getRole().getId(), obj.getGivenName(), obj.getFamilyName(), obj.getUsername(), obj.getEmail(),
				obj.getPassword(), obj.getPhone(), obj.getId());
	}

	@Override
	public void delete(User obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("delete from user where id = ?", obj.getId());

	}

	@Override
	public List<User> getAll() throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query("select * from user", this);
	}

	@Override
	public List<User> getData(String query, Object... params) throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query(query, this, params);
	}
	
	@Override
	public User get(Object unique_key) throws SQLException, ClassNotFoundException{
		List<User> list = jdbcTemplate.query("select * from user where id = ?", this, (Integer) unique_key);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

}
