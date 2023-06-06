package model;

public class Passenger {
	
	private String passengerName;
	private int passengerNumber;
	private String passengerType;
	private int id;
	private int travel_package_id;
	protected double walletBalance;
	
	public Passenger(String name, int passengerNumber) {
        this.passengerName = name;
        this.passengerNumber = passengerNumber;
    }
	
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public int getPassengerNumber() {
		return passengerNumber;
	}
	public void setPassengerNumber(int passengerNumber) {
		this.passengerNumber = passengerNumber;
	}
	public String getPassengerType() {
		return passengerType;
	}
	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}
	public double getWalletBalance() {
		return walletBalance;
	}
	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTravel_package_id() {
		return travel_package_id;
	}

	public void setTravel_package_id(int travel_package_id) {
		this.travel_package_id = travel_package_id;
	}

	@Override
	public String toString() {
		return "Passenger [passengerName=" + passengerName + ", passengerNumber=" + passengerNumber + ", passengerType="
				+ passengerType + ", walletBalance=" + walletBalance + "]";
	}

}
