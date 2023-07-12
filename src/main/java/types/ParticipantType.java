package types;

public class ParticipantType implements IType {

	public static final ParticipantType singleton = new ParticipantType();
	
	public static final String type = "PType";
	
	private ParticipantType() {
		
	}
	
	@Override
	public String getType() {
		return type;
	}
}
