package A3;

import java.io.*;
import java.util.*;

public class CopyFileCapitalized {
	public static void main(String[] args) throws FileNotFoundException {
		String censoredWords[] = { "ABC", "XYZ" };
		String output = "";
		try {
			File file = new File("source.txt");
			Scanner input = new Scanner(file);
			while (input.hasNext()) {// using hasNext to check whether there are remaining streams, concatenate every stream into one stream-output
				output = output+input.next()+" ";
			}
			input.close();
		} catch (Exception ex) {
			System.out.println(new File(".").getAbsoluteFile());// check whether the file is not in the proper location
		}
		
		
		System.out.println(output);
		try (PrintWriter write = new PrintWriter(new File("destination.txt"));) {// at first make output to uppercase ,then using replaceMethod to replace required words, and finally write them into a file
			System.out.println(replaceCensoredWords(output.toUpperCase(), censoredWords));
			write.println(replaceCensoredWords(output, censoredWords).toUpperCase());
		}

		// add code (1)
	}

	private static String replaceCensoredWords(String line, String[] censoredWords) {
		// add code (2)
		Scanner input = new Scanner(line);
		String output = "";
		String temp = null;
		while (input.hasNext()) {
			temp = input.next();
			for (int i = 0; i < censoredWords.length; i++) {
				if (temp.equals(censoredWords[i]))
					temp = "...";
			} // end of for
			output = output + temp + " ";
		} // end of while
		input.close();
		return output;
	}
}