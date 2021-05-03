/**
 * 
 */
package com.ss.utopia.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.dao.AirplaneTypeDAO;
import com.ss.utopia.dao.AirportDAO;
import com.ss.utopia.dao.BookingAgentDAO;
import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.dao.BookingGuestDAO;
import com.ss.utopia.dao.BookingPaymentDAO;
import com.ss.utopia.dao.BookingUserDAO;
import com.ss.utopia.dao.FlightBookingDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.FlightSeatDAO;
import com.ss.utopia.dao.PassengerDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.dao.UserDAO;
import com.ss.utopia.dao.UserRoleDAO;
import com.ss.utopia.de.Airplane;
import com.ss.utopia.de.AirplaneType;
import com.ss.utopia.de.Airport;
import com.ss.utopia.de.Booking;
import com.ss.utopia.de.BookingAgent;
import com.ss.utopia.de.BookingGuest;
import com.ss.utopia.de.BookingPayment;
import com.ss.utopia.de.BookingUser;
import com.ss.utopia.de.Flight;
import com.ss.utopia.de.FlightBooking;
import com.ss.utopia.de.FlightSeat;
import com.ss.utopia.de.Passenger;
import com.ss.utopia.de.Route;
import com.ss.utopia.de.User;
import com.ss.utopia.de.UserRole;

/**
 * @author Parker W.
 *
 *         Administrator class, has permission to do the following:
 *         Add/Update/Delete/Read flights Add/Update/Delete/Read seats
 *         Add/Update/Delete/Read Tickets and Passengers Add/Update/Delete/Read
 *         Airports Add/Update/Delete/Read Travelers Add/Update/Delete/Read
 *         Employees Override trip cancellation for a ticket
 */
@RestController
public class EmployeeService {

	@Autowired
	AirportDAO airportDAO;

	@Autowired
	AirplaneDAO airplaneDAO;

	@Autowired
	AirplaneTypeDAO airplaneTypeDAO;

	@Autowired
	BookingDAO bookingDAO;

	@Autowired
	BookingAgentDAO bookingagentDAO;
	
	@Autowired
	BookingUserDAO bookinguserDAO;
	
	@Autowired
	BookingGuestDAO bookingguestDAO;
	
	@Autowired
	BookingPaymentDAO bookingPaymentDAO;
	
	@Autowired
	FlightDAO flightDAO;
	
	@Autowired
	FlightBookingDAO flightBookingDAO;
	
	@Autowired
	FlightSeatDAO flightSeatDAO;
	
	@Autowired
	PassengerDAO passengerDAO;
	
	@Autowired
	RouteDAO routeDAO;
	
	@Autowired
	UserRoleDAO userRoleDAO;
	
	@Autowired
	UserDAO userDAO;

	// --------------------Read/Get Mappings---------------------//
	/*
	 * This section primarily covers the reading of data as none is sent to the
	 * database.
	 */


