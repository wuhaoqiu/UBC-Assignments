package P5;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Scanner;

/** Has extra code - see end **/

public class Computer {
	private Memory ram;
	private PC pc;
	private IR ir;
	private ACC acc;

	public Computer() {
		ram = new Memory(100);
		pc = new PC(0);
		ir = new IR(0);
		acc = new ACC(0);
	}

	private void fetch() { // IR = Memory[PC]
		ir.setValue(ram.getValue(pc.getValue()));
	}

	private void incrementPC() {
		pc.increment();
	}

	private boolean execute() { // decode IR and execute based on OpCode and
								// Operand
		boolean stop = false;
		switch (ir.getOpCode()) {
		case 0: // Stop
			stop = true;
			break;
		case 1: // Load - ACC = Memory[Operand]
			acc.setValue(ram.getValue(ir.getOperand()));
			break;
		case 2: // Store - Memory[Operand] = ACC;
			ram.setValue(ir.getOperand(), acc.getValue());
			break;
		case 3: // Add - ACC = ACC + Memory[Operand]
			acc.add(ram.getValue(ir.getOperand()));
			break;
		case 4: // Subtract - ACC = ACC - Memory[Operand]
			acc.sub(ram.getValue(ir.getOperand()));
			break;
		case 5: // Multiply - ACC = ACC * Memory[Operand]
			acc.mult(ram.getValue(ir.getOperand()));
			break;
		case 6: // Divide - ACC = ACC / Memory[Operand]
			acc.div(ram.getValue(ir.getOperand()));
			break;
		case 7: // Input - Memory[Operand] = input
			ram.setValue(ir.getOperand(), Terminal.input());
			break;
		case 8: // Output - output = Memory[Operand]
			Terminal.output(ram.getValue(ir.getOperand()));
			break;
		case 9: // Unconditional Branch - PC = Operand
			pc.setValue(ir.getOperand());
			break;
		case 10: // Branch greater
			if (acc.getValue() > 0)
				pc.setValue(ir.getOperand());
			break;
		case 11: // Branch equal
			if (acc.getValue() == 0)
				pc.setValue(ir.getOperand());
			break;
		default: // Not an instruction - could throw their own exception
			Terminal.output("Error - not an instruction\n");
		}
		return stop;
	}

	private void cycle() {
		boolean stop = false;
		while (!stop) {
			this.fetch();
			this.incrementPC();
			stop = this.execute(); // if opcode = stop instruction, will become
									// true
		}
	}

	public void run() {
		ram.loadProgram();
		pc.setValue(0);
		this.cycle();
	}

	/***************** NEW CODE FOR P4 ******************/
	public void run(String filename) {
		ram.loadProgram(filename);
		pc.setValue(0);
		this.cycle();
	}

	public void compile(String fileName) {
		SymbolList symbolTable=firstPass(fileName);
		System.out.println(symbolTable);
		secondPass(symbolTable, fileName);
	
	}// end of compile
	
	public SymbolList firstPass(String fileName){
		try {
			File file=new File(fileName);
			Scanner sc = new Scanner(file);
			int pc = 0, dc = 99;
			String line=null;
			SymbolList list = new SymbolList();
			while (sc.hasNextLine()) {// check whether there are lines remaining
				line = sc.nextLine();
				if (isInstruction(readFirstWord(line)) != -1) {
					pc++;
				}
				else if(isLabel(readFirstWord(line))){
					if(isInstruction(readSecondWord(line))!=-1){
						list.add(new Symbol(readFirstWord(line).substring(0,readFirstWord(line).length()-1),pc,null));
						pc++;
					}else{
						try{//check whether the third value is a number
							int value=Integer.parseInt(readThirdWord(line));
							list.add(new Symbol(readFirstWord(line).substring(0,readFirstWord(line).length()-1),dc,value));
							dc--;
						}catch(Exception ex){
							System.out.println("the third number cannot be converted to a number");
						}
					}
				}
			} // end of while
			sc.close();
			return list;
		} catch (Exception ex) {
			System.out.println("there exists IO exception");
			System.out.println(new File(".").getAbsoluteFile());// if io
			return null;													// exception
																// occurs, using
																// this
																// statement to
																// check the
																// default
																// directory
																// path.
		}
	}// end of first pass
	
	public void secondPass(SymbolList list,String fileName){
		int count=0;
		int dc=100;
		try{
			Scanner input=new Scanner(new File(fileName));
			String newName=createOutputName(fileName);
			File out=new File(newName);
			DataOutputStream output=new DataOutputStream(new BufferedOutputStream(new FileOutputStream(out,true)));
			while(input.hasNextLine()){
				String line=input.nextLine();
				int first=isInstruction(readFirstWord(line));
				if(first!=-1){// if first is instruction
					int second=list.getLocation(readSecondWord(line));
					int instruction=first*100+second;
					output.writeInt(instruction);
					count++;
				}
				else if(isLabel(readFirstWord(line))){
					count++;
					int second=isInstruction(readSecondWord(line));
					if(second!=-1){//if second is instruction
						int third;
						try{third=list.getLocation(readThirdWord(line));
						}catch(Exception ex){// if can not find the third, then using zero to prevent
							third=0;
						}			
						int result=second*100+third;
						output.writeInt(result);
					}
					else if(readSecondWord(line).equals("DC")){//if second is DC 
						dc--;
					}
				}	
			}//end of while
			input.close();
			for(int i=0;i<99-count;i++)//fill executable file with zeros
				output.writeInt(0);
			
			
			
			for(int i=dc;i<100;i++){//if location is less than memory size and the value is not null
				if(list.getValue(dc)!=null&&dc<99){
					output.writeInt(list.getValue(dc));
					dc++;
				}		
			}//end of while
			output.close();
		}catch(Exception ex){
			System.out.println("IO error exits");
		}
	}//end of secondPass

	public String createOutputName(String fileName){
		String[] arr=fileName.split("\\.");
		String result=arr[0]+".exe";
		return result;
	}
	public String readFirstWord(String line) {
		String[] arr = line.split(" ");
		if (arr.length > 0)
			return arr[0];
		return null;
	}

	public String readSecondWord(String line) {
		String[] arr = line.split(" ");
		if (arr.length > 0)
			return arr[1];
		return null;
	}

	public String readThirdWord(String line) {
		String[] arr = line.split(" ");
		if (arr.length > 0)
			return arr[2];
		return null;
	}

	public int isInstruction(String symbol) {
		switch (symbol) {
		case "STOP":
			return 0;
		case "LD":
			return 1;
		case "STO":
			return 2;
		case "ADD":
			return 3;
		case "SUB":
			return 4;
		case "MPY":
			return 5;
		case "DIV":
			return 6;
		case "IN":
			return 7;
		case "OUT":
			return 8;
		case "B":
			return 9;
		case "BGTR":
			return 10;
		case "BZ":
			return 11;
		}
		return -1;
	}

	public boolean isLabel(String symbol) {
		if (symbol.charAt(symbol.length() - 1) == ':') {
			return true;
		}
		return false;
	}

}
