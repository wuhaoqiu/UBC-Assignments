package A3;

import java.util.Scanner;

public class Q1_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] random=new int[50];
		boolean valid=false;
		Scanner scan=new Scanner(System.in);
		for(int i=0;i<50;i++){
			random[i]=(int)(Math.random()*50);
		}
		
		System.out.println("enter an index:");
		int input=scan.nextInt();
		while(!valid){
			if(input<0||input>random.length-1){
				System.out.println("input invalid,pls try again");
				input=scan.nextInt();
			}
			else{
				System.out.println("element is:"+random[input]);
				break;
			}
		}
	}

}
