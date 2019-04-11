package A7;

public class Patient implements Comparable<Patient>{// adding a new compareto method so that thie class can be used for priority queue
	//attributes
	private String name;
	private int order;		//order of arrival
	private int emergency; //1 is normal, 5 is life-and-death situation

	//constructor
	public Patient(int order, String name, int priority) {
		this.order = order;
		this.name = name;
		this.emergency = priority;
	}

	//getters and setters
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getEmergency() {
		return emergency;
	}

	public void setEmergency(int emergency) {
		this.emergency = emergency;
	}

	public String toString() {
		return name;
	}
	
	public int compareTo(Patient patient){// define how the patients should be ordered, and if they have the same emergency level, then comparing their queue orders
		if(this.getEmergency()<patient.getEmergency())
			return 1;
		else if(this.getEmergency()>patient.getEmergency())
			return -1;
		else
			return this.order-patient.getOrder();
	}
}
