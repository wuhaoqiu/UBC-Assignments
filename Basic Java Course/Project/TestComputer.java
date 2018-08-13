package P5;

public class TestComputer {
	public static void main(String[] args) {
		Computer comp =  new Computer();
		/***************** NEW CODE FOR P5 ******************/		
		String fileName="program.asm";
		String[] arr=fileName.split("\\.");
		String result=arr[0]+".exe";
		System.out.println(result);
		String name="file.set";
		//System.out.println(comp.isInstruction(symbol));
		comp.compile(fileName);
		comp.run("program.exe");
	}
}
