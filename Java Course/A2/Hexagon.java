package A2;

public class Hexagon extends Shape implements Cloneable,Comparable<Shape>{
	private double sideLength;
	private double perimeter;
	private double area;
	
	public Hexagon(){}
	public Hexagon(double length){
		setLength(length);
	}
	
	public void setLength(double length){
		if(length>0)
			this.sideLength=length;
		else
			System.out.println("invalid input");
	}
	public double getLength(){
		return this.sideLength;
	}
	
	public void setArea(){this.area=3*Math.sqrt(3)*Math.pow(getLength(), 2)/2.0;}
	public double getArea(){return this.area;}
	
	public void setPerimeter(){this.perimeter=getLength()*6;}
	public double getPerimeter(){return this.perimeter;}
	
	public int compareTo(Shape shp){
		if(this.getArea()>shp.getArea())
			return 1;
		if(this.getArea()<shp.getArea())
			return -1;
		return 0;
	}
	
	public Object clone() throws CloneNotSupportedException{
		Shape sh=(Shape)super.clone();
		return sh;
	}
	
	public String toString(){
		return "color:"+getColor()+". "+(isFilled()? "Filled. ":"Not filled. ")+" area:"+getArea()+". "+" sidelength:"+getLength()+". "+" perimeter:"+getPerimeter();
	}

}
