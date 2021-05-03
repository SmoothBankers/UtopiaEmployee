package com.ss.utopia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.utopia.de.Flight;
import com.ss.utopia.de.FlightSeat;
import com.ss.utopia.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	// ============ Flights ============ //
	
		@GetMapping("/flights")
		public List<Flight> getFlights() {
			return employeeService.getFlights();
		}
		
		@GetMapping("/flights/{id}") 
		public Flight getSingleFlight(@PathVariable Integer id) {
			return employeeService.getSingleFlight(id);
		}
		
		@PostMapping("/flights")
		public String addFlight(@RequestBody Flight flight) {
			return employeeService.addFlight(flight);
		}
		
		@PutMapping("/flights/{id}")
		public String updateFlight(@PathVariable Integer id, @RequestBody Flight flight) {
			flight.setId(id);
			return employeeService.updateFlight(flight);
		}
		
		@DeleteMapping("/flights/{id}")
		public String deleteFlight(@PathVariable Integer id) {
			Flight flight = new Flight();
			flight.setId(id);
			return employeeService.deleteFlight(flight);
		}
		
		// ============ Flight Seats ============ //
		
		@GetMapping("/seats")
		public List<FlightSeat> getSeats() {
			return employeeService.getSeats();
		}
		
		@GetMapping("/flights/{id}/seats")
		public List<FlightSeat> getSeatsByFlight(@PathVariable Integer id) {
			return employeeService.getSeatsByFlight(id);
		}
		
		@PostMapping("/flights/{id}/addseat")
		public FlightSeat addSeat(@PathVariable Integer id, @RequestBody FlightSeat seat) {
			seat.getFlight().setId(id);
			return employeeService.addSeats(seat);
		}
	
}
