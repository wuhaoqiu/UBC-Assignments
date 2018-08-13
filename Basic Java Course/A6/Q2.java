package A6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Q2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printShuffled("story.txt");

	}

	public static void printShuffled(String filename) {
		File input = new File(filename);
		Scanner sc = null;
		int fileLength = (int) (input.length() / 50);// because assume one
														// sentence containing
														// 50 char and one char
														// equals to one byte
														// and the length value
														// returned by
														// file.length is based
														// on bytes so the
														// average number of
														// sentences should be
														// the overall length
														// divided by 50 byte
		ArrayList<String> story = new ArrayList<>(fileLength);// initiate the
																// capacity of
																// the arraylist
		try {
			sc = new Scanner(input).useDelimiter("[.:!?]");
			for (int i = 0; i < fileLength; i++) {
				while (sc.hasNext())//prevent keeping adding when already reaching the last line
					story.add(sc.next());
			} // end of for
			sc.close();
			System.out.println("size:"+story.size()+"\nbefore shuffled:"+story);
			Collections.shuffle(story);
			System.out.println("after shuffled:"+story);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// end of method

}
