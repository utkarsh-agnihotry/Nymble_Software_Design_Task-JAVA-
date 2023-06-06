package runner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.Activity;
import model.Passenger;

class BookingTest {

	@Test
	void testPackageCost_GoldPassenger() {
		// Arrange
		String passengerType = "gold";
		int numberOfAdult = 2;
		double cost = 100.0;
		
		// Act
		double result = Booking.packageCost(passengerType, numberOfAdult, cost);
		
		// Assert
		assertEquals(180.0, result, 0.01);
	}
	
	@Test
	void testPackageCost_StandardPassenger() {
		// Arrange
		String passengerType = "standard";
		int numberOfAdult = 3;
		double cost = 100.0;
		
		// Act
		double result = Booking.packageCost(passengerType, numberOfAdult, cost);
		
		// Assert
		assertEquals(300.0, result, 0.01);
	}
	
	@Test
	void testPackageCost_InvalidPassengerType() {
		// Arrange
		String passengerType = "premium";
		int numberOfAdult = 4;
		double cost = 100.0;
		
		// Act
		double result = Booking.packageCost(passengerType, numberOfAdult, cost);
		
		// Assert
		assertEquals(0.0, result, 0.01);
	}

	@Test
	void testCapacityCheck_EnoughCapacityAndBalance() {
		// Arrange
		Activity activity = new Activity();
		activity.setCapacity(5);
		activity.setCost(50.0);
		
		Passenger passenger = new Passenger("John",12345);
		passenger.setWalletBalance(100.0);
		
		Booking booking = new Booking();
		
		// Act
		boolean result = booking.capacityCheck(activity, passenger);
		
		// Assert
		assertTrue(result);
	}
	
	@Test
	void testCapacityCheck_InsufficientCapacity() {
		// Arrange
		Activity activity = new Activity();
		activity.setCapacity(0);
		activity.setCost(50.0);
		
		Passenger passenger = new Passenger("John",12345);
		passenger.setWalletBalance(100.0);
		
		Booking booking = new Booking();
		
		// Act
		boolean result = booking.capacityCheck(activity, passenger);
		
		// Assert
		assertFalse(result);
	}
	
	@Test
	void testCapacityCheck_InsufficientBalance() {
		// Arrange
		Activity activity = new Activity();
		activity.setCapacity(5);
		activity.setCost(200.0);
		
		Passenger passenger = new Passenger("John",12345);
		passenger.setWalletBalance(100.0);
		
		Booking booking = new Booking();
		
		// Act
		boolean result = booking.capacityCheck(activity, passenger);
		
		// Assert
		assertFalse(result);
	}

}