	public List<Airplane> getAirplanes() {
		try {
			return airplaneDAO.getAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
	public List<AirplaneType> getAirplaneTypes() {
		try {
			return airplaneTypeDAO.getAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	public List<Airport> getAirports() {
		try {
			return airportDAO.getAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public List<Route> getRoutes() {
		try {
			return routeDAO.getAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public List<UserRole> getUserRoles() {
		try {
			return userRoleDAO.getAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public List<User> getUsers() {
		try {
			return userDAO.getAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public List<BookingAgent> getAgents() {
		try {
			return bookingagentDAO.getAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public List<BookingUser> getBUsers() {
		try {
			return bookinguserDAO.getAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public List<BookingGuest> getGuests() {
		try {
			return bookingguestDAO.getAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Flight> getFlights() {
		try {
			return flightDAO.getAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Booking> getBookings() {
		try {
			return bookingDAO.getAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Passenger> getPassengers() {
		try {
			return passengerDAO.getAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<BookingPayment> getBookingPayments() {
		try {
			return bookingPaymentDAO.getAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<FlightBooking> getFlightBookings() {
		try {
			return flightBookingDAO.getAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<FlightSeat> getSeats() {
		try {
			return flightSeatDAO.getAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<FlightSeat> getSeatsByFlight(Integer flightId) {
		try {
			return flightSeatDAO.getByFlight(flightId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	// ----------------Post Mappings-----------------------//
	/*
	 * This section will be important for update, delete, create. For now will
	 * contain the base code for getting a specific entry based on the parameters.
	 * This can be extended to delete or update the returned one.
	 */

	// =============Create==================//

	public String createAirplane(Airplane plane) {
		try {
			Object key = airplaneDAO.create(plane);
			
			return key == null ? "Failed to create airplane." : "Airplane created with ID" + key;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed to create airplane.";
	}


	public String createAirplaneType(AirplaneType planeType) {
		try {
			Object key = airplaneTypeDAO.create(planeType);
			return key == null ? "Failed to create airplane type." : "Airplane Type created with ID" + key;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed to create airplane type.";
	}


	public String createAirport(Airport port) {
		try {
			// this create method always return null
			airportDAO.create(port);
			return "Airport added successfully.";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed to create airport.";
	}


	public String createRoute(Route route) {
		try {
//			if (route.getOrigin().getCode().equals(route.getDestination().getCode())) {
//				return "Cannot create route with same origin and destination";
//			}
			Object key = routeDAO.create(route);
			return key == null ? "Failed to create route." : "Route created with ID" + key;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed to create route.";
	}
	
	
	public String addUserRole(UserRole role) {
		try {
			return "User Role created with ID" + userRoleDAO.create(role);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "User role could not be added";

	}
	

	public String addUser(User user) {
		try {
			return "User created with ID" + userDAO.create(user);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "User could not be added";

	}
	

	public String addBookingUser(BookingUser buser) {
		try {
			return "Booking User created with ID" + bookinguserDAO.create(buser);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Booking User could not be added";

	}
	
	
	public String addBookingAgent(BookingAgent agent) {
		try {
			return "Booking agent created with ID" + bookingagentDAO.create(agent);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Booking agent could not be added";

	}
	
	
	public String addBookingGuest(BookingGuest guest) {
		try {
			return "Guest created with ID" + bookingguestDAO.create(guest);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Guest could not be added";

	}
	
	public String addFlight(Flight flight) {
		try {			
			return "Flight created with ID" + flightDAO.create(flight);
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Flight could not be added";

	}
	
	public String addBooking(Booking booking) {
		try {			
			return "Booking created with ID" + bookingDAO.create(booking);
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Booking could not be added";
	}
	
	public String addPassenger(Passenger passenger) {
		try {			
			return "Passenger created with ID" + passengerDAO.create(passenger);
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Passenger could not be added";
	}
	
	public String addBookingPayment(BookingPayment payment) {
		try {			
			return "Booking Payment created with ID" + bookingPaymentDAO.create(payment);
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Booking Payment could not be added";
	}
	
	public String addFlightBooking(FlightBooking flightBooking) {
		try {
			flightBookingDAO.create(flightBooking);
			return "Flight Booking created";
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Flight Booking could not be added";
	}
	
	public FlightSeat addSeats(FlightSeat seat) {
		try {
			Integer id = flightSeatDAO.create(seat);
			return flightSeatDAO.get(id);
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return null;
	}

	// =============Single Reads===================//
	// good for confirmation/selection

	public Airplane getSingleAirplane(Integer id) {
		try {
			return airplaneDAO.get(id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public AirplaneType getSingleAirplaneType(Integer id) {
		try {
			return airplaneTypeDAO.get(id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public Airport getSingleAirport(String code) {
		try {
			return airportDAO.get(code);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public Route getSingleRoute(Integer id) {
		try {
			return routeDAO.get(id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Flight getSingleFlight(Integer id) {
		try {
			return flightDAO.get(id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Booking getSingleBooking(Integer id) {
		try {
			return bookingDAO.get(id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Passenger getSinglePassenger(Integer id) {
		try {
			return passengerDAO.get(id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public BookingPayment getSingleBookingPayment(Integer id) {
		try {
			return bookingPaymentDAO.get(id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public FlightBooking getSingleFlightBooking(Integer id) {
		try {
			return flightBookingDAO.get(id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

	// =============Update==================//

	public String updateAirplane(Airplane plane) {
		try {
			airplaneDAO.update(plane);
			return "Edited plane.";
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed to edit airplane.";
	}


	public String updateAirplaneType(AirplaneType planeType) {
		try {
			airplaneTypeDAO.update(planeType);
			return "Edited airplane type.";
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed to edit airplane type.";
	}


	public String updateAirport(Airport port) {
		try {
			airportDAO.update(port);
			return "Edited airport.";
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed to update airport.";

	}

	
	public String updateRoute(Route route) {
		try {
			routeDAO.update(route);
			return "Edited route.";
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed to edit route.";
	}
	

	public String updateUserRole(UserRole role) {
		try {
			userRoleDAO.update(role);
			return "Successfully updated";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "User role could not be updated";

	}
	
	
	public String updateUser(User user) {
		try {
			userDAO.update(user);
			return "Successfully updated";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "User could not be updated";

	}
	

	public String updateBookingUser(BookingUser buser) {
		try {
			bookinguserDAO.update(buser);
			return "Successfully updated";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Booking User could not be updated";

	}
	

	public String updateBookingAgent(BookingAgent agent) {
		try {
			bookingagentDAO.update(agent);
			return "Successfully updated";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Booking agent could not be added";

	}

	public String updateBookingGuest(BookingGuest guest) {
		try {
			bookingguestDAO.update(guest);
			return "Successfully updated";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Guest could not be updated";

	}
	
	public String updateFlight(Flight flight) {
		try {
			flightDAO.update(flight);
			return "Successfully updated";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Flight could not be updated";
	}
	
	public String updateBooking(Booking booking) {
		try {
			bookingDAO.update(booking);
			return "Successfully updated";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Booking could not be updated";
	}
	
	public String updatePassenger(Passenger passenger) {
		try {
			passengerDAO.update(passenger);
			return "Successfully updated";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Passenger could not be updated";
	}
	
	public String updateBookingPayment(BookingPayment payment) {
		try {
			bookingPaymentDAO.update(payment);
			return "Successfully updated";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Booking payment could not be updated";
	}

	// =============Delete==================//

	public String deleteAirplane(Airplane plane) {
		try {
			airplaneDAO.delete(plane);
			return "Deleted plane.";
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed to delete airplane.";
	}

	
	public String deleteAirplaneType(AirplaneType planeType) {
		try {
			airplaneTypeDAO.delete(planeType);
			return "Deleted airplane type.";
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed to delete Airplane Type.";
	}


	public String deleteAirport(Airport port) {
		try {
			airportDAO.delete(port);
			return "Deleted airport.";
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed to delete airport.";
	}


	public String deleteRoute(Route route) {
		try {
			routeDAO.delete(route);
			return "Deleted route.";
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed to delete route.";
	}
	
	
	public String deleteUserRole(UserRole role) {
		try {
			userRoleDAO.delete(role);
			return "Successfully deleted";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "User role could not be deleted";

	}
	

	public String deleteUser(User user) {
		try {
			userDAO.delete(user);
			return "Successfully deleted";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "User could not be deleted";

	}
	
	
	public String deleteBookingUser(BookingUser buser) {
		try {
			bookinguserDAO.delete(buser);
			return "Successfully deleted";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Booking User could not be deleted";

	}
	

	public String deleteBookingAgent(BookingAgent agent) {
		try {
			bookingagentDAO.delete(agent);
			return "Successfully deleted";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Booking agent could not be deleted";

	}
	
	
	public String deleteBookingGuest(BookingGuest guest) {
		try {
			bookingguestDAO.delete(guest);
			return "Successfully deleted";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Guest could not be deleted";

	}
	
	public String deleteFlight(Flight flight) {
		try {
			flightDAO.delete(flight);
			return "Successfully deleted";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Flight could not be deleted";
	}
	
	public String deleteBooking(Booking booking) {
		try {
			bookingDAO.delete(booking);
			return "Successfully deleted";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Booking could not be deleted";
	}
	
	public String deletePassenger(Passenger passenger) {
		try {
			passengerDAO.delete(passenger);
			return "Successfully deleted";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "Passenger could not be deleted";
	}
	
	public String deleteBookingPayment(BookingPayment BookingPayment) {
		try {
			bookingPaymentDAO.delete(BookingPayment);
			return "Successfully deleted";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return "BookingPayment could not be deleted";
	}

	// -----------------------------Experimental
	// Section------------------------------------//
	// Does not work at the moment
	/**
	 * Test section for future reference Does not currently work as Jackson reads
	 * the data field in as a LinkedHashMap. Would need to adjust the getData method
	 * inside Message.java to parse the LinkedHashMap and return a DataEntity
	 */

//	@PostMapping("/manage")
//	public String manageData(@RequestBody Message message) {
//		Object data = message.getData();
//		Command command = message.getCommand();
//		DataEntityType det = message.getDataType();
//		if (det == DataEntityType.AIRPLANE) {
//			System.out.println((Airplane) data);
//			switch (command) {
//			case CREATE:
//				return "Updated Airplane." + airplaneDAO.create((Airplane) data);
//			case READ:
//				return airplaneDAO.get(((Airplane) data).getId()).toString();
//			case UPDATE:
//				airplaneDAO.update((Airplane) data);
//				return "Updated Airplane.";
//			case DELETE:
//				airplaneDAO.delete((Airplane) data);
//				return "Deleted Airplane.";
//			default:
//				return "Invalid Command operation.";
//			}
//		} else if (det == DataEntityType.AIRPLANETYPE) {
//			System.out.println((AirplaneType) data);
//			switch (command) {
//			case CREATE:
//				return "Updated Airplane Type." + airplaneTypeDAO.create((AirplaneType) data);
//			case READ:
//				return airplaneTypeDAO.get(((AirplaneType) data).getType()).toString();
//			case UPDATE:
//				airplaneTypeDAO.update((AirplaneType) data);
//				return "Updated AirplaneType.";
//			case DELETE:
//				airplaneTypeDAO.delete((AirplaneType) data);
//				return "Deleted AirplaneType.";
//			default:
//				return "Invalid Command operation.";
//			}
//
//		} else if (det == DataEntityType.AIRPORT) {
//			System.out.println((Airport) data);
//			switch (command) {
//			case CREATE:
//				return "Updated Airport." + airportDAO.create((Airport) data);
//			case READ:
//				return airportDAO.get(((Airport) data).getCode()).toString();
//			case UPDATE:
//				airportDAO.update((Airport) data);
//				return "Updated Airport.";
//			case DELETE:
//				airportDAO.delete((Airport) data);
//				return "Deleted Airport.";
//			default:
//				return "Invalid Command operation.";
//			}
//
//		} else if (det == DataEntityType.ROUTE) {
//			System.out.println((Route) data);
//			switch (command) {
//			case CREATE:
//				return "Created Route. " + routeDAO.create((Route) data);
//			case READ:
//				return routeDAO.get(((Route) data).getId()).toString();
//			case UPDATE:
//				routeDAO.update((Route) data);
//				return "Updated Route.";
//			case DELETE:
//				routeDAO.delete((Route) data);
//				return "Deleted Route.";
//			default:
//				return "Invalid Command operation.";
//			}
//
//		} else {
//			return "Invalid Object Data Type.";
//		}
//	}

}