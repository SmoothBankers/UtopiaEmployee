package com.ss.utopia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ss.utopia.de.UserRole;

@Repository
public class UserRoleDAO extends AbstractDAO<UserRole> implements ResultSetExtractor<List<UserRole>>{


	@Override
	public List<UserRole> extractData(ResultSet rs) throws SQLException{
		List<UserRole> list = new ArrayList<>();
		while (rs.next()) {
			UserRole obj = new UserRole();
			obj.setName(rs.getString("name"));
			obj.setId(rs.getInt("id"));
			list.add(obj);
		}
		return list;
	}

	@Override
	public Integer create(UserRole obj) throws SQLException, ClassNotFoundException{
		String query = "INSERT INTO user_role (name) VALUES (?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query, new String[] { "id" });
			ps.setString(1, obj.getName());
			return ps;
		}, keyHolder);
		Integer key = keyHolder.getKey().intValue();
		obj.setId(key);
		return key;
	}

	@Override
	public void update(UserRole obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("update user_role set name = ? where id = ?", obj.getName(), obj.getId());
		
	}

	@Override
	public void delete(UserRole obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("delete from user_role where id = ?", obj.getId());
		
	}

	@Override
	public List<UserRole> getAll() throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query("select * from user_role", this);
	}

	@Override
	public List<UserRole> getData(String query, Object... params) throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query(query, this, params);
	}
	
	@Override
	public UserRole get(Object unique_key) throws SQLException, ClassNotFoundException{
		List<UserRole> list = jdbcTemplate.query("select * from user_role where id = ?", this, (Integer) unique_key);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

}
