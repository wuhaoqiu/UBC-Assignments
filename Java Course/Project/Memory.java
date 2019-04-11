package P5;

/** Has extra code - see lower half **/

import java.io.*;
public class Memory {
	//attributes
	private int[] contents;
	private int size;
	//constructor
	public Memory(int size) {
		this.size = size;
		contents = new int[size];
	}
	//setter and getter
	public void setValue(int address, int value) {
		if(address >= 0 && address < size)
			contents[address] = value;
		else
			System.out.println("Invalid address");
	}
	public int getValue(int address) {
		if(address >= 0 && address < size)
			return contents[address];
		else{
			System.out.println("Invalid address");
			return -1;
		}
	}
	//other methods
	public void showContents(){
		for (int i = 0; i < size; i++)
			System.out.println(getValue(i));
	}
	public void loadProgram() {
		contents[0] = 799;
		contents[1] = 798; 	//change this to 598 to demonstrate debugging
		contents[2] = 198;
		contents[3] = 499;
		contents[4] = 1008;
		contents[5] = 1108;
		contents[6] = 899;
		contents[7] = 909;
		contents[8] = 898;
		contents[9] = 0;
	}

	/***************** NEW CODE FOR P4 ******************/
	public void loadProgram(String filename) {
		boolean eof = false;
		int index = 0; // program starts at address 0
		try {
			DataInputStream myfile = new DataInputStream(new FileInputStream(filename));
			while (!eof)
				try {
					contents[index++] = myfile.readInt();
				} catch (EOFException e) {
					eof = true;
				} catch (IOException e) {
					System.out.println("IO Error");
				}
			myfile.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Check path or name");
		} catch (SecurityException e) {
			System.out.println("Security Error");
		} catch (IOException e) {
			System.out.println("IO Error");
		}
	}

}
