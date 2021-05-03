/**
 * 
 */
package com.ss.utopia.de;

import java.io.Serializable;

/**
 * @author Parker W.
 *
 */
public class BookingAgent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6836696233275568116L;
	private Booking booking = new Booking();
	private User agentId;

	/**
	 * @return the booking
	 */
	public Booking getBooking() {
		return booking;
	}

	/**
	 * @param booking the booking to set
	 */
	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	/**
	 * @return the id
	 */
	public User getAgentId() {
		return agentId;
	}

	/**
	 * @param id the id to set
	 */
	public void setAgentId(User user) {
		this.agentId = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agentId == null) ? 0 : agentId.hashCode());
		result = prime * result + ((booking == null) ? 0 : booking.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingAgent other = (BookingAgent) obj;
		if (agentId == null) {
			if (other.agentId != null)
				return false;
		} else if (!agentId.equals(other.agentId))
			return false;
		if (booking == null) {
			if (other.booking != null)
				return false;
		} else if (!booking.equals(other.booking))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookingAgent [" + (booking != null ? "booking=" + booking + ", " : "")
				+ (agentId != null ? "agentId=" + agentId : "") + "]";
	}

	
}
