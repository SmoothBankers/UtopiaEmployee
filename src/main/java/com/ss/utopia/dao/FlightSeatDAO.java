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

import com.ss.utopia.de.FlightSeat;

/**
 * @author Parker W.
 *
 */
@Repository
public class FlightSeatDAO extends AbstractDAO<FlightSeat> implements ResultSetExtractor<List<FlightSeat>>{
	
	@Autowired
	SeatTypeDAO seatTypeDAO;
	
	@Autowired
	FlightDAO flightDAO;
	
	@Override
	public List<FlightSeat> extractData(ResultSet rs) throws SQLException {
		List<FlightSeat> list = new ArrayList<>();
		while (rs.next()) {
			FlightSeat obj = new FlightSeat();
			obj.setId(rs.getInt("id"));
			try {
				Integer fid = rs.getInt("flight_id");
				Integer tid = rs.getInt("type_id");
				obj.setFlight(flightDAO.get(fid));
				obj.setType(seatTypeDAO.get(tid));
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			obj.setPrice(rs.getFloat("seat_price"));
			

			list.add(obj);
		}
		return list;
	}

	@Override
	public Integer create(FlightSeat obj) throws SQLException, ClassNotFoundException{
		String query = "insert into flight_seats (flight_id, type_id, seat_price) values (?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query, new String[] { "id" });
			ps.setInt(1, obj.getFlight().getId());
			ps.setInt(2, obj.getType().getId());
			ps.setFloat(3, obj.getPrice());
			return ps;
		}, keyHolder);
		Integer key = keyHolder.getKey().intValue();
		obj.setId(key);
		return key;
	}

	@Override
	public void update(FlightSeat obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update(
				"update flight_seats set flight_id = ?, type_id = ?, seat_price = ? where id = ?",
				obj.getFlight().getId(), obj.getType().getId(), obj.getPrice(), obj.getId());

	}

	@Override
	public void delete(FlightSeat obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("delete from flight_seats where id = ?", obj.getId());

	}

	@Override
	public List<FlightSeat> getAll() throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query("select * from flight_seats", this);
	}
	
	public List<FlightSeat> getByFlight(Object unique_key) throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query("select * from flight_seats where flight_id = ?", this, (Integer) unique_key);
	}


	@Override
	public List<FlightSeat> getData(String query, Object... params) throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query(query, this, params);
	}

	@Override
	public FlightSeat get(Object unique_key) throws SQLException, ClassNotFoundException{
		List<FlightSeat> list = jdbcTemplate.query("select * from flight_seats where id = ?", this, (Integer) unique_key);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

}
