package P5;

/** same as before **/

public class IR extends Register {
	public IR(int value) {
		super(value);
	}
	public int getOpCode() {
		return getValue() / 100;
	}
	public int getOperand() {
		return getValue() % 100;
	}
}
