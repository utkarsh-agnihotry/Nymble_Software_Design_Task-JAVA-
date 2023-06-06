package runner;

import model.Activity;
import model.Destination;
import model.TravelPackage;
import model.Passenger;

/**
 * This class provides methods for printing passenger details and travel package
 * information.
 * 
 * @author utkarsh agnihotry
 * @version 1.0
 */
public class PassengersDetails {
	/**
	 * Prints the itinerary of a travel package.
	 *
	 * @param travelPackageDTO The travel package object
	 */
	public static void printItinerary(TravelPackage travelPackageDTO) {
		System.out.println("Travel Package: " + travelPackageDTO.getPackageName());
		for (Destination destination : travelPackageDTO.getItinerary()) {
			System.out.println("Destination: " + destination.getDestinationName());
			for (Activity activity : destination.getActivitiesList()) {
				System.out.println("Activity: " + activity.getActivityName());
				System.out.println("Description: " + activity.getDescription());
				System.out.println("Cost: " + activity.getCost());
				if (activity.getCapacity() == 0) {
					System.out.println("Capacity: Sorry All booked");
				} else
					System.out.println("Capacity: " + activity.getCapacity());
			}
			System.out.println("--------------------------------");
		}
	}

	/**
	 * Prints the list of passengers enrolled in a travel package.
	 *
	 * @param travelPackageDTO The travel package object
	 */
	public static void printPassengerList(TravelPackage travelPackageDTO) {
		System.out.println("Travel Package: " + travelPackageDTO.getPackageName());
		System.out.println("Passenger Capacity: " + travelPackageDTO.getPassengerCapacity());
		System.out.println("Number of Passengers Enrolled: " + travelPackageDTO.getpList().size());
		for (Passenger passenger : travelPackageDTO.getpList()) {
			System.out.println("Passenger Name: " + passenger.getPassengerName());
			System.out.println("Passenger Number: " + passenger.getPassengerNumber());
		}
	}

	/**
	 * Prints the details of a passenger, including the activities they signed up
	 * for.
	 *
	 * @param passenger        The passenger object
	 * @param travelPackageDTO The travel package object
	 * @param pricePaid        The price paid by the passenger
	 */
	public static void printPassengerDetails(Passenger passenger, TravelPackage travelPackageDTO, double pricePaid) {
		System.out.println("Passenger Name: " + passenger.getPassengerName());
		System.out.println("Passenger Number: " + passenger.getPassengerNumber());
		System.out.println("Balance: " + passenger.getWalletBalance());

		System.out.println("Activities Signed Up:");
		for (Destination destination : travelPackageDTO.getItinerary()) {
			for (Activity activity : destination.getActivitiesList()) {
				if (activity.getCapacity() > 0) {
					System.out.println("Activity: " + activity.getActivityName());
					System.out.println("Destination: " + destination.getDestinationName());
					System.out.println("Price Paid: " + pricePaid);
				}
			}
		}
	}

	/**
	 * Prints the available activities in a travel package.
	 *
	 * @param travelPackageDTO The travel package object
	 */
	public static void printAvailableActivities(TravelPackage travelPackageDTO) {
		System.out.println("Available Activities:");
		for (Destination destination : travelPackageDTO.getItinerary()) {
			for (Activity activity : destination.getActivitiesList()) {
				if (activity.getCapacity() > 0) {
					System.out.println("Activity: " + activity.getActivityName());
					System.out.println("Destination: " + destination.getDestinationName());
					System.out.println("Spaces Available: " + activity.getCapacity());
				}
			}
		}
	}
}
