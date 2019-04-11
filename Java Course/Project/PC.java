package P5;

/** same as before **/

public class PC extends Register {
	public PC(int value) {
		super(value);
	}
	public void increment() {
		setValue(getValue()+1);;
	}
}
