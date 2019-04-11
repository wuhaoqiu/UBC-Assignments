package A2;

import java.util.Arrays;

public class Q3Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double sumArea=0;
		Shape[] shapes1={new Rectangle(8,8),new Rectangle(6,6),new Rectangle(7,7),new Hexagon(9),new Hexagon(5)};
		for(Shape t:shapes1){
			t.setArea();
			sumArea+=t.getArea();
		}
		System.out.println("total area of all shapes in shapes1 is:"+sumArea);
		
		Shape[] shapes2=Arrays.copyOf(shapes1, shapes1.length);
		System.out.println("\nshapes1 has been cloned to shapes2.");
		Arrays.sort(shapes2);
		System.out.println("shapes2 has been reordered.");
		
		System.out.println("\nareas in shapes1    areas in shapes2");
		for(int i=0;i<shapes1.length;i++){
			System.out.printf("%6.2f             %6.2f\n",shapes1[i].getArea(),shapes2[i].getArea());
		}

	}

}
