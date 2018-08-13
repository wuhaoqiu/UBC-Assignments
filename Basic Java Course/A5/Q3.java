package A5;

import java.util.Scanner;

public class Q3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		System.out.println("enter a string: ");
		String s=scan.nextLine();
		System.out.println("enter a character: ");
		char target=scan.nextLine().charAt(0);//avoid people mistakenly enter a string instead of a single character
		 System.out.printf("%c apperts %d times in \"%s\"",target,countNum(s,target),s);
	}
	
	public static int countNum(String s, char target){
		if(s.length()==1){
			if(s.charAt(0)==target)
				return 1;
			else
				return 0;				
		}else{
			if(s.charAt(0)==target)//every time only check the first char of string and then invoke method by using the string that has been truncated by the first char until the length of string equals to one.
				return 1+countNum(s.substring(1),target);
			else
				return 0+countNum(s.substring(1),target);
		}		
	}

}
