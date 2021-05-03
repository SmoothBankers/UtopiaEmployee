/**
 * 
 */
package com.ss.utopia.de;

import java.io.Serializable;

/**
 * @author Parker W.
 *
 */
public class Booking implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 835974543182942137L;
	private Integer id;
	private boolean isActive;
	private String confirmationCode;

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
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the confirmationCode
	 */
	public String getConfirmationCode() {
		return confirmationCode;
	}

	/**
	 * @param confirmationCode the confirmationCode to set
	 */
	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((confirmationCode == null) ? 0 : confirmationCode.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isActive ? 1231 : 1237);
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
		Booking other = (Booking) obj;
		if (confirmationCode == null) {
			if (other.confirmationCode != null)
				return false;
		} else if (!confirmationCode.equals(other.confirmationCode))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isActive != other.isActive)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Booking [" + (id != null ? "id=" + id + ", " : "") + "isActive=" + isActive + ", "
				+ (confirmationCode != null ? "confirmationCode=" + confirmationCode : "") + "]";
	}

}
