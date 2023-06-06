package model;

public class StandardPassenger extends Passenger{
	public StandardPassenger(String name, int passengerNumber) {
        super(name, passengerNumber);
    }
	 public double getBalance() {
	        return walletBalance;
	    }

	    public void setBalance(double balance) {
	        this.walletBalance = balance;
	    }
}
