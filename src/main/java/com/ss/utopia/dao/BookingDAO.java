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

import com.ss.utopia.de.Booking;

@Repository
public class BookingDAO extends AbstractDAO<Booking> implements ResultSetExtractor<List<Booking>>{

	@Override
	public List<Booking> extractData(ResultSet rs) throws SQLException {
		List<Booking> list = new ArrayList<>();
		while (rs.next()) {
			Booking obj = new Booking();
			obj.setActive(rs.getBoolean("is_active"));
			obj.setConfirmationCode(rs.getString("confirmation_code"));
			obj.setId(rs.getInt("id"));
			list.add(obj);
		}
		return list;
	}

	@Override
	public Integer create(Booking obj) throws SQLException, ClassNotFoundException{
		String query = "INSERT INTO booking (is_active, confirmation_code) VALUES (?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query, new String[] { "id" });
			ps.setBoolean(1, obj.isActive());
			ps.setString(2, obj.getConfirmationCode());
			return ps;
		}, keyHolder);
		Integer key = keyHolder.getKey().intValue();
		obj.setId(key);
		return key;
	}

	@Override
	public void update(Booking obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("UPDATE booking set is_active = ?, confirmation_code = ? where id = ?", obj.isActive(), obj.getConfirmationCode(), obj.getId());
		
	}

	@Override
	public void delete(Booking obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("delete from booking where id = ?", obj.getId());
		
	}

	@Override
	public List<Booking> getAll() throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query("select * from booking", this);
	}

	@Override
	public List<Booking> getData(String query, Object... params) throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query(query, this, params);
	}
	
	@Override
	public Booking get(Object unique_key) throws SQLException, ClassNotFoundException{
		List<Booking> list = jdbcTemplate.query("select * from booking where id = ?", this, (Integer) unique_key);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

}
