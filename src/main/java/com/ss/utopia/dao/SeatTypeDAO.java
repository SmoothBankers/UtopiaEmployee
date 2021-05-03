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

import com.ss.utopia.de.SeatType;

/**
 * 
 * @author Parker W.
 *
 */
@Repository
public class SeatTypeDAO extends AbstractDAO<SeatType> implements ResultSetExtractor<List<SeatType>>{

	@Override
	public List<SeatType> extractData(ResultSet rs) throws SQLException {
		List<SeatType> list = new ArrayList<>();
		while (rs.next()) {
			SeatType obj = new SeatType();

			obj.setId(rs.getInt("id"));
			obj.setName(rs.getString("name"));

			list.add(obj);
		}
		return list;
	}

	@Override
	public Integer create(SeatType obj) throws SQLException, ClassNotFoundException{
		String query = "insert into seat_type (name) values (?)";
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
	public void update(SeatType obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update(
				"update seat_type set name = ? where id = ?",
				obj.getName(), obj.getId());

	}

	@Override
	public void delete(SeatType obj) throws SQLException, ClassNotFoundException {
		jdbcTemplate.update("delete from seat_type where id = ?", obj.getId());

	}

	@Override
	public List<SeatType> getAll() throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query("select * from seat_type", this);
	}

	@Override
	public List<SeatType> getData(String query, Object... params) throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query(query, this, params);
	}

	@Override
	public SeatType get(Object unique_key) throws SQLException, ClassNotFoundException{
		List<SeatType> list = jdbcTemplate.query("select * from seat_type where id = ?", this, (Integer) unique_key);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}
	
}
