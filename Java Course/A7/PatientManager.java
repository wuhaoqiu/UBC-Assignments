package A7;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class PatientManager {

	private Queue<Patient> waitingList = new PriorityQueue<Patient>();
	private int order = 0;

	public void start() {
		Scanner scan = new Scanner(System.in);
		System.out
				.println("------------\n(1) New patient\n(2) Next patient\n(3) Waiting list\n(4) Exit\n----------\n");

		boolean invalid = true;
		int choice;
		while (invalid) {
			try {// firstly test whether what the users typed is int then test
					// whether the int value is in the valid range, otherwise
					// repeat the loop
				System.out.println("choose an item from the menu");
				choice = scan.nextInt();
				if (choice < 1 || choice > 4) {
					System.out.println("wrong choice");
				} // end of if
				else {
					switch (choice) {
					case 1:
						addPatient();
						break;
					case 2:
						pollQueue();;
						break;
					case 3:
						viewAll();;
						break;
					case 4:
						invalid=exit();
						break;
					}//end of switch
				}
			} catch (Exception ex) {
				System.out.println("Wrong choice");
				scan.next();
			}

		} // end of while

		System.out.println("program terminated. good bye");
	}// end of start

	public int getOrder() {
		return order;
	}

	public void incrementOrder() {
		this.order++;
	}

	public void addPatient() {
		Scanner scan = new Scanner(System.in);
		System.out.println("enter name:");
		String name = scan.nextLine();
		int emergency;
		boolean invalid = true;
		while (invalid) {//same method as before, try catch to check the input type and if condition to check the range of input
			try {
				System.out.println("enter emergency level:");
				emergency = scan.nextInt();
				if (emergency < 1 || emergency > 5) {
					System.out.println("wrong value, try again");
				} else {
					Patient patient = new Patient(this.getOrder(), name, emergency);
					this.incrementOrder();
					waitingList.add(patient);
					invalid = false;
				} // end of else

			} catch (Exception ex) {
				System.out.println("wrong value, try again");
				scan.next();
			}
		} // end of while
	}// end of addPatient
	
	public void pollQueue(){
		if(waitingList.isEmpty())
			System.out.println("no more patients");
		else
			System.out.println(waitingList.poll().getName()+" is treated");;
	}//end of peek Queue
	
	public void viewAll(){//convert list to array and sort and then printing
		if(waitingList.isEmpty())
			System.out.println("no more patients");
		else{
			Patient[] arr=new Patient[waitingList.size()];
			waitingList.toArray(arr);
			Arrays.sort(arr);
			System.out.println("waiting list includes:");
			for(Patient temp:arr){
				System.out.println("--"+temp.getName());
			}
		}
			
	}//end of view all
	
	public boolean exit(){
		return false;
	}

}
