package A3;

import java.util.Scanner;

public class Q2_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		double x1 = 0, x2 = 0;
		String operator = null;
		System.out.println("enter a simple mathematical formula:");
		boolean valid = false;
		while (!valid) {
			try {
				x1 = scan.nextDouble();
				operator = scan.next();
				if (!operator.equals("-") && !operator.equals("+") && !operator.equals("*") && !operator.equals("/")) {
					System.out.println("invalid number operator.try again");// for operator error, use defensive method here to handle,
					                                                        // so if there still exist error, it must come from operand. 
					continue;
				} // end of if
				x2 = scan.nextDouble();
				break;
			} catch (Exception ex) {
				System.out.println("invalid number. try again");
				scan.nextLine();
			} // end of try
		} // end of while
		if (operator.equals("-"))
			System.out.println("result:" + (x1 - x2));
		if (operator.equals("+"))
			System.out.println("result:" + (x1 + x2));
		if (operator.equals("*"))
			System.out.println("result:" + (x1 * x2));
		try {
			if (operator.equals("/"))
				System.out.println("result:" + (x1 / x2));
		} catch (Exception ex) {
		} finally {
			System.out.println("calculate wrong");
		}
	}

}
