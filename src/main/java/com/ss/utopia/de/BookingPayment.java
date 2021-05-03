/**
 * 
 */
package com.ss.utopia.de;

import java.io.Serializable;

/**
 * @author Parker W.
 *
 */
public class BookingPayment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2997465961996709993L;
	private Booking booking = new Booking();
	private String stripeId;
	private boolean refunded;

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
	 * @return the stripeId
	 */
	public String getStripeId() {
		return stripeId;
	}

	/**
	 * @param stripeId the stripeId to set
	 */
	public void setStripeId(String stripeId) {
		this.stripeId = stripeId;
	}

	/**
	 * @return the refunded
	 */
	public boolean isRefunded() {
		return refunded;
	}

	/**
	 * @param refunded the refunded to set
	 */
	public void setRefunded(boolean refunded) {
		this.refunded = refunded;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((booking == null) ? 0 : booking.hashCode());
		result = prime * result + (refunded ? 1231 : 1237);
		result = prime * result + ((stripeId == null) ? 0 : stripeId.hashCode());
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
		BookingPayment other = (BookingPayment) obj;
		if (booking == null) {
			if (other.booking != null)
				return false;
		} else if (!booking.equals(other.booking))
			return false;
		if (refunded != other.refunded)
			return false;
		if (stripeId == null) {
			if (other.stripeId != null)
				return false;
		} else if (!stripeId.equals(other.stripeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookingPayment [" + (booking != null ? "booking=" + booking + ", " : "")
				+ (stripeId != null ? "stripeId=" + stripeId + ", " : "") + "refunded=" + refunded + "]";
	}

}
