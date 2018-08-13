package A2;

public class Rectangle extends Shape implements Cloneable, Comparable<Shape> {
	private double width;
	private double length;
	private double perimeter;
	private double area;

	public Rectangle() {
	}

	public Rectangle(double length,double width) {
		setSide(width, length);
	}

	public void setSide(double width, double length) {
		if (length > 0 && width > 0) {
			this.width = width;
			this.length = length;
		} else
			System.out.println("invalid input");
	}

	public double getLength() {
		return this.length;
	}
	public double getWidth() {
		return this.width;
	}

	public void setArea() {
		this.area =getLength()*getWidth();
	}

	public double getArea() {
		return this.area;
	}

	public void setPerimeter() {
		this.perimeter = getLength()*2+getWidth()*2;
	}

	public double getPerimeter() {
		return this.perimeter;
	}

	public int compareTo(Shape shp) {
		if (this.getArea() > shp.getArea())
			return 1;
		if (this.getArea() < shp.getArea())
			return -1;
		return 0;
	}

	public Object clone() throws CloneNotSupportedException {
		Shape sh = (Shape) super.clone();
		return sh;
	}

	public String toString() {
		return "color:" + getColor() + ". " + (isFilled() ? "Filled. " : "Not filled. ") + " area:" + getArea() + ". "
				+ " sidewidth:" + getWidth() + ". " + "sidelength: "+getLength()+ " perimeter:" + getPerimeter();
	}

}
