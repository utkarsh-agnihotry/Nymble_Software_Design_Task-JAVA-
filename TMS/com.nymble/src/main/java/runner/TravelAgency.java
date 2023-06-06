package runner;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

import javax.xml.crypto.Data;

import model.Activity;
import model.Passenger;
import model.TravelPackage;
import utill.DataRetrieval;
/**
 * The TravelAgency class represents a travel agency application that allows passengers to book travel packages.
 *  @author  utkarsh agnihotry
 *  @version 1.0
 */
public class TravelAgency {
	private static final String DB_URL = "jdbc:mysql://localhost/nymbletms";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";
	
	/**
     * Signs up a passenger based on the given name and phone number.
     *
     * @param connection The database connection
     * @param name       The name of the passenger
     * @param pNumber    The phone number of the passenger
     * @return The passenger object if found, null otherwise
     * @throws SQLException if an error occurs while accessing the database
     */
	public static Passenger passsengerSignUp(Connection connection, String name, int pNumber) throws SQLException {
		Passenger passenger = DataRetrieval.retrievePassengersFromDatabase(connection, name, pNumber);
		if (Objects.isNull(passenger)) {
			System.out.println("Not registered customer please signup");
			System.exit(1);
		}
		String welcmMsq = "Hi! " + passenger.getPassengerName() + " Welcome to Nymble's Make your trip";
		System.out.println(welcmMsq);
		double balance = passenger.getWalletBalance();
		String ptype = passenger.getPassengerType();
		System.out
				.println("your wallet ballance as of now is " + balance + " and you are our's " + ptype + " passenger");
		return passenger;
	}
	
	/**
     * Allows the user to select a travel package from the available options.
     * @param selectedPackageName The selected package name
     * @return The selected travel package object
     * @throws SQLException if an error occurs while accessing the database
     */

	public static TravelPackage packageOption(Connection connection, String selectedPackageName) throws SQLException {
		Scanner sc = new Scanner(System.in);

		ArrayList<TravelPackage> packageList = DataRetrieval.retrieveTravelPackageFromDB(connection);
		System.out.println("List of available Packages are");

		for (TravelPackage i : packageList) {
			System.out.println(i.getId() + "st package name: " + i.getPackageName());
			System.out.println("Capacity left for " + i.getId() + " Package is " + i.getPassengerCapacity());
		}
		System.out.println("Please select a package name");

		System.out.println("----------------------------");
		selectedPackageName = sc.nextLine();
		TravelPackage selectPackage = null;
		for (TravelPackage i : packageList) {
			if (i.getPackageName().equalsIgnoreCase(selectedPackageName)) {
				selectPackage = i;

			}

		}
		return selectPackage;
	}
	/**
     * Allows the user to choose a package and checks if it is available and affordable.
     * @param selectedActivity     The selected activity
     * @param selectedDestination  The selected destination
     * @param numberOfAdults       The number of adults
     * @return A map containing the cost and capacity information
     */
	public static HashMap<String, Object> choosePackage(Connection connection, TravelPackage selectPackage,
			Passenger passenger, String selectedActivity, String selectedDestination, int numberOfAdults) {
		HashMap<String, Object> costCpacityMap = DataRetrieval.retrieveCostAndCapacity(selectPackage, selectedActivity,
				selectedDestination);

		double finalCost = Booking.packageCost(passenger.getPassengerType(), numberOfAdults,
				(double) costCpacityMap.get("cost"));
		if ((int) costCpacityMap.get("capacity") < numberOfAdults) {
			System.out.println("we have only " + costCpacityMap.get("capacity")
					+ " these many seats available please choose accordingly");
			System.exit(1);
		} else if ((double) costCpacityMap.get("cost") > passenger.getWalletBalance()) {
			System.out.println("insufficient wallet balance");
			System.exit(1);

		} else {
			System.out.println("For the chosen itenary total cost would be =" + finalCost);
			System.out.println("Do you want to continue with this package ");
		}
		return costCpacityMap;
	}
	

	public static void bookTravelPackage(Connection connection, TravelPackage selectPackage, Passenger passenger,
			HashMap<String, Object> costCapacityMap, String selectedActivity, int numberOfAdults) {
		int updatedCapacity = (int) costCapacityMap.get("capacity") - numberOfAdults;
		double updatedBalance = passenger.getWalletBalance() - (double) costCapacityMap.get("cost");

		try {
			// Update the capacity of the activity
			DataRetrieval.updateCapacity(connection, selectedActivity, updatedCapacity);

			// Update the passenger details
			DataRetrieval.updatePassenger(connection, selectPackage, passenger, updatedBalance);

			System.out.println("Thanks for booking! Your itinerary looks like this:");
			System.out.println();
			System.out.println("Passenger Name: " + passenger.getPassengerName());
			System.out.println("Passenger Number: " + passenger.getPassengerNumber());
			System.out.println("Balance: " + passenger.getWalletBalance());
			System.out.println("Price Paid: " + (double) costCapacityMap.get("cost"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * The main method of the TravelAgency application.
     *
     * @param args The command-line arguments
     */
	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			System.out.println("Please enter your USER_CRED");
			Scanner sc = new Scanner(System.in);
			String name = sc.nextLine();
			int pNumber = sc.nextInt();
			sc.nextLine();
			Passenger passenger = passsengerSignUp(connection, name, pNumber);

			String selectedPackageName = null;
			TravelPackage selectPackage = packageOption(connection, selectedPackageName);

			PassengersDetails.printItinerary(selectPackage);

			System.out.println("Please select the destination ,activty and number of Adults");
			String selectedDestination = sc.nextLine();
			String selectedActivity = sc.nextLine();
			int numberOfAdults = sc.nextInt();
			HashMap<String, Object> costCapacityMap = choosePackage(connection, selectPackage, passenger,
					selectedActivity, selectedDestination, numberOfAdults);
			sc.nextLine();

			String response = sc.nextLine();

			if (response.equalsIgnoreCase("yes")) {
				bookTravelPackage(connection, selectPackage, passenger, costCapacityMap, selectedActivity,
						numberOfAdults);
			} else {
				System.out.println("thanks for your time");
				System.exit(1);
			}
			sc.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
