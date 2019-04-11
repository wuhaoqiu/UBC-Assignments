package A4;

import java.util.Scanner;

public class Q3_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numOfPieces = BackRestore.backup("book.pdf", (1024 * 1024)/4.0);
		while (true) {
			System.out.println("Ready to restore,have you deleted original one at first?(yes)");
			Scanner scan = new Scanner(System.in);
			String answer = scan.nextLine();
			if (answer.equals("yes"))
				break;
		}

		BackRestore.restore("book.pdf", numOfPieces);
		System.out.println("restore complete");
	}

}
