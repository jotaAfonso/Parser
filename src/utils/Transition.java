package utils;

public class Transition {

	private static final int OLD_PARTICIPANTS = 0;
	private static final int NEW_PARTICIPANTS = 1;
	private static final int BOTH_PARTICIPANTS = 2;
	
	private String fromState;
	private String toState;

	private String operation;
	private String participant;
	
	private int typeOfParticipant;
	
	public Transition() {
		
	}
	
	public void setFromState(String state) {
		this.fromState = state;
	}
	
	public String getFromState() {
		return this.fromState;
	}
	
	public void setToState(String state) {
		this.toState = state;
	}
	
	public String getToState() {
		return this.toState;
	}
	
	public void setOperation(String op) {
		this.operation = op;
	}
	
	public String getOperation() {
		return this.operation;
	}
	
	public void setParticipant(String part) {
		this.participant = part;
	}
	
	public String getParticipant() {
		return this.participant;
	}
	
	public void setTypeOfParticipant(int type) {
		this.typeOfParticipant = type;
	}
	
	public int getTypeOfParticipant() {
		return this.typeOfParticipant;
	}
}
