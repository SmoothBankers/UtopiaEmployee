/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ss.utopia.de.Airport;

/**
 * @author Parker W.
 *
 */
@Repository
public class AirportDAO extends AbstractDAO<Airport> implements ResultSetExtractor<List<Airport>> {

	@Override
	public List<Airport> extractData(ResultSet rs) throws SQLException {
		List<Airport> ports = new ArrayList<>();
		while (rs.next()) {
			Airport port = new Airport();
			port.setCode(rs.getString("iata_id"));
			port.setCityName(rs.getString("city"));
			ports.add(port);
		}
		return ports;
	}

	@Override
	public Integer create(Airport obj) throws SQLException, ClassNotFoundException{
		try {
			jdbcTemplate.update("INSERT INTO airport (iata_id, city) VALUES (?,?)", obj.getCode(), obj.getCityName());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null; // airport has no key that it is given, unique id comes from iata_id
	}

	@Override
	public void update(Airport obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("UPDATE airport set city = ? where iata_id = ?", obj.getCityName(), obj.getCode());
	}

	@Override
	public void delete(Airport obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("DELETE FROM airport where iata_id = ?", obj.getCode());

	}

	@Override
	public List<Airport> getAll() throws ClassNotFoundException, SQLException{
		return jdbcTemplate.query("SELECT * FROM airport", this);
	}
	
	@Override
	public List<Airport> getData(String query, Object...params) throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query(query, this, params);
	}
	
	@Override
	public Airport get(Object unique_key) throws SQLException, ClassNotFoundException{
		List<Airport> list = jdbcTemplate.query("select * from airport where iata_id = ?", this, (String) unique_key);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

}
