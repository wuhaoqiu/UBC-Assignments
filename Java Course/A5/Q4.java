package A5;

import java.io.File;

public class Q4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
listAllFile(new File("c:/Users/whq672437089/Desktop/courses/2017W2/cosc121"));
	}

	public static void listAllFile(File file) {//helper method
		String space = "";//initialize the space, the root directory does not need space
		listAllFile(file, space);
	}

	public static void listAllFile(File dir, String space) {
		space = space + "  ";//every time when invoking this method, adding a single space automatically
		if (dir.isFile()) {	
			System.out.println(space+ dir.getName());
		} else {
			System.out.println(space+"[" + dir.getName().toUpperCase() + "]");
			for (File f : dir.listFiles()) {
				if (f.isFile()) {
					System.out.println(space+"  "+ dir.getName());//in the same directory, file should have one more space before print name.
				}//end of if
				else{
					listAllFile(f,space);
				}
			}
		}

	}

}
