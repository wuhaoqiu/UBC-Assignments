package A3;

import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Q3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		double x11 = 0, x22 = 0;
		String operator = null, x1 = null, x2 = null;
		System.out.println("enter a simple mathematical formula:");
		boolean valid = false;
		while (!valid) {
			try {
				x1 = scan.next();
				operator = scan.next();
				if (!operator.equals("-") && !operator.equals("+") && !operator.equals("*") && !operator.equals("/")) {
					System.out.println("invalid number operator.try again");
					continue;
				} // end of if
				x2 = scan.next();
			} catch (Exception ex) {
				System.out.println("invalid number. try again");
				scan.nextLine();
			} // end of try
			try {
				x11 = Double.parseDouble(x1);
				x22 = Double.parseDouble(x2);
				break;
			} catch (NumberFormatException ex) {
				System.out.println("invalid input,try again");
			} // end of try
			// because the format of input string is not a number, so numberformatexceotion may happen when u try to convert a non--number-format string into a number
		} // end of while

		if (operator.equals("-"))
			System.out.println("result:" + (x11-x22));
		if (operator.equals("+"))
			System.out.println("result:" + (x11+x22));
		if (operator.equals("*"))
			System.out.println("result:" + (x11*x22));
		try {
			if (operator.equals("/"))
				System.out.println("result:" + (x11/x22));
		} catch (Exception ex) {
		} 
	}

}
