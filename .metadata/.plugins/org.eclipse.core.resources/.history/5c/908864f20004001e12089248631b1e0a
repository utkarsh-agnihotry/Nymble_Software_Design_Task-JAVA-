package utill;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import model.Passenger;
import model.TravelPackage;
class DataRetrievalTest {
	@Test
	public void testRetrievePassengersFromDatabase() throws SQLException {
        // Mock the necessary objects
        Connection connection = mock(Connection.class);
        PreparedStatement statement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        // Mock the behavior of the objects
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("passenger_name")).thenReturn("John");
        when(resultSet.getInt("passenger_number")).thenReturn(12345);

        // Call the method under test
        Passenger passenger = DataRetrieval.retrievePassengersFromDatabase(connection, "John", 12345);

        // Assert the result
        Assertions.assertNotNull(passenger);
        Assertions.assertEquals("John", passenger.getPassengerName());
        Assertions.assertEquals(12345, passenger.getPassengerNumber());
    }

    @Test
    public void testRetrieveTravelPackageFromDB() throws SQLException {
        // Mock the necessary objects
        Connection connection = mock(Connection.class);
        PreparedStatement statement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        // Mock the behavior of the objects
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("packageName")).thenReturn("Package 1");
        when(resultSet.getInt("passenger_capacity")).thenReturn(10);

        // Call the method under test
        ArrayList<TravelPackage> packageList = DataRetrieval.retrieveTravelPackageFromDB(connection);

        // Assert the result
        Assertions.assertEquals(1, packageList.size());
        TravelPackage travelPackage = packageList.get(0);
        Assertions.assertEquals(1, travelPackage.getId());
        Assertions.assertEquals("Package 1", travelPackage.getPackageName());
        Assertions.assertEquals(10, travelPackage.getPassengerCapacity());
    }

    // Add similar tests for other methods

    @Test
    public void testUpdateCapacity() throws SQLException {
        // Mock the necessary objects
        Connection connection = mock(Connection.class);
        PreparedStatement statement = mock(PreparedStatement.class);

        // Mock the behavior of the objects
        when(connection.prepareStatement(anyString())).thenReturn(statement);

        // Call the method under test
        DataRetrieval.updateCapacity(connection, "Activity 1", 20);

        // Verify that the expected SQL query is executed
        verify(statement).setInt(eq(1), eq(20));
        verify(statement).setString(eq(2), eq("Activity 1"));
        verify(statement).executeUpdate();
    }

    @Test
    public void testUpdatePassenger() throws SQLException {
        // Mock the necessary objects
        Connection connection = mock(Connection.class);
        PreparedStatement statement = mock(PreparedStatement.class);

        // Mock the behavior of the objects
        when(connection.prepareStatement(anyString())).thenReturn(statement);

        // Call the method under test
        DataRetrieval.updatePassenger(connection, new TravelPackage(), new Passenger("John",12345), 100.0);

        // Verify that the expected SQL query is executed
        verify(statement).setInt(eq(1), ArgumentMatchers.anyInt());
        verify(statement).setDouble(eq(2), eq(100.0));
        verify(statement).setString(eq(3), ArgumentMatchers.anyString());
        verify(statement).setInt(eq(4), ArgumentMatchers.anyInt());
        verify(statement).executeUpdate();
    }

}
