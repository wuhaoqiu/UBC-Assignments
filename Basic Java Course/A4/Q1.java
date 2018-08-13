package A4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Q1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		if (!new File("data.dat").exists()) {//check whetehr the file already existed, if existing, creating one and write 1 to it, otherwise, read the integer and increment it
			DataOutputStream out = new DataOutputStream(new FileOutputStream("data.dat"));
			out.writeInt(1);
			out.close();
		} else {
			DataInputStream in = new DataInputStream(new FileInputStream("data.dat"));
			int input=in.readInt()+1;
			System.out.println("read number is "+(input-1));
			in.close();
			DataOutputStream out = new DataOutputStream(new FileOutputStream("data.dat"));
			out.writeInt(input);
			out.close();
		}

	}

}
