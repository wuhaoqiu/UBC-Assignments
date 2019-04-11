package P5;

public class Symbol{
	private String name;
	private int location;
	private Integer value;
	
	public Symbol(String name, int location, Integer value){
		setName(name);
		setLocation(location);
		setValue(value);
	}
	
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return this.name;
	}
	
	public void setLocation(int location){
		this.location=location;
	}
	public int getLocation(){
		return this.location;
	}
	
	public void setValue(Integer value){
		this.value=value;
	}
	public Integer getValue(){
		return this.value;
	}

}
