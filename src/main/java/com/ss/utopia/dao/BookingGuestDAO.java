/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ss.utopia.de.BookingGuest;

/**
 * @author Parker W.
 *
 */
@Repository
public class BookingGuestDAO extends AbstractDAO<BookingGuest> implements ResultSetExtractor<List<BookingGuest>>{
	
	@Autowired
	BookingDAO bdao;

	@Override
	public List<BookingGuest> extractData(ResultSet rs) throws SQLException {
		List<BookingGuest> list = new ArrayList<>();
		while (rs.next()) {
			BookingGuest obj = new BookingGuest();
			try {
				obj.setBooking(bdao.get(rs.getInt("booking_id")));
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			obj.setEmail(rs.getString("contact_email"));
			obj.setPhone(rs.getString("contact_phone"));

			list.add(obj);
		}
		return list;
	}

	@Override
	public Integer create(BookingGuest obj) throws SQLException, ClassNotFoundException{		
		String query = "INSERT INTO booking_guest (booking_id, contact_email, contact_phone) VALUES (?,?,?)";
		jdbcTemplate.update(query, obj.getBooking().getId(), obj.getEmail(), obj.getPhone());
		return null; // does not have a unique key, is all determined by booking and user
	}

	@Override
	public void update(BookingGuest obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("update booking_guest set contact_email = ?, contact_phone = ? where booking_id = ?",
				obj.getEmail(), obj.getPhone(), obj.getBooking().getId());
	}

	@Override
	public void delete(BookingGuest obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("delete from booking_guest where booking_id = ?", obj.getBooking().getId());
	}

	@Override
	public List<BookingGuest> getAll() throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query("select * from booking_guest", this);
	}

	@Override
	public List<BookingGuest> getData(String query, Object... params) throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query(query, this, params);
	}
	
	@Override
	public BookingGuest get(Object unique_key) throws SQLException, ClassNotFoundException{
		List<BookingGuest> list = jdbcTemplate.query("select * from booking_guest where booking_id = ?", this, (Integer) unique_key);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

}
