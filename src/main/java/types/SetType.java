package types;

public class SetType implements IType {

	public static final SetType singleton = new SetType();
	
	public static final String type = "set";
	
	private SetType() {
		
	}
	
	@Override
	public String getType() {
		return type;
	}
}