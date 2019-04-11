package A2;

import java.util.Scanner;

public class Q2Test {

	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Rectangle rec1 = new Rectangle();

		Scanner scan = new Scanner(System.in);

		System.out.println("Color:");
		String color = scan.nextLine();
		rec1.setColor(color);

		while (true) {
			System.out.println("Filled(y/n?):");
			String filled = scan.nextLine();
			if (filled.equalsIgnoreCase("yes")) {
				rec1.setFilled(true);
				break;
			} else if (filled.equalsIgnoreCase("no")) {
				rec1.setFilled(false);
				break;
			} else
				System.out.println("invalid input, pls input yes or no\n");
		} // end of while
		
		System.out.println("side length(first length,second width):");
		double length=scan.nextDouble();
		double width=scan.nextDouble();
		rec1.setSide(width,length);
		rec1.setArea();
		rec1.setPerimeter();
		System.out.println(rec1.toString());
		
		Rectangle rec2=(Rectangle)rec1.clone();
		System.out.println("second rec:");
		System.out.println(rec2.toString());
		
		if(rec1.compareTo(rec2)==0)
			System.out.println("both are identical");
		else
			System.out.println("not identical");

	}


}
