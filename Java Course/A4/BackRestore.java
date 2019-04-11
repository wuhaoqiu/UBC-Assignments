package A4;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BackRestore {
	//create a targeted backup file-out and the length of bytes to be read
	//each time, creating a while loop where conditon is the length of remaining
	//bytes of the file should be greater than 0, and store this length in thisTimeBytes
	//then using write method to read pre-fixed length of bytes array-partFileLength and write
	//the true length of bytes-thisTimeBytes into different subfiles
	public static int backup(String filename, double partSize){
		byte[] partFileLength=new byte[(int)partSize];
		int pieceFileNum=0;
		DataInputStream input=null;
		DataOutputStream output = null;
		File out=new File(filename);//creat a file object of original file to be used below for creating new child piece file of it
		int thisTimeBytes=0;
		try{
			input = new DataInputStream(new BufferedInputStream(new FileInputStream(out)));
			while((thisTimeBytes=input.read(partFileLength))>0){//check whether the actual number of bytes read from file is greater than 0 
				String partFileName = String.format("%s.%d", filename, pieceFileNum++);
				output=new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File(out.getParent(),partFileName))));//create chile piece file of orginal one in parent(original)'s file's pathname.
				output.write(partFileLength,0,thisTimeBytes);
			}
			output.close();
			input.close();
		}
		catch(Exception ex){
			System.out.println("there exists IO exception");
			System.out.println(new File(".").getAbsoluteFile());//if io exception occurs, using this statement to check the default directory path.
		}//end of catch
		return pieceFileNum;	
		}//end of backup

	public static int restore(String filename, int numberOfPieces) {
		//same logic as above,according to name, and make output stream allowing appending instead of overwriting, also each time the length of bytes is determined the length of such
		//file-I use new byte[(int)partFile.length()], then write these bytes into targted file.
		byte[] partFileLength;
		DataInputStream input = null;
		DataOutputStream output = null;
		File in=new File(filename);
		int bytesRead=0;
		int sizeCount=0;
		try{
			output=new DataOutputStream(new BufferedOutputStream(new FileOutputStream(in,true)));
			for(int i=0;i<numberOfPieces;i++){
				String partFileName = String.format("%s.%d", filename, i);
				File partFile=new File(partFileName);
				input = new DataInputStream(new BufferedInputStream(new FileInputStream(partFile)));
				partFileLength=new byte[(int)partFile.length()];//every time the length of byte[] is determined by current length of child piece file because the last piece file's length may be differnet from former ones.
				while((bytesRead=input.read(partFileLength))>0){
					output.write(partFileLength,0,bytesRead);
					sizeCount++;
				}//end of while
			}//end of for
			output.flush();//output all of remaining bytes stream
			output.close();
			input.close();
		}catch(Exception ex){
			System.out.println("there exists IO exception");
			System.out.println(new File(".").getAbsoluteFile());//if io exception occurs, using this statement to check the default directory path.
		}//end of catch
		return sizeCount;
	}
}
