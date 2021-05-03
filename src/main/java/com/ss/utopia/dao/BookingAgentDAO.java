package com.ss.utopia.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ss.utopia.de.BookingAgent;

@Repository
public class BookingAgentDAO extends AbstractDAO<BookingAgent> implements ResultSetExtractor<List<BookingAgent>> {

	@Autowired
	BookingDAO bdao;

	@Autowired
	UserDAO udao;

	@Override
	public List<BookingAgent> extractData(ResultSet rs) throws SQLException {
		List<BookingAgent> list = new ArrayList<>();
		while (rs.next()) {
			BookingAgent obj = new BookingAgent();

			try {
				obj.setAgentId(udao.get(rs.getInt("agent_id")));
				obj.setBooking(bdao.get(rs.getInt("booking_id")));
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			list.add(obj);
		}
		return list;
	}

	@Override
	public Integer create(BookingAgent obj) throws SQLException, ClassNotFoundException{
		// return super.addPK("INSERT INTO booking_agent (booking_id, agent_id) VALUES
		// (?,?)", obj.getBooking().getId(), obj.getAgentId().getId());
		String query = "INSERT INTO booking_agent (booking_id, agent_id) VALUES (?,?)";
		jdbcTemplate.update(query, obj.getBooking().getId(), obj.getAgentId().getId());
		return null; // does not have a unique key, is all determined by booking and user
	}

	@Override
	public void update(BookingAgent obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("UPDATE booking_agent set agent_id = ? where booking_id = ?",
				obj.getAgentId().getId(), obj.getBooking().getId());

	}

	@Override
	public void delete(BookingAgent obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("delete from booking_agent where booking_id = ?", obj.getBooking().getId());

	}

	@Override
	public List<BookingAgent> getAll() throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query("select * from booking_agent", this);
	}

	@Override
	public List<BookingAgent> getData(String query, Object... params) throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query(query, this, params);
	}

	@Override
	public BookingAgent get(Object unique_key) throws SQLException, ClassNotFoundException {
		List<BookingAgent> list = jdbcTemplate.query("select * from booking_agent where booking_id = ?", this,
				(Integer) unique_key);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

}
