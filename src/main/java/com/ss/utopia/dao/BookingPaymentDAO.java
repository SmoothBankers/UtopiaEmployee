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

import com.ss.utopia.de.BookingPayment;
/**
 * @author Parker W.
 *
 */
@Repository
public class BookingPaymentDAO extends AbstractDAO<BookingPayment> implements ResultSetExtractor<List<BookingPayment>>{
	
	@Autowired
	BookingDAO bdao;


	@Override
	public List<BookingPayment> extractData(ResultSet rs) throws SQLException {
		List<BookingPayment> payments = new ArrayList<>();
		while(rs.next()) {
			BookingPayment payment = new BookingPayment();
			
			try {
				payment.setBooking(bdao.getData("select * from booking where id = ?", rs.getInt("booking_id")).get(0));
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			payment.setStripeId(rs.getString("stripe_id"));
			payment.setRefunded(rs.getBoolean("refunded"));
			
			payments.add(payment);
			
		}
		return payments;

	}

	@Override
	public Integer create(BookingPayment obj) throws SQLException, ClassNotFoundException{

		String query = "INSERT INTO booking_payment (booking_id, stripe_id, refunded) VALUES (?,?,?)";
		jdbcTemplate.update(query, obj.getBooking().getId(), obj.getStripeId(), obj.isRefunded());
		return null; //does not have a unique/generated key
	}

	@Override
	public void update(BookingPayment obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("update booking_payment set stripe_id = ?, refunded = ? where booking_id = ?", obj.getStripeId(),
				obj.isRefunded(), obj.getBooking().getId());

	}

	@Override
	public void delete(BookingPayment obj) throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("delete from booking_payment where booking_id = ?", obj.getBooking().getId());	
		//booking_id isn't actually unique here so this could delete multiple entries, need to watch?
		

	}

	@Override
	public List<BookingPayment> getAll() throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query("select * from booking_payment", this);
	}
	
	@Override
	public List<BookingPayment> getData(String query, Object... params) throws SQLException, ClassNotFoundException{
		return jdbcTemplate.query(query, this, params);
	}
	
	@Override
	public BookingPayment get(Object unique_key) throws SQLException, ClassNotFoundException{
		List<BookingPayment> list = jdbcTemplate.query("select * from booking_payment where booking_id = ?", this, (Integer) unique_key);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

}
