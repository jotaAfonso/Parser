package utils;

public class Transition {

	private String fromState;
	private String toState;

	private String operation;
	private String participant;

	private boolean newParticipant;
	private boolean bothParticipant;

	public Transition(String from, String to, String op, String part) {
		this.fromState = from;
		this.toState = to;
		this.operation = op;
		setupPart(part);
	}

	private void setupPart(String part) {
		String partLocal = UtilsParser.removeParenthesisFromString(part);
		String[] elements = partLocal.split("[|]");
		// both old and new
		if (elements.length == 2) {
			this.newParticipant = true;
			this.bothParticipant = true;
		} else {
			if (elements[0].contains(":")) 
				this.newParticipant = true;
		}	

		this.participant = part;
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

	public boolean isNewParticipant() {
		return newParticipant;
	}

	public void setNewParticipant(boolean newParticipant) {
		this.newParticipant = newParticipant;
	}

	public boolean isBothParticipant() {
		return bothParticipant;
	}

	public void setBothParticipant(boolean bothParticipant) {
		this.bothParticipant = bothParticipant;
	}
}
