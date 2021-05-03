/**
 * 
 */
package com.ss.utopia.de;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author Parker W.
 *
 */
public class Flight implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8903170524931954479L;
	private Integer id;
	private Route route = new Route();
	private Airplane plane = new Airplane();
	private Timestamp departureTime;
	private Timestamp arrivalTime;
//	private Integer reservedSeats;
//	private Float seatPrice;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the route
	 */
	public Route getRoute() {
		return route;
	}

	/**
	 * @param route the route to set
	 */
	public void setRoute(Route route) {
		this.route = route;
	}

	/**
	 * @return the depatureTime
	 */
	public Timestamp getDepartureTime() {
		return departureTime;
	}

	/**
	 * @param depatureTime the depatureTime to set
	 */
	public void setDepartureTime(Timestamp departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * @return the arrivalTime
	 */
	public Timestamp getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * @param arrivalTime the depatureTime to set
	 */
	public void setArrivalTime(Timestamp arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * @return the plane
	 */
	public Airplane getPlane() {
		return plane;
	}

	/**
	 * @param plane the plane to set
	 */
	public void setPlane(Airplane plane) {
		this.plane = plane;
	}

	/**
	 * @return the reservedSeats
	 */
//	public Integer getReservedSeats() {
//		return reservedSeats;
//	}
//
//	/**
//	 * @param reservedSeats the reservedSeats to set
//	 */
//	public void setReservedSeats(Integer reservedSeats) {
//		this.reservedSeats = reservedSeats;
//	}
//
//	/**
//	 * @return the seatPrice
//	 */
//	public Float getSeatPrice() {
//		return seatPrice;
//	}
//
//	/**
//	 * @param seatPrice the seatPrice to set
//	 */
//	public void setSeatPrice(Float seatPrice) {
//		this.seatPrice = seatPrice;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalTime == null) ? 0 : arrivalTime.hashCode());
		result = prime * result + ((departureTime == null) ? 0 : departureTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((plane == null) ? 0 : plane.hashCode());
//		result = prime * result + ((reservedSeats == null) ? 0 : reservedSeats.hashCode());
		result = prime * result + ((route == null) ? 0 : route.hashCode());
//		result = prime * result + ((seatPrice == null) ? 0 : seatPrice.hashCode());
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
		Flight other = (Flight) obj;
		if (arrivalTime == null) {
			if (other.arrivalTime != null)
				return false;
		} else if (!arrivalTime.equals(other.arrivalTime))
			return false;
		if (departureTime == null) {
			if (other.departureTime != null)
				return false;
		} else if (!departureTime.equals(other.departureTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (plane == null) {
			if (other.plane != null)
				return false;
		} else if (!plane.equals(other.plane))
			return false;
//		if (reservedSeats == null) {
//			if (other.reservedSeats != null)
//				return false;
//		} else if (!reservedSeats.equals(other.reservedSeats))
//			return false;
		if (route == null) {
			if (other.route != null)
				return false;
		} else if (!route.equals(other.route))
			return false;
//		if (seatPrice == null) {
//			if (other.seatPrice != null)
//				return false;
//		} else if (!seatPrice.equals(other.seatPrice))
//			return false;
		return true;
	}

}
