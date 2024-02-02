package types;

public class SetType implements IType {

	public static final SetType singleton = new SetType();
	
	public static final String type = "array";
	
	public String typeValue = "";
	
	private SetType() {
		
	}
	
	@Override
	public String getType() {
		return type;
	}
	
	public SetType(IType type) {
		this.typeValue = type.getType();
	}
	
	public String getTypeValue() {
		return typeValue;
	}
	
	public String setTypeValue(String typeValue) {
		return this.typeValue = typeValue;
	}
}