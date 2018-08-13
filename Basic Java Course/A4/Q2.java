package A4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Q2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		viewHex("data(1).dat");
	}

	private static void viewHex(String filename) {
		int i=0;
		try {
			DataInputStream input = new DataInputStream(new FileInputStream(filename));
			while(input.available()>0){
				byte number=input.readByte();
				System.out.printf(" %1$2s ",Integer.toHexString(number & 0xFF).toUpperCase());//to make highest 8 bits equal to zero instead of ff and make each hex string has the same fixed length
				i++;
				if(i%8==0){// i used to store the number of outputs to control the output format.
					if(i%16==0)
						System.out.println("");
					else
						System.out.printf("|");
				}		
			}
			input.close();
		} catch (IOException ex) {
			System.out.println("there exists IO exception");
			System.out.println(new File(".").getAbsoluteFile());//if io exception occurs, using this statement to check the default directory path.
		}
	}

}
