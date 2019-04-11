package A6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Q1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		ArrayList<Integer> duplicate=new ArrayList<>();
		System.out.println("enter an integer");
		for(int i=0;i<5;i++){
			duplicate.add(scan.nextInt());
		}
		System.out.println("what you have entered are:"+duplicate.toString());
		System.out.println("after filter:"+noDuplicates(duplicate).toString());

	}

	public static ArrayList<Integer> noDuplicates(ArrayList<Integer> list){
		ArrayList<Integer> noDuplicate=new ArrayList<>();//ctreate an another arraylist to store the filtered result.
		for(int temp:list){
			if(!noDuplicate.contains(temp)){//if new arraylist donot contain the present element, then adding it
				noDuplicate.add(temp);
			}//end of if
		}//end of for
		return noDuplicate;//returen the new arraylist
	}//end of method
}
