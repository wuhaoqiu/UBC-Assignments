package A2;

import java.util.Scanner;

public class Q1Test {

	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Hexagon hex1 = new Hexagon();

		Scanner scan = new Scanner(System.in);

		System.out.println("Color:");
		String color = scan.nextLine();
		hex1.setColor(color);

		while (true) {
			System.out.println("Filled(y/n?):");
			String filled = scan.nextLine();
			if (filled.equalsIgnoreCase("yes")) {
				hex1.setFilled(true);
				break;
			} else if (filled.equalsIgnoreCase("no")) {
				hex1.setFilled(false);
				break;
			} else
				System.out.println("invalid input, pls input yes or no\n");
		} // end of while
		
		System.out.println("side length:");
		double length=scan.nextDouble();
		hex1.setLength(length);
		hex1.setArea();
		hex1.setPerimeter();
		System.out.println(hex1.toString());
		
		Hexagon hex2=(Hexagon)hex1.clone();
		System.out.println("second hex:");
		System.out.println(hex2.toString());
		
		if(hex1.compareTo(hex2)==0)
			System.out.println("both are identical");
		else
			System.out.println("not identical");

	}

}
