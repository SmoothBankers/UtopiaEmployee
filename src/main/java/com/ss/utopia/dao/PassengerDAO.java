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

import com.ss.utopia.de.Booking;
import com.ss.utopia.de.Passenger;

@Repository
public class PassengerDAO extends AbstractDAO<Passenger> implements ResultSetExtractor<List<Passenger>>{

	@Autowired
	BookingDAO bdao;

	@Override
	public List<Passenger> extractData(ResultSet rs) throws SQLException {
		List<Passenger> passengers = new ArrayList<>();
		while (rs.next()) {
			Passenger passenger = new Passenger();
			passenger.setId(rs.getInt("id"));
			passenger.setAddress(rs.getString("address"));

			Booking booking;
			try {
				booking = bdao.get(rs.getInt("booking_id"));
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

			passenger.setBooking(booking);
			passenger.setFamilyName(rs.getString("family_name"));
			passenger.setGivenName(rs.getString("given_name"));
			passenger.setDateOfBirth(rs.getDate("dob"));
			passenger.setGender(rs.getString("gender"));

			passengers.add(passenger);
		}
		return passengers;
	}

	@Override
	public Integer create(Passenger obj) throws SQLException, ClassNotFoundException{
		String query = "INSERT INTO passenger (booking_id, given_name, family_name, dob, gender, address) VALUES (?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query, new String[] { "id" });
			ps.setInt(1, obj.getBooking().getId());
			ps.setString(2, obj.getGivenName());
			ps.setString(3, obj.getFamilyName());
			ps.setDate(4, obj.getDateOfBirth());
			ps.setString(5, obj.getGender());
			ps.setString(6, obj.getAddress());
			return ps;
		}, keyHolder);
		Integer key = keyHolder.getKey().intValue();
		obj.setId(key);
		return key;
	}

	@Override
	public void update(Passenger obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update(
				"update passenger set booking_id = ?, given_name = ?, family_name= ?, dob = ?, gender = ?, address = ? where id = ?",
				obj.getBooking().getId(), obj.getGivenName(), obj.getFamilyName(), obj.getDateOfBirth(),
				obj.getGender(), obj.getAddress(), obj.getId());

	}

	@Override
	public void delete(Passenger obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("delete from passenger where id = ?", obj.getId());
	}

	@Override
	public List<Passenger> getAll() throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query("select * from passenger", this);
	}

	@Override
	public List<Passenger> getData(String query, Object... params) throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query(query, this, params);
	}
	
	@Override
	public Passenger get(Object unique_key) throws SQLException, ClassNotFoundException{
		List<Passenger> list = jdbcTemplate.query("select * from passenger where id = ?", this, (Integer) unique_key);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

}
