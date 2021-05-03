/**
 * 
 */
package com.ss.utopia.de;

import java.io.Serializable;

/**
 * @author Parker W.
 *
 */
public class Airplane implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3301237395759491587L;
	private Integer id;
	private AirplaneType type = new AirplaneType();

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
	 * @return the type
	 */
	public AirplaneType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(AirplaneType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Airplane other = (Airplane) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Airplane [" + (id != null ? "id=" + id + ", " : "") + (type != null ? "type=" + type : "") + "]";
	}
	
	

}
