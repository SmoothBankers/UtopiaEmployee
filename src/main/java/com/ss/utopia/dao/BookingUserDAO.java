package com.ss.utopia.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ss.utopia.de.BookingUser;

@Repository
public class BookingUserDAO extends AbstractDAO<BookingUser> implements ResultSetExtractor<List<BookingUser>> {

	@Autowired
	BookingDAO bdao;

	@Autowired
	UserDAO udao;

	@Override
	public List<BookingUser> extractData(ResultSet rs) throws SQLException {
		List<BookingUser> list = new ArrayList<>();
		while (rs.next()) {
			BookingUser obj = new BookingUser();

			try {
				obj.setUserId(udao.get(rs.getInt("user_id")));
				obj.setBooking(bdao.get(rs.getInt("booking_id")));
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
	public Integer create(BookingUser obj) throws SQLException, ClassNotFoundException {
		// return super.addPK("INSERT INTO booking_agent (booking_id, agent_id) VALUES
		// (?,?)", obj.getBooking().getId(), obj.getAgentId().getId());
		String query = "INSERT INTO booking_user (booking_id, user_id) VALUES (?,?)";
		jdbcTemplate.update(query, obj.getBooking().getId(), obj.getUserId().getId());
		return null; // does not have a unique key, is all determined by booking and user
	}

	@Override
	public void update(BookingUser obj) throws SQLException, ClassNotFoundException {
		jdbcTemplate.update("UPDATE booking_user set user_id = ? where booking_id = ?", obj.getUserId().getId(),
				obj.getBooking().getId());

	}

	@Override
	public void delete(BookingUser obj) throws SQLException, ClassNotFoundException {
		jdbcTemplate.update("delete from booking_user where booking_id = ?", obj.getBooking().getId());

	}

	@Override
	public List<BookingUser> getAll() throws SQLException, ClassNotFoundException {
		return jdbcTemplate.query("select * from booking_user", this);
	}

	@Override
	public List<BookingUser> getData(String query, Object... params) throws SQLException, ClassNotFoundException {
		return jdbcTemplate.query(query, this, params);
	}

	@Override
	public BookingUser get(Object unique_key) throws SQLException, ClassNotFoundException {
		List<BookingUser> list = jdbcTemplate.query("select * from booking_user where booking_id = ?", this,
				(Integer) unique_key);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

}
