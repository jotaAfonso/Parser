package types;

public class SequenceType implements IType {

	public static final SequenceType singleton = new SequenceType();

	public static final String type = "sequence";

	private SequenceType() {
	}

	@Override
	public String getType() {
		return type;
	}
}
