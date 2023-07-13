package types;

public class BoolType implements IType {

	public static final BoolType singleton = new BoolType();

	public static final String type = "bool";

	private BoolType() {
	}

	@Override
	public String getType() {
		return type;
	}
}
