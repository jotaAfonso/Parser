package types;

public class IntType implements IType {
	
	public static final IntType singleton = new IntType();
	
	public static final String type = "int";
	
	private IntType() {}

	@Override
	public String getType() {
		return type;
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		return this == obj;
//	}
}
