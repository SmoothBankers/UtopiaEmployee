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

import com.ss.utopia.de.Flight;

@Repository
public class FlightDAO extends AbstractDAO<Flight> implements ResultSetExtractor<List<Flight>> {

	@Autowired
	RouteDAO rdao;

	@Autowired
	AirplaneDAO pdao;

	@Override
	public List<Flight> extractData(ResultSet rs) throws SQLException {
		List<Flight> list = new ArrayList<>();
		while (rs.next()) {
			Flight obj = new Flight();

			obj.setId(rs.getInt("id"));
			obj.setDepartureTime(rs.getTimestamp("departure_time"));
			try {
				obj.setRoute(rdao.get(rs.getInt("route_id")));
				obj.setPlane(pdao.get(rs.getInt("airplane_id")));
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			obj.setArrivalTime(rs.getTimestamp("arrival_time"));
//			obj.setReservedSeats(rs.getInt("reserved_seats"));
//			obj.setSeatPrice(rs.getFloat("seat_price"));

			list.add(obj);
		}
		return list;
	}

	@Override
	public Integer create(Flight obj) throws SQLException, ClassNotFoundException{
		String query = "insert into flight (route_id, airplane_id, departure_time, arrival_time) values (?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query, new String[] { "id" });
			ps.setInt(1, obj.getRoute().getId());
			ps.setInt(2, obj.getPlane().getId());
			ps.setTimestamp(3, obj.getDepartureTime());
			ps.setTimestamp(4, obj.getArrivalTime());
//			ps.setInt(5, obj.getReservedSeats());
//			ps.setFloat(6, obj.getSeatPrice());
			return ps;
		}, keyHolder);
		Integer key = keyHolder.getKey().intValue();
		obj.setId(key);
		return key;
	}

	@Override
	public void update(Flight obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update(
				"update flight set route_id = ?, airplane_id = ?, departure_time = ?, arrival_time = ? where id = ?",
				obj.getRoute().getId(), obj.getPlane().getId(), obj.getDepartureTime(), obj.getArrivalTime(), obj.getId());

	}

	@Override
	public void delete(Flight obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("delete from flight where id = ?", obj.getId());

	}

	@Override
	public List<Flight> getAll() throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query("select * from flight", this);
	}

	@Override
	public List<Flight> getData(String query, Object... params) throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query(query, this, params);
	}

	@Override
	public Flight get(Object unique_key) throws SQLException, ClassNotFoundException{
		List<Flight> list = jdbcTemplate.query("select * from flight where id = ?", this, (Integer) unique_key);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

}
