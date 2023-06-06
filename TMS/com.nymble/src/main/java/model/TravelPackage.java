package model;

import java.util.ArrayList;

public class TravelPackage {

	private int id;
	private String packageName;
	private int passengerCapacity;

	private ArrayList<Destination> itinerary;  
	private ArrayList<Passenger> pList;
	 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public int getPassengerCapacity() {
		return passengerCapacity;
	}
	public void setPassengerCapacity(int passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}
	public ArrayList<Destination> getItinerary() {
		return itinerary;
	}
	public void setItinerary(ArrayList<Destination> itinerary) {
		this.itinerary = itinerary;
	}
	
	public ArrayList<Passenger> getpList() {
		return pList;
	}
	public void setpList(ArrayList<Passenger> pList) {
		this.pList = pList;
	}
	
	 
	 public void addPassenger(Passenger passenger) {
	        if (pList.size() >= passengerCapacity) {
	            throw new IllegalStateException("Passenger capacity reached for this travel package.");
	        }
	        pList.add(passenger);
	    }
	 
	@Override
	public String toString() {
		return "TravelPackage [name=" + packageName + ", passengerCapacity=" + passengerCapacity + ", itinerary=" + itinerary
				+ ", pList=" + pList + "]";
	}
}
