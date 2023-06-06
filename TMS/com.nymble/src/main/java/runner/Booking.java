package runner;

import java.util.ArrayList;

import model.Activity;
import model.Destination;
import model.GoldPassenger;
import model.Passenger;
import model.PremiumPassenger;
import model.StandardPassenger;
import model.TravelPackage;

/**
 * The Booking class provides methods for calculating package costs and checking
 * activity capacity.
 * 
 * @author utkarsh agnihotry
 * @version 1.0
 */
public class Booking {

	private final static double GOLDDICOUNT = 0.9;

	/**
	 * Calculates the cost of a travel package based on the passenger type and
	 * number of adults.
	 *
	 * @param passengerType The type of passenger (gold, standard, premium)
	 * @param numberOfAdult The number of adult passengers
	 * @param cost          The base cost of the travel package
	 * @return The calculated package cost
	 */
	public static double packageCost(String passengerType, int numberOfAdult, double cost) {
		double actualCost = cost;
		if (passengerType.equalsIgnoreCase("gold")) {
			return actualCost * GOLDDICOUNT * numberOfAdult;
		} else if (passengerType.equalsIgnoreCase("standard")) {
			return actualCost * numberOfAdult;
		} else {
			return 0.0;
		}
	}

	/**
	 * Checks the capacity of an activity and the wallet balance of a passenger to
	 * determine if they can book the activity.
	 *
	 * @param activityDTO  The activity object
	 * @param passengerDTO The passenger object
	 * @return True if the activity can be booked, false otherwise
	 */
	public boolean capacityCheck(Activity activityDTO, Passenger passengerDTO) {
		int capacity = activityDTO.getCapacity();
		double activityCost = activityDTO.getCost();
		if (capacity > 0 && passengerDTO.getWalletBalance() >= activityCost)
			return true;

		else
			return false;
	}

}
