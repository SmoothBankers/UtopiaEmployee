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

import com.ss.utopia.de.FlightBooking;

/**
 * @author Parker W.
 *
 */
@Repository
public class FlightBookingDAO extends AbstractDAO<FlightBooking> implements ResultSetExtractor<List<FlightBooking>> {

	@Autowired
	BookingDAO bdao;

	@Autowired
	FlightDAO fdao;

	@Override
	public List<FlightBooking> extractData(ResultSet rs) throws SQLException {
		List<FlightBooking> bookings = new ArrayList<>();
		while (rs.next()) {
			FlightBooking booking = new FlightBooking();

			try {
				booking.setBooking(bdao.get(rs.getInt("booking_id")));
				booking.setFlight(fdao.get(rs.getInt("flight_id")));
				bookings.add(booking);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return bookings;
	}

	@Override
	public Integer create(FlightBooking obj) throws SQLException, ClassNotFoundException {
		jdbcTemplate.update("insert into flight_bookings (flight_id, booking_id) values (?,?)", obj.getFlight().getId(),
				obj.getBooking().getId());

		// flightBookings have no unique id or generated key, return null
		return null;

	}

	@Override
	public void update(FlightBooking obj) throws SQLException, ClassNotFoundException {
		// There is no unique identifier for a FlightBooking, and in turn no way to edit
		// only a single entry
		// Leave empty for now to avoid writing over multiple entries

	}

	@Override
	public void delete(FlightBooking obj) throws SQLException, ClassNotFoundException {
		jdbcTemplate.update("delete from flight_bookings where flight_id = ? and booking_id = ?",
				obj.getFlight().getId(), obj.getBooking().getId());

	}

	@Override
	public List<FlightBooking> getAll() throws SQLException, ClassNotFoundException {
		return jdbcTemplate.query("select * from flight_bookings", this);
	}

	@Override
	public List<FlightBooking> getData(String query, Object... params) throws SQLException, ClassNotFoundException {
		return jdbcTemplate.query(query, this, params);
	}

	@Override
	public FlightBooking get(Object unique_key) throws SQLException, ClassNotFoundException {
		List<FlightBooking> list = jdbcTemplate.query("select * from flight_bookings where booking_id = ?", this,
				(Integer) unique_key);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

}
