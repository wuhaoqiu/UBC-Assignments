package A5;

import java.util.Scanner;

public class Q2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("enter a string");
		Scanner scan=new Scanner(System.in);
		String s=scan.nextLine();
		System.out.println(reverse(s));

	}
	
	public static String reverse(String s){
		if(s.length()==1)
			return s;
		else
			return s.charAt(s.length()-1)+reverse(s.substring(0, s.length()-1));//every time abstract the last character of string and truncate the last character, and then invoke this method by this new string unitl the length of string equals to 1 
	}

}
