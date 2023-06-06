package model;

import java.util.ArrayList;

public class Destination {
	
	private String destinationName;
	private ArrayList<Activity> activitiesList;
	private int id;
	
	
	public String getDestinationName() {
		return destinationName;
	}
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	public ArrayList<Activity> getActivitiesList() {
		return activitiesList;
	}
	public void setActivitiesList(ArrayList<Activity> activitiesList) {
		this.activitiesList = activitiesList;
	}
	public void addActivity(Activity activity) {
		activitiesList.add(activity);
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Destination [destinationName=" + destinationName + ", activitiesList=" + activitiesList + "]";
	}

}
