package P5;

/** same as before **/

public class Register {

	//attribute
	private int value;
	//constructor
	public Register(int value) {
		setValue(value);
	}
	//basic getter and setter
	public void setValue(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
