/**
 * 
 */
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

import com.ss.utopia.de.AirplaneType;

/**
 * @author Parker W.
 *
 */
@Repository
public class AirplaneTypeDAO extends AbstractDAO<AirplaneType> implements ResultSetExtractor<List<AirplaneType>> {

	@Autowired
	AirportDAO adao;

	@Override
	public List<AirplaneType> extractData(ResultSet rs) throws SQLException {
		List<AirplaneType> types = new ArrayList<>();
		while (rs.next()) {
			AirplaneType type = new AirplaneType();
			type.setType(rs.getInt("id"));
			type.setCapacity(rs.getInt("max_capacity"));
			types.add(type);
		}
		return types;
	}

	@Override
	public Integer create(AirplaneType obj) throws SQLException, ClassNotFoundException{
		String query = "INSERT INTO airplane_type (max_capacity) VALUES (?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query, new String[] { "id" });
			ps.setInt(1, obj.getCapacity());
			return ps;
		}, keyHolder);
		Integer key = keyHolder.getKey().intValue();
		obj.setType(key);
		return key;
	}

	@Override
	public void update(AirplaneType obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("UPDATE airplane_type set max_capacity = ? where id = ?", obj.getCapacity(), obj.getType());

	}

	@Override
	public void delete(AirplaneType obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("DELETE FROM airplane_type where id = ?", obj.getType());

	}

	@Override
	public List<AirplaneType> getAll() throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query("SELECT * FROM airplane_type", this);
	}

	@Override
	public List<AirplaneType> getData(String query, Object... params) throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query(query, this, params);
	}
	
	@Override
	public AirplaneType get(Object unique_key) throws SQLException, ClassNotFoundException{
		List<AirplaneType> list = jdbcTemplate.query("select * from airplane_type where id = ?", this, (Integer) unique_key);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

}
