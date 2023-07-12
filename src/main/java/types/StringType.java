package types;

public class StringType implements IType{

	public static final StringType singleton = new StringType();
	
	public static final String type = "string";
	
	private StringType() {
		
	}
	
	@Override
	public String getType() {
		return type;
	}
}