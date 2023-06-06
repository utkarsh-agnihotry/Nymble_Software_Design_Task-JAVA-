package runner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


import model.Passenger;
import model.TravelPackage;
import runner.Booking;
import runner.TravelAgency;
import utill.DataRetrieval;
class TravelAgencyTest {


	@Mock
	private Connection connectionMock;

	@BeforeEach
	void setUp() throws SQLException {
		when(connectionMock.createStatement()).thenReturn(null);
	}

	@Test
	void testPassengerSignUp_PassengerFound() throws SQLException {
		// Arrange
		String name = "John";
		int pNumber = 12345;
		Passenger passenger = new Passenger("John",12345);
		passenger.setPassengerName("John");
		passenger.setWalletBalance(100.0);
		passenger.setPassengerType("Standard");

		when(DataRetrieval.retrievePassengersFromDatabase(connectionMock, name, pNumber)).thenReturn(passenger);

		// Act
		Passenger result = TravelAgency.passsengerSignUp(connectionMock, name, pNumber);

		// Assert
		assertEquals(passenger, result);
	}


	@Test
	void testPackageOption() throws SQLException {
		// Arrange
		String selectedPackageName = "Package A";
		TravelPackage travelPackage = new TravelPackage();
		ArrayList<TravelPackage> travelPackageList = new ArrayList<TravelPackage>();
		travelPackage.setId(1);
		travelPackage.setPackageName("Package A");
		travelPackage.setPassengerCapacity(10);
		travelPackageList.add(travelPackage);
		when(DataRetrieval.retrieveTravelPackageFromDB(connectionMock)).thenReturn(travelPackageList);

		// Act
		TravelPackage result = TravelAgency.packageOption(connectionMock, selectedPackageName);

		// Assert
		assertEquals(travelPackage, result);
	}

	

	

	@Test
	void testChoosePackage_ValidSelection() {
		// Arrange
		TravelPackage selectPackage = new TravelPackage();
		selectPackage.setPassengerCapacity(10);

		Passenger passenger = new Passenger("John",12345);
		passenger.setWalletBalance(150.0);

		HashMap<String, Object> costCapacityMap = new HashMap<>();
		costCapacityMap.put("capacity", 8);
		costCapacityMap.put("cost", 100.0);

		// Act
		HashMap<String, Object> result = TravelAgency.choosePackage(connectionMock, selectPackage, passenger, "Activity A", "Destination A", 2);

		// Assert
		assertEquals(costCapacityMap, result);
	}

	
}
