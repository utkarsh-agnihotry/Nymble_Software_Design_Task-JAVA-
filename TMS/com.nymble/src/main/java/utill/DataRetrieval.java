package utill;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import model.Activity;
import model.Destination;
import model.Passenger;
import model.TravelPackage;

/**
 * This class provides utility methods for retrieving data from the database.
 * 
 * @author utkarsh agnihotry
 * @version 1.0
 */
public class DataRetrieval {

	/**
	 * Retrieves a passenger from the database based on the name and number.
	 *
	 * @param connection The database connection
	 * @param pName      The passenger name
	 * @param pNumber    The passenger number
	 * @return The retrieved Passenger object, or null if not found
	 * @throws SQLException If an SQL error occurs
	 */

	public static Passenger retrievePassengersFromDatabase(Connection connection, String pName, int pNumber)
			throws SQLException {
		String sql = "SELECT * FROM passengers WHERE passenger_name = ? and passenger_number= ?";
		Passenger passenger = null;
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, pName);
			statement.setInt(2, pNumber);

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					passenger = new Passenger(resultSet.getString("passenger_name"),
							resultSet.getInt("passenger_number"));
					passenger.setPassengerType(resultSet.getString("passenger_type"));
					passenger.setWalletBalance(resultSet.getDouble("balance"));
					passenger.setTravel_package_id(resultSet.getInt("travel_package_id"));
					passenger.setId(resultSet.getInt("id"));

				}
			}
		}
		return passenger;
	}

	/**
	 * Retrieves a list of travel packages from the database.
	 *
	 * @param connection The database connection
	 * @return The list of retrieved TravelPackage objects
	 * @throws SQLException If an SQL error occurs
	 */
	public static ArrayList<TravelPackage> retrieveTravelPackageFromDB(Connection connection) throws SQLException {
		String sql = "select * from travel_packages";
		ArrayList<TravelPackage> packageList = new ArrayList<TravelPackage>();

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			try (ResultSet resultSet = stmt.executeQuery()) {
				while (resultSet.next()) {
					TravelPackage travelPackage = new TravelPackage();
					travelPackage.setId(resultSet.getInt("id"));
					travelPackage.setPackageName(resultSet.getString("packageName"));
					travelPackage.setPassengerCapacity(resultSet.getInt("passenger_capacity"));

					retreiveDestinationFromDb(connection, travelPackage);
					packageList.add(travelPackage);

				}
			}
		}
		return packageList;
	}

	/**
	 * Retrieves destinations for a given travel package from the database.
	 *
	 * @param connection    The database connection
	 * @param travelPackage The travel package object
	 * @throws SQLException If an SQL error occurs
	 */

	public static void retreiveDestinationFromDb(Connection connection, TravelPackage travelPackage)
			throws SQLException {
		String sql = "select * from destinations where travel_package_id=?";
		Destination destination;
		ArrayList<Destination> destinationList = new ArrayList<Destination>();
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, travelPackage.getId());
			try (ResultSet resultSet = stmt.executeQuery()) {

				while (resultSet.next()) {
					destination = new Destination();
					destination.setDestinationName(resultSet.getString("destination_name"));
					destination.setId(resultSet.getInt("id"));
					retrieveActivitiesFromDatabase(connection, destination);
					destinationList.add(destination);
					travelPackage.setItinerary(destinationList);

				}
			}
		}

	}

	/**
	 * Retrieves activities for a given destination from the database.
	 *
	 * @param connection  The database connection
	 * @param destination The destination object
	 * @return The list of retrieved Activity objects
	 * @throws SQLException If an SQL error occurs
	 */

	public static ArrayList<Activity> retrieveActivitiesFromDatabase(Connection connection, Destination destination)
			throws SQLException {
		String sql = "select * from activities where destination_id=?";
		Activity activity;
		ArrayList<Activity> activityList = new ArrayList<Activity>();
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, destination.getId());
			try (ResultSet resultSet = stmt.executeQuery()) {
				while (resultSet.next()) {
					activity = new Activity();
					activity.setActivityName(resultSet.getString("activity_name"));
					activity.setDescription(resultSet.getString("description"));
					activity.setCost(resultSet.getDouble("cost"));
					activity.setCapacity(resultSet.getInt("capacity"));
					activityList.add(activity);
					destination.setActivitiesList(activityList);

				}
			}

		}
		return activityList;
	}

	/**
	 * Retrieves the cost and capacity for a given activity in a destination of a
	 * travel package.
	 *
	 * @param travelPackageDTO The travel package object
	 * @param activtyName      The activity name
	 * @param destinationName  The destination name
	 * @return A HashMap containing the cost and capacity
	 */

	public static HashMap<String, Object> retrieveCostAndCapacity(TravelPackage travelPackageDTO, String activtyName,
			String destinationName) {
		HashMap<String, Object> costCapacityMap = null;
		for (Destination destination : travelPackageDTO.getItinerary()) {
			if (destination.getDestinationName().equalsIgnoreCase(destinationName)) {
				for (Activity activity : destination.getActivitiesList()) {
					if (activity.getActivityName().equalsIgnoreCase(activtyName)) {
						costCapacityMap = new HashMap<String, Object>();
						costCapacityMap.put("cost", activity.getCost());
						costCapacityMap.put("capacity", activity.getCapacity());
					}
				}

			}
		}
		return costCapacityMap;
	}

	/**
	 * Updates the capacity of an activity in the database.
	 *
	 * @param connection      The database connection
	 * @param activtyName     The activity name
	 * @param updatedCapacity The updated capacity value
	 * @throws SQLException If an SQL error occurs
	 */
	public static void updateCapacity(Connection connection, String activtyName, int updatedCapacity)
			throws SQLException {
		String sql = "update activities set capacity=? where activity_name=?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, updatedCapacity);
			stmt.setString(2, activtyName);
			stmt.executeUpdate();
		}
	}

	/**
	 * Updates the passenger details in the database.
	 *
	 * @param connection       The database connection
	 * @param travelPackageDTO The travel package object
	 * @param passenger        The passenger object
	 * @param updatedBalance   The updated balance value
	 * @throws SQLException If an SQL error occurs
	 */
	public static void updatePassenger(Connection connection, TravelPackage travelPackageDTO, Passenger passenger,
			double updatedBalane) throws SQLException {
		String sql = "update passengers set travel_package_id=?,balance=? where passenger_name=? and passenger_number=?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, travelPackageDTO.getId());
			stmt.setDouble(2, updatedBalane);
			stmt.setString(3, passenger.getPassengerName());
			stmt.setInt(4, passenger.getPassengerNumber());
			stmt.executeUpdate();
		}
	}

}
