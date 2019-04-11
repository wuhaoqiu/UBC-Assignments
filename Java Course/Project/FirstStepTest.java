package P5;

public class FirstStepTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SymbolList list=new SymbolList();
		list.add(new Symbol("else",8,null));
		list.add(new Symbol("FINISH",9,null));
		list.add(new Symbol("FIRST",99,0));
		list.add(new Symbol("SECOND",98,0));
		list.add(new Symbol("SECOND",90,0));
		System.out.println(list);
		
		Computer com=new Computer();
		list=com.firstPass("program.asm");
		System.out.println(list);

	}

}
