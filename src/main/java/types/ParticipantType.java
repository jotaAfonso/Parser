package types;

public class ParticipantType implements IType {

	public static final ParticipantType singleton = new ParticipantType();
	
	public static final String type = "participant";
	
	public String role = "";
	
	private ParticipantType() {	
	}
	
	public ParticipantType(String role) {
		this.role = role;
	}
	
	@Override
	public String getType() {
		return type;
	}
	
	public String getRole() {
		return role;
	}
	
	public String setRole(String role) {
		return this.role = role;
	}
}
