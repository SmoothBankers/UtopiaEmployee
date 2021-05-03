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

import com.ss.utopia.de.Airplane;
import com.ss.utopia.de.AirplaneType;

/**
 * @author Parker W.
 *
 */
@Repository
public class AirplaneDAO extends AbstractDAO<Airplane> implements ResultSetExtractor<List<Airplane>> {
	@Autowired
	AirportDAO adao;
	
	@Autowired
	AirplaneTypeDAO tdao;

	@Override
	public Integer create(Airplane obj) throws SQLException, ClassNotFoundException{

		String query = "INSERT INTO airplane (type_id) VALUES (?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query, new String[] { "id" });
			ps.setInt(1, obj.getType().getType());
			return ps;
		}, keyHolder);
		Integer key = keyHolder.getKey().intValue();
		obj.setId(key);
		return key;
	}

	@Override
	public void update(Airplane obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("UPDATE airplane set type_id = ? where id = ?", obj.getType().getType(), obj.getId());

	}

	@Override
	public void delete(Airplane obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("DELETE FROM airplane where id = ?", obj.getId());
	}

	@Override
	public List<Airplane> extractData(ResultSet rs) throws SQLException {
		List<Airplane> planes = new ArrayList<>();
		while (rs.next()) {
			Airplane plane = new Airplane();
			plane.setId(rs.getInt("id"));

			AirplaneType t;
			try {
				t = tdao.get(rs.getInt("type_id"));
				plane.setType(t);
				planes.add(plane);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

			
		}
		return planes;
	}

	@Override
	public List<Airplane> getAll() throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query("select * from airplane", this);
	}

	@Override
	public List<Airplane> getData(String query, Object... params) throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query("select * from airplane", this, params);
	}
	
	@Override
	public Airplane get(Object unique_key) throws SQLException, ClassNotFoundException {
		List<Airplane> list = jdbcTemplate.query("select * from airplane where id = ?", this, (Integer) unique_key);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

}
