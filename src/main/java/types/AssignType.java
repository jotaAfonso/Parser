package types;

public class AssignType implements IType {

	public static final AssignType singleton = new AssignType();

	public static final String type = "assign";

	private AssignType() {
	}

	@Override
	public String getType() {
		return type;
	}
}
